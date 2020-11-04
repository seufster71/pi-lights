package org.pidragon.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pidragon.common.UtilSvc;
import org.pidragon.gpio.GPIOController;
import org.pidragon.model.AppCacheMenuUtil;
import org.pidragon.model.GlobalConstant;
import org.pidragon.model.MenuItem;
import org.pidragon.model.PrefCacheUtil;
import org.pidragon.utils.Request;
import org.pidragon.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/public")
public class PublicWS {

	@Autowired
	GPIOController gpioController;
	
	@Autowired
	PrefCacheUtil prefCacheUtil;
	
	@Autowired
	AppCacheMenuUtil appCacheMenuUtil;
	
	@Autowired 
	UtilSvc utilSvc;
	
	@RequestMapping(value = "callService", method = RequestMethod.POST)
	public Response service(@RequestBody Request request) {
		String action = (String) request.getParams().get("action");
		
		Response response = new Response();
		
		switch (action) {
		case "INIT":
			request.addParam(PrefCacheUtil.PREFPARAMLOC, PrefCacheUtil.RESPONSE);
			request.addParam(GlobalConstant.LANG, "en");
			prefCacheUtil.getPrefInfo(request,response);
			response.addParam(GlobalConstant.LANG, prefCacheUtil.getDefaultLang());
			// get menus
			if (request.containsParam(GlobalConstant.MENUNAMES)){
				this.initMenu(request, response);
			}
			break;
		case "TEST":
			gpioController.test(request, response);
			break;
		
		default:
			break;
		}
		
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public void initMenu(Request request, Response response){
		List<MenuItem> menu = null;
		Map<String,List<MenuItem>> menuList = new HashMap<String,List<MenuItem>>();
		
		ArrayList<String> mylist = (ArrayList<String>) request.getParam(GlobalConstant.MENUNAMES);
		for (String menuName : mylist) {
			menu = appCacheMenuUtil.getMenu(menuName,(String)request.getParam(GlobalConstant.MENUAPIVERSION),(String)request.getParam(GlobalConstant.MENUAPPVERSION),(String)request.getParam(GlobalConstant.LANG));
			menuList.put(menuName, menu);
		}
		
		if (!menuList.isEmpty()){
			response.addParam(Response.MENUS, menuList);
		} else {
			utilSvc.addStatus(Response.INFO, Response.SUCCESS, "Menu Issue", response);
		}
	}
}
