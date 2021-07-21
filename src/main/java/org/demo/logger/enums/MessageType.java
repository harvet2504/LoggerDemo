package org.demo.logger.enums;

public enum MessageType {
	
	MESSAGE (1, "message"), 
	ERROR (2, "error"),
	WARNING (3, "warning");
	
	private final int id;
	private final String name;
	
	private MessageType(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
