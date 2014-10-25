package com.kic.hrm.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVServiceImpl {
	
	public static List<String[]> ReadCSVFile(Reader reader) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		List<String[]> listData = new ArrayList<String[]>();
		
		
		try {
			br = new BufferedReader(reader);
			while ((line = br.readLine()) != null) {
				String[] tempLine;
				tempLine = line.split(cvsSplitBy);
				//System.out.println(tempLine.toString());
				listData.add(tempLine);	          
			};
			
			System.out.println("Skip header : " + listData.get(0)[0].toString());
			//Remove AC-No. Colum.
			if(listData.get(0)[0].contentEquals("AC-No.")){
				listData.remove(0);
				System.out.println("Skiped Header AC-No.");
			}
				
			//printDataListinArry
			
			System.out.println("CSV Read File is Done.What long data is : " + listData.size());
			return listData;
			//System.out.println("Count : " + country.length);
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("Done,Data is Null.");
		return null;
	}
	
	public static void printDataListinArry(List<String[]> LeaveLog) {
		
		for(int x = 0 ; x < LeaveLog.size() ; x++)
		{
			System.out.print("Line : " + x + "Size : " + LeaveLog.get(x).length);
			for(int y = 0 ; y < LeaveLog.get(x).length; y++)
			{
				System.out.print("	| Value : " + LeaveLog.get(x)[y]);
			}
			System.out.println("");
		}
	}
}
