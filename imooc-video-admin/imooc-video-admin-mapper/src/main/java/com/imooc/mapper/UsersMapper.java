package com.imooc.mapper;

import com.imooc.pojo.Users;
import com.imooc.pojo.UsersExample;
import java.util.List;

import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Example;

public interface UsersMapper extends MyMapper<Users> {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);



}