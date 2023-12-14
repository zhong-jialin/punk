package com.punk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.punk.dao.ImgDao;
import com.punk.entity.Commodity;
import com.punk.entity.Img;
import com.punk.entity.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ImgService {
    @Resource
    private ImgDao imgDao;
    public PageInfo<Img> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Img> list = imgDao.findBySearch(params);
        return PageInfo.of(list);
    }
}
