package com.punk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.punk.dao.GKindDao;
import com.punk.entity.GKind;
import com.punk.entity.GameComment;
import com.punk.entity.Params;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GKindService {

    @Resource
    private GKindDao gKindDao;

    public PageInfo<GKind> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<GKind> gKindsList =  gKindDao.findBySearch(params);
        return PageInfo.of(gKindsList);
    }

    public void updateuser(GKind gKind) {
        gKindDao.updateByPrimaryKeySelective(gKind);
    }

    public void delete(Integer id) {
        gKindDao.deleteByPrimaryKey(id);
    }

    public Object findAll() {
        return gKindDao.selectAll();
    }
}
