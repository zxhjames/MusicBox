package com.neteasecommunity.james.controller;

import com.neteasecommunity.james.dto.CountDTO;
import com.neteasecommunity.james.dto.RelationDTO;
import com.neteasecommunity.james.service.CountService;
import com.neteasecommunity.james.service.DateCheckService;
import com.neteasecommunity.james.service.RelationShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 执行一些点赞评论转发,关注的业务处理
 */

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ActionController {
    @Autowired
    private CountService countService;

    @Autowired
    private RelationShipService relationShipService;

    @Autowired
    private DateCheckService dateCheckService;
    /**
     * 点赞处理
     * @param countDTO
     * @return
     */
    @PostMapping("/likeWanted")
    public Object operateCount(@RequestBody CountDTO countDTO){
        return countService.operateCount(countDTO);
    }


    /**
     * 添加/取消关注
     * @param relationDTO
     * @return
     */
    @PostMapping("/addRelationShip")
    public Object addRelationShip(@RequestBody RelationDTO relationDTO){
        return relationShipService.addRelationShip(relationDTO);
    }


    /**
     * 判断二者之间是否存在关系
     * @param relationDTO
     * @return
     */
    @PostMapping("/judgeRelationShip")
    public Object judgeRelationShip(@RequestBody RelationDTO relationDTO){
        return relationShipService.judgeRelationShip(relationDTO);
    }

    /**
     * 处理签到业务
     */
    @GetMapping("/CheckDateOnline/{flag}")
    public Object judgeCheckDateOnline(@PathVariable(name="flag") String flag){
        return dateCheckService.judgeIsCheck(flag);
    }
    /**
     * 签到
     */
    @GetMapping("/pushDateOnline/{flag}")
    public Object  pushCheckDateOnline(@PathVariable(name="flag") String flag){
        return dateCheckService.pushCheck(flag);
    }

}
