package com.neteasecommunity.james.service;

import com.alibaba.fastjson.JSON;
import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.RelationDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.ShareExtMapper;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.ShareExample;
import com.neteasecommunity.james.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value(("${props.redis.Table_User}"))
    private String Table_User;
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
        share.setGmtCreate(actionsDTO.getGmtCreate());
        share.setGmtModified(actionsDTO.getGmtModified());
        share.setViewCount(0);
        share.setCommentCount(0);
        share.setRepostCount(0);
        share.setLikeCount(0);
        share.setType(actionsDTO.getType());
        User user = userService.getUserInfo(actionsDTO.getCreator());
        int status = shareMapper.insert(share);
        return status == 1 ? ResultDTO.okOf("发布成功") : ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }

    /**
     * 根据用户名获取单条动态
     * @param username
     * @return
     */
    public List<Share> getUserActionsByName(String username) {
        ShareExample shareExample = new ShareExample();
        shareExample.createCriteria().andCreatorEqualTo(username);
        List<Share> shares = shareMapper.selectByExampleWithBLOBs(shareExample)
                .stream().sorted(Comparator.comparing(Share::getGmtModified).reversed()).collect(Collectors.toList());
        return shares;
    }


    /**
     * 获取所有动态
     * @return
     */
    public List<ActionsDTO> getAllUserActions() {
        /**
         * 先寻找缓存
         */
        ShareExample shareExample = new ShareExample();
        List<Share> shares = shareMapper.selectByExampleWithBLOBs(shareExample).stream().
                sorted(Comparator.comparing(Share::getGmtModified).reversed()).collect(Collectors.toList());
        //每次遍历一次单条动态,同时拷贝到ActionsDTO中,返回给前端
        List<ActionsDTO> actionsDTOList = new ArrayList<>();
        User user;
        ActionsDTO actionsDTO;
        for (Share share : shares) {
            //如何处理多次查询
            user = userService.getUserInfo(share.getCreator());
            actionsDTO = new ActionsDTO();
            user.setPassword("");
            actionsDTO.setUser(user);
            BeanUtils.copyProperties(share, actionsDTO);
            //每次复制给bean时候,就插入一条到缓存列表中
            actionsDTOList.add(actionsDTO);
        }
        return actionsDTOList;
    }


    /**
     * 删除动态
     * @param id
     * @param creator
     * @return
     */
    @Transactional
    public Object deleteOneActionByActionId(Integer id,String creator) {
        /**
         * 先删除数据库
         */
        int a = shareMapper.deleteByPrimaryKey(id);
        /**
         * 淘汰缓存
         */
        return a==1?ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }


    /**
     * 获得转发的动态
     * @param sid
     * @return
     */
    public ActionsDTO getRepostByParentId(Integer sid) {
        Share share =  shareMapper.selectByPrimaryKey(sid);
        if(share==null){
            return errorActions();
        }
        User user = userService.getUserInfo(share.getCreator());
        ActionsDTO actionsDTO = new ActionsDTO();
        BeanUtils.copyProperties(share,actionsDTO);
        actionsDTO.setUser(user);
        return actionsDTO;
    }

    /**
     * 获得关注人的信息
     * @param username
     * @return
     */
    public List<User> getRelationAction(String username) {
        /**
         * 根据一个用户名,查出其关注人的信息
         */
        List<User> users = new ArrayList<>();
        SetOperations<String, RelationDTO> set = redisTemplate.opsForSet();
        Set<RelationDTO> members = set.members("relationship");
        Iterator<RelationDTO> iterator = members.iterator();
        while(iterator.hasNext()){
            RelationDTO relationDTO = JSON.parseObject(JSON.toJSONString(iterator.next()),RelationDTO.class);
            if(relationDTO.getUname1().equals(username)){
                users.add(userService.getUserInfo(relationDTO.getUname2()));
            }
        }
        return users;
    }

    /**
     * 返回一个错误的动态
     * @return
     */
    public ActionsDTO errorActions(){
        ActionsDTO actionsDTO = new ActionsDTO();
        actionsDTO.setTitle("找不到了!");
        actionsDTO.setContent("这个内容可能被主人删了o");
        return actionsDTO;
    }

}
