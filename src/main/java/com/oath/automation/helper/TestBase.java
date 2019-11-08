package com.oath.automation.helper;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.events.EventFiringWebDriver;
	import org.testng.ITestContext;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Listeners;
	import org.testng.annotations.Parameters;

import com.oath.automation.listeners.ListenerTest;
import com.oath.automation.listeners.WebDriverListener;
import com.relevantcodes.extentreports.ExtentReports;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
	
	@Listeners(ListenerTest.class)
	public class TestBase
	{
		
	    private static Logger Log = Logger.getLogger(TestBase.class);
		private WebDriver basedriver;
	    public WebDriver driver;
	    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	  
	    Properties defaultProps = new Properties();

	    public static ExtentReports extent;
	    
	  @Parameters({"browser", "url"})
	  @BeforeTest
	  public void createDriver(String browser, String url) throws MalformedURLException {
		  //setEnvProperties();
		  System.setProperty(ESCAPE_PROPERTY, "false");
		  StringBuffer defaultGrid = new StringBuffer();

		  
		 if(browser.toLowerCase().contains("chrome"))
		  {
			  ChromeOptions options=new ChromeOptions();
			  options.addArguments("disable-infobars");
			  System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver 14");
			  basedriver = new ChromeDriver(options);
		  }

		  EventFiringWebDriver efwd=new EventFiringWebDriver(basedriver);
		  WebDriverListener eventListener=new WebDriverListener(basedriver);
		  efwd.register(eventListener);
		  driver=efwd;
		  
		  driver.manage().window().maximize();
		  driver.manage().deleteAllCookies();
		  Log.info("Opening the Url - "+ url);
		  driver.get(url);
	  }
	  
	  public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  
		                 //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		  String destination = System.getProperty("user.dir") + "\\FailedTestsScreenshots\\"+screenshotName+dateName+".png";
		  File finalDestination = new File(destination);
		  FileUtils.copyFile(source, finalDestination);
		  return destination;
	 }

	  /*protected void setEnvProperties()  {
			String workingDir = System.getProperty("user.dir");
			String defaultConfigPath = workingDir
					+ "//src//test//resources//application.properties";
			Log.info("defaultConfigPath"+defaultConfigPath);
			try {
				defaultProps.load(new FileInputStream(defaultConfigPath));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}*/
	  
	  protected Properties getDefaultConfig() {
		  
		  return defaultProps;
	  }
	
	public WebDriver getDriver()
	  {
		  return driver;
	  }
	  
	@AfterTest(alwaysRun=true)
	public void quitDriver(ITestContext testcontext) {
		  if(driver!=null)
		  {
			  try
			  {
				  driver.close(); 
				  driver.quit();
				  //extent.flush();
				  
			  }
			  catch(WebDriverException e)
			  {
				  System.out.println("**********CAUGHT EXCEPTION IN DRIVER TEAR DOWN**********");
				  System.out.println(e);
			  }
		  }
		 //ListenerTest.writeSauceDataToFile(testcontext);
	}
	}
