package com.jobplus.pojo.response;

import java.util.List;
import java.util.Map;

import com.jobplus.pojo.TypeConfig;

public class TypeConfigResponse extends BaseResponse{

	
	private static final long serialVersionUID = 1L;
	
	private List<TypeConfig> typeConfigList;
	
	private Map<String,List<TypeConfig>> map;

	public Map<String, List<TypeConfig>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<TypeConfig>> map) {
		this.map = map;
	}

	public List<TypeConfig> getTypeConfigList() {
		return typeConfigList;
	}

	public void setTypeConfigList(List<TypeConfig> typeConfigList) {
		this.typeConfigList = typeConfigList;
	}


}
