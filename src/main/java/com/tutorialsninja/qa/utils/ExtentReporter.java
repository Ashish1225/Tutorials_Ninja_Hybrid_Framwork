package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter  {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		File file = new File(System.getProperty("user.dir")+"\\ExtentReportFolder\\extentReport.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(file);
		
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setDocumentTitle("TutorialsNinja Extent Report");
		sparkReport.config().setReportName("TutorialsNinja Test Automation Result");
		sparkReport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReport);
		
		Properties configProp = new Properties();
		File configfile = new File("D:\\HybridTestNGFramework\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(configfile);
		} catch (FileNotFoundException e1) {
		
			e1.printStackTrace();
		}
		try {
			configProp.load(fis);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}

}
