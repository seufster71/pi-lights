package org.pidragon.utils;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private Map<String,Object> params;
	
	public Request(){
		setParams(new HashMap<String,Object>());
	}

	public Map<String,Object> getParams() {
		return params;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
}
