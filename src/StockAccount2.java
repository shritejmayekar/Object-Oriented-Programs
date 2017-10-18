

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

public class StockAccount2 {
	public static String filename;
	static long totalValue;
	static long eachStock;
	static StockAccount2 stock;
	static Scanner scanner=new Scanner(System.in);
	
	public void create(String filename) throws FileNotFoundException, IOException, ParseException {
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
		
		
		
			JSONObject jsonObject=new JSONObject();
			
			
			
			jsonObject.put("stockName", filename);
			
			System.out.print("please enter the number of share:");
			int numberOfShare = scanner.nextInt();
			
			jsonObject.put("numberOfShare", numberOfShare);
			
			System.out.print("price of share:");
			int sharePrice = scanner.nextInt();
			
			jsonObject.put("sharePrice", sharePrice);
			
			
			shares.add(jsonObject);
		
		stocks.put("shares",shares);
		FileWriter fileWriter=new FileWriter(filename);
		fileWriter.write(stocks.toJSONString());
		fileWriter.close();
		
	}
	public void buy(int amount,String symbol) throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonObject=new JSONObject();
		JSONParser jsonParser=new JSONParser();
		JSONArray jsonArray=new JSONArray();
		jsonObject=(JSONObject) jsonParser.parse(new FileReader(symbol));
		jsonArray=(JSONArray) jsonObject.get("share");
		for(int i=0;i<jsonArray.size();i++) {
			jsonObject=(JSONObject) jsonArray.get(i);
		}
		
		
		
	}
	public void readAndPut(long shareAfter,String filename) {
		
	}
	public static void main(String args[]) {
		StockAccount2 stockAccount2=new StockAccount2();
		
		
	}
}
		
		
		