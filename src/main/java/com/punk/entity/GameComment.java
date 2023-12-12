package com.punk.entity;


import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Table(name = "gamecomments")
public class GameComment {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Getter
    private Integer id;
    @Getter
    private Integer gameId;
    @Getter
    private String comment;
    @Getter
    private String gamename;
    @Getter
    private String cusername;

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    @Getter
    private LocalDateTime ctime;

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public LocalDateTime getUtime() {
        return utime;
    }

    public void setUtime(LocalDateTime utime) {
        this.utime = utime;
    }

    @Getter
    private LocalDateTime utime;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }
}
