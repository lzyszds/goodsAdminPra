package com.example.demo005.domain;

import com.example.demo005.utill.DateTimeUtil;

import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private String repassword;
    private int status;
    private Date create_time;
    private String Email;
    private int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return DateTimeUtil.dateToStr(create_time);
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                ", Email='" + Email + '\'' +
                ", power=" + power +
                '}';
    }
}
