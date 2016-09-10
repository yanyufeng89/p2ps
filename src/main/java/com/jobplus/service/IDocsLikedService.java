package com.jobplus.service;

import com.jobplus.pojo.DocsLiked;
import com.jobplus.pojo.User;

public interface IDocsLikedService {


    int deleteByPrimaryKey(DocsLiked key);

    int insert(DocsLiked record,String contextPath,User user);

    int insertSelective(DocsLiked record);
}
