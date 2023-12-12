package com.punk.dao;


import com.punk.entity.Params;
import com.punk.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {


    List<User> findBySearch(@Param("params") Params params);

    @Select("select * from user where username = #{name} limit 1")
    User findByName(@Param("name") String name);

    @Select("select * from user where username = #{username} and password = #{password} limit 1")
    User findByNameLogin(@Param("username") String name, @Param("password") String password);
}
