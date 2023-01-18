package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	// Using Page Factory class to support POM design pattern
	
	
	// objects
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginOption;
	
	@FindBy(xpath = "//a[text()='Register']")
	private WebElement registerOption;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchBoxField;
	
	@FindBy(xpath = "//div[@id='search']//button[contains(@class,'btn-lg')]")
	private WebElement searchButton;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // this and HomePage.class both are same
	}
	
	// Actions
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	public void searchProduct(String productText) {
		searchBoxField.sendKeys(productText);
	}
	
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
		
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}

}
