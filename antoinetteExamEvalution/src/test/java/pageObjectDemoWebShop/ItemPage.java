package pageObjectDemoWebShop;

import org.openqa.selenium.By;

import frameworkClasses.BasePage;

public class ItemPage extends BasePage {

	//Method: Click on Add To Compare List Button
	public void clickAddToCompareList() {
		
		clickElement(By.cssSelector("[class='button-2 add-to-compare-list-button']"));
	}
	
	//Method: Get the Item Description of the selected Item
	public String getItemDesc() {
		String priceDesc = getElementText(By.cssSelector("h1"));
		return priceDesc;
	}
	
	//Method: Get the Price of the selected Item
	public String getItemPrice() {
		String priceText = getElementText(By.className("product-price"));
		return priceText.substring(7);

	}
}
