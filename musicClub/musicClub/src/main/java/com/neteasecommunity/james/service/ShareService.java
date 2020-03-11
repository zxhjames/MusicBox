package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.ShareExtMapper;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.ShareExample;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.model.UserExample;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//注入bean
@Component
public class ShareService {
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private ShareExtMapper shareExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Value(("${prop.avatarUrl}"))
    private String AvatarUrl; //静态资源地址
    @Autowired
    private RedisTemplate redisTemplate; //注入redistemplate
    @Autowired
    private RedisTemplate<Object,ActionsDTO> actionsDTORedisTemplate;
    @Autowired
    private RedisTemplate<Object,Share> shareRedisTemplate;
    @Value(("${props.redis.Table_Share}"))
    private String redis_Share;
    @Autowired
    private UserService userService;
    /**
     * 插入一条新的动态
     *
     * @param actionsDTO
     * @return ResultDTO
     */
    @Transactional
    public Object insertActions(ActionsDTO actionsDTO) {
        /**
         * 用户插入一条动态,首先插入到数据库中,然后社区动态列表中,其次插入到用户动态列表中
         */
        actionsDTO.setGmtCreate(System.currentTimeMillis());
        actionsDTO.setGmtModified(actionsDTO.getGmtCreate());
        actionsDTO.setViewCount(0);
        actionsDTO.setLikeCount(0);
        actionsDTO.setCommentCount(0);
        actionsDTO.setRepostCount(0);
        Share share = new Share();
        share.setCreator(actionsDTO.getCreator());
        share.setTitle(actionsDTO.getTitle());
        share.setContent(actionsDTO.getContent());
        share.setParentId(actionsDTO.getParentId());
        System.out.println(actionsDTO.getParentId());
        share.setGmtCreate(actionsDTO.getGmtCreate());
        share.setGmtModified(actionsDTO.getGmtModified());
        share.setViewCount(actionsDTO.getViewCount());
        share.setCommentCount(actionsDTO.getCommentCount());
        share.setRepostCount(actionsDTO.getRepostCount());
        share.setLikeCount(actionsDTO.getLikeCount());
        share.setType(actionsDTO.getType());
        User user = userService.getUserInfo(actionsDTO.getCreator());
        actionsDTO.setUser(user);
        /**
         * 插入数据库
         */
        int status = shareMapper.insert(share);
        /**
         * 插入社区动态队列
         */
        redisTemplate.opsForList().leftPush(redis_Share,actionsDTO);
//        /**
//         * 插入用户动态队列
//         */
        String key = actionsDTO.getCreator()+"_Actions";
        redisTemplate.opsForList().leftPush(key,share);
        return status == 1 ? ResultDTO.okOf("发布成功") : ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }

    public List<Share> getUserActionsByName(String username) {
        String key = username + "_Actions";
        /**
         * 先去缓存查找用户的动态列表
         */
        if (redisTemplate.boundListOps(key).size() != 0) {
            List<Share> shares = redisTemplate.opsForList().range(key, 0, -1);
            System.out.println("缓存中取得所有用户动态");
            return shares;
        } else {
            ShareExample shareExample = new ShareExample();
            /**
             * 这里如果数据库的content要使用blob字段
             * 后来在手机上测试又改为了longText
             * 根据时间降序排序
             */
//            shareExample.setOrderByClause("gmt_modified");
            shareExample.createCriteria().andCreatorEqualTo(username);
            List<Share> shares = shareMapper.selectByExampleWithBLOBs(shareExample)
                    .stream().sorted(Comparator.comparing(Share::getGmtModified).reversed()).collect(Collectors.toList());
            System.out.println("插入缓存用户动态");
            shares.stream().forEach(share -> {redisTemplate.opsForList().rightPush(key,share);});
            return shares;
        }
    }


    public List<ActionsDTO> getAllUserActions() {
        /**
         * 首先去缓存中查找,将所有动态缓存成一个链表,如果存在,直接由缓存返回数据
         */
        if (redisTemplate.boundListOps(redis_Share).size() != 0) {
            System.out.println("缓存中取得所有动态");
            List<ActionsDTO> actionsDTOList = redisTemplate.opsForList().range(redis_Share, 0, -1);
            return actionsDTOList;
        } else {
            /**
             * 查询不到则去,查询DB,并且在缓存中存储一个副本
             */
            ShareExample shareExample = new ShareExample();
            List<Share> shares = shareExtMapper.selectAllByExampleWithDate(shareExample).stream().
                    sorted(Comparator.comparing(Share::getGmtModified).reversed()).collect(Collectors.toList());
            //每次遍历一次单条动态,同时拷贝到ActionsDTO中,返回给前端
            List<ActionsDTO> actionsDTOList = new ArrayList<>();
            User user = null;
            ActionsDTO actionsDTO;
            for (Share share : shares) {
                //如何处理多次查询
                user = userService.getUserInfo(share.getCreator());
                actionsDTO = new ActionsDTO();
                user.setPassword("");
                actionsDTO.setUser(user);
                BeanUtils.copyProperties(share, actionsDTO);

                //每次复制给bean时候,就插入一条到缓存列表中
                System.out.println("插入缓存所有动态");
                actionsDTOList.add(actionsDTO);
                redisTemplate.opsForList().rightPush(redis_Share, actionsDTO);
            }
//            redisTemplate.expire(redis_Share,30, TimeUnit.MINUTES);
            //存储缓存副本
            return actionsDTOList;
        }
    }

    public Object deleteOneActionByActionId(Integer id) {
        return shareMapper.deleteByPrimaryKey(id) == 1?ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }


    public ActionsDTO getRepostByParentId(Integer sid) {
        Share share =  shareMapper.selectByPrimaryKey(sid);
        User user = userService.getUserInfo(share.getCreator());
        ActionsDTO actionsDTO = new ActionsDTO();
        BeanUtils.copyProperties(share,actionsDTO);
        actionsDTO.setUser(user);
        return actionsDTO;
    }
}
