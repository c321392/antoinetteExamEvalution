package testsDemoWebShop;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import frameworkClasses.ReadExcel;
import pageObjectDemoWebShop.BasePageDemoWebShop;
import pageObjectDemoWebShop.ResultsPage;

public class TestsDemoWebShop {
	
	//Instantiate Pages
	ReadExcel rExcel = new ReadExcel();
	BasePageDemoWebShop basePG = new BasePageDemoWebShop();
	ResultsPage resultsPG = new ResultsPage();
	
	
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
	}
	
	//User Story 2: browse using the categories list
		//  GIVEN
		//		Given the shopper is on the Landing page
		//	WHEN
		//		Using the Categories List
		//	THEN
		//		The selected category page will be displayed
		//
	public void GIVEN_ShopperOnLandingPage_WHEN_CategoriesList_THEN_SelectedCategoryPageDisplayed() {
	
}
	
	//User Story 3: go to cart and update quantity
		//  GIVEN
		//		Given the cart is not empty
		//	WHEN
		//		Go to cart
		//	THEN
		//		Then update the quantity
		//
	public void GIVEN_CartnotEmpty_WHEN_GoToCart_THEN_UpdateQty() {

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
