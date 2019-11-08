package com.oath.automation.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

import com.opencsv.CSVReader;


public class CSVReader1 {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		System.out.println("Total" + System.getProperty("user.dir"));
		CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"test21.csv"));
		List<String> sList = new ArrayList<String>();
				//CSVReader reader = new CSVReader(new FileReader("C:\\Users\\user\\Desktop\\test31.csv"));
				List<String[]> li=reader.readAll();
				System.out.println("Total rows which we have is "+li.size());

				Iterator<String[]>i1 = li.iterator();

				while(i1.hasNext()){
										String[] str=i1.next();
										if(str[0].matches("test 12")) {
											System.out.println("number : " +str.length);
											 for(int i=0; i<str.length; i++) {
												 //System.out.println(str[i]);
												 sList.add(str[i]);
											 }
										}
				}
				
				reader.close();
				
				/*URL url = new URL("https://help.aol.com/");
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.connect();*/

/*				int code = connection.getResponseCode();
				if(code == 2003) {
					System.out.println("Server Response Code : " + code);
				} else
					System.out.println("Fail....");*/

        for (String list : sList) {
            System.out.print(list.trim()+"\t");
            //System.out.println();
        }
}
}
