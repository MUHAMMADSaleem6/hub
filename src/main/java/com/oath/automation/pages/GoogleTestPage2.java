package com.oath.automation.pages;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.oath.automation.helper.UtilClass;

public class GoogleTestPage2 extends UtilClass{
	
	private static Logger Log = Logger.getLogger(GoogleTestPage2.class);
	private WebDriver driver;
	private WebDriverWait wait;

    List<String> sList = new ArrayList<String>();

	@FindBy(xpath = "//input[@title='Search']")
    private WebElement btnSearch;

	By btnSearchSubmit= By.xpath("(//input[@value='Google Search'])[2]");
	
	String btnSearchSubmit1 = "t(//input[@value='Google Search'])[2]";
	
	//Constructor
		public GoogleTestPage2(WebDriver driver) {
			Log.info("Home Page constructor is Invoked");
			this.driver = driver;
			//driver.get("https://www.irctc.co.in/nget/train-search");
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
		
		public void ExcelRead(String inputValue) throws EncryptedDocumentException, InvalidFormatException, IOException {
			Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\NagarajanKulasekaran\\Desktop\\test.xlsx"));
	        Sheet sheet = workbook.getSheet("Sheet1");
	        DataFormatter dataFormatter = new DataFormatter();
	        //List<String> sList = new ArrayList<String>();
	        Iterator<Row> rowIterator = sheet.rowIterator();
	        
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	           
	            if(row.getRowNum()==0)
	                continue;
	            
	            Iterator<Cell> cellIterator = row.cellIterator();

	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                String cellValue = dataFormatter.formatCellValue(cell);
	                if(cellValue.matches(inputValue)) {
	                //if(cell.getStringCellValue().matches(inputValue)) {
	                    while (cellIterator.hasNext()) {
	                        cell = cellIterator.next();
	                        cellValue = dataFormatter.formatCellValue(cell);
	                        sList.add(cellValue);
	                    }
	                }
	            	
	            }
	        }
	        workbook.close();

	        System.out.println("Total Element Count : " + sList.size());
	        
	        //int size = driver.findElements(By.xpath("")).size();
	        
	        /*for(int i=0; i<=size; i++) {
	        	System.out.println("Total Element Count : " + sList.get(i));
	        }*/
	        	
		}
		
		public boolean VerifyExcelRead() {
			for (String list : sList) {
	            System.out.println(list.trim()+ "\t");
	        }
			
			sList.removeAll(sList);
			
			System.out.println("Total Element Count after REMOVE: " + sList.size());
			
			return true;
		}

}