package com.jobplus.pojo.response;

import com.jobplus.pojo.OperationSum;

public class OperationSumResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4420806926175683624L;
	
	private OperationSum operationSum;

	public OperationSum getOperationSum() {
		return operationSum;
	}

	public void setOperationSum(OperationSum operationSum) {
		this.operationSum = operationSum;
	}
}
