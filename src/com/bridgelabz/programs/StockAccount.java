package com.bridgelabz.programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StockAccount {
	public static String filename;
	public StockAccount() {
		
	}
	public StockAccount(String filename) {
		File file=new File(filename);
		try {
			if(file.createNewFile())
				System.out.println("File created successfully");
			else
				System.out.println("File exists");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public double valueOf() {
		return 0;
	}
	public void buy(int amount,String symbol) {
		try {
			FileReader fileReader=new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject stocks=new JSONObject();
		JSONArray sharesArray=new JSONArray();
		
		
	}
	public void sell(int amount,String symbol) {
		
	}
	public void save(String filename) {
		
	}
	public void printReport()  {
		
	}

}
