package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ResourcesController {
    @Autowired
    private ShareService shareService;


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

    @GetMapping("/getAllUserActions")
    public List<ActionsDTO> getAllUserActions(){
        return shareService.getAllUserActions();
    }
}
