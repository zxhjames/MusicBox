package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.CommentsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.dto.UserInfoDTO;
import com.neteasecommunity.james.model.Comments;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.service.CommentService;
import com.neteasecommunity.james.service.ShareService;
import com.neteasecommunity.james.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Transactional
public class ResourcesController {
    @Autowired
    private ShareService shareService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    //获取某个用户的信息
    @GetMapping("/getUserInfoByUsername/{username}")
    public User getUserInfo(@PathVariable(name="username") String username){
        return userService.getUserInfo(username);
    }
    //用户执行上传的动态的业务
    @PostMapping("/actions")
    public Object doUserActions(@RequestBody ActionsDTO actionsDTO){
        //获取用户上传的动态，并且插入到数据库
        return shareService.insertActions(actionsDTO);
    }

    //用户获取所有自己的动态
    @GetMapping("/getUserActions/{username}")
    public List<Share> getUserActions(@PathVariable(name="username") String username){
        return shareService.getUserActionsByName(username);
    }

    //用于社区显示所有的动态
    @GetMapping("/getAllUserActions")
    public List<ActionsDTO> getAllUserActions(){
        return shareService.getAllUserActions();
    }

    //获取单条转发的动态
    @GetMapping("/getRepostByParentId/{sid}")
    public ActionsDTO getRepostByParentId(@PathVariable(name = "sid") Integer sid){
        return shareService.getRepostByParentId(sid);
    }


    //用户删除自己的一条动态
    @DeleteMapping("/deleteActions/{id}/{creator}")
    public Object deleteActions(@PathVariable(name="id") Integer id,
    @PathVariable(name="creator") String creator){
        return shareService.deleteOneActionByActionId(id,creator);
    }

    //用户发布评论
    @PostMapping("/pushComments")
    public Object pushComments(@RequestBody Comments comments){
//        System.out.println(comments.getType());
        return commentService.pushComments(comments);
    }

    //用户根据id获取评论内容
    @GetMapping("/getAllcommentsById/{id}/{type}")
    public List<CommentsDTO> getAllcommentsById(@PathVariable(name="id") Integer id, @PathVariable(name="type") Integer type){
        //id 动态的id或者是一级评论的id
        //type 表示id是来自动态还是来自一级id
        return commentService.getAllcommentsById(id,type);
    }

    @GetMapping("/getRelation/{username}")
    public List<User> getRelationAction(@PathVariable(name = "username") String username){
        return shareService.getRelationAction(username);
    }

}
