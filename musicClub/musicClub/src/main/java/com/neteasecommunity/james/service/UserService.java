package com.neteasecommunity.james.service;

import com.alibaba.fastjson.JSON;
import com.neteasecommunity.james.dto.LoginAndRegistDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.model.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${web.upload-path}")
    private String UPLOAD_FOLDER; //静态资源地址
    @Value(("${prop.avatarUrl}"))
    private String AvatarUrl;
    @Autowired
    private RedisTemplate<Object,User> template;
    /**
     * 用户注册
     * @param request
     * @return
     */
    public ResultDTO UserRegister(HttpServletRequest request){
        /**
         * 头像上传
         */
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest)request);
        MultipartFile file = params.getFile("file");
        try {
            byte[] bytes = file.getBytes();
            /**
             * 不知道怎么回事
             */
            Path path = Paths.get("/home/zxh/桌面/shop/avatar/" + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get("/home/zxh/桌面/shop/avatar/"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultDTO.errorOf(CustomizeErrorCode.FILE_ERROR);
        }
        /**
         * 用户第一次注册,数据进入db后,再在缓存中插入一个Table_User表,使用hash
         */
        User user = new User();
        user.setUsername(params.getParameter("username"));
        user.setPassword(params.getParameter("password"));
        user.setRank(1);
        user.setConcerns(0);
        user.setFollowers(0);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAvatarUrl(AvatarUrl+file.getOriginalFilename());
        return  userMapper.insert(user) == 1?  ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.EXIST_USER);
    }




    /**
     *用户登录
     * @param loginAndRegistDTO
     * @return
     */
    public ResultDTO UserLogin(LoginAndRegistDTO loginAndRegistDTO) {
        String username = loginAndRegistDTO.getUsername();
        String pwd = loginAndRegistDTO.getPassword();
        String token = loginAndRegistDTO.getToken();
        String accessToken = stringRedisTemplate.opsForValue().get(username);
        //之前遇到的坑
        if (accessToken != null && accessToken.equals(token)) {
            return ResultDTO.okOf("loginCache");
        }

        List<User> dbUsers = null;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        dbUsers = userMapper.selectByExample(userExample);

        if(dbUsers.size() == 0){
            //用户不存在
            return ResultDTO.errorOf(CustomizeErrorCode.NO_USER);
        }
        if(dbUsers.get(0).getPassword().equals(pwd)){
            String userToken = UUID.randomUUID().toString();
            //设置token在缓存中的过期时间为10分钟
            stringRedisTemplate.opsForValue().set(username,userToken);
            LoginAndRegistDTO u = new LoginAndRegistDTO();
            BeanUtils.copyProperties(dbUsers.get(0),u);
            u.setToken(userToken);
            return ResultDTO.okOf(u);
        }
        //密码错误
        return ResultDTO.errorOf(CustomizeErrorCode.ERROR_PWD);
    }


    /**
     * 获取用户表的信息,首先去缓存中招用户信息,找到则直接返回User
     * 否则去数据库招,找到的同时并且返回一个User对象
     * @param username
     * @return
     */
    public User getUserInfo(String username) {
        User user = null;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        user = userMapper.selectByExample(userExample).get(0);
        user.setPassword("");
        return user;
    }

}
