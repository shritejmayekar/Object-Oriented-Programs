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

public class Inventory {
	public static Object value;
	
	public static void fileCreate() throws IOException {
		File file=new File("demoJson.json");
		if(file.createNewFile())
			System.out.println("succes");
		else
			System.out.println("file already exist");
		
	}
	public static void inventory() throws IOException {
		JSONObject inventory=new JSONObject();
		Scanner scanner=new Scanner(System.in);
		System.out.println("please eneter the size:");
		int size=scanner.nextInt();
		JSONArray rice=new JSONArray();
		for(int i=0;i<size;i++) {
			JSONObject jsonObject=new JSONObject();
			System.out.println("enter the name");
			jsonObject.put("name", scanner.next());
			System.out.println("enter the quantity");
			jsonObject.put("quantity", scanner.nextInt());
			System.out.println("enter the price");
			jsonObject.put("price", scanner.nextInt());
			rice.add(jsonObject);
			
		}
		inventory.put("rice", rice);
		FileWriter fileWriter=new FileWriter("demoJson.json");
		fileWriter.write(inventory.toString());
		System.out.println();
		fileWriter.close();
	}
	public static void display() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		/*JSONObject retrieve=(JSONObject)jsonArray.get(0);
		value=(Object) retrieve.get(0);
		System.out.println(value);
		*/
		/*value=retrieve.get("rice");
		Object object=jsonParser.parse(new FileReader("demoJson.json"));*/
		value=jsonParser.parse(new FileReader("demoJson.json"));
		System.out.println(value);
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("demoJson.json"));
		Object x=object.getOrDefault("rice", "name");
		System.out.println(x);
	}
	public static void main(String[] args) throws IOException, ParseException  {
		fileCreate();
		inventory();
		display();
	
	}

}
/*	JSONObject inventory=new JSONObject();
JSONArray jsonArray=new JSONArray(); 
JSONObject retrieve=new JSONObject();
JSONObject rice=new JSONObject();

jsonObject.put("weight", new Integer(180));
jsonObject.put("price", new Integer(80));
jsonArray.add("rice");
jsonArray.add(new Integer(2));
jsonArray.add(new Integer(30));


rice.put("name","basmati");
rice.put("quantity", 100);
rice.put("price", 80);
jsonArray.add(rice);
retrieve= (JSONObject) jsonArray.get(0);
retrieve.get(rice);
System.out.println(retrieve);
rice.put("name","kolam");
rice.put("quantity", 100);
rice.put("price", 50);
jsonArray.add(rice);


retrieve=(JSONObject) jsonArray.get(0);
value=inventory.get("rice");
System.out.println(retrieve);
System.out.println(value);*/
/*FileWriter filewriter=new FileWriter(file);
filewriter.write(jsonObject.toJSONString());
filewriter.flush();



JSONParser jsonParser=new JSONParser();

Object object=jsonParser.parse(new FileReader("demoJson.json"));
JSONObject jsonObject2=(JSONObject) object;
//System.out.println(jsonObject);
value=(Object) jsonObject2.get("weight");
System.out.println(value);*/