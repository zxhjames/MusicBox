package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.CountDTO;
import com.neteasecommunity.james.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 执行一些点赞评论转发的业务处理
 */

@CrossOrigin
@RestController
public class ActionController {
    @Autowired
    private CountService countService;
    @PostMapping("/likeWanted")
    public Object operateCount(@RequestBody CountDTO countDTO){
        System.out.println("shoudao");
        System.out.println(countDTO.getId());
        return countService.operateCount(countDTO);
    }

}
