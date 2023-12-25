package com.punk.dao;
import com.punk.entity.Category;
import com.punk.entity.GKind;
import com.punk.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

@Repository
public interface CategoryDao extends Mapper<Category>{
    List<Category> findBySearch(@Param("params") Params params);
}
