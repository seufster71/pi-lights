package org.pidragon.gpio;

import org.pidragon.utils.Request;
import org.pidragon.utils.Response;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Service("LedController")
public class LedController {

	final GpioController gpio = GpioFactory.getInstance();
	final GpioPinDigitalOutput ledPin29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29,"PinLED", PinState.LOW);
	
	public void test(Request request, Response response) {
		response.getParams().put("response", "test good");
		
	}
	
	public void blink(Request request, Response response) {
		
		response.getParams().put("response", "blinking");
		try {
			
			ledPin29.high();
			System.out.println("Light is: ON");
		
			Thread.sleep(2000);
			
			ledPin29.low();
			System.out.println("Light is: OFF");
			
	        // release the GPIO controller resources
	        //gpio.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void on(Request request, Response response) {
		
		response.getParams().put("response", "on");
		try {
			
			ledPin29.high();
			System.out.println("Light is: ON");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void off(Request request, Response response) {
		
		response.getParams().put("response", "off");
		try {
			
			ledPin29.low();
			System.out.println("Light is: OFF");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
