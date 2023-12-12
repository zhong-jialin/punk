package com.punk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.punk.dao.CommodityDao;
import com.punk.entity.Commodity;
import com.punk.entity.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommodityService {
    @Resource
    private CommodityDao commodityDao;

    public PageInfo<Commodity> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Commodity> list = commodityDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        commodityDao.deleteByPrimaryKey(id);
    }

    public void addCommodity(Commodity commodity) {
        commodityDao.insertSelective(commodity);
    }

    public void editCommodity(Commodity commodity) {
        commodity.setUtime(LocalDateTime.now());
        commodityDao.updateByPrimaryKeySelective(commodity);
    }

    public List<Commodity> findAll() {
        return commodityDao.selectAll();
    }

    public void add(Commodity commodity) {
        commodityDao.insertSelective(commodity);
    }

    public Commodity searchById(Integer id) {
       return commodityDao.searchById(id);
    }
}
