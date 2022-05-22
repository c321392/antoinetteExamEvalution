package pageObjectDemoWebShop;

import org.openqa.selenium.By;

import frameworkClasses.BasePage;

public class ResultsPage extends BasePage {
	
	//Get the Header Element Text
	public String getElementTextPageHeader() {
		String hText = getElementText(By.cssSelector("h1"));
		return hText;
	}

	//Click on Add to Cart
	public void clickAddToCart() {
		clickElement(By.cssSelector("[data-productid='13'] [class='button-2 product-box-add-to-cart-button']"));
		
	}
	
	//Get the Header Element Text
	public String getCartTextPageHeader() {
		String hText = getElementText(By.xpath("//li[@id='topcartlink']/a[@href='/cart']"));
		return hText;
	}
	
	//Click on cart
	public void clickOnCart() {
		clickElement(By.cssSelector("#topcartlink .ico-cart"));
		//clickElement(By.cssSelector("[class='button-1 cart-button']"));
		
	}
}
