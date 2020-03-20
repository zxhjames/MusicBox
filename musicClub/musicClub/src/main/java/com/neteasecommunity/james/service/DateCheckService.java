package com.neteasecommunity.james.service;

import cn.hutool.core.date.DateUtil;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateCheckService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 判断用户今天是否打卡
     * @param check
     * @return
     */
    public Object judgeIsCheck(String check) {
        Integer year = DateUtil.thisYear();
        Integer month = DateUtil.thisMonth();
        Integer date = DateUtil.thisDayOfMonth();
        String res =  check+ year.toString()+month.toString()+date.toString();
        System.out.println(res);
        if(stringRedisTemplate.opsForValue().get(res)!=null) {
            return ResultDTO.okOf();
        }
        return ResultDTO.errorOf(CustomizeErrorCode.NO_FOUND);
    }

    /**
     * 用户执行打卡动作
     * @param check
     * @return
     */
    public  Object pushCheck(String check){
        Integer year = DateUtil.thisYear();
        Integer month = DateUtil.thisMonth();
        Integer date = DateUtil.thisDayOfMonth();
        String res = check + year.toString()+month.toString()+date.toString();
        stringRedisTemplate.opsForValue().set(res,"1");
        return null;
    }
}
