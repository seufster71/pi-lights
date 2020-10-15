package org.pidragon.model;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("AppCacheMenu")
@Scope("singleton")

public class AppCache implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String,String> items = new ConcurrentHashMap<String,String>();
	
	
	public AppCache(){
			
	}

	
}
