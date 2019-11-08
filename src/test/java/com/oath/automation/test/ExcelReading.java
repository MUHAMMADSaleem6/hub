package com.oath.automation.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
 
public class ExcelReading { 
    public static void main(String[] args) throws Exception {

        String filename = "C:\\Users\\NagarajanKulasekaran\\Desktop\\test.xlsx";
        FileInputStream fis = null;
 
        try {
 
            fis = new FileInputStream(filename);
            @SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<?> rowIter = sheet.rowIterator(); 
 
            while(rowIter.hasNext()){
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<?> cellIter = myRow.cellIterator();
                Vector<String> cellStoreVector=new Vector<String>();
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    String cellvalue = myCell.getStringCellValue();
                    cellStoreVector.addElement(cellvalue);
                }
                String firstcolumnValue = null;
                String secondcolumnValue = null;
 
                int i = 0;
                firstcolumnValue = cellStoreVector.get(i).toString(); 
                secondcolumnValue = cellStoreVector.get(i+1).toString();
 
                insertQuery(firstcolumnValue,secondcolumnValue);
            }
        } catch (IOException e) {
 
            e.printStackTrace();
 
        } finally {
 
            if (fis != null) {
 
                fis.close();
 
            }
        }
    }
 
    private static void insertQuery(String firstcolumnvalue,String secondcolumnvalue) {
 
        System.out.println(firstcolumnvalue +  " "  +secondcolumnvalue);
    }
}