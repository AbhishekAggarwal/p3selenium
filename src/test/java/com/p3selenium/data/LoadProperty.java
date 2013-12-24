package com.p3selenium.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperty {

	String var = null;

	public String getVar(String key) {
		Properties props = new Properties();
		String path = System.getProperty("user.dir");
		try {
			// load a properties file
			path = path + "\\src\\test\\resources\\";
			props.load(new FileInputStream(path + "data.properties"));
			this.var = props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.var;
	}

}
