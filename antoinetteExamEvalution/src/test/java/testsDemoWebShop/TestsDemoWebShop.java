package testsDemoWebShop;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworkClasses.BasePage;
import frameworkClasses.ReadExcel;
import pageObjectDemoWebShop.BasePageDemoWebShop;
import pageObjectDemoWebShop.CartPageDemoWebShop;
import pageObjectDemoWebShop.CompareProductsPage;
import pageObjectDemoWebShop.ItemPage;
import pageObjectDemoWebShop.ResultsPage;

public class TestsDemoWebShop extends BasePage {
	
	//Instantiate Pages
	ReadExcel rExcel = new ReadExcel();
	BasePageDemoWebShop basePG = new BasePageDemoWebShop();
	ResultsPage resultsPG = new ResultsPage();
	CartPageDemoWebShop cartPG = new CartPageDemoWebShop();
	ItemPage itemPG = new ItemPage();
	CompareProductsPage comparePG = new CompareProductsPage();
	
	
	//User Story 1: search using the top menu bar
		//  GIVEN
		//		Given the shopper is on the Landing page
		//	WHEN
		//		Using the Top Menu Bar
		//  AND
		//		Selecting Category from excel spreadsheet
		//	THEN
		//		The selected category page will be displayed
		//
	@DataProvider(name = "Selection Category")
	public Object[][] getDataFromExcel(){
		String excelDirectory = rExcel.getDataConfigPropeties("excelDataDir");
		Object[][] errObj = rExcel.getExcelData(excelDirectory +"TestCaseInput.xlsx", "Sheet1");
		return errObj;
	}
	
	@Test(dataProvider="Selection Category")
	public void GIVEN_ShopperOnLandingPage_WHEN_TopMenuBar_THEN_SelectedCategoryPageDisplayed(String category, String recordToRetrieve) throws InterruptedException {
		//Variables
		String actualDescription;
		String cartQTYDescription;
		int n;
		//Neatly start the test by ensuring we in the home page
		//Step One: Select the Category from Top Bar depending on Input
		Reporter.log("User Story 1: search using the top menu bar");

		basePG.NavigateToHomePage();
		basePG.selectTopMenuCategory(category);
		actualDescription = resultsPG.getElementTextPageHeader();
		Reporter.log("Description : " + actualDescription + " - Contains Category - " + category);
		Assert.assertEquals(actualDescription.contains(category),true);
		
		//Step Two: Add  Item to the Cart
		resultsPG.clickAddToCart(recordToRetrieve);
		
		//Step Three: Verify that Item was added to the cart
		resultsPG.clickOnCart();
		
		cartQTYDescription = cartPG.selectCartQty();
		Reporter.log("Cart QTY = " + cartQTYDescription);
		Assert.assertEquals(cartQTYDescription.contentEquals("1"),true);
		
		//Step Four: Clean-up
		cartPG.selectFirstItemTickBox();
		cartPG.updateShoppingCart();
		basePG.NavigateToHomePage();
	}
	
	//User Story 2: browse using the categories list
		//  GIVEN
		//		Given the shopper is on the Landing page
		//	WHEN
		//		Using the Categories List
		//	THEN
		//		The selected category page will be displayed
		//
	@Test(dataProvider="Selection Category")
	public void GIVEN_ShopperOnLandingPage_WHEN_CategoriesList_THEN_SelectedCategoryPageDisplayed(String category, String recordToRetrieve) {
		//Variables
		String actualDescription2;
		
		//Neatly start the test by ensuring we in the home page
		//Step One: Select the Category from Side Category List depending on Input
		Reporter.log("User Story 2: browse using the categories list");
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(category);
		actualDescription2 = resultsPG.getElementTextPageHeader();
		Assert.assertEquals(actualDescription2.contains(category),true);
		
		//Step Two: Add  Item to the Cart
		resultsPG.clickAddToCart(recordToRetrieve);
		
	                                                                                                                                                                                                               
		basePG.NavigateToHomePage();
}
	
	//User Story 3: go to cart and update quantity
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then update the quantity
		//
	@Test
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_UpdateQty() throws InterruptedException {
		//Variables
		String actualDescription3;
		String selectCategory = "Books";
		String cartQTY;
		String actUpdQty = "3";
		
		//Step One Select the Category
		Reporter.log("User Story 3: go to cart and update quantity");
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(selectCategory);
		actualDescription3 = resultsPG.getElementTextPageHeader();
		//Assert.assertEquals(actualDescription3.contains(selectCategory),true);

		//Step Two add first item to Cart
		resultsPG.clickAddToCart("1");
		Thread.sleep(3000);
		
		//Step Three go to Cart
		resultsPG.clickOnCart();
		cartQTY = resultsPG.getCartTextPageHeader();
		System.out.println("The print QTY is : " + cartQTY);
		//Assert.assertEquals(cartQTY.contentEquals("(1)"), true);		

		//Step Four Amend Qty
		cartPG.updateItemOneQTY(actUpdQty);
		
		//Step Five Update the Shopping Cart
		cartPG.updateShoppingCart();
		Thread.sleep(3000);
		cartQTY = resultsPG.getCartTextPageHeader();
		//Assert.assertEquals(cartQTY.contentEquals("("+actUpdQty+")"), true);
		
	}
	
	//User Story 4: remove item from cart
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then remove the item
		//
	@Test(dependsOnMethods="GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_UpdateQty")  
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_Remove() throws InterruptedException {

		//Variables
		String cartQTY1;
		String cartText; 
		
		//Neatly start the test by ensuring we in the home page
		Reporter.log("User Story 4: remove item from cart");
		basePG.NavigateToHomePage();
		GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_UpdateQty();
		
		//Step One check if cart is empty
		cartQTY1 = resultsPG.getCartTextPageHeader();
		System.out.println("The print QTY is : " + cartQTY1);
		Assert.assertEquals(cartQTY1.contentEquals("(0)"), false);	
		Reporter.log(" Actual Cart QTY : " + cartQTY1);

		//Step Two go to Cart
		resultsPG.clickOnCart();
		Thread.sleep(3000);
		
		//Step Three select Item to be removed
		cartPG.selectFirstItemTickBox();
	
		//Step Four Update the Shopping Cart
		cartPG.updateShoppingCart();
		Thread.sleep(3000);
		cartQTY1 = resultsPG.getCartTextPageHeader();
		Reporter.log("Cart QTY after item removed : " + cartQTY1);
		cartText = cartPG.cartRemovalText();
		Reporter.log("Cart text after item removed : " + cartText);
		
}	
	//User Story 5: estimate shipping
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then perform Estimate Shipping
		//
	@Test(dependsOnMethods="GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_UpdateQty")
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_EstimateSHipping() throws InterruptedException {
		
		//Variables
		String cartQTY2;
		String selectCountry = "United States";
		String resultText;
		
		//Neatly start the test by ensuring we in the home page
		Reporter.log("User Story 5: estimate shipping");
		basePG.NavigateToHomePage();
		GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_UpdateQty();
		
		//Step One check if cart is empty
		cartQTY2 = resultsPG.getCartTextPageHeader();
		System.out.println("The print QTY is : " + cartQTY2);
		Assert.assertEquals(cartQTY2.contentEquals("(0)"), false);	
		Reporter.log(" Actual Cart QTY : " + cartQTY2);

		//Step Two go to Cart
		resultsPG.clickOnCart();
		Thread.sleep(3000);
		
		//Step Three select the Country and province if United States
		cartPG.selectCountry(selectCountry);
		Thread.sleep(3000);
		cartPG.clickEstimate();
	
		//Step Four verify estimate text
		Thread.sleep(3000);
		resultText = cartPG.resultEstimateReturned();

		Reporter.log("Result text after Estimate of Shipping : " + resultText);
		Assert.assertEquals(resultText.contains("In-Store Pickup "), true);
		
}	
	//User Story 6: add to your compare list
		//  GIVEN
		//		Given the shopper is on the Landing page
		//	WHEN
		//		Selecting two items out of same category
		//	THEN
		//		Then you compare items
		//

	@Test()
	public void GIVEN_ShopperOnLandingPage_WHEN_SelectTwoItems_THEN_CompareItems() throws InterruptedException {
		
		//Variables
		String itemDescOneBefore;
		String itemDescTwoBefore;
		String itemPriceOneBefore;
		String itemPriceTwoBefore;
		String itemOneDescription1;
		String itemTwoDescription2;
		String itemOnePrice1;
		String itemTwoPrice2;		
		
		//Neatly start the test by ensuring we in the home page
		//Step One: Select the First Item to the compare list
		Reporter.log("User Story 6: add to your compare list");
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory("Books");
		
		Thread.sleep(3000);
		resultsPG.clickOnItem("1");
		
		Thread.sleep(2000);
		itemPriceOneBefore = itemPG.getItemPrice();
		itemDescOneBefore = itemPG.getItemDesc();
		itemPG.clickAddToCompareList();
		
		//Step Two: Select the Second Item to the compare list
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory("Books");
		
		Thread.sleep(3000);
		resultsPG.clickOnItem("2");
		
		Thread.sleep(3000);
		itemPriceTwoBefore = itemPG.getItemPrice();
		itemDescTwoBefore = itemPG.getItemDesc();
		itemPG.clickAddToCompareList();
		
		//Step Three: Compare item Descriptions
		Thread.sleep(2000);
		itemOneDescription1 = comparePG.compareItemDescription("2");
		itemOnePrice1 = comparePG.compareItemPrice("2");
		Reporter.log("Item 1 for Compare Description : " + itemOneDescription1);

		itemTwoDescription2 = comparePG.compareItemDescription("3");
		itemTwoPrice2 = comparePG.compareItemPrice("3");
		Reporter.log("Item 2 for Compare Description : " + itemTwoDescription2);
	
		//Step Four: Compare the values
		Reporter.log(itemOneDescription1 + " Expected Value " + " " + itemPriceOneBefore + " and Actual Value " + itemOnePrice1);
		Reporter.log(itemTwoDescription2 + " Expected Value " + " " + itemPriceTwoBefore + " and Actual Value " + itemTwoPrice2);
		Assert.assertEquals(itemPriceOneBefore, itemOnePrice1);
		Assert.assertEquals(itemPriceTwoBefore, itemTwoPrice2);
		
		
	}
	
}
