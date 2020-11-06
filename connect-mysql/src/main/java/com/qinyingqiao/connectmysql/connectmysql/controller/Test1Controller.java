package com.qinyingqiao.connectmysql.connectmysql.controller;

import com.qinyingqiao.connectmysql.connectmysql.Service.LoginService;
import com.qinyingqiao.connectmysql.connectmysql.bean.Result;
import com.qinyingqiao.connectmysql.connectmysql.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class Test1Controller {
    @Autowired
    LoginService loginService;
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser){
        return loginService.login(requestUser);
    }


}

