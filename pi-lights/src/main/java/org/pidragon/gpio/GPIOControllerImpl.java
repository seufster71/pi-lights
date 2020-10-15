package org.pidragon.gpio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import org.pidragon.model.Config;
import org.pidragon.model.Light;
import org.pidragon.model.Plug;
import org.pidragon.model.Schedule;
import org.pidragon.model.Switch;
import org.pidragon.utils.Request;
import org.pidragon.utils.Response;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.sun.management.OperatingSystemMXBean;

@Service("GPIOController")
public class GPIOControllerImpl implements GPIOController {

	public static GpioController gpio;
	public static OperatingSystemMXBean os;
	protected boolean isMock = false;
	
	// Config
	protected Config config;
	
	public GPIOControllerImpl() {
		os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		if ("x86_64".equals(os.getArch())) {
			isMock = true;
		} else {
			try {
				gpio = GpioFactory.getInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		initGPIO();
	}
	
	public void initGPIO() {
		
		
		getConfig();
		
		
		if (config.getPlug1() == null) {
			config.setPlug1(new Plug("P1"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug1().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug1().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00,"R1", PinState.HIGH)); }
		
		if (config.getPlug2() == null) {
			config.setPlug2(new Plug("P2"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug2().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug2().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"R2", PinState.HIGH)); }
		
		if (config.getPlug3() == null) {
			config.setPlug3(new Plug("P3"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug3().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug3().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,"R3", PinState.HIGH)); }
		
		if (config.getPlug4() == null) {
			config.setPlug4(new Plug("P4"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug4().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug4().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03,"R4", PinState.HIGH)); }
		
		if (config.getPlug5() == null) {
			config.setPlug5(new Plug("P5"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug5().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug5().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,"R5", PinState.HIGH)); }
		
		if (config.getPlug6() == null) {
			config.setPlug6(new Plug("P6"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug6().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug6().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,"R6", PinState.HIGH)); }
		
		if (config.getPlug7() == null) {
			config.setPlug7(new Plug("P7"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug7().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug7().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06,"R7", PinState.HIGH)); }
		
		if (config.getPlug8() == null) {
			config.setPlug8(new Plug("P8"));
			List<Schedule> schedules = new ArrayList<Schedule>();
			schedules.add(new Schedule());
			config.getPlug8().setSchedules(schedules);
		}
		if (gpio != null) { config.getPlug8().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07,"R8", PinState.HIGH)); }
				
		// Lights
		if (config.getLight1() == null) {
			config.setLight1(new Light("L1"));
		}
		if (gpio != null) { config.getLight1().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27,"LED1", PinState.LOW)); }
		if (config.getLight2() == null) {
			config.setLight2(new Light("L2"));
		}
		if (gpio != null) { config.getLight2().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28,"LED2", PinState.LOW)); }
		if (config.getLight3() == null) {
			config.setLight3(new Light("L3"));
		}
		if (gpio != null) { config.getLight3().setGpio(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29,"LED3", PinState.LOW)); }
		
		
		// Switches
		if (config.getSwitch1() == null) {
			config.setSwitch1(new Switch("S1"));
		}
		if (gpio != null) {	
			config.getSwitch1().setGpio(gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN)); 
			config.getSwitch1().getGpio().setShutdownOptions(true);
			config.getSwitch1().getGpio().addListener(new GpioPinListenerDigital() {
	            @Override
	            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
	                // display pin state on console
	                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
	                try {
	                	if ("HIGH".equals(event.getState().toString())) {
	                		config.getLight2().getGpio().high();
	                		config.getPlug2().getGpio().low();
	                	} else {
	                		config.getLight2().getGpio().low();
	                		config.getPlug2().getGpio().high();
	                	}
	                	//led29.high();
	                } catch (Exception e) {
	                	e.printStackTrace();
	                }
	            }

	        });
		}
			
		if (config.getSwitch2() == null) {
			config.setSwitch2(new Switch("S2"));
		}
		if (gpio != null) {	
			config.getSwitch2().setGpio(gpio.provisionDigitalInputPin(RaspiPin.GPIO_26, PinPullResistance.PULL_DOWN)); 
			config.getSwitch2().getGpio().setShutdownOptions(true);
			config.getSwitch2().getGpio().addListener(new GpioPinListenerDigital() {
	            @Override
	            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
	                // display pin state on console
	                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
	                try {
	                	if ("HIGH".equals(event.getState().toString())) {
	                		config.getLight3().getGpio().high();
	                		config.getPlug3().getGpio().low();
	                	} else {
	                		config.getLight3().getGpio().low();
	                		config.getPlug3().getGpio().high();
	                	}
	                	//led29.high();
	                } catch (Exception e) {
	                	e.printStackTrace();
	                }
	            }

	        });
		}

		
		
	}
	
	@Override
	public void test(Request request, Response response) {
		response.getParams().put("response", "test good");
		
	}
	
	@Override
	public void blink(Request request, Response response) {
		
		response.getParams().put("response", "blinking");
		try {
			if (!isMock) {
				config.getLight1().getGpio().blink(1000,15000);
			}
			System.out.println("Light is: Blinking");
	       
			
		} catch (Exception e) {
			if (!isMock) {
				config.getLight1().getGpio().low();
				gpio.shutdown();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void on(Request request, Response response) {
		
		response.getParams().put("response", "on");
		try {
			if (!isMock) {
				config.getLight1().getGpio().high();
			}
			System.out.println("Light is: ON");
			saveConfig();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void off(Request request, Response response) {
		
		response.getParams().put("response", "off");
		try {
			if (!isMock) {
				config.getLight1().getGpio().low();
			}
			System.out.println("Light is: OFF");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void listPlug(Request request, Response response) {
		response.addParam("plug1", config.getPlug1());
		response.addParam("plug2", config.getPlug2());
		response.addParam("plug3", config.getPlug3());
		response.addParam("plug4", config.getPlug4());
		response.addParam("plug5", config.getPlug5());
		response.addParam("plug6", config.getPlug6());
		response.addParam("plug7", config.getPlug7());
		response.addParam("plug8", config.getPlug8());
	}
	
	@Override
	public void listLight(Request request, Response response) {
		response.addParam("light1", config.getLight1());
		response.addParam("light2", config.getLight2());
		response.addParam("light3", config.getLight3());
	}
	
	@Override
	public void listSwitch(Request request, Response response) {
		response.addParam("switch1", config.getSwitch1());
		response.addParam("switch2", config.getSwitch2());
		response.addParam("switch3", config.getSwitch3());
	}
	
	public void savePlug(Request request, Response response) {
		String plugCode = (String) request.getParam("plugCode");
		
		saveConfig();
	}
	
	
	private void saveConfig() {
		Gson gson = new Gson();
		try {
			FileWriter file = new FileWriter("/tmp/config.json");
			String jsonInString = gson.toJson(config);
			file.write(jsonInString);
			file.close();
		} catch (IOException e) {
			System.out.println("Error writing to file");
			e.printStackTrace();
		}
		
	}
	
	private void getConfig() {
		Gson gson = new Gson();
		
			File file = new File("/tmp/config.json");
			if (file.exists()) {
				try {
					config = gson.fromJson(new FileReader("/tmp/config.json"), Config.class);
				} catch (JsonSyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonIOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (config == null) {
				config = new Config();
			}
		
	}
}
