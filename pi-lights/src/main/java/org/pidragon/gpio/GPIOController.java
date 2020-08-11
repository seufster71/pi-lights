package org.pidragon.gpio;

import java.util.HashMap;
import java.util.Map;

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

@Service("GPIOController")
public class GPIOController {

	public static GpioController gpio;
	
	// relays outputs
	Map<String,GpioPinDigitalOutput> gpioMap = new HashMap<String,GpioPinDigitalOutput>();
	public static GpioPinDigitalOutput gpio0;
	public static GpioPinDigitalOutput gpio1;
	public static GpioPinDigitalOutput gpio2;
	public static GpioPinDigitalOutput gpio3;
	public static GpioPinDigitalOutput gpio4;
	public static GpioPinDigitalOutput gpio5;
	public static GpioPinDigitalOutput gpio6;
	public static GpioPinDigitalOutput gpio7;
	
	// inputs
	public static GpioPinDigitalInput switch1;
	public static GpioPinDigitalInput switch2;
	
	// light outputs
	public static GpioPinDigitalOutput led27;
	public static GpioPinDigitalOutput led28;
	public static GpioPinDigitalOutput led29;
	
	public GPIOController() {
		try {
			gpio = GpioFactory.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Constructor LedController called");
		
		if (gpio != null) {
			gpio0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00,"SW0", PinState.LOW); // This is reversed LOW is ON 
			gpio1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"SW1", PinState.LOW);
			gpio2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,"SW2", PinState.HIGH);
			gpio3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03,"SW3", PinState.HIGH);
			gpio4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,"SW4", PinState.HIGH);
			gpio5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,"SW5", PinState.HIGH);
			gpio6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06,"SW6", PinState.HIGH);
			gpio7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07,"SW7", PinState.HIGH);
			
			gpioMap.put("R1", gpio0);
			gpioMap.put("R2", gpio1);
			gpioMap.put("R3", gpio2);
			gpioMap.put("R4", gpio3);
			gpioMap.put("R5", gpio4);
			gpioMap.put("R6", gpio5);
			gpioMap.put("R7", gpio6);
			gpioMap.put("R8", gpio7);
			
			switch1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);
			switch2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_26, PinPullResistance.PULL_DOWN);
			
			led27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27,"LED1", PinState.LOW);
			led28 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28,"LED2", PinState.LOW);
			led29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29,"LED3", PinState.LOW);
			
			switch1.setShutdownOptions(true);
			switch1.addListener(new GpioPinListenerDigital() {
	            @Override
	            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
	                // display pin state on console
	                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
	                try {
	                	if ("HIGH".equals(event.getState().toString())) {
	                		led28.high();
	                	} else {
	                		led28.low();
	                	}
	                	//led29.high();
	                } catch (Exception e) {
	                	e.printStackTrace();
	                }
	            }

	        });
			
			switch2.setShutdownOptions(true);
			switch2.addListener(new GpioPinListenerDigital() {
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
		
		
	}
	
	
	public void test(Request request, Response response) {
		response.getParams().put("response", "test good");
		
	}
	
	public void blink(Request request, Response response) {
		
		response.getParams().put("response", "blinking");
		try {
			
			led27.blink(1000,15000);
			System.out.println("Light is: Blinking");
	       
			
		} catch (Exception e) {
			led27.low();
			gpio.shutdown();
			e.printStackTrace();
		}
	}

	public void on(Request request, Response response) {
		
		response.getParams().put("response", "on");
		try {
		
			led27.high();
			
			System.out.println("Light is: ON");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void off(Request request, Response response) {
		
		response.getParams().put("response", "off");
		try {
			
			led27.low();
			System.out.println("Light is: OFF");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
