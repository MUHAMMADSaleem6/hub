package com.oath.automation.test;

import static org.testng.AssertJUnit.assertTrue;



import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.oath.automation.helper.TestBase;
import com.oath.automation.pages.GoogleTestPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.internal.http.Status;

public class GoogleTest extends TestBase{
	
	public static ExtentTest test1;
	
	public GoogleTestPage homePage1;
	
	@BeforeClass
	public void setup()
	{
		//homePage1=new GoogleTestPage(driver);
	}
	
	@Test(priority = 1)
	public void view1() throws Exception {
		homePage1=new GoogleTestPage(driver);
		test1 = extent.startTest("view1", "view1");
		assertTrue("Error in Displayig..", homePage1.test());
		test1.log(LogStatus.PASS, "Passed");
				//MarkupHelper.createLabel("Tet Case Passed is passTest", ExtentColor.GREEN));
		
	}
	
	@Test(priority = 2)
	public void view2() throws Exception {
		test1 = extent.startTest("view2", "view2");
		assertTrue("Error in Displayig..", homePage1.test2());
		test1.log(LogStatus.PASS, "Passed");
	}
	
	@Test(priority = 3)
	public void view3() throws Exception {
		test1 = extent.startTest("view3", "view3");
		assertTrue("Error in Displayig..", homePage1.test3());
		test1.log(LogStatus.PASS, "Passed");
	}
	
	@AfterMethod
	 public void getResult(ITestResult result) throws Exception{
	 if(result.getStatus() == ITestResult.FAILURE){
		 test1.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		 test1.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		 String screenshotPath = TestBase.getScreenshot(driver, result.getName());
		 System.out.println("PATH is : "  + screenshotPath);
		// test1.addBase64ScreenShot(       
		// test1.addScreenCaptureFromPath(screenshotPath);
		 //test1.log(Status.FAIL, "Snapshot below: " + test1.addScreenCaptureFromPath(screenshotPath));
		 test1.log(LogStatus.FAIL, "Snapshot below: " + test1.addScreenCapture(screenshotPath));
		 //MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath);
		 
	 }else if(result.getStatus() == ITestResult.SKIP){
		 test1.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
	 }
		System.out.println("Status.PASS");
		
	}

	@AfterClass
	public void signOut()
	{
		//manageSubscriptionspage.signOut();
	}
}
