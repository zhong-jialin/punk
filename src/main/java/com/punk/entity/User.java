package com.punk.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String nickname;
    @Getter
    private String email;
    @Getter
    private String phone;
    @Getter
    private Integer stat ;
    @Getter
    private Integer age;
    @Getter
    private LocalDateTime ctime;
    @Getter
    private LocalDateTime utime;
    @Getter
    private String role;
    @Getter
    private String avater;

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Transient
    private String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public void setUtime(LocalDateTime utime) {
        this.utime = utime;
    }

}