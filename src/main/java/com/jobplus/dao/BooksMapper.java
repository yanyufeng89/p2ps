package com.jobplus.dao;


import java.util.List;

import com.jobplus.pojo.Books;

public interface BooksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer id);
    //书籍详情
    Books selectRecord(Integer id);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKeyWithBLOBs(Books record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(Books record);
    
    /**
     * 获取 书籍简略信息
     * @param id
     * @return
     */
    Books getSimpleInfo(Integer id);
    
    /**
     * 个人中心  -- 获取我分享的书籍列表
     * @param record
     * @return
     */
    List<Books> getSharedBookList(Books record);
    int getSharedBookListCount(Books record);
    
    /**
     * 个人中心  -- 获取我收藏的书籍列表
     * @param record
     * @return
     */
    List<Books> getCollectedBookList(Books record);
    int getCollectedBookListCount(Books record);
    
}