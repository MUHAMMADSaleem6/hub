package com.oath.automation.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilClass {
	
    private static Logger Log = Logger.getLogger(UtilClass.class);
	private WebDriverWait wait;
	protected static XSSFSheet ExcelWSheet;
	protected static XSSFWorkbook ExcelWBook;
	protected static XSSFRow Row;
	protected static XSSFCell Cell;
	
	public void clickElement(WebDriver driver, WebElement element)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void clickElementByLocator(WebDriver driver, By bylocator)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		wait.until(ExpectedConditions.elementToBeClickable(bylocator));
		//driver.findElement(bylocator).click();
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(bylocator)).click().build().perform();
	}
	
	public void mouseoverElementAndClick(WebDriver driver, WebElement element, By locator)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions a = new Actions(driver);
		a.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		a.moveToElement(driver.findElement(locator)).click().build().perform();
	}
	
	public String getElementText(WebDriver driver, WebElement element)
	{
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}
	
	public void selectByValue(WebDriver driver, WebElement element, String value)
	{
		new Select(element).selectByVisibleText(value);
	}
	
	public void selectByIndex(WebDriver driver, WebElement element, int value)
	{
		new Select(element).selectByIndex(value);
	}
	
	public void setText(WebDriver driver, WebElement element, String text)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}
	
	public void sleepinSeconds(int sec)
	{
		try
		{
			Log.info("Sleeping for "+sec+" seconds");
			Thread.sleep(sec * 1000);
		}
		catch(Exception e)
		{
			Log.info("Problem in sleep");
		}
	}

	public void waitForSpinner(WebDriver driver)
	{
		try
		{
			wait = new WebDriverWait(driver,20);
			By spinner=By.xpath("//span[@id='billing-please-wait']/img");
			wait.until(ExpectedConditions.visibilityOfElementLocated(spinner));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
		}
		catch(Exception e)
		{
			//No spinner
		}
	}
	
	protected boolean isAtleastOneElementDisplayed(WebDriver driver, By by)
	{
		List<WebElement> elements = driver.findElements(by);
		for(WebElement element:elements)
		{
			if(element.isDisplayed())
				return true;
		}
		return false;
	}
	
	protected boolean isAtleastOneElementDisplayed(WebDriver driver, List<WebElement> elements)
	{
		for(WebElement element:elements)
		{
			if(element.isDisplayed())
				return true;
		}
		return false;
	}
	
	public void waitForSpinner1(WebDriver driver)
	{
		By spinner=By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForSpin(driver, spinner, 1);
	}
	
	public boolean waitForSpin(WebDriver driver, By waitspinnerloc, int maxwaitminutes)
	{
		boolean iswaitingComplete = false;
		int sleepTimeSeconds = 3;
		int maxNumberofTrials = maxwaitminutes*60/sleepTimeSeconds;
		Log.info("Waiting for loading bar/spinner ...");
		Log.debug("Sleeping... maxTimeout="+ maxwaitminutes +" minutes.");
		try
		{
			for(int i=0; !iswaitingComplete && i< maxNumberofTrials; i++)
			{
				try{
					Thread.sleep(sleepTimeSeconds);
				}
				catch(Exception e) {
				}
			
				try {
					iswaitingComplete = !this.isAtleastOneElementDisplayed(driver, waitspinnerloc);
				} catch(StaleElementReferenceException e){
					iswaitingComplete = !this.isAtleastOneElementDisplayed(driver, waitspinnerloc);
				} catch(Exception e) {
					Log.info("Failed to find atleast one element");
				}
			}
		}
		catch(StaleElementReferenceException e)
		{
			iswaitingComplete = true;
		}
		if(!iswaitingComplete)
		{
			Log.warn("Waited for " + maxwaitminutes + " minutes. But still the operation/load is in progress.");
			return false;
		}
		Log.info("Loading Spinner is closed.");
		return true;
	}
		
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   
	   Object[][] tabArray = null;
	   try {
		   FileInputStream ExcelFile = new FileInputStream(FilePath);
		   ExcelWBook = new XSSFWorkbook(ExcelFile);
		   ExcelWSheet = ExcelWBook.getSheet(SheetName);
		   int startRow = 1;
		   int startCol = 1;
		   int ci,cj;
		   int totalRows = ExcelWSheet.getLastRowNum();
		   Row = ExcelWSheet.getRow(0);
		   int totalCols = Row.getLastCellNum();
		   tabArray=new String[totalRows][totalCols];
		   ci=0;
		   for (int i=startRow;i<=totalRows;i++, ci++) {
			  cj=0;
			   for (int j=startCol-1;j<totalCols;j++, cj++){
				   tabArray[ci][cj]=getCellData(i,j);
				   System.out.println(tabArray[ci][cj]);  
					}
				}
			}
		catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
			}
		catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
			}
		return(tabArray);
		}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			@SuppressWarnings("deprecation")
			//int dataType = Cell.getCellType();
			int dataType=1;
			if  (dataType == 3) {
				return "";
			}else{
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		}
			catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);
			}
	}
}