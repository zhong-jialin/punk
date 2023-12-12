package com.punk.controller;


import com.github.pagehelper.PageInfo;
import com.punk.common.Result;
import com.punk.entity.GKind;
import com.punk.entity.GameComment;
import com.punk.entity.Params;
import com.punk.service.GKindService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/kind")
public class GKindController {
    @Resource
    private GKindService gameKindService;

    @GetMapping
    public Result findAll() {
        return Result.success(gameKindService.findAll());
    }

//    查询
    @GetMapping("/search")
    public Result findBySearch(Params params){
        PageInfo<GKind> gKindPageInfo = gameKindService.findBySearch(params);
        return Result.success(gKindPageInfo);
    }

    //        增加分类
    @PostMapping
    public Result add(@RequestBody GKind gKind){
        gameKindService.updateuser(gKind);
        return Result.success();
    }

    //    单条删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        gameKindService.delete(id);
        return Result.success();
    }

    //    批量删除
    @PutMapping("/deleteList")
    public Result deleteList(@RequestBody List<GKind> list){
        for (GKind gKind : list){
            gameKindService.delete(gKind.getId());
        }
        return Result.success();
    }

}
