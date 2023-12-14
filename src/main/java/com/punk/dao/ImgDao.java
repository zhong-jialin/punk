package com.punk.dao;

import com.punk.entity.Commodity;
import com.punk.entity.Img;
import com.punk.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface ImgDao extends Mapper<Img> {
    List<Img> findBySearch(@Param("params") Params params);
}
