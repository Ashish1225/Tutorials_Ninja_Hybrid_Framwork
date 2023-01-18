package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public void loadPropertiesFile() {
		prop = new Properties();
		dataprop = new Properties();
		File propFile = new File("D:\\HybridTestNGFramework\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		File dataPropFile = new File("D:\\HybridTestNGFramework\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		FileInputStream fis = null;
		FileInputStream fis1 = null;
		try {
			fis = new FileInputStream(propFile);
			fis1 = new FileInputStream(dataPropFile);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		try {
			prop.load(fis);
			dataprop.load(fis1);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		//loadPropertiesFile();
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
