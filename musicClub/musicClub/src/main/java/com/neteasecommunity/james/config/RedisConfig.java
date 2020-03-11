package com.neteasecommunity.james.config;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.model.Comments;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//Redis配置类
@Configuration
@EnableCaching
public class RedisConfig {

    @Autowired
    private ObjectMapperFactory objectMapper;

    /**
     * 自定义springSessionDefaultRedisSerializer对象，将会替代默认的SESSION序列化对象。
     * 默认是JdkSerializationRedisSerializer，缺点是需要类实现Serializable接口。
     * 并且在反序列化时如果异常会抛出SerializationException异常，
     * 而SessionRepositoryFilter又没有处理异常，故如果序列化异常时就会导致请求异常
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * JacksonJsonRedisSerializer和GenericJackson2JsonRedisSerializer的区别：
     * GenericJackson2JsonRedisSerializer在json中加入@class属性，类的全路径包名，方便反系列化。
     * JacksonJsonRedisSerializer如果存放了List则在反系列化的时候，
     * 如果没指定TypeReference则会报错java.util.LinkedHashMap cannot be cast。
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer(Object.class);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        //开启事务支持
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    /**
     *
     * @param redisConnectionFactory
     * @return 自定义的User工具类
     */
    @Bean
    public RedisTemplate<Object, User> UserRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, User> redistemplate = new RedisTemplate<>();
        redistemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> ser = new Jackson2JsonRedisSerializer<>(User.class);
        redistemplate.setDefaultSerializer(ser);
        redistemplate.setEnableDefaultSerializer(true);
        redistemplate.afterPropertiesSet();
        //开启事务支持
        redistemplate.setEnableTransactionSupport(true);
        return redistemplate;
    }

    /**
     *
     * @param redisConnectionFactory
     * @return 自定义的Share工具类
     */
    @Bean
    public RedisTemplate<Object, Share> ShareRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Share> redistemplate = new RedisTemplate<>();
        redistemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Share> ser = new Jackson2JsonRedisSerializer<>(Share.class);
        redistemplate.setDefaultSerializer(ser);
        redistemplate.setEnableDefaultSerializer(true);
        redistemplate.afterPropertiesSet();
        //开启事务支持
        redistemplate.setEnableTransactionSupport(true);
        return redistemplate;
    }

    /**
     *
     * @param redisConnectionFactory
     * @return 自定义的Comments工具类
     */
    @Bean
    public RedisTemplate<Object, Comments> CommentsRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object,Comments> redistemplate = new RedisTemplate<>();
        redistemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Comments> ser = new Jackson2JsonRedisSerializer<>(Comments.class);
        redistemplate.setDefaultSerializer(ser);
        redistemplate.setEnableDefaultSerializer(true);
        redistemplate.afterPropertiesSet();
        //开启事务支持
        redistemplate.setEnableTransactionSupport(true);
        return redistemplate;
    }

    /**
     *
     * @param redisConnectionFactory
     * @return 自定义的User工具类
     */
    @Bean
    public RedisTemplate<Object,ActionsDTO> ActionsDTORedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, ActionsDTO> redistemplate = new RedisTemplate<>();
        redistemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<ActionsDTO> ser = new Jackson2JsonRedisSerializer<>(ActionsDTO.class);
        redistemplate.setDefaultSerializer(ser);
        redistemplate.setEnableDefaultSerializer(true);
        redistemplate.afterPropertiesSet();
        //开启事务支持
        redistemplate.setEnableTransactionSupport(true);
        return redistemplate;
    }


    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}