package com.bridgelabz.programs;

import java.io.BufferedReader;
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
/**
 * Purpose: To do implement main inventory factory methods 
 * @author shritej
 * @version 1.0
 * @since 17-10-2017
 *
 */
public class InventoryFactory {

	/**
	 *{@link InventoryFactory} method will create .json  file if not exist
	 * @throws IOException
	 */
	public InventoryFactory() throws IOException {


		File file=new File("/home/bridgeit/shritej/inventoryfactory.json");
		if(file.createNewFile())
			System.out.println("succes");
		else
			System.out.println("file already exist");


	}
	/**
	 * inventory method will add products to the inventory
	 * @throws IOException
	 * @throws ParseException 
	 * */
	Object	inventory() throws IOException, ParseException {
		JSONArray product=new JSONArray();
		JSONParser jsonParser=new JSONParser();


		JSONObject inventory=new JSONObject();

		
		Scanner scanner=new Scanner(System.in);
		System.out.println("please enter size of Inventory:");

		int sizeOf=scanner.nextInt();
		BufferedReader bufferedReader=new BufferedReader(new FileReader("/home/bridgeit/shritej/inventoryfactory.json"));
		if(bufferedReader.readLine()==null) {
		for(int size=0;size<sizeOf;size++) {

			JSONObject jsonObject=new JSONObject();
			System.out.println("enter the name of inventory");
			jsonObject.put("name", scanner.next());
			System.out.println("enter the quantity");
			jsonObject.put("quantity", scanner.nextInt());
			System.out.println("enter the price");

			jsonObject.put("price", scanner.nextInt());
			product.add(jsonObject);
		}

		inventory.put("inventory", product);
		}
		else {
			
			JSONObject jsonObject=new JSONObject();
			
			JSONParser jParser=new JSONParser();
			jsonObject=(JSONObject) jParser.parse(new FileReader("/home/bridgeit/shritej/inventoryfactory.json"));
			JSONArray jsonArray=(JSONArray) jsonObject.get("inventory");
			for(int size=0;size<sizeOf;size++) {

				JSONObject jsonObjectGet=new JSONObject();
				System.out.println("enter the name of inventory");
				jsonObjectGet.put("name", scanner.next());
				System.out.println("enter the quantity");
				jsonObjectGet.put("quantity", scanner.nextInt());
				System.out.println("enter the price");

				jsonObjectGet.put("price", scanner.nextInt());
				jsonArray.add(jsonObjectGet);
			
			}
			inventory.put("inventory",jsonArray);

		}

		FileWriter fileWriter=new FileWriter("/home/bridgeit/shritej/inventoryfactory.json");
		fileWriter.write(inventory.toString());
		System.out.println();
		fileWriter.close();
		return inventory;

	}
	/**
	 * display method will display the .json file  contents to user
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void  inventoryPrice() throws FileNotFoundException, IOException, ParseException {

		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();

		Object value = jsonParser.parse(new FileReader("/home/bridgeit/shritej/inventoryfactory.json"));
		System.out.println(value);

		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("/home/bridgeit/shritej/inventoryfactory.json"));

		JSONArray jsonArray2= (JSONArray) object.get("inventory");

		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			Object getPrice = jsonObject.get("price");
			Object getQuatitity = jsonObject.get("quantity");
			long getTotal = (long)getPrice*(long)getQuatitity;
			System.out.println("Total amount of inventory factory "+jsonObject.get("name")+"="+getTotal);

		}

	}


}
