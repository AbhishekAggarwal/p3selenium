package com.p3selenium.scripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperty {

	public Properties loadProperty() {
		Properties props = new Properties();
		try {
			//load a properties file
    		props.load(new FileInputStream("data.properties"));
		} catch (IOException e) {
		}
		return props;
	}

}
