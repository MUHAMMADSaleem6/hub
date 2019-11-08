package com.oath.automation.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.oath.automation.helper.TestBase;

public class ListenerTest implements ISuiteListener, ITestListener, IInvokedMethodListener {
	
    private static Logger Log = Logger.getLogger(ListenerTest.class);
    public String suitename;
    
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite arg0) {
		Log.info(arg0.getName() + "---Test Suite Completed---\n");		
	}

	@Override
	public void onStart(ISuite arg0) {
		suitename = arg0.getName();
		Log.info(arg0.getName() + "---Test Suite starting---\n");		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		Log.info(tr.getName() + "---Test method failed---\n");
    	try {
			takeScreenShot(tr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	      
	}
	
	public void takeScreenShot(ITestResult result) throws InterruptedException {
    	//get the driver
		Object currentclass=result.getMethod().getInstance();
    	WebDriver driver=((TestBase) currentclass).getDriver();

		String status="failed";
		if(result.isSuccess())
		{
			status="passed";
		}
    	String methodName=result.getName().toString().trim();
    	
    	Thread.sleep(10000);
    	//JavascriptExecutor js = (JavascriptExecutor) driver;
    	//js.executeScript("document.getElementById('searchform').style.visibility = 'hidden'");
    	
     	WebElement btnGoogleImage = driver.findElement(By.xpath("(//input[@value='Google Search'])[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.visibility='hidden'", btnGoogleImage);
    	
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            	String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            	String workingDir1 = System.getProperty("JOB_URL");// + "\\target\\surefire-reports\\html";
            	//Log.info("jenkins url"+ workingDir1);
            	//String workingDir = System.getProperty("user.dir");
            	String workingDir = System.getProperty("user.dir");// + "\\target\\surefire-reports\\html";
            	String path=workingDir+"\\target\\surefire-reports\\Screenshots\\"+status+"\\"+methodName+"-"+timeStamp+".png";
            	//Log.info(path);
            	String url="https://ci.obi.aol.com/cc/jenkins/view/OMP-AUTOMATION/job/omp_purchase_cancel_automation/ws/"+"target/surefire-reports/Screenshots/"+status+"/"+methodName+"-"+timeStamp+".png";
            //	String url="https://ci.obi.aol.com/cc/jenkins/view/yahoo-ui-regression/ws/"+"target/surefire-reports/Screenshots/"+status+"/"+methodName+"-"+timeStamp+".png";
            	if(suitename.contains("Regression"))
            	{
                	 url="https://ci.obi.aol.com/cc/jenkins/job/yahoo-ui-regression/ws/"+"target/surefire-reports/Screenshots/"+status+"/"+methodName+"-"+timeStamp+".png";    		
            	}
            	//Log.info("JOB_URL"+url);
            	//https://ci.obi.aol.com/cc/jenkins/view/OMP-AUTOMATION/job/omp_purchase_cancel_automation/ws/
            	//String path=JOB_URL+"/target/surefire-reports/Screenshots/"+status+"/"+methodName+"-"+timeStamp+".png";
            	//String path=JOB_URL+"/target/surefire-reports/Screenshots/"+status+"/"+methodName+"-"+timeStamp+".png";
            	
            	
            	//WebElement btnGoogleImage = driver.findElement(By.xpath("(//input[@value='Google Search'])[2]"));
    		    //JavascriptExecutor js = (JavascriptExecutor) driver;
    		    //js.executeScript("arguments[0].style.visibility='hidden'", btnGoogleImage);
    		    
            	//((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", btnGoogleImage);
    		    

    		    
				FileUtils.copyFile(scrFile, new File(path));
				//FileUtils.copyFile(scrFile, new File(workingDir+"\\Screenshots\\"+status+"\\"+methodName+"-"+timeStamp+".png"));
				//String html = "<div style=\"height:450px;width:800px;overflow:scroll\"><a target='_blank' href='" + path + "'> <img src=\""+ path +"\"></a></div>";
				//Reporter.log("screenshot for " + path + " JOB_URL=" + driver.getCurrentUrl() + html, true);
				String html = "<div style=\"height:450px;width:800px;overflow:scroll\"><a target='_blank' href='" + url + "'> <img src=\""+ url +"\"></a></div>";
				//Reporter.log("screenshot for " + url + " url=" + driver.getCurrentUrl() + html, true);
				//Reporter.log("screenshot for " + path + " url=" + driver.getCurrentUrl() + html, true);
				//Log.info(new File(JOB_URL+"/Screenshots/"+status+"/"+methodName+"-"+timeStamp+".png"));
				
		} catch (IOException e) {
				e.printStackTrace();
		}
    }

	@Override
	public void onTestSkipped(ITestResult tr) {
		Log.info(tr.getName() + "---Test method blocked---\n");	
	}

	@Override
	public void onTestStart(ITestResult tr) {
		Log.info(tr.getName() + "---Test method starting---\n");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		Log.info(tr.getName() + "---Test method passed---\n");
    	try {
			takeScreenShot(tr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates .dat file with job name and url to access video for job
	 *
	 * @param tr
	 */
	/*public static void writeSauceDataToFile(ITestContext tr) {

		//String fileName = mainProps.getProperty("SAUCE_RECORDS_FILE", "SauceRecords.dat");
		//Random r= new Random();
		//int a=r.nextInt();
		//String b= String.valueOf(a);
		//String fileName = b+"SauceRecords.dat";
		String fileName = "target/SauceRecords.dat";
		File file = new File(fileName);
		//ITestContext testContext = tr.getTestContext();

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			else {
				file.delete();
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile()); //, true);
			BufferedWriter sauceDatFile = new BufferedWriter(fw);

			String testName = tr.getName();
			sauceDatFile.write("job = \""+ testName +"\"\n");

			String sessionId = (String) tr.getAttribute("sessionId");
			String authKey = (String) tr.getAttribute("sauceAnonAuthKey");
			String url = getSauceUrl(sessionId, authKey);
			
			Log.debug("Writing SauceLabs data for '"+ testName +"' to '"+ fileName +"': "+ url);
			sauceDatFile.write("url = \""+ url +"\"\n");

			sauceDatFile.close();
			writeHtmlVideoFile(testName, url);
		} catch (IOException e) {
			Log.error("Unable to open file for writing: " + fileName + ", " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	 private static final String htmlPrefix = "<!DOCTYPE html>\n"
		  		+ "<html lang='en'>\n"
		  		+ "  <head>\n"
		  		+ "    <meta charset='utf-8'>\n"
		  		+ "    <title>Video of Test Result</title>\n"
		  		+ "  </head>\n"
		  		+ "  <body>\n";
	
	 private static final String htmlSuffix = "  </body>\n"
		  		+ "</html>";

	/**
	 * Creates an html version of the SauceLabs.dat file for jenkins html report publisher
	 * @param testName
	 * @param url
	 */
	/*public static void writeHtmlVideoFile(String testName, String url) {

		String fileName = "target/SauceReports.html";
		File file = new File(fileName);
		Log.debug("Writing to '"+ fileName +"' for test '"+ testName +"': " + url);
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			else {
				file.delete();
				file.createNewFile();
			}
			 decided to blow away previous file because we're only interested in seeing
			 * the failure 
			FileWriter fw = new FileWriter(file.getAbsoluteFile()); //, true);
			BufferedWriter htmlReportFile = new BufferedWriter(fw);

			htmlReportFile.write(htmlPrefix);

			 write job name 
			htmlReportFile.write("    <h2>"+ testName +"</h2>\n");

			 write video url 
			htmlReportFile.write("    <script src='"+ url +"'></script>\n");

			htmlReportFile.write(htmlSuffix);

			htmlReportFile.close();
		} catch (IOException e) {
			Log.error("Unable to open file for writing: " + fileName + ", " + e.getMessage());
			e.printStackTrace();
		}
	}*/
	
	private static String getSauceUrl(String jobId, String anonymousAuthKey) {
		String base = "https://saucelabs.com/video-embed/";
		return base + jobId + ".js?auth=" + anonymousAuthKey;
	}
}