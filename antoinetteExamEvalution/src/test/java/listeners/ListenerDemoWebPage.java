package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import frameworkClasses.BasePage;
import frameworkClasses.Utilities;

public class ListenerDemoWebPage extends BasePage implements ITestListener {

	Utilities uts = new Utilities();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		//when this method is triggered take a screenshot
		try {
			uts.takeSnapShot("On test Success" + uts.timeReturn() + ".png");
			System.out.println("On test Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		//when this method is triggered take a screenshot
		try {
			uts.takeSnapShot("On test failure" + uts.timeReturn() + ".png");
			System.out.println("On test Failure");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		cleanUp();
	}


}
