package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id = "input-password")
	private WebElement validPasswordField;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyCheckBox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement ContinueButton;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement newsletterCheckbox;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement existingEmailAddressWarning;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement PasswordWarning;
	
	
	// constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public void enterEmailAddress(String emailAdd) {
		emailAddressField.sendKeys(emailAdd);
	}
	
	public void enterTelephoneNum(String telephone) {
		telephoneField.sendKeys(telephone);
	}
	
	public void enterPassword(String password) {
		validPasswordField.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}
	
	public void clickPrivacyPolicy() {
		privacyCheckBox.click();
	}
	
	public void clickContinueButton() {
		ContinueButton.click();
	}
	
	public void selectNewsletterCheckBox() {
		newsletterCheckbox.click();
	}
	
	public String retrieveExistingEmailWarning() {
		String duplicateWarningText = existingEmailAddressWarning.getText();
		return duplicateWarningText;
	}
	
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyText = privacyPolicyWarning.getText();
		return privacyPolicyText;
	}
	
	public boolean getDisplayStatusOfFirstNameWarning() {
		boolean displayStatusFirstName = firstNameWarning.isDisplayed();
		return displayStatusFirstName;
	}
	
	public boolean getDisplayStatusOfLastNameWarning() {
		boolean displayStatusLastName = lastNameWarning.isDisplayed();
		return displayStatusLastName;
	}
	
	public boolean getDisplayStatusOfEmailAddressWarning() {
		boolean displayStatusEmail = emailAddressWarning.isDisplayed();
		return displayStatusEmail;
	}
	
	public boolean getDisplayStatusOfTelephoneNumberWarning() {
		boolean displayStatusTelephone = telephoneWarning.isDisplayed();
		return displayStatusTelephone;
	}
	
	public boolean getDisplayStatusOfPasswordWarning() {
		boolean displayStatusOfPassword = PasswordWarning.isDisplayed();
		return displayStatusOfPassword;
	}

}
