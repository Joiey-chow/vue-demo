package com.qinyingqiao.connectmysql.connectmysql.Service;

import com.qinyingqiao.connectmysql.connectmysql.bean.Result;
import com.qinyingqiao.connectmysql.connectmysql.bean.User;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Service("loginService")
public class LoginService {
    public Result login(User requestUser){
        String username=requestUser.getUsername();
        username= HtmlUtils.htmlEscape(username);
        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPwd())) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}
