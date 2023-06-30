package com.example.logincheck.check.pojo;

import java.util.Objects;

public class Stu {
    private String username;//用户名
    private String password;//密码
    private Integer id;//编号

    public String getCheckpass() {
        return checkpass;
    }

    public void setCheckpass(String checkpass) {
        this.checkpass = checkpass;
    }

    private String checkpass;//确认密码

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stu stu = (Stu) o;
        return Objects.equals(username, stu.username) && Objects.equals(password, stu.password) && Objects.equals(id, stu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, id);
    }

    @Override
    public String toString() {
        return "Stu{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
