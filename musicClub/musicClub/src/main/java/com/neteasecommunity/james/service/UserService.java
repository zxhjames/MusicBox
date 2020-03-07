package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.LoginAndRegistDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.model.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER; //静态资源地址
    @Value(("${prop.avatarUrl}"))
    private String AvatarUrl;
    /**
     * 用户注册
     * @param request
     * @return
     */
    public ResultDTO UserRegister(HttpServletRequest request){
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest)request);
        MultipartFile file = params.getFile("file");
        System.out.println(file);
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            //文件写入指定路径
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultDTO.errorOf(CustomizeErrorCode.FILE_ERROR);
        }
        User user = new User();
        user.setUsername(params.getParameter("username"));
        user.setPassword(params.getParameter("password"));
        user.setRank(1);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAvatarUrl(file.getOriginalFilename());
        int status =  userMapper.insert(user);
        if(status == 1){
            return ResultDTO.okOf();
        }
        return ResultDTO.errorOf(CustomizeErrorCode.EXIST_USER);
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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        //比对密码
        List<User> dbUsers = userMapper.selectByExample(userExample);
        if(dbUsers.size() == 0){
            //用户不存在
            return ResultDTO.errorOf(CustomizeErrorCode.NO_USER);
        }
        if(dbUsers.get(0).getPassword().equals(pwd)){
            String userToken = UUID.randomUUID().toString();
            //设置token在缓存中的过期时间为10分钟
            stringRedisTemplate.opsForValue().set(username,userToken,10, TimeUnit.MINUTES);
            LoginAndRegistDTO u = new LoginAndRegistDTO();
            BeanUtils.copyProperties(dbUsers.get(0),u);
            u.setToken(userToken);
            return ResultDTO.okOf(u);
        }
        //密码错误
        return ResultDTO.errorOf(CustomizeErrorCode.ERROR_PWD);
    }

    public User getUserInfo(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        User user = userMapper.selectByExample(userExample).get(0);
        user.setPassword(null);
        user.setAvatarUrl(AvatarUrl+user.getAvatarUrl());
        return user;
    }
}
