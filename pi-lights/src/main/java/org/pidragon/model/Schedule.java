package org.pidragon.model;

import java.io.Serializable;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String name;
	
	protected Integer startHour;
	protected Integer startMinute;
	protected Integer startSecond;
	
	protected Integer endHour;
	protected Integer endMinute;
	protected Integer endSecond;
	
	// continuous or wave
	protected String mode;
	
	// for wave form
	protected int timeOn;
	protected int timeOff;
	
	// Constructor
	public Schedule() {
		this.setName("All Day");
		this.setStartHour(0);
		this.setStartMinute(0);
		this.setStartSecond(0);
		this.setEndHour(23);
		this.setEndMinute(59);
		this.setEndSecond(59);
		this.setMode(Plug.MODE_CONTINUOUS);
	}
	// Methods
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getStartHour() {
		return startHour;
	}
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public Integer getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public Integer getStartSecond() {
		return startSecond;
	}
	public void setStartSecond(Integer startSecond) {
		this.startSecond = startSecond;
	}

	public Integer getEndHour() {
		return endHour;
	}
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}

	public Integer getEndMinute() {
		return endMinute;
	}
	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public Integer getEndSecond() {
		return endSecond;
	}
	public void setEndSecond(Integer endSecond) {
		this.endSecond = endSecond;
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getTimeOn() {
		return timeOn;
	}
	public void setTimeOn(int timeOn) {
		this.timeOn = timeOn;
	}

	public int getTimeOff() {
		return timeOff;
	}
	public void setTimeOff(int timeOff) {
		this.timeOff = timeOff;
	}
	
}
