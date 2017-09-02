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
	final GpioPinDigitalOutput led27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27,"LED1", PinState.LOW);
	final GpioPinDigitalOutput led28 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28,"LED2", PinState.LOW);
	final GpioPinDigitalOutput led29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29,"LED3", PinState.LOW);
	
	public void test(Request request, Response response) {
		response.getParams().put("response", "test good");
		
	}
	
	public void blink(Request request, Response response) {
		
		response.getParams().put("response", "blinking");
		try {
			
			led27.blink(1000,15000);
			led28.blink(1000,15000);
			led29.blink(1000,15000);
			
		} catch (Exception e) {
			led27.low();
			led28.low();
			led29.low();
			gpio.shutdown();
			e.printStackTrace();
		}
	}

	public void on(Request request, Response response) {
		
		response.getParams().put("response", "on");
		try {
		
			led27.high();
			led28.high();
			led29.high();
			System.out.println("Light is: ON");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void off(Request request, Response response) {
		
		response.getParams().put("response", "off");
		try {
			
			led27.low();
			led28.low();
			led29.low();
			System.out.println("Light is: OFF");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
