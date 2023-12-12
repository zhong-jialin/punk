package com.punk.dao;


import com.punk.entity.Params;
import com.punk.entity.Commodity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommodityDao extends Mapper<Commodity> {


    List<Commodity> findBySearch(@Param("params") Params params);
    @Select("select * from commodity where id = #{id}")
    Commodity searchById(Integer id);
}
