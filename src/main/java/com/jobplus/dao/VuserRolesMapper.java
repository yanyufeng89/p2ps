package com.jobplus.dao;

import com.jobplus.pojo.VuserRoles;

public interface VuserRolesMapper {
    int insert(VuserRoles record);

    int insertSelective(VuserRoles record);
}