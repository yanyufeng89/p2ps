package com.jobplus.service.impl;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jobplus.dao.SequenceMapper;
import com.jobplus.pojo.Sequence;
import com.jobplus.service.ISequenceService;

@Service("seqService")
public class SequenceServiceImpl implements ISequenceService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SequenceServiceImpl.class);
	@Resource
	private SequenceMapper seqDao;

	@Override
	public Sequence get(String seqName) {
		

		return seqDao.selectByPrimaryKey(seqName);
	}

	@Override
	public int post(Sequence record) {
		
		return seqDao.insert(record);
	}

	@Override
	public int put(Sequence record) {
		
		return seqDao.updateByPrimaryKey(record);
	}

	@Override
	public int delete(String seqName) {
		
		return seqDao.deleteByPrimaryKey(seqName);
	}

	@Override
	public int nextVal(String seqName) {
		
		return seqDao.nextVal(seqName);
	}

	// 保证tableName在tbl_sequence存在相关记录 高并发情况下有可能拿回的ID为-1这时候需要重新拿
	@Override
	public int getSeqByTableName(String tableName) {
		int seq = -1;
		int i = 0;
		while (i < 5) {
			seq = seqDao.nextVal(tableName);
			if (-1 != seq)
				return seq;
			logger.info(JSON.toJSONString(tableName + "获取ID次数为: " + ++i));
		}

		return seq;
	}
	
	@Override
	public int[] getSeqByTableName(String tableName,int count) {
		int seqs[] = new int[count];
		for(int i=0;i<count;i++){
			seqs[i] = getSeqByTableName(tableName);
		}
		return seqs;
	}


}
