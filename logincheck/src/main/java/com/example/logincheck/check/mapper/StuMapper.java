package com.example.logincheck.check.mapper;

import com.example.logincheck.check.pojo.Stu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StuMapper {


    @Select("select * from information where username = #{username} and password = #{password}")
    Stu getByUsernameAndPassword(Stu stu);//根据用户名和密码来查询学生信息

    @Insert("insert into information(username,password)" +
            "values(#{username},#{password})")
    void createNewStu(Stu stu);//新增学生账号
}
