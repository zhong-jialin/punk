package com.punk.entity;


import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;
    /** 分类名称 */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
