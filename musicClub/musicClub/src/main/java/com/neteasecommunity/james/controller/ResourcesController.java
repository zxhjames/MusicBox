package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.CommentsDTO;
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
@RequestMapping("/api")
@Transactional
public class ResourcesController {
    @Autowired
    private ShareService shareService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 获取某个用户的信息
     * @param username
     * @return
     */
    @GetMapping("/getUserInfoByUsername/{username}")
    public User getUserInfo(@PathVariable(name="username") String username){
        return userService.getUserInfo(username);
    }


    /**
     * 用户上传自己的动态
     * @param actionsDTO
     * @return
     */
    @PostMapping("/actions")
    public Object doUserActions(@RequestBody ActionsDTO actionsDTO){
        return shareService.insertActions(actionsDTO);
    }

    /**
     * 用户获取自己的所有动态
     * @param username
     * @return
     */
    @GetMapping("/getUserActions/{username}")
    public List<Share> getUserActions(@PathVariable(name="username") String username){
        return shareService.getUserActionsByName(username);
    }

    /**
     * 用于社区显示所有动态
     * @return
     */
    @GetMapping("/getAllUserActions")
    public List<ActionsDTO> getAllUserActions(){
        return shareService.getAllUserActions();
    }

    /**
     * 获取单条转发的动态
     * @param sid
     * @return
     */
    @GetMapping("/getRepostByParentId/{sid}")
    public ActionsDTO getRepostByParentId(@PathVariable(name = "sid") Integer sid){
        return shareService.getRepostByParentId(sid);
    }


    /**
     * 用户删除自己的动态
     * @param id
     * @param creator
     * @return
     */
    @DeleteMapping("/deleteActions/{id}/{creator}")
    public Object deleteActions(@PathVariable(name="id") Integer id,
    @PathVariable(name="creator") String creator){
        return shareService.deleteOneActionByActionId(id,creator);
    }

    /**
     * 用户发表评论
     * @param comments
     * @return
     */
    @PostMapping("/pushComments")
    public Object pushComments(@RequestBody Comments comments){
        return commentService.pushComments(comments);
    }

    /**
     * 根据id获取评论内容
     * @param id
     * @param type
     * @return
     */
    @GetMapping("/getAllcommentsById/{id}/{type}")
    public List<CommentsDTO> getAllcommentsById(@PathVariable(name="id") Integer id, @PathVariable(name="type") Integer type){
        return commentService.getAllcommentsById(id,type);
    }

    /**
     * 获得关注的人的信息
     * @param username
     * @return
     */
    @GetMapping("/getRelation/{username}")
    public List<User> getRelationAction(@PathVariable(name = "username") String username){
        return shareService.getRelationAction(username);
    }

}
