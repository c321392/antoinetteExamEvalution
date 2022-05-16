package pageObjectDemoWebShop;

import frameworkClasses.BasePage;

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
}
