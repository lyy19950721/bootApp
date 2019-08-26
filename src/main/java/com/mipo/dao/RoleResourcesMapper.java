package com.mipo.dao;

import com.mipo.entity.RoleResources;

public interface RoleResourcesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResources record);

    int insertSelective(RoleResources record);

    RoleResources selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResources record);

    int updateByPrimaryKey(RoleResources record);
}