package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 20;
	public static final int PAGE_LOAD_TIME = 15;
	

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "ashishpal1" + timestamp + "@gmail.com";
	}
	
	// reading data from excel
	public static Object[][] getTestDataFromExcel(String sheetName) {

		File file = new File(
				"D:\\HybridTestNGFramework\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		FileInputStream fisExcel=null;
		try {
			fisExcel = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		XSSFWorkbook workbook=null;
		try {
			workbook = new XSSFWorkbook(fisExcel);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int ttlRows = sheet.getLastRowNum() + 1;
		int ttlColumns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[ttlRows - 1][ttlColumns];

		for (int r = 0; r < ttlRows - 1; r++) {

			XSSFRow row = sheet.getRow(r + 1);

			for (int c = 0; c < ttlColumns; c++) {

				XSSFCell cell = row.getCell(c);
				CellType cellType = cell.getCellType();
				switch (cellType) {
				case STRING:
					data[r][c] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[r][c] = String.valueOf((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[r][c] = cell.getBooleanCellValue();
					break;
				default:
					data[r][c] = cell.getStringCellValue();
					break;
				}
			}
		}
		try {
			workbook.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return data;
	}
	
	// taking screenshot
	public static String captureScreenshot(WebDriver driver, String testName) {
		TakesScreenshot tss = (TakesScreenshot)driver;
		File srcScreenshot = tss.getScreenshotAs(OutputType.FILE);
		File destScreenshot = new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png");
		
		try {
			FileHandler.copy(srcScreenshot, destScreenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destScreenshot.getAbsolutePath();
	}

}
