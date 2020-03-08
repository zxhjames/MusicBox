package com.neteasecommunity.james;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neteasecommunity.james.model.BType;
import com.neteasecommunity.james.model.Comments;
import com.neteasecommunity.james.service.RedisService;
import com.neteasecommunity.james.util.RedisKeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@SpringBootTest
class JamesApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;

    @Test
    public void testObj() throws Exception{
        Comments comments = new Comments();
        comments.setId(1);
        comments.setParentId(10);
        comments.setLikeCount(1);
        comments.setCommentator("james");
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(System.currentTimeMillis());
        comments.setType(1);
//        hashOperations.put("hash:user",comments.hashCode(),comments);
//        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
//        redisService.expireKey("name",20, TimeUnit.SECONDS);
//        String key = RedisKeyUtil.getKey("comments.Table","name",comments.getId().toString());
//        Comments co = (Comments)operations.get(key);
//        System.out.println(co);

    }

    @Test
    public void testString(){

        //设置过期时间
        valueOperations.set("kama",12,10,TimeUnit.SECONDS);
        System.out.println(valueOperations.get("kama"));
    }

    @Test
    public void testList(){
        Comments comments = new Comments();
        comments.setId(1);
        comments.setParentId(10);
        comments.setLikeCount(1);
        comments.setCommentator("james");
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(System.currentTimeMillis());
        comments.setType(1);
        hashOperations.put("comments",comments.hashCode()+"","100");
        System.out.println(hashOperations.entries("comments"));
    }

    @Test
    public void test01(){
        stringRedisTemplate.opsForHash().put("h1","username","admin");
        stringRedisTemplate.opsForHash().put("h1","email","admin");
        String username = (String)stringRedisTemplate.opsForHash().get("h1", "username");
        System.out.println(username);
    }

    @Test
    public void test02(){
        Comments comments = new Comments();
        comments.setId(1);
        comments.setParentId(10);
        comments.setLikeCount(1);
        comments.setCommentator("james");
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(System.currentTimeMillis());
        comments.setType(1);
        redisTemplate.opsForList().leftPush("Co",comments);
        Comments co = (Comments) redisTemplate.opsForList().leftPop("Co");
        System.out.println(co.getCommentator());
    }

    @Test
    public void test03(){
        stringRedisTemplate.opsForSet().add("s1","c++","c","java","go","python","js");
        Set<String> str = stringRedisTemplate.opsForSet().members("s1");
        str.stream().forEach(System.out::println);
        System.out.println("随机弹出一个");
        System.out.println(stringRedisTemplate.opsForSet().randomMember("s1"));
        stringRedisTemplate.opsForSet().add("s2","java","js");
        System.out.println("查询并集元素");
        Set<String> union = stringRedisTemplate.opsForSet().union("s1","s2");
        union.stream().forEach(System.out::println);
        System.out.println("查询交集元素");
        union = stringRedisTemplate.opsForSet().intersect("s1","s2");
        union.stream().forEach(System.out::println);
        System.out.println("查询差集元素");
        union = stringRedisTemplate.opsForSet().difference("s1","s2");
        union.stream().forEach(System.out::println);

    }

    @Test
    public void test04(){
        for(int i=0;i<=100;++i){
            stringRedisTemplate.opsForZSet().add("z1","V"+i,i);
        }

        //统计最高的前100
        Set<String> s = stringRedisTemplate.opsForZSet().reverseRange("z1",0,10);
        s.stream().forEach(System.out::println);
    }

    @Test
    public void test05(){
        Comments comments = new Comments();
        comments.setId(1);
        comments.setParentId(10);
        comments.setLikeCount(1);
        comments.setCommentator("哈哈");
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(System.currentTimeMillis());
        comments.setType(1);
        //要记得去实现序列化接口
        redisTemplate.opsForValue().set("co1",comments);
        //object 转 对象
        //使用jackson反序列化
//        Object json = redisTemplate.opsForValue().get("co1");
//        Comments co = JSON.parseObject(JSON.toJSONString(json),Comments.class);
        Comments co = (Comments) redisTemplate.opsForValue().get("co1");
        System.out.println(co.getCommentator());
    }

    @Test
    public void test6(){
        try{

        //redis事务
        redisTemplate.multi(); // 开启事务
        Comments comments1 = new Comments();
        comments1.setId(1);
        comments1.setParentId(10);
        comments1.setLikeCount(1);
        comments1.setCommentator("哈哈12312");
        comments1.setGmtCreate(System.currentTimeMillis());
        comments1.setGmtModified(System.currentTimeMillis());
        comments1.setType(1);

        int a = 100/0;
        Comments comments2 = new Comments();
        comments2.setId(1);
        comments2.setParentId(10);
        comments2.setLikeCount(1);
        comments2.setCommentator("哈哈大");
        comments2.setGmtCreate(System.currentTimeMillis());
        comments2.setGmtModified(System.currentTimeMillis());
        comments2.setType(1);

        redisTemplate.exec();//执行所有命令
        //要记得去实现序列化接口
        redisTemplate.opsForValue().set("co1", Arrays.asList(comments1,comments2));
        //object 转 对象
        //使用jackson反序列化
        Object json = redisTemplate.opsForValue().get("co1");
        List<Comments> co = JSON.parseArray(JSON.toJSONString(json),Comments.class);
        co.stream().map(Comments::getCommentator).forEach(System.out::println);
        }catch (Exception e){
            //取消事务,放弃事务内的所有命令
            redisTemplate.discard();
        }finally {

        }

    }


    @Test
    public void test7(){
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("k3","a");
                redisOperations.opsForValue().set("k4","b");
                return null;
            }
        });
    }


    @Test
    public void test8(){
        System.out.println(BType.LIKE_VIDEO);
    }
}
