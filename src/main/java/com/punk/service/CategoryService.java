package com.punk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.punk.dao.CategoryDao;
import com.punk.entity.Category;
import com.punk.entity.GKind;
import com.punk.entity.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryDao categoryDao;
    public Object findAll() {
        return categoryDao.selectAll();
    }

    public PageInfo<Category> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Category> categoryList =  categoryDao.findBySearch(params);
        return PageInfo.of(categoryList);
    }

    public void updateuser(Category category) {
        categoryDao.updateByPrimaryKeySelective(category);
    }

    public void delete(Integer id) {
        categoryDao.deleteByPrimaryKey(id);
    }

    public List<Category> selectAll(Category category) {
        return categoryDao.selectAll();
    }
}
