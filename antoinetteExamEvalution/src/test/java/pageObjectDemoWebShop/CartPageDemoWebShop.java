package pageObjectDemoWebShop;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
	
	//Method: Get Element QTY to ensure that it was updated
	public String selectCartQty() {
			String qText = getElementText(By.cssSelector(".cart-qty"));
			//System.out.println(qText);
			return qText.substring(1, 2);

	}
	
	//Method: Get Text after removal of item
	public String cartRemovalText() {
		
		String cText = getElementText(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='master-wrapper-content']/div[@class='master-wrapper-main']//div[@class='order-summary-content']"));
		//System.out.println(cText);
		return cText;
	}

	//Method: Select the Shipping Country from the Drop Down list
	public void selectCountry(String myCountry) throws InterruptedException {
		Thread.sleep(2000);

		Select dropdown_Countries = new Select(driver.findElement(By.xpath("/html//select[@id='CountryId']")));
		
		// select the desired Country
		dropdown_Countries.selectByVisibleText(myCountry);
				
		Thread.sleep(2000);
		
		if (myCountry == "United States") {
			Select dropdown_Province = new Select(driver.findElement(By.xpath("/html//select[@id='StateProvinceId']")));
			dropdown_Province.selectByIndex(2);
			Thread.sleep(2000);
			
		}
	}
	
	//Method: Click on Estimate Shipping
	public void clickEstimate() {
		//clickElement(By.xpath("[class='button-2 estimate-shipping-button']"));
		clickElement(By.cssSelector("input[name='estimateshipping']"));

	}
	
	//Method: Select the text after the Estimate was clicked
	public String resultEstimateReturned() {
		String rText = getElementText(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='master-wrapper-content']/div[@class='master-wrapper-main']/div[@class='center-1']/div[@class='page shopping-cart-page']//form[@action='/cart']//ul[@class='shipping-results']/li[4]/strong[@class='option-name']"));
		System.out.println(rText);
			return rText;
		
	}
}
