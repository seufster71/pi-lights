package org.pidragon.gpio;

import org.pidragon.utils.Request;
import org.pidragon.utils.Response;

public interface GPIOController {
	void test(Request request, Response response);
	void blink(Request request, Response response);
	void on(Request request, Response response);
	void off(Request request, Response response);
}
