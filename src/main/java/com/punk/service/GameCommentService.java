package com.punk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.punk.dao.GameCommentDao;
import com.punk.entity.GameComment;
import com.punk.entity.Params;
import com.punk.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
//加入mavenscan 扫描资源类
@Service
public class GameCommentService {

    @Resource
    private GameCommentDao gameCommentDao;
    public List<GameComment> findAll() {
        return gameCommentDao.selectAll();
    }

    public PageInfo<GameComment> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<GameComment> gameCommentList =  gameCommentDao.findBySearch(params);
        return PageInfo.of(gameCommentList);
    }

//    新增评论
//    public void adduser(GameComment gameComment) {
//    }

//    编辑评论
    public void updateuser(GameComment gameComment) {
        gameComment.setUtime(LocalDateTime.now());
        gameCommentDao.updateByPrimaryKeySelective(gameComment);
    }

    public void delete(Integer id) {
        gameCommentDao.deleteByPrimaryKey(id);
    }
}
