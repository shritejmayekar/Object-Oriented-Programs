
package com.bridgelabz.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/******************************************************************************

 *  
 *  Purpose: To add stock ,compute total value of stock and display report
 *  			
 *  		
 *  			
 *  			
 *  @author  Shritej
 *  @version 1.0
 *  @since   12-10-2017
 *
 ******************************************************************************/
public class StockPortfolio {
	public  int numberOfShare,totalValueOfStock,numberOfStock,sharePrice;
	public static long totalValue,eachStock;
	public static Object value;
	public  String  stockName;
	public static Scanner scanner=new Scanner(System.in);
	/**
	 * addStock method will add stocks to account
	 * @throws IOException
	 */
	public  void addStock() throws IOException {
		JSONObject stocks=new JSONObject();
		JSONArray shares=new JSONArray();

		System.out.print("Please eneter the no. of stocks:");
		numberOfStock=scanner.nextInt();

		for(int i=0;i<numberOfStock;i++) {
			JSONObject jsonObject=new JSONObject();

			System.out.print("please enter the stock name:");
			stockName=scanner.next();

			jsonObject.put("stockName", stockName);

			System.out.print("please enter the number of share:");
			numberOfShare=scanner.nextInt();

			jsonObject.put("numberOfShare", numberOfShare);

			System.out.print("price of share:");
			sharePrice=scanner.nextInt();

			jsonObject.put("sharePrice", sharePrice);


			shares.add(jsonObject);
		}
		stocks.put("shares",shares);
		FileWriter fileWriter=new FileWriter("stockreport.json");
		fileWriter.write(stocks.toJSONString());
		fileWriter.close();

	}
	/**
	 * valueOfEachStock method will gives the each stock value report
	 * @param numberOfShare
	 * @param sharePrice
	 * @return
	 */
	public static long valueOfEachStock(Object numberOfShare,Object sharePrice) {

		return (long)sharePrice*(long)numberOfShare;
	}
	/**
	 * valueOfTotalStock method will calculates total stock value
	 * @param sharePrice
	 */
	public static void valueOfTotalStock(Object sharePrice) {
		totalValue=(long)sharePrice+totalValue;
	}
	/**
	 * stockReports method show the report of stocks
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void stockReports() throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();

		value=jsonParser.parse(new FileReader("stockreport.json"));
		System.out.println(value);

		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("stockreport.json"));

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


}
