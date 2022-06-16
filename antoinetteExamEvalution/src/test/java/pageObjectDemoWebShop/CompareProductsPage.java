package pageObjectDemoWebShop;

import org.openqa.selenium.By;

import frameworkClasses.BasePage;

public class CompareProductsPage extends BasePage {

	//Method: Retrieve the Item Description for comparison
	public String compareItemDescription(String recordToRetrieve) {
		return getElementText(By.cssSelector(".product-name > td:nth-of-type("+recordToRetrieve+")"));
	}
	
	//Method: Retrieve the Item Price for comparison
	public String compareItemPrice(String recordToRetrieve) {
		return getElementText(By.cssSelector(".product-price > td:nth-of-type("+recordToRetrieve+")"));
	}
}
