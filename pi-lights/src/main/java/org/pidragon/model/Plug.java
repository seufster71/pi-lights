package org.pidragon.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Plug implements Serializable {

	private static final long serialVersionUID = 1L;
	public final static String MODE_WAVE = "WAVE";
	public final static String MODE_CONTINUOUS = "CONTINUOUS";
	public final static String MODE_OFF = "OFF";
	public final static String FEED_ON_CONTNIUOUS = "FEED_ON_CONTINUOUS";
	public final static String FEED_ON_WAVE = "FEED_ON_WAVE";
	public final static String FEED_OFF = "FEED_OFF";
	
	protected String name;
	protected String code;
	protected Boolean enabled;
	protected List<Schedule> schedules;
	protected GpioPinDigitalOutput gpio;
	protected String feedingMode;
	
	// Constructor
	public Plug() {
	}
	
	public Plug(String name) {
		this.setName(name);
		this.setCode(name);
		this.setEnabled(true);
		this.setFeedingMode(FEED_OFF);
	}

	// Methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@JsonIgnore
	public GpioPinDigitalOutput getGpio() {
		return gpio;
	}
	public void setGpio(GpioPinDigitalOutput gpio) {
		this.gpio = gpio;
	}

	public String getFeedingMode() {
		return feedingMode;
	}
	public void setFeedingMode(String feedingMode) {
		this.feedingMode = feedingMode;
	}


}
