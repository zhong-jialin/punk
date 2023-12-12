package com.punk.controller;


import com.github.pagehelper.PageInfo;
import com.punk.common.Result;
import com.punk.entity.GameComment;
import com.punk.entity.Params;
import com.punk.entity.User;
import com.punk.service.GameCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/gamecomment")
public class GameCommentController {
    @Resource
    private GameCommentService gameCommentService;

    //    查询全部
    @GetMapping()
    public Result findAll(){
        List<GameComment> gameCommentList = gameCommentService.findAll();
        return Result.success(gameCommentList);
    }

    //    条件查询
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<GameComment> gameCommentPageInfo =  gameCommentService.findBySearch(params);
        return Result.success(gameCommentPageInfo);
    }

//        增加评论
    @PostMapping
    public Result addUser(@RequestBody GameComment gameComment){
            gameCommentService.updateuser(gameComment);
        return Result.success();
    }

    //    单条删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        gameCommentService.delete(id);
        return Result.success();
    }

    //    批量删除
    @PutMapping("/deleteList")
    public Result deleteList(@RequestBody List<GameComment> list){
        for (GameComment gameComment : list){
            gameCommentService.delete(gameComment.getId());
        }
        return Result.success();
    }

}
