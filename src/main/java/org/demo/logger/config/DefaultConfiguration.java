package org.demo.logger.config;

public class DefaultConfiguration  {

	public String getProperty(final String property) {
		String propertyValue = null;
		if (property.equals("logger.logFilePath")) {
			propertyValue = ".\\data\\logFile.txt";
		} else if (property.equals("logger.bd.userName")) {
			propertyValue = "admin";
		} else if (property.equals("logger.bd.password")) {
			propertyValue = "admin";
		} else if (property.equals("logger.bd.dbUrl")) {
			propertyValue = "jdbc:h2:./data/default";
		} else if (property.equals("logger.bd.jdbcDriver")) {
			propertyValue = "org.h2.Driver";
		}
		
		return propertyValue;
	}

}