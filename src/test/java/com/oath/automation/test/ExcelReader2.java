package com.oath.automation.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ExcelReader2 {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		// TODO Auto-generated method stub
		//System.out.println("test");
		
/*		String str = "4/26/2019";
		String output = str.substring(0,str.indexOf("/", str.indexOf("/")+1));
        
		System.out.println(output);
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		 
		String dateInString = "07/06/2013";
		DateTime dateTime = DateTime.parse(dateInString, formatter);
		
		System.out.println(dateTime);*/
		
		String inputDateString = "4/06/2019";
		
		SimpleDateFormat existingFormat = new SimpleDateFormat("M/dd/yyyy");
		SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		try {

		    String reformattedDateString = newFormat.format(existingFormat.parse(inputDateString));
		    System.out.println(reformattedDateString);
		
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		
}
}
