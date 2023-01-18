package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='product-thumb']//div[@class='caption']//h4//a")
	private WebElement validSearchedProduct;
	
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement InvalidateSearchedProduct;
	
	public boolean getDisplayStatiusOfValidSearchProduct() {
		boolean displayStatusOfValidProduct = validSearchedProduct.isDisplayed();
		return displayStatusOfValidProduct;
	}
	
	public boolean getDisplayStatiusOfInvalidSearchProduct() {
		boolean displayStatusOfInvalidProduct = InvalidateSearchedProduct.isDisplayed();
		return displayStatusOfInvalidProduct;
	}

}
