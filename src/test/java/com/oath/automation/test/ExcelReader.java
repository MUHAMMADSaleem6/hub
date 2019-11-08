package com.oath.automation.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		//Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\NagarajanKulasekaran\\Desktop\\test.xlsx"));
		
		Workbook workbook = WorkbookFactory.create(new File("C:"+ File.separator + "Users" + File.separator + "NagarajanKulasekaran" + File.separator + "Desktop" + File.separator + "test.xlsx"));
		// Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        Sheet sheet = workbook.getSheet("test123");

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        
        List<String> sList = new ArrayList<String>();
        
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
           
            if(row.getRowNum()==0)
                continue;
            
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                sList.add(cellValue);
                System.out.print(cellValue + "\t");
            }
            	System.out.println();
        }
       
        // Closing the workbook
        workbook.close();
        System.out.println("Total Element Count : " +sList.size());
        System.out.println("Element at 7 position : "+sList.get(7));
        System.out.println();
        
        for (String list : sList) {
            System.out.print(list+ "\t");
            //System.out.println();
        }
}
}
