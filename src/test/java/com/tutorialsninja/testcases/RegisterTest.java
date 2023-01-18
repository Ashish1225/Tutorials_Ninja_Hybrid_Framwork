package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{

	public WebDriver driver;
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@BeforeMethod
	public void setUp() {
		loadPropertiesFile();	
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
//		driver.findElement(By.xpath("//a[text()='Register']")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringWithMandatoryFields() {
		//registerPage = new RegisterPage(driver);
		accountSuccessPage = new AccountSuccessPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNum(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickPrivacyPolicy();
		registerPage.clickContinueButton();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessHeading();
//		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='agree']")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']//h1")).getText();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("accountSuccessfullyCreatedMessage"),
				"Account Success Heading is not showing..");
	}

	@Test(priority = 2)
	public void verifyRegisteringWithAllFields() {
		//registerPage = new RegisterPage(driver);
		accountSuccessPage = new AccountSuccessPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNum(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickPrivacyPolicy();
		registerPage.selectNewsletterCheckBox();
		registerPage.clickContinueButton();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessHeading();
//		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='agree']")).click();
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']//h1")).getText();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("accountSuccessfullyCreatedMessage"),
				"Account Success Heading is not showing..");
	}

	@Test(priority = 3)
	public void verifyRegisteringWithExistingEmailAddress() {
		//registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNum(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickPrivacyPolicy();
		registerPage.selectNewsletterCheckBox();
		registerPage.clickContinueButton();
		String actualWarningMessage = registerPage.retrieveExistingEmailWarning();
//		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='agree']")).click();
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//		String actualWariningMessage = driver
//				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedWariningMessage = dataprop.getProperty("duplicateEmailWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),
				"Email Address is not already registered..");
	}

	@Test(priority = 4)
	public void verifyRegisterWithoutFillingAnyDetails() {
		//registerPage = new RegisterPage(driver);
		registerPage.clickContinueButton();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		
//		String actualPrivacyPolicyWarning = driver
//				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String privacyWariningMessage = dataprop.getProperty("privacyPolicyWarningMessage");
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(privacyWariningMessage),
				"Privacy Policy Warining is not displayed..");
		
		Assert.assertTrue(registerPage.getDisplayStatusOfFirstNameWarning(), "First Name warning is not displayed");
		
//		Assert.assertTrue(driver
//				.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"))
//				.isDisplayed(), "First Name warning is not displayed");
		
		Assert.assertTrue(registerPage.getDisplayStatusOfLastNameWarning(), "Last Name warning is not displayed");

//		Assert.assertTrue(
//				driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"))
//						.isDisplayed(),
//				"Last Name warning is not displayed");
		
		Assert.assertTrue(registerPage.getDisplayStatusOfEmailAddressWarning(), "E-Mail Address warning is not displayed");

//		Assert.assertTrue(
//				driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]"))
//						.isDisplayed(),
//				"E-Mail Address warning is not displayed");
		
		
		Assert.assertTrue(registerPage.getDisplayStatusOfTelephoneNumberWarning(), "Telephone warning is not displayed");
//		Assert.assertTrue(
//				driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]"))
//						.isDisplayed(),
//				"Telephone warning is not displayed");
		
		Assert.assertTrue(registerPage.getDisplayStatusOfPasswordWarning(), "Password warning is not displayed");

//		Assert.assertTrue(
//				driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]"))
//						.isDisplayed(),
//				"Password warning is not displayed");
	}
}
