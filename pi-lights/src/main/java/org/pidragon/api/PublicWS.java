package org.pidragon.api;

import java.util.HashMap;
import java.util.Map;

import org.pidragon.gpio.LedController;
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
	LedController ledController;
	
	@RequestMapping(value = "callService", method = RequestMethod.POST)
	public Response service(@RequestBody Request request) {
		String action = (String) request.getParams().get("action");
		
		Response response = new Response();
		
		switch (action) {
		case "test":
			ledController.test(request, response);
			break;
		case "blink":
			ledController.blink(request, response);
			break;
		case "on":
			ledController.on(request, response);
			break;
		case "off":
			ledController.off(request, response);
			break;
		default:
			break;
		}
		
		
		return response;
	}
}
