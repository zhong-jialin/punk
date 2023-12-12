package com.punk.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.punk.common.PassToken;
import com.punk.common.Result;
import com.punk.entity.Commodity;
import com.punk.entity.Params;
import com.punk.entity.User;
import com.punk.exception.CustomException;
import com.punk.service.CommodityService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Resource
    private CommodityService commodityService;
    @PassToken
    @GetMapping()
    public Result findAll(){
        List<Commodity> commodityList = commodityService.findAll();
        return Result.success(commodityList);
    }
    @PassToken
//  单个查询
    @GetMapping("/searchById")
    public Result searchById(@RequestParam Integer id){
        Commodity commodity = commodityService.searchById(id);
        return Result.success(commodity);
    }

    @PassToken
    //    获取
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Commodity> info = commodityService.findBySearch(params);
        return Result.success(info);
    }

    //    删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commodityService.delete(id);
        return Result.success();
    }

    //增加或修改
    @PostMapping
    public Result save(@RequestBody Commodity commodity) {
        if (commodity.getId() == null) {
            commodityService.addCommodity(commodity);
        } else {
            commodityService.editCommodity(commodity);
        }
        return Result.success();
    }

    //    批量删除
    @PutMapping("/deleteList")
    public Result deleteList(@RequestBody List<Commodity> list) {
        for (Commodity commodity : list) {
            commodityService.delete(commodity.getId());
        }
        return Result.success();
    }


    //    导入导出
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        // 思考：
        // 要一行一行的组装数据，塞到一个list里面
        // 每一行数据，其实就对应数据库表中的一行数据，也就是对应Java的一个实体类Type
        // 我们怎么知道它某一列就是对应某个表头呢？？ 需要映射数据，我们需要一个Map<key,value>，把这个map塞到list里

        // 干！
        // 1. 从数据库中查询出所有数据
        List<Commodity> all = commodityService.findAll();

        if (CollectionUtil.isEmpty(all)) {
            throw new CustomException("未找到数据");
        }

        // 2. 定义一个 List，存储处理之后的数据，用于塞到 list 里
        List<Map<String, Object>> list = new ArrayList<>(all.size());

        // 3. 定义Map<key,value> 出来，遍历每一条数据，然后封装到 Map<key,value> 里，把这个 map 塞到 list 里
        for (Commodity commodity : all) {
            Map<String, Object> row = new HashMap<>();
            row.put("商品名称", commodity.getName());
            row.put("制作商", commodity.getCreater());
            row.put("简介", commodity.getIntroduce());
            row.put("系统要求", commodity.getSystemcfg());
            row.put("价格", commodity.getPrice());
            row.put("上架日期", commodity.getCtime());
            row.put("更新日期", commodity.getUtime());

            list.add(row);
        }

        // 4. 创建一个 ExcelWriter，把 list 数据用这个writer写出来（生成出来）
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list, true);

        // 5. 把这个 excel 下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=commodity.xlsx");
        ServletOutputStream out = response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        IoUtil.close(System.out);


        return Result.success();
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        List<Commodity> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Commodity.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (Commodity commodity : infoList) {
                try {
                    commodityService.add(commodity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success();
    }
}
