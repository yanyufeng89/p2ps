package com.jobplus.service;

import com.jobplus.pojo.Sequence;

public interface ISequenceService {

	Sequence get(String seqName);

	int post(Sequence record);

	int put(Sequence record);

	int delete(String seqName);

	int nextVal(String seqName);
	
	int getSeqByTableName(String tableName);
	
	int[] getSeqByTableName(String tableName,int count);

}
