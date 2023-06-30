package com.example.logincheck.check.controller;


import com.example.logincheck.check.pojo.Result;
import com.example.logincheck.check.pojo.Stu;
import com.example.logincheck.check.service.ExamService;
import com.example.logincheck.check.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private ExamService examService;
    @PostMapping("/login")
    public Result login(@RequestBody Stu stu){
        log.info("学生登录：{}",stu);
        Stu s = examService.login(stu);
        if (s != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", s.getId());
            claims.put("username", s.getUsername());
            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");

    }
    @PostMapping("/sign")
    public Result sign(@RequestBody Stu stu){
        log.info("注册学生：{}",stu);
        examService.sign(stu);

        return Result.success();

    }

}
