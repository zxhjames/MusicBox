package com.neteasecommunity.james;

import com.alibaba.fastjson.JSON;
import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.RelationDTO;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.model.*;
import com.neteasecommunity.james.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private  RedisTemplate<Object, ActionsDTO> actionsDTORedisTemplate;
    @Autowired
    private ShareMapper shareMapper;

    @Test
    public void testObj() {
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
    public void t1(){
        System.out.println("k");
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
        //获取所有评论/动态
        Comments comments1 = new Comments();
        comments1.setId(11);
        comments1.setCommentator("james");
        Comments comments2 = new Comments();
        comments2.setId(3);
        comments2.setCommentator("wade");
        hashOperations.put("hash:comment",comments1.getCommentator(),comments1);
        hashOperations.put("hash:comment",comments2.getCommentator(),comments2);
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Comments.class));
        List<Comments> list = redisTemplate.boundHashOps("hash:comment").values();
        list.stream().map(comments -> {return comments.getCommentator();}).forEach(System.out::println);

    }

    @Test
    public void test9(){
        //删除hash的m某个key
        hashOperations.delete("hash:comment","james");
    }

    @Test
    public void test10(){
        //获取某个id的值
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Comments.class));
        List<Comments> list = redisTemplate.boundHashOps("hash:comment").values();
        list.stream().filter(c -> "wade".equals(c.getCommentator())).map(comments -> {return comments.getCommentator();}).forEach(System.out::println);

    }

//    @Test
//    public void test11(){
//        //操作list
//        User user1 = new User();
//        user1.setId(2);
//        user1.setUsername("hehe");
//        userRedisTemplate.opsForList().rightPush("list:user",user1);
//        System.out.println(userRedisTemplate.opsForList().range("list:user",0,-1));
//
//    }

    @Test
    public void test12(){

        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(ActionsDTO.class));
        List<ActionsDTO> actionsDTOList = actionsDTORedisTemplate.opsForList().range("Table_Share",0,-1);
        System.out.println(actionsDTOList.get(0).getTitle());
    }

    @Autowired
    private RedisTemplate<Object,User> template;
    @Test
    public void test13(){
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        List<User> users = redisTemplate.boundHashOps("Table_User").values();
        users.stream().map(User::getUsername).forEach(System.out::println);
    }


    @Test
    public void test14(){
        /**
         * 测试数据库更新操作
         */
        Share share = shareMapper.selectByPrimaryKey(1);
        System.out.println(share.getCreator());
        share.setCommentCount(share.getCommentCount()+1);
        shareMapper.updateByPrimaryKey(share);
    }

    @Test
    public void test15(){
        Relationship relationship = new Relationship();
        relationship.setId(0);
        relationship.setUid1(1);
        relationship.setUid2(2);
        relationship.setType(1);
        HashOperations<String,Integer,Integer> map= redisTemplate.opsForHash();
        map.put("relationShip",relationship.getUid1(),relationship.getUid2());
        System.out.println(map.get("relationShip",relationship.getUid1()));
    }

    @Test
    public void test16(){
        List<String> list = new ArrayList<>();
        String name = "qq";
        SetOperations<String, RelationDTO> set = redisTemplate.opsForSet();
        Set<RelationDTO> members = set.members("relationship");
        Iterator<RelationDTO> iterator = members.iterator();
        while(iterator.hasNext()){
            RelationDTO it =  JSON.parseObject(JSON.toJSONString(iterator.next()),RelationDTO.class);
            if(it.getUname1().equals("qq")){
                System.out.println(it.getUname2());
            }
        }

    }

    @Test
    public void test17(){
        List<String> list = new ArrayList<>();
        SetOperations<String, RelationDTO> set = redisTemplate.opsForSet();
        Set<RelationDTO> members = set.members("relationship");
        Iterator<RelationDTO> iterator = members.iterator();
        while(iterator.hasNext()){
            RelationDTO it =  JSON.parseObject(JSON.toJSONString(iterator.next()),RelationDTO.class);
            if(it.getUname1().equals("qq")){

            }
        }
        /**
         * 关注的好友已经找到了,接下来去筛选出好友
         */
        if(list.size() == 0) return;
        List<User> users = new ArrayList<>();
        list.stream().forEach(s -> {
            users.add(JSON.parseObject(JSON.toJSONString(redisTemplate.opsForHash().get("Table_User",s)),User.class));
        });
        users.stream().forEach(System.out::print);
    }

    @Test
    public void test18(){
        System.out.println(redisTemplate.opsForHash().hasKey("Table_User","jj"));

    }
}
