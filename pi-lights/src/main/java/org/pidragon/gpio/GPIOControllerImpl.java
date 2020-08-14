package org.pidragon.gpio;

import java.io.File;
import java.lang.management.ManagementFactory;
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
import com.sun.management.OperatingSystemMXBean;

@Service("GPIOController")
public class GPIOControllerImpl implements GPIOController {

	public static GpioController gpio;
	protected boolean isMock = false;
	
	// relays outputs
	Map<String,GpioPinDigitalOutput> gpioMap = new HashMap<String,GpioPinDigitalOutput>();
	public static GpioPinDigitalOutput gpio1;
	public static GpioPinDigitalOutput gpio2;
	public static GpioPinDigitalOutput gpio3;
	public static GpioPinDigitalOutput gpio4;
	public static GpioPinDigitalOutput gpio5;
	public static GpioPinDigitalOutput gpio6;
	public static GpioPinDigitalOutput gpio7;
	public static GpioPinDigitalOutput gpio8;
	
	// inputs
	public static GpioPinDigitalInput switch1;
	public static GpioPinDigitalInput switch2;
	
	// light outputs
	public static GpioPinDigitalOutput led27;
	public static GpioPinDigitalOutput led28;
	public static GpioPinDigitalOutput led29;
	
	public GPIOControllerImpl() {
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		if (!"x86_64".equals(os.getArch())) {
			initGPIO();
		} else {
			isMock = true;
			System.out.println("System is in MOCK Mode");
		}
		systemStats();
		perfStats();
	}
	
	public void systemStats() {
		/* Total number of processors or cores available to the JVM */
	    System.out.println("Available processors (cores): " + 
	        Runtime.getRuntime().availableProcessors());

	    /* Total amount of free memory available to the JVM */
	    System.out.println("Free memory (bytes): " + 
	        Runtime.getRuntime().freeMemory());

	    /* This will return Long.MAX_VALUE if there is no preset limit */
	    long maxMemory = Runtime.getRuntime().maxMemory();
	    /* Maximum amount of memory the JVM will attempt to use */
	    System.out.println("Maximum memory (bytes): " + 
	        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

	    /* Total memory currently available to the JVM */
	    System.out.println("Total memory available to JVM (bytes): " + 
	        Runtime.getRuntime().totalMemory());

	    /* Get a list of all filesystem roots on this system */
	    File[] roots = File.listRoots();

	    /* For each filesystem root, print some info */
	    for (File root : roots) {
	      System.out.println("File system root: " + root.getAbsolutePath());
	      System.out.println("Total space (bytes): " + root.getTotalSpace());
	      System.out.println("Free space (bytes): " + root.getFreeSpace());
	      System.out.println("Usable space (bytes): " + root.getUsableSpace());
	    }
	}
	
	public void perfStats() {
			OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
			System.out.println("Arch: " +  os.getArch());
			System.out.println("Name: " +  os.getName());
			System.out.println("Version: " +  os.getVersion());
			System.out.println("Processor count: " +  os.getAvailableProcessors());
			System.out.println("Load average: " +  os.getSystemLoadAverage());
			
		    System.out.println("Physical Memory Size: " + os.getTotalPhysicalMemorySize());
		    System.out.println("Free Physical Memory: " + os.getFreePhysicalMemorySize());
		    System.out.println("Free Swap Size: " + os.getFreeSwapSpaceSize());
		    System.out.println("Commited Virtual Memory Size: " + os.getCommittedVirtualMemorySize());
		   
	}
	public void initGPIO() {
		try {
			gpio = GpioFactory.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Constructor GPIOController called");
		
		if (gpio != null) {
			gpio1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00,"R1", PinState.HIGH); // This is reversed LOW is ON 
			gpio2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"R2", PinState.HIGH);
			gpio3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,"R3", PinState.HIGH);
			gpio4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03,"R4", PinState.HIGH);
			gpio5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,"R5", PinState.HIGH);
			gpio6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,"R6", PinState.HIGH);
			gpio7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06,"R7", PinState.HIGH);
			gpio8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07,"R8", PinState.HIGH);
			
			
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
	                		gpio1.low();
	                	} else {
	                		led28.low();
	                		gpio1.high();
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
	                		gpio2.low();
	                	} else {
	                		led29.low();
	                		gpio2.high();
	                	}
	                	//led29.high();
	                } catch (Exception e) {
	                	e.printStackTrace();
	                }
	            }

	        });
		}
		
		led27.blink(2000);
		gpio6.blink(4000);
	}
	
	
	public void test(Request request, Response response) {
		response.getParams().put("response", "test good");
		
	}
	
	public void blink(Request request, Response response) {
		
		response.getParams().put("response", "blinking");
		try {
			if (!isMock) {
				led27.blink(1000,15000);
			}
			System.out.println("Light is: Blinking");
	       
			
		} catch (Exception e) {
			if (!isMock) {
				led27.low();
				gpio.shutdown();
			}
			e.printStackTrace();
		}
	}

	public void on(Request request, Response response) {
		
		response.getParams().put("response", "on");
		try {
			if (!isMock) {
				led27.high();
			}
			System.out.println("Light is: ON");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void off(Request request, Response response) {
		
		response.getParams().put("response", "off");
		try {
			if (!isMock) {
				led27.low();
			}
			System.out.println("Light is: OFF");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
