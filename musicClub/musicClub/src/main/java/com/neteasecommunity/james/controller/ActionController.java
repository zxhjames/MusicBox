package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.CountDTO;
import com.neteasecommunity.james.dto.RelationDTO;
import com.neteasecommunity.james.service.CountService;
import com.neteasecommunity.james.service.RelationShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 执行一些点赞评论转发,关注的业务处理
 */

@CrossOrigin
@RestController
public class ActionController {
    @Autowired
    private CountService countService;

    @Autowired
    private RelationShipService relationShipService;

    @PostMapping("/likeWanted")
    public Object operateCount(@RequestBody CountDTO countDTO){
        return countService.operateCount(countDTO);
    }


    @PostMapping("/addRelationShip")
    public Object addRelationShip(@RequestBody RelationDTO relationDTO){
        return relationShipService.addRelationShip(relationDTO);
    }

    @PostMapping("/judgeRelationShip")
    public Object judgeRelationShip(@RequestBody RelationDTO relationDTO){
        return relationShipService.judgeRelationShip(relationDTO);
    }

}
