package com.oath.automation.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class NewExcelReader {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\NagarajanKulasekaran\\Desktop\\test.xlsx"));
		
		// Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        Sheet sheet = workbook.getSheet("Sheet1");
        
        System.out.println("No. of rows.. " + (sheet.getPhysicalNumberOfRows() - 1));

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        List<String> sList = new ArrayList<String>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        boolean status = false;
        while (rowIterator.hasNext() && status==false) {
            Row row = rowIterator.next();
           
            if(row.getRowNum()==0)
                continue;
            
            Iterator<Cell> cellIterator = row.cellIterator();
            //System.out.println("Text 3:" + row.getFirstCellNum());

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println("Text :" + cell.getStringCellValue());
                if(cell.getStringCellValue().matches("Test2")) {
                    while (cellIterator.hasNext()) {
                        //Cell cell1 = cellIterator.next();
                        cell = cellIterator.next();
                        String cellValue = dataFormatter.formatCellValue(cell);
                        sList.add(cellValue);
                        status = true;
                    }
                    //break;
                }
               break;
            }
        }

        // Closing the workbook
        workbook.close();
        System.out.println("Total Element Count : " + sList.size());
        
        String[] test;
        
        for (String list : sList) {
            System.out.print(list.trim()+ "\t");
        }
        
        //System.out.println();
        //System.out.println("I part : "+ sList.get(0));
        //test = sList.get(0).split("-");
        //System.out.println("I part : " + test[0].trim());
        //System.out.println("II part : " + test[1].trim());
	}
}
