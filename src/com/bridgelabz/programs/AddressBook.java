package com.bridgelabz.programs;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddressBook {
	String firstName,lastName,address,city,state,zip,mobile,title;
	int size;
	ArrayList<String>   personInfoList=new ArrayList<>();
	
	//static JSONArray jsonArray=new JSONArray();

	public AddressBook() throws IOException {
		File file=new File("addressBook.json");
		if(file.createNewFile())
			System.out.println("success");
		else
			System.out.println("file exits");
		
	}
	public int getNumberOfPerson() {
		return 0;
		
	}
	public static void addPerson(String firstName,String lastName,String address,String city,String state,
			String zip,String mobile) throws IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObjectMain=new JSONObject();
		JSONObject jsonObject=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObjectGet=new JSONObject();
		
		BufferedReader bufferedReader=new BufferedReader(new FileReader("addressBook.json"));
		if(bufferedReader.readLine()==null) {
			bufferedReader.close();
			System.out.println("File is empty");
			jsonObject.put("firstName", firstName);
			jsonObject.put("lastName", lastName);
			jsonObject.put("address", address);
			jsonObject.put("city", city);
			jsonObject.put("state", state);
			jsonObject.put("zip", mobile);
			jsonObject.put("mobile", mobile);
			jsonArray.add(jsonObject);
			jsonObjectMain.put("addressBook", jsonObject);
			FileWriter fileWriter=new FileWriter("addressBook.json");
			fileWriter.write(jsonObjectMain.toString());
			fileWriter.close();
			System.out.println();
			
		}
		else {
			bufferedReader.close();
		jsonObjectGet=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONArray jsonArrayy=(JSONArray) jsonObject.get("addressBook");
		System.out.println(jsonObjectGet);
		
		jsonObjectGet.put("firstName", firstName);
		jsonObjectGet.put("lastName", lastName);
		jsonObjectGet.put("address", address);
		jsonObjectGet.put("city", city);
		jsonObjectGet.put("state", state);
		jsonObjectGet.put("zip", mobile);
		jsonObjectGet.put("mobile", mobile);
		jsonArrayy.add(jsonObjectGet);
		jsonObjectMain.put("addressBook", jsonObjectGet);
		FileWriter fileWriter=new FileWriter("addressBook.json");
		fileWriter.write(jsonObjectMain.toString());
		fileWriter.close();
		System.out.println();
		}
		
	}
	public String getFullNameOfPerson(int index) {
		return firstName+" "+lastName;
		
	}
	public String[] getOtherPersonInformation(int index) {
		String personInfo[]={firstName,lastName,address,city,state,zip,mobile};
		return personInfo;
	}
	public String getTitle() {
		return title;
	}
	public static void print() throws FileNotFoundException, IOException, ParseException {
		//System.out.println(bookList);
		JSONParser jsonParser=new JSONParser();
		Object value=jsonParser.parse(new FileReader("addressBook.json"));
		System.out.println(value);
		JSONParser jsonParser2=new JSONParser();
		JSONObject jsonObject=(JSONObject) jsonParser2.parse(new FileReader("addressBook.json"));
	}
	public static void main(String[] args) throws IOException, ParseException {
		
		AddressBook addressBook=new AddressBook();
		
	addressBook.addPerson("sham","kumar","nehru nagagar","kurla","maha","456226","879996666");
		//addressBook.addPerson("raj","kumar","gandhi nagagar","dadar","maha","456246","899855555");
		print();

	}

}
/*addressBook.addPerson("sham","kumar","nehru nagagar","kurla","maha","456226","879996666");
addressBook1.addPerson("raj","kumar","gandhi nagagar","dadar","maha","456246","899855555");
addressBook.print();
System.out.println(addressBook.getNumberOfPerson());*/

