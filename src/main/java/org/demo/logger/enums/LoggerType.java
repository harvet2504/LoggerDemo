package org.demo.logger.enums;

public enum LoggerType {
	
	CONSOLE ("console"), 
	DATABASE ("database"), 
	FILE ("file");
	
	private final String type;
	
	private LoggerType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	

}
