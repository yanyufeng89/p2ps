package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.BookShare;

public interface BookShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookShare record);

    int insertSelective(BookShare record);

    BookShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookShare record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(BookShare record);
    
    
    /**
     * 获取评论列表
     * @param record
     * @return
     */
    List<BookShare> getList(BookShare record);
    int getListCount(BookShare record);
    
    /**
	 * 获取书籍分享详情用以编辑  
	 * @param id
	 * @return
	 */
    public BookShare getBookShareDetailForEdit(@Param("id")Integer id);
    
    /**
     *  批量删除书籍分享
     * @param conditions
     * @return
     */
    public int deleteByConditions(@Param("conditions")String conditions[]);
}