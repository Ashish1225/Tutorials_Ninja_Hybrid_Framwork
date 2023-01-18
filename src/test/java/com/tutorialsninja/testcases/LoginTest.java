package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	@BeforeMethod
	public void setUp() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
		
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.xpath("//a[text()='Login']")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1,dataProvider = "validCredentialSupplier")
	public void verifyLoginWithValidCred(String email, String password) {
		//loginPage = new LoginPage(driver);
		
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		//driver.findElement(By.id("input-email")).sendKeys(email);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation(),
				"Edit your account information is not displayed");
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Edit your account information']")).isDisplayed(),
//				"Edit your account information is not displayed");
	}

	@DataProvider(name="validCredentialSupplier")
	public Object supplyData() {

		Object[][] data = Utilities.getTestDataFromExcel("LoginData");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCred() {
		//loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginPage.clickLoginButton();
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMsg = loginPage.retriveEmailPassworNotMatchingdWarning();
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
//				.getText();
		String exptWarningMsg = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(exptWarningMsg), "Warning Message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassCred() {
		//loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();
		String actualWarningMsg = loginPage.retriveEmailPassworNotMatchingdWarning();
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
//				.getText();
		String exptWarningMsg = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(exptWarningMsg), "Warning Message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassCred() {
		//loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginPage.clickLoginButton();
		String actualWarningMsg = loginPage.retriveEmailPassworNotMatchingdWarning();
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
//				.getText();
		String exptWarningMsg = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(exptWarningMsg), "Warning Message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCred() {
		//loginPage = new LoginPage(driver);
		loginPage.clickLoginButton();
		String actualWarningMsg = loginPage.retriveEmailPassworNotMatchingdWarning();
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		String actualWarningMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
//				.getText();
		String exptWarningMsg = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMsg.contains(exptWarningMsg), "Warning Message is not displayed");
	}
}
