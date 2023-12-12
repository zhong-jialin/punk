package com.punk.dao;

import com.punk.entity.GameComment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.punk.entity.Params;

//必须加入 mybatis自动生成bean需要扫描包 要加上资源标识
@Repository
public interface GameCommentDao extends Mapper<GameComment> {
    List<GameComment> findBySearch(@Param("params")Params params);
}
