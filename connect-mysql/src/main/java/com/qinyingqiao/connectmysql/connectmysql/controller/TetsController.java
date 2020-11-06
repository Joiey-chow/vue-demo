package com.qinyingqiao.connectmysql.connectmysql.controller;

import com.qinyingqiao.connectmysql.connectmysql.bean.Department;
import com.qinyingqiao.connectmysql.connectmysql.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TetsController {

@Autowired
JdbcTemplate jdbcTemplates;

@Autowired(required = false)
DepartmentMapper departmentMapper;

@ResponseBody
@GetMapping("/test")
public Map<String,Object> map(){
    List<Map<String, Object>> list = jdbcTemplates.queryForList("select * FROM test111");
    return list.get(0);

}
@ResponseBody
@GetMapping("/abc/{id}")
    public Department getDepartment(@PathVariable("id")Integer id) {
        return departmentMapper.getDeptById(id);
    }
}
