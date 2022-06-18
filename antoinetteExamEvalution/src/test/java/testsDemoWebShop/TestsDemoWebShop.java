package testsDemoWebShop;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
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
		String cartQTYDescription2;
		
		//Neatly start the test by ensuring we in the home page
		//Step One: Select the Category from Side Category List depending on Input
		Reporter.log("User Story 2: Browse using the categories list");
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(category);
		actualDescription2 = resultsPG.getElementTextPageHeader();
		Reporter.log("Description : " + actualDescription2 + " - Contains Category - " + category);
		Assert.assertEquals(actualDescription2.contains(category),true);
		
		//Step Two: Add  Item to the Cart
		resultsPG.clickAddToCart(recordToRetrieve);
		
		//Step Three: Verify that Item was added to the cart
		resultsPG.clickOnCart();
		
		cartQTYDescription2 = cartPG.selectCartQty();
		Reporter.log("Cart QTY = " + cartQTYDescription2);
		Assert.assertEquals(cartQTYDescription2.contentEquals("1"),true);
		
		//Step Four: Clean-up
		cartPG.selectFirstItemTickBox();
		cartPG.updateShoppingCart();
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
		String cartupdQTY;
		String actUpdQty = "3";
		
		//Step One Select the Category
		Reporter.log("User Story 3: Go to cart and update quantity");
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(selectCategory);
		actualDescription3 = resultsPG.getElementTextPageHeader();
		Reporter.log("Description : " + actualDescription3 + " - Contains Category - " + selectCategory);
		Assert.assertEquals(actualDescription3.contains(selectCategory),true);

		//Step Two add first item to Cart
		resultsPG.clickAddToCart("1");
		
		//Step Three go to Cart
		resultsPG.clickOnCart();
		cartQTY = cartPG.selectCartQty();
		Reporter.log("The Before Cart QTY is : " + cartQTY );
		Assert.assertEquals(cartQTY.contentEquals("1"), true);		

		//Step Four Amend Qty
		cartPG.updateItemOneQTY(actUpdQty);
		Reporter.log("QTY to update to : " + actUpdQty );
		
		//Step Five Update the Shopping Cart
		cartPG.updateShoppingCart();
		Thread.sleep(3000);
		cartupdQTY = cartPG.selectCartQty();
		Reporter.log("The Updated Cart QTY is : " + cartupdQTY );
		Assert.assertEquals(cartupdQTY.contentEquals(actUpdQty), true);		
		
		//Step six : Cleanup
		cartPG.selectFirstItemTickBox();
		cartPG.updateShoppingCart();
		basePG.NavigateToHomePage();
	}
	
	//User Story 4: remove item from cart
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then remove the item
		//
	@Test
	
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_Remove() throws InterruptedException {

		//Variables
		String selectCategory = "Books";
		String cartQTY1;
		String cartQTY2;
		String cartText; 
		
		//Neatly start the test by ensuring we in the home page
		Reporter.log("User Story 4: Remove item from cart");
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(selectCategory);
		resultsPG.clickAddToCart("1");
		resultsPG.clickOnCart();
		
		//Step One check if cart is empty
		cartQTY1 = cartPG.selectCartQty();
		Reporter.log(" Actual Cart QTY : " + cartQTY1);
		Assert.assertEquals(cartQTY1.contentEquals("0"), false);	

		//Step Two Select Item to be removed
		cartPG.selectFirstItemTickBox();
	
		//Step Three Update the Shopping Cart
		cartPG.updateShoppingCart();

		cartQTY2 = cartPG.selectCartQty();
		Reporter.log("Cart QTY after item removed : " + cartQTY2);
		cartText = cartPG.cartRemovalText();
		Reporter.log("Cart text after item removed : " + cartText);
		Assert.assertEquals(cartQTY2.contentEquals("0"), true);	
		
		//Step six : Cleanup
		basePG.NavigateToHomePage();
		
}	
	//User Story 5: estimate shipping
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then perform Estimate Shipping
		//
	@Test
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_EstimateSHipping() throws InterruptedException {
		
		//Variables
		String selectCountry = "United States";
		String resultText;
		String selectCategory = "Books";
		String cartQTY1;
		
		//Neatly start the test by ensuring we in the home page
		Reporter.log("User Story 5: Estimate shipping");
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(selectCategory);
		resultsPG.clickAddToCart("1");
		resultsPG.clickOnCart();
		
		//Step One check if cart is empty
		cartQTY1 = cartPG.selectCartQty();
		Reporter.log(" Actual Cart QTY : " + cartQTY1);
		Assert.assertEquals(cartQTY1.contentEquals("0"), false);	

		//Step Two Select the Country and province if United States
		cartPG.selectCountry(selectCountry);
		Thread.sleep(3000);
		cartPG.clickEstimate();
	
		//Step Four verify estimate text
		Thread.sleep(3000);
		resultText = cartPG.resultEstimateReturned();

		Reporter.log("Result text after Estimate of Shipping : " + resultText);
		Assert.assertEquals(resultText.contains("In-Store Pickup "), true);
		
		//Step six : Cleanup
		cartPG.selectFirstItemTickBox();
		cartPG.updateShoppingCart();
		basePG.NavigateToHomePage();
		
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
		String itemPriceOneBefore;
		String itemPriceTwoBefore;
		String itemDescOneBefore;
		String itemDescTwoBefore;
		String itemOneDescription1;
		String itemTwoDescription2;
		String itemOnePrice1;
		String itemTwoPrice2;		
		
		//Neatly start the test by ensuring we in the home page
		//Step One: Select the First Item to the compare list
		Reporter.log("User Story 6: Add to your compare list");
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
		
		//Step six : Cleanup
		basePG.NavigateToHomePage();

	}
	
	@AfterTest
	public void testCleanup() {
		cleanUp();
	}
	
}
