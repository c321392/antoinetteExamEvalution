package pageObjectDemoWebShop;

import org.openqa.selenium.By;

import frameworkClasses.BasePage;

public class CartPageDemoWebShop extends BasePage{
	
	//Method: Update the qty
	public void updateItemOneQTY(String updQTY) {

		// First clear the text
		clearText(By.cssSelector(".qty-input"));
		
		// Second update qty
		enterText(By.cssSelector(".qty-input"), updQTY);
		
	}
	
	//Method: Update the Shopping Cart
	public void updateShoppingCart() {
		
		clickElement(By.cssSelector("[class='button-2 update-cart-button']"));
	}
	
	//Method: Select the first Item box remove
	public void selectFirstItemTickBox() {
		
		clickElement(By.cssSelector(".remove-from-cart [type]"));
	}

}
