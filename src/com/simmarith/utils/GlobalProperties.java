package com.simmarith.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalProperties {
	// Properties
	private static GlobalProperties instance;
	private static Properties props;
	
	//Methods
	public static void initProperties() {
		GlobalProperties.initProperties("/global.properties");
	}
	
	public static void initProperties(String fileName) {
		if (GlobalProperties.instance == null) {
			GlobalProperties.instance = new GlobalProperties(fileName);
		}
	}
	
	public static String getProperty(String prop) {
		return GlobalProperties.props.getProperty(prop);
	}

	// Constructors
	private GlobalProperties(String fileName) {
		try {
			props.load(new FileInputStream(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
