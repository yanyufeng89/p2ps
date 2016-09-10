package com.jobplus.service;

import com.jobplus.pojo.OperationSum;

/**
 * 
 * @author Jerry
 * 2016年6月27日下午2:28:29
 *
 */
public interface IOperationSumService {
	
	int deleteByPrimaryKey(Integer userid);

    int insert(OperationSum record);

    int insertSelective(OperationSum record);


    int updateByPrimaryKeySelective(OperationSum record);

	
	/**
	 * 根据用户ID获取 用户操作数
	 * @param userid
	 * @return
	 */
	public OperationSum selectByPrimaryKey(Integer userid);

	int updOperationSum(OperationSum record);
	
	/**
	 * 更新operationSum表  各个统计数
	 * 
	 * //TABLECOLUM()="docShareSum", "docDownSum", "docCollSum","topicsShareSum", "topicsComSum","topicsAtteSum","bookShareSum", "bookCollSum", 7
		//"articleShareSum", "articleCollSum", "coursesShareSum","coursesCollSum", "sitesShareSum", "sitesCollSum", "attentionSum", "fansSum",
		 * 
		 * 
		 * if(userid == null){
			userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userid");
		}
	 * @param tableColum
	 * @param AddOrDecrease
	 * @param adOrDeNum
	 * @param userid  传递null表示从当前request里取userid
	 * @return
	 */
	public int updOperationSum(Integer tableColum,Integer AddOrDecrease,Integer adOrDeNum,String userid);
}
