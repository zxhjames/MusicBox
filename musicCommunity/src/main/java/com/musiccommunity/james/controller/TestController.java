package com.musiccommunity.james.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
    @RequestMapping("/haha")
    public String fun(){
        return "欢迎你";
    }




    @RequestMapping("/")
    public String fun1(){
        return "欢迎你大佬";
    }
}
