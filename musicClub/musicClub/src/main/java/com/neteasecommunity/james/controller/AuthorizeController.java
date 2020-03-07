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



    /**
     *用户登录逻辑
     * @param loginAndRegistDTO
     * @return Object
     * 用户登录状态码
     */
    @PostMapping("/login")
    public Object Login(@RequestBody LoginAndRegistDTO loginAndRegistDTO) {
        /**
         *  第一次登录首先去查找数据库,
         *  如果用户存在则登录成功,
         *  返回一个token到客户端,
         *  同时存储在redis数据库上
         */
        return userService.UserLogin(loginAndRegistDTO);
    }


    /**
     * 用户注册逻辑
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Object Register(HttpServletRequest request) {
        return userService.UserRegister(request);
    }

}
