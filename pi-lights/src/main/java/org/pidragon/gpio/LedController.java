package org.pidragon.gpio;

import org.pidragon.utils.Request;
import org.pidragon.utils.Response;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

@Service("LedController")
public class LedController {

	final GpioController gpio = GpioFactory.getInstance();
	final GpioPinDigitalOutput led27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27,"LED1", PinState.LOW);
	final GpioPinDigitalOutput led28 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28,"LED2", PinState.LOW);
	final GpioPinDigitalOutput led29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29,"LED3", PinState.LOW);
	final GpioPinDigitalInput switch1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);
	
	public LedController() {
		super();
		System.out.println("Constructor LedController called");
		switch1.setShutdownOptions(true);
		switch1.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                try {
                	if ("HIGH".equals(event.getState().toString())) {
                		led29.high();
                	} else {
                		led29.low();
                	}
                	//led29.high();
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }

        });
	}
	
	
	public void test(Request request, Response response) {
		response.getParams().put("response", "test good");
		
	}
	
	public void blink(Request request, Response response) {
		
		response.getParams().put("response", "blinking");
		try {
			
			led27.blink(1000,15000);
			led28.blink(1000,15000);
			System.out.println("Light is: Blinking");
	       
			
		} catch (Exception e) {
			led27.low();
			led28.low();
			gpio.shutdown();
			e.printStackTrace();
		}
	}

	public void on(Request request, Response response) {
		
		response.getParams().put("response", "on");
		try {
		
			led27.high();
			led28.high();
			
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
			System.out.println("Light is: OFF");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
