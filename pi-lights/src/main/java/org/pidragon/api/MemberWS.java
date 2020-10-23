/*
 * Copyright (C) 2016 The ToastHub Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pidragon.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pidragon.common.UtilSvc;
import org.pidragon.gpio.GPIOController;
import org.pidragon.security.api.MyUserPrincipal;
import org.pidragon.utils.Request;
import org.pidragon.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api/member")
public class MemberWS {

	@Autowired 
	UtilSvc utilSvc;
	
	@Autowired
	HttpServletRequest servletRequest;
	@Autowired
	HttpServletResponse servletResponse;

	@Autowired
	GPIOController gpioController;
	
	@RequestMapping(value = "callService", method = RequestMethod.POST)
	public Response callService(@RequestBody Request request) {
		String action = (String) request.getParams().get("action");
		Response response = new Response();
		
		// validate request
		switch (action) {
		case "TEST":
			break;
		case "BLINK":
			gpioController.blink(request, response);
			break;
		case "ON":
			gpioController.on(request, response);
			break;
		case "OFF":
			gpioController.off(request, response);
			break;
		case "LIST_PLUG":
			gpioController.listPlug(request, response);
			break;
		case "LIST_SWITCH":
			gpioController.listSwitch(request, response);
			break;
		case "LIST_LIGHT":
			gpioController.listLight(request, response);
			break;
		case "CHECK":
			response.addParam("USER", ((MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
			utilSvc.addStatus(Response.INFO, Response.SUCCESS, "Alive", response);
			break;
		case "LOGOUT":
			logout(request, response);
			break;
		default:
			break;
		}
		return response;
	}
	
	protected void logout(Request request, Response response) {
		// invalidate user context and terminate session
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(servletRequest, servletResponse, auth);
		}
		utilSvc.addStatus(Response.INFO, Response.SUCCESS, "Good Bye", response);
		// log user activity
		
	}
}
