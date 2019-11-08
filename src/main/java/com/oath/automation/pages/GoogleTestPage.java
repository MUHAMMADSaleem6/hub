package com.oath.automation.pages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.oath.automation.helper.UtilClass;

public class GoogleTestPage extends UtilClass{
	
	private static Logger Log = Logger.getLogger(GoogleTestPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//input[@title='Search']")
    private WebElement btnSearch;

	By btnSearchSubmit= By.xpath("(//input[@value='Google Search'])[2]");
	
	String btnSearchSubmit1 = "t(//input[@value='Google Search'])[2]";
	
	//Constructor
		public GoogleTestPage(WebDriver driver) {
			Log.info("Home Page constructor is Invoked");
			this.driver = driver;
			wait = new WebDriverWait(driver, 30);
			PageFactory.initElements(driver, this);
			wait.until(ExpectedConditions.titleContains("Google"));
		}
		
		public boolean test() throws ParseException {
			try {
				  Log.info("came try");
				  wait.until(ExpectedConditions.visibilityOf(btnSearch));
				  return wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).isDisplayed();
			}catch (Exception e) {
				return false;
			}
		}
		
		/*public boolean test() throws ParseException {
			try {
				  Log.info("came try");

				  String btnSearchSubmit11 = "";
				  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(btnSearchSubmit11)));
				  //wait.until(ExpectedConditions.visibilityOfElementLocated(btnSearchSubmit));
				  int firstSundayCount =  driver.findElements(By.xpath(btnSearchSubmit11)).size();
				  
				  if (firstSundayCount > 0 && firstSundayCount > 0 && firstSundayCount > 0)
					  return true;
			}catch (Exception e) {
				return false;
			}
			
			//return false;
		}*/
		
		public boolean test2() throws ParseException {
			try {
				Log.info("done..");
				return wait.until(ExpectedConditions.visibilityOfElementLocated(btnSearchSubmit)).isDisplayed();
			}catch (Exception e) {
				  return false;
			}
		}
		
		public boolean test3() throws ParseException {
			try {
				Log.info("Test 3 ....");
				return driver.findElement(By.xpath(btnSearchSubmit1)).isDisplayed();
			}catch (Exception e) {
				  return false;
			}
		}
		
		public String comparisonImage() throws ParseException, IOException {
			WebElement btnGoogleImage = driver.findElement(By.xpath("//img[@alt='Google']"));
		    
			org.openqa.selenium.Point elementPoint = btnGoogleImage.getLocation();
			 //int coordX = elementPoint.getX();
			 //int coordY = elementPoint.getY();
			 int elemEntWidth = btnGoogleImage.getSize().getWidth();
			 int elementHeight = btnGoogleImage.getSize().getHeight();
			 BufferedImage buffImg = null;
			
			//File fileInput = new File("C:\\Users\\NagarajanKulasekaran\\Desktop\\SeleniumProject\\SeleniumProject\\target\\surefire-reports\\Screenshots\\passed\\view1-2019-03-29-14-48-08.png");
		    File fileOutPut = new File("C:\\Users\\NagarajanKulasekaran\\Desktop\\SeleniumProject\\SeleniumProject\\target\\surefire-reports\\Screenshots\\passed\\test.png");

		    try {
			    buffImg = ImageIO.read(fileOutPut);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		    
		    //JavascriptExecutor js = (JavascriptExecutor) driver;
		    //js.executeScript("arguments[0].style.visibility='hidden'", btnGoogleImage);
		 
		    //Graphics2D graphics = buffImg.createGraphics();
		    //graphics.setColor(new Color(255, 255, 255));
		    //graphics.fillRect(elementPoint.getX(), elementPoint.getY(), elemEntWidth, elementHeight);
		    
		    ImageIO.write(buffImg, "png", fileOutPut);
			return btnSearchSubmit1;
			
		}

}