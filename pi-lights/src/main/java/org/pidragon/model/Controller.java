package org.pidragon.model;

import java.io.Serializable;

public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	public final static String MODE_CENTRAL = "CENTRAL";
	public final static String MODE_REMOTE = "REMOTE";
	
	
	protected String name;
	protected String mode;
	protected Boolean active;
	
	// Constructor
	public Controller(){}
	
	public Controller(String name, String mode){
		this.setName(name);
		this.setMode(mode);
		this.setActive(true);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
