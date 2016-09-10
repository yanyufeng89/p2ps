package com.jobplus.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.SmsFilterMapper;
import com.jobplus.pojo.SmsFilter;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsFilterService;

@Service("smsFilterService")
public class SmsFilterServiceImpl implements ISmsFilterService {

	@Resource
	private SmsFilterMapper smsFilterDao;
	@Resource
	private ISequenceService seqService;


	
	@Transactional
	@Override
	public int insertOrUpd(SmsFilter record) {
		int id = seqService.getSeqByTableName("tbl_smsFilter");
		record.setId(id);
		int ret = 0;
		ret = smsFilterDao.insertOrUpd(record);
		return ret;
	}
	/**
	 * 获取消息设置参数 列
	 * Map:ntsSmsF   prvtSmsF
	 */
	@Override
	public Map<Object, Object> getSmsFilterParm(SmsFilter record) {
		Map<Object, Object>  retMap = new HashMap<>();
		record.setFilteritem(record.getFILTERITEMS()[0]);
		//如果为空  设置为默认值：接受所有消息、私信
		int ntsSmsF = smsFilterDao.selFilterLvByRecord(record)==null?record.getFILTERLEVELS()[0]:smsFilterDao.selFilterLvByRecord(record).getFilterlevel();
		record.setFilteritem(record.getFILTERITEMS()[1]);
		int prvtSmsF = smsFilterDao.selFilterLvByRecord(record)==null?record.getFILTERLEVELS()[0]:smsFilterDao.selFilterLvByRecord(record).getFilterlevel();
		retMap.put("ntsSmsF", ntsSmsF);
		retMap.put("prvtSmsF", prvtSmsF);
		
		return retMap;
	}
	//获取过滤等级和粉丝ids
	@Override
	public SmsFilter getFilterLvAndFansIds(SmsFilter record) {
		
		return smsFilterDao.getFilterLvAndFansIds(record);
	}
	
}
