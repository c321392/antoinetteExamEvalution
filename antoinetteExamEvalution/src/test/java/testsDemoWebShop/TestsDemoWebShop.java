package testsDemoWebShop;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworkClasses.BasePage;
import frameworkClasses.ReadExcel;
import pageObjectDemoWebShop.BasePageDemoWebShop;
import pageObjectDemoWebShop.CartPageDemoWebShop;
import pageObjectDemoWebShop.ResultsPage;

public class TestsDemoWebShop extends BasePage {
	
	//Instantiate Pages
	ReadExcel rExcel = new ReadExcel();
	BasePageDemoWebShop basePG = new BasePageDemoWebShop();
	ResultsPage resultsPG = new ResultsPage();
	CartPageDemoWebShop cartPG = new CartPageDemoWebShop();
	
	
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
	public void GIVEN_ShopperOnLandingPage_WHEN_TopMenuBar_THEN_SelectedCategoryPageDisplayed(String category, String quantity) throws InterruptedException {
		//Variables
		String actualDescription;
		basePG.NavigateToHomePage();
		basePG.selectTopMenuCategory(category);
		actualDescription = resultsPG.getElementTextPageHeader();
		Assert.assertEquals(actualDescription.contains(category),true);
		//resultsPG.clickAddToCart();
		//resultsPG.clickOnCart();
		//Test for Successfully add to Cart
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
	public void GIVEN_ShopperOnLandingPage_WHEN_CategoriesList_THEN_SelectedCategoryPageDisplayed(String category, String quantity) {
		//Variables
		String actualDescription2;
		
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(category);
		actualDescription2 = resultsPG.getElementTextPageHeader();
		Assert.assertEquals(actualDescription2.contains(category),true);
		//resultsPG.clickAddToCart();
		//resultsPG.clickOnCart();
		//Test for Successfully add to Cart                                                                                                                                                                                                                       
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
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_UpdateQty() {
		//Variables
		String actualDescription3;
		String selectCategory = "Books";
		String cartQTY;
		
		//Step One Select the Category
		basePG.NavigateToHomePage();
		basePG.selectSideMenuCategory(selectCategory);
		actualDescription3 = resultsPG.getElementTextPageHeader();
		Assert.assertEquals(actualDescription3.contains(selectCategory),true);

		//Step Two add first item to Cart
		resultsPG.clickAddToCart();
		cartQTY = resultsPG.getCartTextPageHeader();
		//Assert.assertEquals(cartQTY.contentEquals("Shopping cart (1)"), true);
		
		//Step Three go to Cart
		resultsPG.clickOnCart();
		//cartQTY = resultsPG.getCartTextPageHeader();
		//Assert.assertEquals(cartQTY.contentEquals("Shopping cart (1)"), true);
		
		//Step Four Amend Qty
		cartPG.updateItemOneQTY("3");
		//cartQTY = resultsPG.getCartTextPageHeader();
		//Assert.assertEquals(cartQTY.contentEquals("Shopping cart (1)"), true);
		
		//Step Five Update the Shopping Cart
		cartPG.updateShoppingCart();
		//cartQTY = resultsPG.getCartTextPageHeader();
		//Assert.assertEquals(cartQTY.contentEquals("Shopping cart (1)"), true);
		

	}
	
	//User Story 4: remove item from cart
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then remove the item
		//
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_Remove() {

}	
	//User Story 5: estimate shipping
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then perform Estimate Shipping
		//
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_EstimateSHipping() {

}	
	//User Story 6: add to your compare list
		//  GIVEN
		//		Given the shopper is on the Landing page
		//	WHEN
		//		Selecting two items out of same category
		//	THEN
		//		Then you compare items
		//
	public void GIVEN_ShopperOnLandingPage_WHEN_SelectTwoItems_THEN_CompareItems() {

}
	
}
