package org.pidragon.gpio;

import org.pidragon.utils.Request;
import org.pidragon.utils.Response;

public interface GPIOController {
	void test(Request request, Response response);
	void blink(Request request, Response response);
	void on(Request request, Response response);
	void off(Request request, Response response);
	
	void countController(Request request, Response response);
	void listController(Request request, Response response);
	void listPlug(Request request, Response response);
	void listLight(Request request, Response response);
	void listSwitch(Request request, Response response);
	void listSchedule(Request request, Response response);
	
	void getPlug(Request request, Response response);
	void getSchedule(Request request, Response response);
	void addSchedule(Request request, Response response);
	void deleteSchedule(Request request, Response response);
	
	void saveConfig();
}
