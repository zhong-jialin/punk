package com.punk.controller;


import com.github.pagehelper.PageInfo;
import com.punk.common.Result;
import com.punk.entity.Category;
import com.punk.entity.GKind;
import com.punk.entity.Params;
import com.punk.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Category category) {
        List<Category> list = categoryService.selectAll(category);
        return Result.success(list);
    }
    @GetMapping
    public Result findAll() {
        return Result.success(categoryService.findAll());
    }

    //    查询
    @GetMapping("/search")
    public Result findBySearch(Params params){
        PageInfo<Category> categoryPageInfo = categoryService.findBySearch(params);
        return Result.success(categoryPageInfo);
    }

    //        增加分类
    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.updateuser(category);
        return Result.success();
    }

    //    单条删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return Result.success();
    }

    //    批量删除
    @PutMapping("/deleteList")
    public Result deleteList(@RequestBody List<Category> list){
        for (Category category : list){
            categoryService.delete(category.getId());
        }
        return Result.success();
    }
}
