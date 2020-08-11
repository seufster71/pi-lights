package org.pidragon.utils;

import java.util.HashMap;
import java.util.Map;

public class Response {

	private Map<String,Object> params;
	
	public Response(){
		setParams(new HashMap<String,Object>());
	}

	public Map<String,Object> getParams() {
		return params;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
	
	public void addParam(String key, Object value){
		if (params == null){
			params = new HashMap<String,Object>();
		}
		params.put(key, value);
	}
	
	public Object getParam(String key){
		if (params != null && params.containsKey(key)){
			return params.get(key);
		}
		return null;
	}
	
	public boolean containsParam(String key){
		if (params != null && params.containsKey(key)){
			return true;
		}
		return false;
	}
}
