package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;

	@BeforeMethod
	public void setUp() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		homePage =new HomePage(driver);
		homePage.searchProduct(dataprop.getProperty("validProduct"));
		homePage.clickSearchButton();
		searchPage = new SearchPage(driver);
//		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(dataprop.getProperty("validProduct"));
//		driver.findElement(By.xpath("//div[@id='search']//button[contains(@class,'btn-lg')]")).click();

		Assert.assertTrue(searchPage.getDisplayStatiusOfValidSearchProduct(),
				"Searched Product is not displayed..");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		homePage =new HomePage(driver);
		homePage.searchProduct(dataprop.getProperty("invalidProductt"));
		homePage.clickSearchButton();
		searchPage = new SearchPage(driver);
//		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(dataprop.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("//div[@id='search']//button[contains(@class,'btn-lg')]")).click();
		Assert.assertTrue(searchPage.getDisplayStatiusOfInvalidSearchProduct(), "No Product message in search results is not displayed..");
	}
	
	@Test(priority = 3,dependsOnMethods = {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutProduct() {
		homePage =new HomePage(driver);
		homePage.clickSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']//button[contains(@class,'btn-lg')]")).click();
		searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.getDisplayStatiusOfInvalidSearchProduct(), "No Product message in search results is not displayed..");
	}

}
