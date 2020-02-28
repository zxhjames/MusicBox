package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.LoginAndRegistDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@CrossOrigin
@RestController
public class AuthorizeController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;


    /**
     *用户登录逻辑
     * @param loginAndRegistDTO
     * @return 用户登录状态码
     */
    @PostMapping("/login")
    public Object Login(@RequestBody LoginAndRegistDTO loginAndRegistDTO) {
        //第一次登录首先去查找数据库,如果用户存在则登录成功,返回一个token到客户端,同时存储在redis数据库上
        String username = loginAndRegistDTO.getUsername();
        String pwd = loginAndRegistDTO.getPassword();
        String token = loginAndRegistDTO.getToken();
        String accessToken = redisTemplate.opsForValue().get(username);

        //之前遇到的坑
        if (accessToken != null && accessToken.equals(token)) {
            return ResultDTO.okOf("redisOK");
        }
        //第一次登录去查数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(pwd);
        return userService.UserLogin(user);
    }


    /**
     * 用户注册逻辑
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Object Register(HttpServletRequest request) {
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
        User user = new User();
        user.setUsername(params.getParameter("username"));
        user.setPassword(params.getParameter("password"));
        user.setRank(1);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAvatarUrl(file.getOriginalFilename());
        return userService.UserRegister(user);
    }



    @GetMapping("/demo")
    public Object demo(){
        return ResultDTO.okOf(UUID.randomUUID());
    }
}
