package pageObjectDemoWebShop;

import org.openqa.selenium.By;

import frameworkClasses.BasePage;

public class ResultsPage extends BasePage {
	
	public String getElementTextPageHeader() {
		String hText = getElementText(By.cssSelector("h1"));
		return hText;
	}

}
