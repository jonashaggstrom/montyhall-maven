package com.jh.montyhall.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
	public static int lowestBox = 1;
	public static int highestBox = 3;
	public static int numberOfBoxes = highestBox - lowestBox + 1;
	public static int iterations = 10000;
	public static boolean debugoutput = false;

	public AppConfig() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}

			// Load a properties file from class path, inside static method
			prop.load(input);

			// Set properties
			AppConfig.lowestBox = Integer.parseInt(prop.getProperty("lowestBox", "1"));
			AppConfig.highestBox = Integer.parseInt(prop.getProperty("highestBox", "3"));
			AppConfig.numberOfBoxes = highestBox - lowestBox + 1;
			AppConfig.iterations = Integer.parseInt(prop.getProperty("iterations", "10000"));
			AppConfig.debugoutput = Boolean.parseBoolean(prop.getProperty("debugoutput", "false"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
