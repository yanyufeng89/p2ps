package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Sms;

public interface SmsMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Sms record);

	int insertSelective(Sms record);

	Sms selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Sms record);

	int updateByPrimaryKeyWithBLOBs(Sms record);

	int updateByPrimaryKey(Sms record);

	/**
	 * 获取所有消息
	 * 
	 * @param record
	 * @return
	 */
	List<Sms> getAllSms(Sms record);

	/**
	 * 获取系统消息列表
	 * 
	 * @param record
	 * @return
	 */
	// List<Sms> getSmsListSystem(Sms record);

	//未读消息总数(除私信)
	int getNewSmsSum(@Param(value = "userid") Integer userid);
	//未读私信总数
	int getPrivateSmsSum(@Param(value = "userid") Integer userid);
	//所有消息总数
	int geSmsSum(@Param(value = "userid") Integer userid);
	/**
	 * //弹窗显示未读消息列表
	 * @param record
	 * @return
	 */
	public List<Sms> getNewSms(Sms record);
	 /**
     * 批量标记为已读   传递ID即为单个,否则全部
     * @return
     */
    public int makeReadSms(Sms record);
    /**
     * 批量删除   传递ID即为单个,否则全部
     * @return
     */
    public int delSms(Sms record);
    
    public String justForObjName(@Param(value = "colName")String colName,@Param(value = "tableName")String tableName,@Param(value = "id")String id);

}