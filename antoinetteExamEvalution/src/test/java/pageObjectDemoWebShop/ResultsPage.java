package pageObjectDemoWebShop;

import org.openqa.selenium.By;

import frameworkClasses.BasePage;

public class ResultsPage extends BasePage {
	
	//Method: Get the Header Element Text
	public String getElementTextPageHeader() {
		String hText = getElementText(By.cssSelector("h1"));
		return hText;
	}

	//Method: Click on Add to Cart
	public void clickAddToCart(String recordToRetrieve) {
		clickElement(By.cssSelector("div:nth-of-type(" + recordToRetrieve +") > .product-item input[value='Add to cart']"));

	}
	
	//Method: Get the Header Element Text
	public String getCartTextPageHeader() {
		String hText = getElementText(By.cssSelector(".cart-qty"));
		return hText;
	}
	
	//Method: Click on cart
	public void clickOnCart() {
		clickElement(By.cssSelector("#topcartlink .ico-cart"));
		
	}
	
	//Method: Click on Item to Open Item
	public void clickOnItem(String recordToRetrieve) {

		clickElement(By.cssSelector("div:nth-of-type("+recordToRetrieve+") > .product-item  h2 > a"));
	}
}
