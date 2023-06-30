package com.example.logincheck.check.service;

import com.example.logincheck.check.pojo.Stu;
import org.springframework.stereotype.Component;

@Component
public interface ExamService {
    void recordAndPlay(String outputFile);//录音
    //转文字
    Stu login(Stu stu);//学生登录


    void sign(Stu stu);//学生注册


}
