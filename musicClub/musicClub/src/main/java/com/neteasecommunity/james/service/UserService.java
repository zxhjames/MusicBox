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
    @Value(("${props.redis.Table_User}"))
    private String Table_User;
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
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest)request);
        MultipartFile file = params.getFile("file");
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
        int status =  userMapper.insert(user);
        /**
         * 插入缓存
         */
        HashOperations<String,String,User> map= redisTemplate.opsForHash();
        map.put(Table_User,user.getUsername(),user);

        return status == 1?  ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.EXIST_USER);
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
        System.out.println(username + " " + pwd + " " + token);
        String accessToken = stringRedisTemplate.opsForValue().get(username);
        //之前遇到的坑
        if (accessToken != null && accessToken.equals(token)) {
            return ResultDTO.okOf("loginCache");
        }
        /**
         * 同样,先去缓存中寻找,找不到再去数据库里招
         */
        List<User> dbUsers = null;
        if(redisTemplate.boundHashOps(Table_User).hasKey(username)){
            /**
             * 序列化操作取出的数据,再返回User对象
             */
            Object json = redisTemplate.opsForHash().get(Table_User,username);
            User u = JSON.parseObject(JSON.toJSONString(json),User.class);
            System.out.println("缓存取对象");
            dbUsers = new ArrayList<>();
            dbUsers.add(u);
            System.out.println(dbUsers.get(0).getPassword());
            System.out.println(dbUsers.get(0).getPassword() == pwd);
            System.out.println(pwd.equals(dbUsers.get(0).getPassword()));
        }else{
            System.out.println("从数据库中取User");
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(username);
            dbUsers = userMapper.selectByExample(userExample);
        }

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
            /**
             * 同时存入对象
//             */
            HashOperations<String,String,User> map= redisTemplate.opsForHash();
            map.put(Table_User,dbUsers.get(0).getUsername(),dbUsers.get(0));
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
        HashOperations<String,String,User> map= redisTemplate.opsForHash();
        System.out.println("需要"+username);
        User user = null;
        if(map.get(Table_User,username)!=null){
            System.out.println("获取了一个用户信息");
            Object json = map.get(Table_User,username);
            user = JSON.parseObject(JSON.toJSONString(json),User.class);
        }else{
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(username);
            user = userMapper.selectByExample(userExample).get(0);
            user.setPassword("");
            map.put(Table_User,user.getUsername(),user);
            System.out.println("从数据库招信息");
        }
        return user;
    }

}
