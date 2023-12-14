package com.punk.entity;


import lombok.Getter;

import javax.persistence.*;


@Getter
@Table(name = "img")
public class Img {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;

    public Integer game;
    public String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
