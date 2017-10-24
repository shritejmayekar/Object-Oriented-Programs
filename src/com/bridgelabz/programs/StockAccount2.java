package com.bridgelabz.programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.util.List;
import com.bridgelabz.util.Queue;
import com.bridgelabz.util.Stack;
/***********************************************************************
 * Purpose: To 
 * 
 * @author shritej
 * @version 1.0
 * @since 18-10-2017
 *************************************************************************
 */
public class StockAccount2 {
	/*Declaration of variables */
	public static String filename,userFile,brokerFile,dateTime;
	public static long totalValue;
	public static long eachStock;
	public static StockAccount2 stock;
	public static Scanner scanner=new Scanner(System.in);
	public static List<String> list=new List<>();
	public static Stack<Long> stack=new Stack<>();
	public static Queue<String> queue=new Queue<>();
	/**
	 * Account method will create user and broker file
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void Account(String filename) throws FileNotFoundException, IOException, ParseException {
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

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime(); 
		dateTime= dateFormat.format(today);
		jsonObject.put("dateTime",dateTime);

		jsonObject.put("transactionType", "none");
		shares.add(jsonObject);

		stocks.put("shares",shares);
		FileWriter fileWriter=new FileWriter(filename);
		fileWriter.write(stocks.toJSONString());
		fileWriter.close();

	}
	/**
	 * valueOfEachStock method will give total of number of share and shareprice
	 * @param numberOfShare
	 * @param sharePrice
	 * @return total amount
	 */
	public static long valueOfEachStock(Object numberOfShare,Object sharePrice) {

		return (long)sharePrice*(long)numberOfShare;
	}
	/**
	 * valueOfstock will calculates users and broker total amount
	 * @param sharePrice
	 */
	public static void valueOfTotalStock(Object sharePrice) {
		totalValue=(long)sharePrice+totalValue;
	}
	/**
	 * valueOf method returns totalValue
	 * @return
	 */
	public double valueOf() {
		return totalValue*68;
	}
	/**
	 * sell method will sell user sales to broker
	 * @param amount
	 * @param symbol
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public void sell(int amount,String broker,String user) throws IOException, ParseException {
		long numberOfSharePrevious=0,numberOf=0;
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		JSONObject jsonObject2Put=new JSONObject();
		//JSON Objects of broker and user
		JSONObject brokerObject=(JSONObject) jsonParser.parse(new FileReader(broker));
		JSONObject userobject=(JSONObject) jsonParser.parse(new FileReader(user));
		//JSON Array of broker and user
		JSONArray jsonArrayUser= (JSONArray) userobject.get("shares");
		JSONArray jsonArrayForBroker=(JSONArray)brokerObject.get("shares");

		for(int i=0;i<jsonArrayForBroker.size();i++) {
			jsonObject= (JSONObject) jsonArrayForBroker.get(i);
			jsonObject2Put= (JSONObject) jsonArrayUser.get(i);
			if(jsonObject.get("stockName").equals(broker)) {
				long priceOfShare= valueOfEachStock(jsonObject.get("numberOfShare"),jsonObject.get("sharePrice"));
				System.out.println("price of each share:"+jsonObject.get("sharePrice"));
				long numberOfShareBefore=(long) jsonObject.get("numberOfShare");
				numberOf=(long) jsonObject.get("numberOfShare");
				long priceOfEach=(long) jsonObject.get("sharePrice");

				numberOf=amount/priceOfEach;
				if(amount>priceOfShare) {
					System.out.println("impossible");
					return;
				}

				JSONObject jsonObject2=new JSONObject();
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date today = Calendar.getInstance().getTime(); 
				dateTime= dateFormat.format(today);
				jsonObject.replace("dateTime",dateTime);
				jsonObject.replace("transactionType", "buy");
				list.insertAtEnd(broker);
				list.insertAtEnd("buy");
				list.insertAtEnd(dateTime);
				stack.push(numberOf);
				queue.Enqueue(dateTime);

				jsonObject.replace("numberOfShare",numberOfShareBefore+numberOf);
				JSONObject stocks=new JSONObject();
				JSONArray shares=new JSONArray();
				shares.add(jsonObject);
				stocks.put("shares",shares);

				FileWriter fileWriter=new FileWriter(broker);
				fileWriter.write(stocks.toJSONString());
				fileWriter.close();
				totalValue=0;
			}
			else {
				System.out.println("file not found");
				return;
			}
			JSONObject jsonObject2=new JSONObject();
			for(int j=0;j<jsonArrayUser.size();j++) {
				jsonObject2=(JSONObject) jsonArrayUser.get(j);
				if(jsonObject2.get("stockName").equals(user)) {
					numberOfSharePrevious=(long) jsonObject2.get("numberOfShare");
					System.out.println(jsonObject2);
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date today = Calendar.getInstance().getTime(); 
					dateTime= dateFormat.format(today);
					jsonObject2.replace("dateTime",dateTime);
					jsonObject2.replace("transactionType", "sell");
					list.insertAtEnd(user);
					list.insertAtEnd("sell");
					list.insertAtEnd(dateTime);
					stack.push(numberOf);
					queue.Enqueue(dateTime);

					jsonObject2.replace("numberOfShare",numberOfSharePrevious-numberOf);
					JSONObject stocks=new JSONObject();
					JSONArray shares=new JSONArray();
					shares.add(jsonObject2);
					stocks.put("shares",shares);

					FileWriter fileWriter=new FileWriter(user);
					fileWriter.write(stocks.toJSONString());
					fileWriter.close();
					break;
				}
			}
		}
	}
	/**
	 * buy method will buy user shares from broker
	 * @param amount
	 * @param broker
	 * @param user
	 * @throws IOException
	 * @throws ParseException
	 */
	public void buy(int amount,String broker,String user) throws IOException, ParseException {
		long numberOfSharePrevious=0,numberOf=0;
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		//JSON Objects of broker and user
		JSONObject brokerObject=(JSONObject) jsonParser.parse(new FileReader(broker));
		JSONObject userobject=(JSONObject) jsonParser.parse(new FileReader(user));
		//JSON Array of broker and user
		JSONArray jsonArrayUser= (JSONArray) userobject.get("shares");
		JSONArray jsonArrayForBroker=(JSONArray)brokerObject.get("shares");

		for(int i=0;i<jsonArrayForBroker.size();i++) {
			jsonObject= (JSONObject) jsonArrayForBroker.get(i);
			if(jsonObject.get("stockName").equals(broker)) {
				long priceOfShare= valueOfEachStock(jsonObject.get("numberOfShare"),jsonObject.get("sharePrice"));
				System.out.println("price of each share:"+jsonObject.get("sharePrice"));
				long numberOfShareBefore=(long) jsonObject.get("numberOfShare");
				numberOf=(long) jsonObject.get("numberOfShare");
				long priceOfEach=(long) jsonObject.get("sharePrice");

				numberOf=amount/priceOfEach;
				if(amount>priceOfShare&&numberOf>0) {
					System.out.println("imposibble");
					return;
				}
				JSONObject jsonObject2=new JSONObject();

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date today = Calendar.getInstance().getTime(); 
				dateTime= dateFormat.format(today);
				jsonObject.replace("dateTime",dateTime);
				jsonObject.replace("transactionType", "sell");
				list.insertAtEnd(broker);
				list.insertAtEnd("sell");
				list.insertAtEnd(dateTime);
				stack.push(numberOf);
				queue.Enqueue(dateTime);

				jsonObject.replace("numberOfShare",numberOfShareBefore-numberOf);
				JSONObject stocks=new JSONObject();
				JSONArray shares=new JSONArray();
				shares.add(jsonObject);
				stocks.put("shares",shares);

				FileWriter fileWriter=new FileWriter(broker);
				fileWriter.write(stocks.toJSONString());
				fileWriter.close();
				totalValue=0;

			}
			else {
				System.out.println("file not found");
				return;
			}
			JSONObject jsonObject2=new JSONObject();
			for(int j=0;j<jsonArrayUser.size();j++) {
				jsonObject2=(JSONObject) jsonArrayUser.get(j);
				if(jsonObject2.get("stockName").equals(user)) {
					numberOfSharePrevious=(long) jsonObject2.get("numberOfShare");
					System.out.println(jsonObject2);

					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date today = Calendar.getInstance().getTime(); 
					dateTime= dateFormat.format(today);
					jsonObject2.replace("dateTime",dateTime);
					jsonObject2.replace("transactionType", "buy");
					list.insertAtEnd(user);
					list.insertAtEnd("buy");
					list.insertAtEnd(dateTime);
					stack.push(numberOf);
					queue.Enqueue(dateTime);

					jsonObject2.replace("numberOfShare",numberOfSharePrevious+numberOf);
					JSONObject stocks=new JSONObject();
					JSONArray shares=new JSONArray();
					shares.add(jsonObject2);
					stocks.put("shares",shares);

					FileWriter fileWriter=new FileWriter(user);
					fileWriter.write(stocks.toJSONString());
					fileWriter.close();
					break;
				}
			}
		}
	}
	public void save(String filename) {

	}
	/**
	 * printReport method will print user as well as broker share report
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void printReport(String filename) throws FileNotFoundException, IOException, ParseException  {
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
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		StockAccount2 stockAccount2=new StockAccount2();
		userFile="user.json";
		brokerFile="broker.json";
		while(true) {
			System.out.println("\nMenu \n1.Account create\n2.Print Report\n3.buy share\n4.sell share"
					+ "\n5.exit\n6.view List\n7.view Stack\n8.view queue\n");
			switch (scanner.nextInt()) {
			case 1:	System.out.println("Account Creation");
			System.out.println("please enter filename with .json as extension");
			stockAccount2.Account(userFile);
			stockAccount2.Account(brokerFile);

			break;
			case 2:System.out.println("Print report");
			System.out.println("please enter filename user/broker");
			filename=scanner.next();
			if(filename.equals("user"))		
				stockAccount2.printReport(userFile);
			else
				stockAccount2.printReport(brokerFile);
			break;
			case 3:System.out.println("buy share");
			System.out.println("Please enter tha amount to buy share");
			stockAccount2.buy(scanner.nextInt(),brokerFile,userFile);
			break;
			case 4:	System.out.println("sell share");
			System.out.println("Please enter tha amount to sell share");
			stockAccount2.sell(scanner.nextInt(),brokerFile,userFile);
			break;
			case 5:System.exit(0);
			break;
			case 6:list.display();
			break;
			case 7:stack.display();
			break;
			case 8:queue.display();
			break;
			default:System.out.println("invalid");
			break;
			}
		}
	}

}
