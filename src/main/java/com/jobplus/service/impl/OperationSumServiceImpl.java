package com.jobplus.service.impl;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.jobplus.dao.OperationSumMapper;
import com.jobplus.pojo.OperationSum;
import com.jobplus.service.IOperationSumService;

@Service("operationSumService")
public class OperationSumServiceImpl implements IOperationSumService{

	@Resource
	private OperationSumMapper operationSumDao;
	
	
	/**
	 * 根据用户ID获取 用户操作数
	 * @param userid
	 * @return
	 */
	@Override
	public OperationSum selectByPrimaryKey(Integer userid) {
		OperationSum operationSum = new OperationSum();
		operationSum = operationSumDao.selectByPrimaryKey(userid);
		return operationSum;
	}
	@Override
	public int deleteByPrimaryKey(Integer userid) {
		
		return operationSumDao.deleteByPrimaryKey(userid);
	}

	@Override
	public int insert(OperationSum record) {
		
		return operationSumDao.insert(record);
	}

	@Override
	public int insertSelective(OperationSum record) {
		
		return operationSumDao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OperationSum record) {
		
		return operationSumDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updOperationSum(OperationSum record) {
		
		return operationSumDao.updOperationSum(record);
	}
	
	
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
	@Override
	public int updOperationSum(Integer tableColum,Integer AddOrDecrease,Integer adOrDeNum,String userid) {
		int ret = 0;
		if(userid == null){
			userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userid");
		}
		// 对应用户课程分享数 增加
		OperationSum op = new OperationSum();
		op.setUserid(Integer.parseInt(userid));
		//TABLECOLUM()="docShareSum", "docDownSum", "docCollSum","topicsShareSum", "topicsComSum","topicsAtteSum","bookShareSum", "bookCollSum", 7
		//"articleShareSum", "articleCollSum", "coursesShareSum","coursesCollSum", "sitesShareSum", "sitesCollSum", "attentionSum", "fansSum",
		op.setTableColum(op.getTABLECOLUM()[tableColum]);
		op.setAddOrDecrease(op.getADDORDECREASE()[AddOrDecrease]);// "+" "-"
		op.setAdOrDeNum(adOrDeNum);
		ret = this.updOperationSum(op);
		return ret;
	}

}
