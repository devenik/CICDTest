package devenik.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import devenik.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extReports = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extReports.createTest(result.getMethod().getMethodName());
		extTest.set(test);//unique thread id
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extTest.get().log(Status.PASS, result.getMethod().getMethodName()+"Tested Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extTest.get().fail(result.getThrowable());
		//Screenshot and attach it to report
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String filepath=null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
	
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extReports.flush();
	}
}
