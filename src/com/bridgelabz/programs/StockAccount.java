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
	static StockAccount stock;
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
				if(priceOfShare<amount){
					System.out.println("not enough shares to sell");
					return;
				}

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
		long numberOfSharePrevious=0,numberOf=0;
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();

		JSONObject symobolObject=(JSONObject) jsonParser.parse(new FileReader(symbol));
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader(filename));

		JSONArray jsonArray2= (JSONArray) object.get("shares");
		JSONArray jsonArrayForSymbol=(JSONArray) symobolObject.get("shares");
		for(int j=0;j<jsonArrayForSymbol.size();j++) {
			jsonObject=(JSONObject) jsonArrayForSymbol.get(j);
			if(jsonObject.get("stockName").equals(filename)) {
				numberOfSharePrevious=(long) jsonObject.get("numberOfShare");
				break;
			}
		}


		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject= (JSONObject) jsonArray2.get(i);
			if(jsonObject.get("stockName").equals(symbol)) {
				long priceOfShare= valueOfEachStock(jsonObject.get("numberOfShare"),jsonObject.get("sharePrice"));
				System.out.println("price of each share:"+jsonObject.get("sharePrice"));
				long numberOfShareBefore=(long) jsonObject.get("numberOfShare");
				numberOf=(long) jsonObject.get("numberOfShare");
				long priceOfEach=(long) jsonObject.get("sharePrice");

				numberOf=amount/priceOfEach;

				JSONObject jsonObject2=new JSONObject();

				jsonObject.replace("numberOfShare",numberOfShareBefore-numberOf);
				JSONObject stocks=new JSONObject();
				JSONArray shares=new JSONArray();
				shares.add(jsonObject);
				stocks.put("shares",shares);

				FileWriter fileWriter=new FileWriter(symbol);
				fileWriter.write(stocks.toJSONString());
				fileWriter.close();
				totalValue=0;


				break;


			}
			jsonObject.replace("numberOfShare",numberOfSharePrevious+numberOf);
			JSONObject stocks=new JSONObject();
			JSONArray shares=new JSONArray();
			shares.add(jsonObject);
			stocks.put("shares",shares);

			FileWriter fileWriter=new FileWriter(filename);
			fileWriter.write(stocks.toJSONString());
			fileWriter.close();
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

		System.out.println("Please enter the filename below \n1.NSE\n2.Nifty\n3.Sensex\n4.create new");
		switch (scanner.nextInt()) {
		case 1:System.out.println("NSE");
		filename="NSE.json";
		stock=new StockAccount();


		break;
		case 2:System.out.println("Nifty");
		filename="Nifty.json";
		stock=new StockAccount();
		break;
		case 3:System.out.println("Nifty");
		filename="Sensex.json";
		stock=new StockAccount();
		break;
		case 4:System.out.println("create new");
		filename=scanner.next();
		stock=new StockAccount(filename);
		break;

		default:System.out.println("invalid");
		break;
		}





		stock.printReport();
		while(true) {
			System.out.println("\n1.buy stocks\n2.sell\n3.stock report\n4.exit");
			switch (scanner.nextInt()) {
			case 1:System.out.println("Enter the amount and sysmbol");
			stock.buy(scanner.nextInt(), scanner.next());				
			break;
			case 2:	System.out.println("Enter the amount and symbol");
			stock.sell(scanner.nextLong(), scanner.next());
			break;
			case 3:	System.out.println("stock report");
			stock.printReport();
			break;
			case 4:System.exit(0);
			break;
			default:
				break;
			}
		}
	}

}
