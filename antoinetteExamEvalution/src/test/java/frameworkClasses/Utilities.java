package frameworkClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

public class Utilities extends BasePage {

	//Method : This creates a screen shot
	
	public void takeSnapShot(String fileWithPath) throws IOException {
		//Convert web driver object to takeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		
		//call get Screenshot as a method to create image file
		File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
		
		//move the image file to the new destination
		File DestFile = new File("target//" + "surefire-reports-"+ getAppConfigPropeties("build.timestamp") + "//images" + fileWithPath );
		//copy file
		FileUtils.copyFile(ScrFile, DestFile);
		
		//update the pdf report with the screenshot
		Reporter.log("<a href='"+ DestFile.getAbsolutePath() + "'> <img src='" + DestFile.getAbsolutePath() + "' height='200' with='200'/> </a>");
	}
	
	//Method : Return the time now
	public String timeReturn() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
		return dtf.format(now);
	}
	
	//Method : Get the property values from the app.properties
	public String getAppConfigPropeties(String propertyName) {
		// Properties set

		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(".\\target\\app.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(propertyName);

	}
	
	//Method : Create an empty output file
	
	public void resetOutputFile(String OutputFileName) {
		try {
			FileWriter myObj = new FileWriter(OutputFileName,false);
			
		}
		catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}
	
}
