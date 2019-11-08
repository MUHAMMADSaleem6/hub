package com.oath.automation.test;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
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

public class GoogleTest2 extends TestBase{
	
	public GoogleTestPage homePage1;

	boolean matchFlag = true;	 
	
	@BeforeClass
	public void setup()
	{
			
	}
	 
	@Test(priority = 1)
	public void view1() throws Exception {
		homePage1=new GoogleTestPage(driver);
		assertTrue("Error in Displayig..", homePage1.test());
	}
	
	/*@AfterMethod
	public void result() throws IOException, ParseException {
		//homePage1.comparisonImage();
	File fileInput = new File("C:\\Users\\NagarajanKulasekaran\\Desktop\\SeleniumProject\\SeleniumProject\\target\\surefire-reports\\Screenshots\\passed\\view1-2019-03-29-14-48-08.png");
	File fileOutPut = new File("C:\\Users\\NagarajanKulasekaran\\Desktop\\SeleniumProject\\SeleniumProject\\target\\surefire-reports\\Screenshots\\passed\\view1-2019-03-29-14-30-54 - Copy.png");
 
    BufferedImage bufferfileInput = ImageIO.read(fileInput);
    DataBuffer bufferfileInput1 = bufferfileInput.getData().getDataBuffer();
    int sizefileInput = bufferfileInput1.getSize();
    
    BufferedImage bufferfileOutPut = ImageIO.read(fileOutPut);
    DataBuffer datafileOutPut1 = bufferfileOutPut.getData().getDataBuffer();
    int sizefileOutPut = datafileOutPut1.getSize();
   
    if(sizefileInput == sizefileOutPut) {                         
       for(int i=0; i<sizefileInput; i++) {
             if(bufferfileInput1.getElem(i) != datafileOutPut1.getElem(i)) {
            	 //System.out.println("Done"+ bufferfileInput1.getElem(i));
            	 System.out.println("failed");
                   matchFlag = false;
                   break;
             } 
        }
    }
    else                         
       matchFlag = false;
    Assert.assertTrue(matchFlag, "Images are not same");
    System.out.println("Done");
 }*/

}
