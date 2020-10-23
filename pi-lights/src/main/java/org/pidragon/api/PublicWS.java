package org.pidragon.api;

import org.pidragon.gpio.GPIOController;
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
	
	@RequestMapping(value = "callService", method = RequestMethod.POST)
	public Response service(@RequestBody Request request) {
		String action = (String) request.getParams().get("action");
		
		Response response = new Response();
		
		switch (action) {
		case "TEST":
			gpioController.test(request, response);
			break;
		
		default:
			break;
		}
		
		
		return response;
	}
}
