package com.neteasecommunity.james;

import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class JamesApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    void contextLoads() {
    }


    @Test
    void JudgeInsert(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRank(1);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAvatarUrl("file.getOriginalFilename()");
        int a = userMapper.insert(user);
        System.out.println(a);
    }

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("xix","haha", 1,TimeUnit.DAYS);
        System.out.println(redisTemplate.opsForValue().get("xix"));
    }

}
