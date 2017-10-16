package com.bridgelabz.programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockAccount {
	public static String filename;
	static long totalValue;
	static long eachStock;
	static Scanner scanner=new Scanner(System.in);
	public StockAccount() {
		
	}
	public StockAccount(String filename) throws FileNotFoundException, IOException, ParseException {
		File file=new File(filename);
		try {
			if(file.createNewFile())
				System.out.println("File created successfully");
			else
				System.out.println("File exists");
			FileWriter fileWriter=new FileWriter(file);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject stocks=new JSONObject();
		JSONArray shares=new JSONArray();
		
		System.out.print("Please eneter the no. of stocks:");
		int numberOfStock = scanner.nextInt();
		
		for(int i=0;i<numberOfStock;i++) {
			JSONObject jsonObject=new JSONObject();
			
			System.out.print("please enter the stock name:");
			String stockName = scanner.next();
			
			jsonObject.put("stockName", stockName);
			
			System.out.print("please enter the number of share:");
			int numberOfShare = scanner.nextInt();
			
			jsonObject.put("numberOfShare", numberOfShare);
			
			System.out.print("price of share:");
			int sharePrice = scanner.nextInt();
			
			jsonObject.put("sharePrice", sharePrice);
			
			
			shares.add(jsonObject);
		}
		stocks.put("shares",shares);
		FileWriter fileWriter=new FileWriter(filename);
		fileWriter.write(stocks.toJSONString());
		fileWriter.close();
		
	}
	public static long valueOfEachStock(Object numberOfShare,Object sharePrice) {
		
		return (long)sharePrice*(long)numberOfShare;
	}
	public static void valueOfTotalStock(Object sharePrice) {
		totalValue=(long)sharePrice+totalValue;
	}
	public double valueOf() {
		return totalValue;
	}
	public void sell(long amount,String symbol) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		
		
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader(filename));
	
		JSONArray jsonArray2= (JSONArray) object.get("shares");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject= (JSONObject) jsonArray2.get(i);
			if(jsonObject.get("stockName").equals(symbol)) {
				long priceOfShare= valueOfEachStock(jsonObject.get("numberOfShare"),jsonObject.get("sharePrice"));
				System.out.println("price of each share:"+jsonObject.get("sharePrice"));
			
				long numberOf=(long) jsonObject.get("numberOfShare");
				long priceOfEach=(long) jsonObject.get("sharePrice");
			
				numberOf=(priceOfShare-amount)/priceOfEach;
				
				JSONObject jsonObject2=new JSONObject();

				jsonObject.replace("numberOfShare",numberOf);
				JSONObject stocks=new JSONObject();
				JSONArray shares=new JSONArray();
				shares.add(jsonObject);
				stocks.put("shares",shares);
				
				FileWriter fileWriter=new FileWriter(filename);
				fileWriter.write(stocks.toJSONString());
				fileWriter.close();
				totalValue=0;
				
				
				break;
				
		
			}
		}
		
		
		
	}
	public void buy(int amount,String symbol) throws IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		
		
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader(filename));
	
		JSONArray jsonArray2= (JSONArray) object.get("shares");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject= (JSONObject) jsonArray2.get(i);
			if(jsonObject.get("stockName").equals(symbol)) {
				long priceOfShare= valueOfEachStock(jsonObject.get("numberOfShare"),jsonObject.get("sharePrice"));
				System.out.println("price of each share:"+jsonObject.get("sharePrice"));
			
				long numberOf=(long) jsonObject.get("numberOfShare");
				long priceOfEach=(long) jsonObject.get("sharePrice");
			//total=quantity*price;
			//(total+x)/price=quantity;
			
				numberOf=(priceOfShare+amount)/priceOfEach;
				
				JSONObject jsonObject2=new JSONObject();

				jsonObject.replace("numberOfShare",numberOf);
				JSONObject stocks=new JSONObject();
				JSONArray shares=new JSONArray();
				shares.add(jsonObject);
				stocks.put("shares",shares);
				
				FileWriter fileWriter=new FileWriter(filename);
				fileWriter.write(stocks.toJSONString());
				fileWriter.close();
				totalValue=0;
				
				
				break;
				
		
			}
		}
		
	}
	public void save(String filename) {
		
	}
	public void printReport() throws FileNotFoundException, IOException, ParseException  {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		Object value;
		value=jsonParser.parse(new FileReader(filename));
		System.out.println(value);
		
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader(filename));
	
		JSONArray jsonArray2= (JSONArray) object.get("shares");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			eachStock=(int) valueOfEachStock(jsonObject.get("numberOfShare"),jsonObject.get("sharePrice"));
			System.out.println("Stock Name:"+jsonObject.get("stockName")+"\n"
					+ "Each stock value ="+eachStock);
			valueOfTotalStock(eachStock);
		}
		System.out.println("Total stock value="+totalValue);
		
	}
		
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		filename="stock.json";
		StockAccount st=new StockAccount(filename);
		st.printReport();
		st.buy(200,"tata");
		st.printReport();
		st.sell(200, "tata");
		st.printReport();
		while(true) {
			System.out.println("\n1.Create file\n2.buy stocks\n3.printreport\n4.sell stock\n5.exit");
		}
	}

}
