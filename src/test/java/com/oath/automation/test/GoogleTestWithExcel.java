package com.oath.automation.test;

import static org.testng.AssertJUnit.assertTrue;



import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.oath.automation.helper.TestBase;
import com.oath.automation.pages.GoogleTestPage;
import com.oath.automation.pages.GoogleTestPage2;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.internal.http.Status;

public class GoogleTestWithExcel extends TestBase{
	
	public GoogleTestPage2 homePage1;

	@BeforeClass
	public void setup()  // NUMP0919153859
	{
		//homePage1=new GoogleTestPage(driver);
	}
	
	@Test(priority = 1)
	public void view1() throws Exception {
		homePage1=new GoogleTestPage2(driver);
		assertTrue("Error in Displayig..", homePage1.test());
	}
	
	/*@Test(priority = 2)
	public void view2() throws Exception {
		homePage1.ExcelRead("tyyi");
		assertTrue("Error in Displayig..", homePage1.VerifyExcelRead());
	}
	
	@Test(priority = 3)
	public void view3() throws Exception {
		homePage1.ExcelRead("rgreg");
		assertTrue("Error in Displayig..", homePage1.VerifyExcelRead());
	}*/

	@AfterClass
	public void signOut()
	{
		//manageSubscriptionspage.signOut();
	}
}
