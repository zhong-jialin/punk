package com.punk.controller;


import com.github.pagehelper.PageInfo;
import com.punk.common.Result;
import com.punk.entity.Img;
import com.punk.entity.Params;
import com.punk.service.ImgService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/img")
public class ImgController {
    @Resource
    private ImgService imgService;

    //获取
    @GetMapping("/search")
    public Result finBySearch(Params params){
        PageInfo<Img> info = imgService.findBySearch(params);
        return Result.success(info);
    }
}
