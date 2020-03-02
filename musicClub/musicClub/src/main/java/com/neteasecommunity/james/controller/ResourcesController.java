package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.CommentsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.model.Comments;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.service.CommentService;
import com.neteasecommunity.james.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ResourcesController {
    @Autowired
    private ShareService shareService;
    @Autowired
    private CommentService commentService;

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

    //用户删除自己的一条动态
    @DeleteMapping("/deleteActions/{id}")
    public Object deleteActions(@PathVariable(name="id") Integer id){
        System.out.println(id);
        return shareService.deleteOneActionByActionId(id);
    }

    @PostMapping("/pushComments")
    public Object pushComments(@RequestBody Comments comments){
        System.out.println(comments.getContent());
        return commentService.pushComments(comments);
    }

    @GetMapping("/getAllcommentsById/{id}/{type}")
    public List<CommentsDTO> getAllcommentsById(@PathVariable(name="id") Integer id, @PathVariable(name="type") Integer type){
        //id 动态的id或者是一级评论的id
        //type 表示id是来自动态还是来自一级id
        return commentService.getAllcommentsById(id,type);
    }

}
