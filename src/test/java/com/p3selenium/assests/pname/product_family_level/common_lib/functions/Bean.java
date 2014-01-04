/**
 * 
 */
package com.p3selenium.assests.pname.product_family_level.common_lib.functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.p3selenium.assests.pname.product_family_level.common_lib.data_source.LoadProperty;

/**
 * @author ABHISHEK
 * 
 */
public class Bean {

	private WebDriver driver = null;
	private DesiredCapabilities capability = null;
	private String project_root = System.getProperty("user.dir");
	private LoadProperty property = null;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public DesiredCapabilities getCapability() {
		return capability;
	}

	public void setCapability(DesiredCapabilities capability) {
		this.capability = capability;
	}

	public String getProject_root() {
		return project_root;
	}

	public void setProject_root(String project_root) {
		this.project_root = project_root;
	}

	public LoadProperty getProperty() {
		return property;
	}

	public void setProperty(LoadProperty property) {
		this.property = property;
	}
}
