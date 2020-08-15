package org.pidragon.gpio;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
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
import com.pi4j.platform.PlatformManager;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import com.sun.management.OperatingSystemMXBean;

@Service("GPIOController")
public class GPIOControllerImpl implements GPIOController {

	public static GpioController gpio;
	public static OperatingSystemMXBean os;
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
		os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		if (!"x86_64".equals(os.getArch())) {
			initGPIO();
			piStats();
		} else {
			isMock = true;
			System.out.println("System is in MOCK Mode");
		}
		systemStats();
		perfStats();
	}
	
	public void systemStats() {
		System.out.println("----------------------------------------------------");
        System.out.println("SYSTEM INFO");
        System.out.println("----------------------------------------------------");
        System.out.println("Arch: " +  os.getArch());
		System.out.println("Name: " +  os.getName());
		System.out.println("Version: " +  os.getVersion());
		/* Total number of processors or cores available to the JVM */
	    System.out.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());

	    System.out.println("----------------------------------------------------");
        System.out.println("Memory INFO");
        System.out.println("----------------------------------------------------");
	    /* Total amount of free memory available to the JVM */
	    System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());

	    /* This will return Long.MAX_VALUE if there is no preset limit */
	    long maxMemory = Runtime.getRuntime().maxMemory();
	    /* Maximum amount of memory the JVM will attempt to use */
	    System.out.println("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

	    /* Total memory currently available to the JVM */
	    System.out.println("Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());

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
			
			
			System.out.println("Processor count: " +  os.getAvailableProcessors());
			System.out.println("Load average: " +  os.getSystemLoadAverage());
			
		    System.out.println("Physical Memory Size: " + os.getTotalPhysicalMemorySize());
		    System.out.println("Free Physical Memory: " + os.getFreePhysicalMemorySize());
		    System.out.println("Free Swap Size: " + os.getFreeSwapSpaceSize());
		    System.out.println("Commited Virtual Memory Size: " + os.getCommittedVirtualMemorySize());
		   
	}
	
	public void piStats() {
		
		System.out.println("----------------------------------------------------");
        System.out.println("PLATFORM INFO");
        System.out.println("----------------------------------------------------");
        try{System.out.println("Platform Name     :  " + PlatformManager.getPlatform().getLabel());}
        catch(UnsupportedOperationException ex){}
        try{System.out.println("Platform ID       :  " + PlatformManager.getPlatform().getId());}
        catch(UnsupportedOperationException ex){}
        System.out.println("----------------------------------------------------");
        System.out.println("HARDWARE INFO");
        System.out.println("----------------------------------------------------");
        try{System.out.println("Serial Number     :  " + SystemInfo.getSerial());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("CPU Revision      :  " + SystemInfo.getCpuRevision());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("CPU Architecture  :  " + SystemInfo.getCpuArchitecture());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("CPU Part          :  " + SystemInfo.getCpuPart());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("CPU Temperature   :  " + SystemInfo.getCpuTemperature());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("CPU Core Voltage  :  " + SystemInfo.getCpuVoltage());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("CPU Model Name    :  " + SystemInfo.getModelName());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Processor         :  " + SystemInfo.getProcessor());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Hardware          :  " + SystemInfo.getHardware());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Hardware Revision :  " + SystemInfo.getRevision());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Is Hard Float ABI :  " + SystemInfo.isHardFloatAbi());}
        catch(UnsupportedOperationException ex){}
        try{System.out.println("Board Type        :  " + SystemInfo.getBoardType().name());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        System.out.println("----------------------------------------------------");
        System.out.println("MEMORY INFO");
        System.out.println("----------------------------------------------------");
        try{System.out.println("Total Memory      :  " + SystemInfo.getMemoryTotal());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Used Memory       :  " + SystemInfo.getMemoryUsed());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Free Memory       :  " + SystemInfo.getMemoryFree());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Shared Memory     :  " + SystemInfo.getMemoryShared());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Memory Buffers    :  " + SystemInfo.getMemoryBuffers());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("Cached Memory     :  " + SystemInfo.getMemoryCached());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("SDRAM_C Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_C());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("SDRAM_I Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_I());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("SDRAM_P Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_P());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}

        System.out.println("----------------------------------------------------");
        System.out.println("OPERATING SYSTEM INFO");
        System.out.println("----------------------------------------------------");
        try{System.out.println("OS Name           :  " + SystemInfo.getOsName());}
        catch(UnsupportedOperationException ex){}
        try{System.out.println("OS Version        :  " + SystemInfo.getOsVersion());}
        catch(UnsupportedOperationException ex){}
        try{System.out.println("OS Architecture   :  " + SystemInfo.getOsArch());}
        catch(UnsupportedOperationException ex){}
        try{System.out.println("OS Firmware Build :  " + SystemInfo.getOsFirmwareBuild());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("OS Firmware Date  :  " + SystemInfo.getOsFirmwareDate());}
        catch(UnsupportedOperationException | IOException | InterruptedException | ParseException ex){}
        System.out.println("----------------------------------------------------");
        System.out.println("JAVA ENVIRONMENT INFO");
        System.out.println("----------------------------------------------------");
        System.out.println("Java Vendor       :  " + SystemInfo.getJavaVendor());
        System.out.println("Java Vendor URL   :  " + SystemInfo.getJavaVendorUrl());
        System.out.println("Java Version      :  " + SystemInfo.getJavaVersion());
        System.out.println("Java VM           :  " + SystemInfo.getJavaVirtualMachine());
        System.out.println("Java Runtime      :  " + SystemInfo.getJavaRuntime());

        System.out.println("----------------------------------------------------");
        System.out.println("NETWORK INFO");
        System.out.println("----------------------------------------------------");

        // display some of the network information
        try {
			System.out.println("Hostname          :  " + NetworkInfo.getHostname());
		
        for (String ipAddress : NetworkInfo.getIPAddresses())
            System.out.println("IP Addresses      :  " + ipAddress);
        for (String fqdn : NetworkInfo.getFQDNs())
            System.out.println("FQDN              :  " + fqdn);
        for (String nameserver : NetworkInfo.getNameservers())
            System.out.println("Nameserver        :  " + nameserver);
        } catch (IOException | InterruptedException e) {}
        System.out.println("----------------------------------------------------");
        System.out.println("CODEC INFO");
        System.out.println("----------------------------------------------------");
        try{System.out.println("H264 Codec Enabled:  " + SystemInfo.getCodecH264Enabled());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("MPG2 Codec Enabled:  " + SystemInfo.getCodecMPG2Enabled());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
        try{System.out.println("WVC1 Codec Enabled:  " + SystemInfo.getCodecWVC1Enabled());}
        catch(UnsupportedOperationException | IOException | InterruptedException ex){}
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
	                		gpio2.low();
	                	} else {
	                		led28.low();
	                		gpio2.high();
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
	                		gpio3.low();
	                	} else {
	                		led29.low();
	                		gpio3.high();
	                	}
	                	//led29.high();
	                } catch (Exception e) {
	                	e.printStackTrace();
	                }
	            }

	        });
		}
		
		gpio2.blink(4000);
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
