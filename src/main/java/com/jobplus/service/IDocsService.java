package com.jobplus.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.jobplus.pojo.Docs;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;

public interface IDocsService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(Docs record);

    int insertSelective(Docs record);

    int updateByPrimaryKeySelective(Docs record);
    
    public Docs selectByRecord(Docs record);
  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(Docs record);
	
	/**
	 * 批量插入文档
	 * @param list
	 * @return
	 */
	public int insertDocs(List<Docs> list); 
	
	
	/**
	 * 文件上传
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String upload(MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException ;
	
	
	/**
	 * 我上传的文档
	 * @param userID
	 * @return
	 */
	public Page<Docs> getMyDocsUploaded(Docs record); 
	/**
	 * 回收站文档
	 * @return
	 */
	public Page<Docs> getGbgDocs(Docs record); 
	
	
	/**
	 * 根据主键获取docs
	 * @param id
	 * @return
	 */
	public Docs selectByPrimaryKey(Integer id);
	
	
	/**
	 * 获取文档详情   浏览次数++
	 * @param record
	 * @return
	 */
	public Docs getDocsDetail(Docs record);
	
	/**
	 * 批量逻辑删除docs
	 * @param condition
	 * @param userid
	 * @param ispublic
	 * @param delStatus 删除状态 ：0 彻底删除  2 放入回收站 
	 * @return
	 */
	public int deleteDocs(String[] condition,String userid,String ispublic, String delStatus);
	/**
	 * 回收站   逻辑删除docs
	 * @param condition
	 * @param delStatus 删除状态 ：0 彻底删除  2 放入回收站 
	 * @return
	 */
	public int gbgDelDocs(String[] condition,String delStatus);
	
	/**
	 * 回收站 文档还原
	 * @param condition
	 * @param userid
	 * @param ispublic 被还原文档的ispublic
	 * @param delStatus 删除状态 ：0 彻底删除  2 放入回收站 
	 * @return
	 */
	public int gbgReBackDocs(String[] condition,String userid,String ispublic, String delStatus);
	
	/**
	 * 收藏文档
	 * @param record
	 * @return
	 */
	public MyCollect collectDoc(MyCollect record);
	/**
	 * 下载文档
	 */
	public MyCollect downloadDoc(MyCollect record,Docs doc,HttpServletRequest request);
	
	int updDocAndUpdMoney(Docs record,String preIsPublic);
}
