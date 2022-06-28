package pageObjectDemoWebShop;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import frameworkClasses.BasePage;
import frameworkClasses.Utilities;

public class BasePageDemoWebShop extends BasePage {
	// Method: Go to Home Page
	public void NavigateToHomePage() {

		String URL = getDataConfigPropeties("systemUnderTest");
		driver.get(URL);
		waitForUrl(30, "demowebshop");
	}
	// Method: Get WebPage Title
	public String getHomePageTitle () {
		String myTitle = driver.getTitle();
		System.out.println("THis is the Page Title = " + myTitle);
		return myTitle;

	}
	
	//Method : Select Top Menu Bar Category
	public void selectTopMenuCategory (String selectedCategory) {
		List<WebElement> list_AllCategories = driver.findElements(By.xpath("//body//div[@class='header-menu']/ul[@class='top-menu']"));
		
		String catTxt = selectedCategory.toUpperCase();
		clickElement(By.linkText(catTxt));
	}

	//Method : Select Side Menu Bar Category
	public void selectSideMenuCategory (String selectedCategory) {
		List<WebElement> list_AllCategories = driver.findElements(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='master-wrapper-content']//div[@class='block block-category-navigation']/div[@class='listbox']"));
		
		String catTxt = selectedCategory.toUpperCase();
		clickElement(By.linkText(selectedCategory));
	}
	
	// Method: Enter text on field
	public void enterText(By pLocator, String enterText) {
		waitforClick(30, pLocator);
		driver.findElement(pLocator).sendKeys(enterText);
	}
	
	//Method: Screenshot to ensure the correct screen shot appears in the report
	public void scrnShot() {
		Utilities uts = new Utilities();
		try {
			uts.takeSnapShot("On test Success" + uts.timeReturn() + ".png");
			System.out.println("On test Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
