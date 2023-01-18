package com.tutorialsninja.qa.listeneres;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListener implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" Start executing..");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName+" successfully executed..");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();

		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		// calling screenshot

		extentTest.addScreenCaptureFromPath(Utilities.captureScreenshot(driver, testName));
	
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();

		File pathOfExtentReport = new File(System.getProperty("user.dir")+"\\ExtentReportFolder\\extentReport.html");
		try {
			Desktop.getDesktop().browse(pathOfExtentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
