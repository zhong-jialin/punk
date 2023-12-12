package com.punk.entity;

import lombok.Getter;

import javax.persistence.*;
@Getter
@Table(name = "kind")
public class GKind {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    private String kindname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKindname() {
        return kindname;
    }

    public void setKindname(String kindname) {
        this.kindname = kindname;
    }
}
