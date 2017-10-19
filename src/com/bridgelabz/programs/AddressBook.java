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
 * Purpose: To create an address book , where we can add,edit,delete or sort address book
 * @author Shritej
 * @version 1.0
 * @since 17-10-2017
 *
 */
public class AddressBook {
	static Scanner scanner=new Scanner(System.in);
	String firstName,lastName,address,city,state,zip,mobile,title;
	static int size;

	/**
	 * constructor create address book and initialize
	 * @throws IOException
	 */
	public AddressBook() throws IOException {
		File file=new File("addressBook.json");
		if(file.createNewFile())
			System.out.println("success");
		else
			System.out.println("file exits");

	}
	/**
	 * getNumberOfPerson method will able to give addressBook records in number
	 * @return
	 */
	public int getNumberOfPerson() {
		return size;

	}
	/**
	 * addPerson method will able to add person in addressBook
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param mobile
	 */
	public  void addPerson(String firstName,String lastName,String address,String city,String state,
			String zip,String mobile)  {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObjectMain=new JSONObject();
		JSONObject jsonObject=new JSONObject();
		JSONArray jsonArray=new JSONArray();


		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("addressBook.json"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(bufferedReader.readLine()==null) {
				bufferedReader.close();
				System.out.println("File is empty");
				jsonObject.put("firstName", firstName);
				jsonObject.put("lastName", lastName);
				jsonObject.put("address", address);
				jsonObject.put("city", city);
				jsonObject.put("state", state);
				jsonObject.put("zip", zip);
				jsonObject.put("mobile", mobile);
				jsonArray.add(jsonObject);
				jsonObjectMain.put("addressBook", jsonArray);
				FileWriter fileWriter=new FileWriter("addressBook.json");
				fileWriter.write(jsonObjectMain.toString());
				fileWriter.close();
				System.out.println();

			}
			else {
				bufferedReader.close();
				JSONObject jsonObjectGet=new JSONObject();
				jsonObjectGet=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
				JSONArray jsonArrayy=(JSONArray) jsonObjectGet.get("addressBook");

				JSONObject jsonObject2=new JSONObject();
				jsonObject2.put("firstName", firstName);
				jsonObject2.put("lastName", lastName);
				jsonObject2.put("address", address);
				jsonObject2.put("city", city);
				jsonObject2.put("state", state);
				jsonObject2.put("zip", zip);
				jsonObject2.put("mobile", mobile);
				System.out.println(jsonObject2);
				jsonArrayy.add(jsonObject2);
				jsonObjectMain.put("addressBook", jsonArrayy);
				FileWriter fileWriter=new FileWriter("addressBook.json",false);
				fileWriter.write(jsonObjectMain.toString());
				fileWriter.close();
				System.out.println();
			}
			size++;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * getFullNameOfPerson method will take index and display fullName
	 * @param index
	 * @return {@value fullName}
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String getFullNameOfPerson(int index) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONObject jsonObject=new JSONObject();
		JSONArray jsonArray2= (JSONArray) object.get("addressBook");


		jsonObject=(JSONObject) jsonArray2.get(index);
		firstName = (String) jsonObject.get("firstName");
		lastName = (String) jsonObject.get("lastName");
		return firstName+" "+lastName;

	}
	/**
	 * getOtherPersonInformation method will give information via index
	 * @param index
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String[] getOtherPersonInformation(int index) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONObject jsonObject=new JSONObject();
		JSONArray jsonArray2= (JSONArray) object.get("addressBook");
		jsonObject=(JSONObject) jsonArray2.get(index);
		firstName = (String) jsonObject.get("firstName");
		lastName = (String) jsonObject.get("lastName");
		address = (String) jsonObject.get("address");
		city = (String) jsonObject.get("city");
		zip = (String) jsonObject.get("zip");
		mobile = (String) jsonObject.get("mobile");
		String personInfo[]={firstName,lastName,address,city,state,zip,mobile};
		return personInfo;
	}
	public String getTitle() {
		return "addressBook";
	}
	/**
	 * editBook method will able to edit  current records
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void editBook() throws FileNotFoundException, IOException, ParseException {

		System.out.println("please enter index to edit");
		JSONParser jsonParser=new JSONParser();
		JSONArray jaArrayForReplace=new JSONArray();
		JSONObject addressBookObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONObject jsonObject=new JSONObject();
		JSONArray jsonArray= (JSONArray) object.get("addressBook");
		jsonObject=(JSONObject) jsonArray.get(scanner.nextInt());
		FileWriter fileWriter=new FileWriter("addressBook.json");
		System.out.println("\nMenu Edit \n1.FirstName\n2.lastname\n3.address\n4.city\n5.state\n6.zip\n7.mobile");
		switch (scanner.nextInt()) {
		case 1:System.out.println("enter first name");

		jsonObject.replace("firstName",scanner.next());

		jaArrayForReplace.add(jsonObject);
		addressBookObject.put("addressBook",jaArrayForReplace);
		addressBookObject.put("addressBook", jsonArray);

		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();

		break;
		case 2:System.out.println("enter last name");
		jsonObject.replace("lastName",scanner.next());
		jaArrayForReplace.add(jsonObject);
		addressBookObject.put("addressBook",jaArrayForReplace);
		addressBookObject.put("addressBook", jsonArray);

		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();

		break;
		case 3:System.out.println("enter address ");
		jsonObject.replace("address",scanner.next());
		jaArrayForReplace.add(jsonObject);
		addressBookObject.put("addressBook",jaArrayForReplace);
		addressBookObject.put("addressBook", jsonArray);
		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();

		break;
		case 4:System.out.println("enter city");
		jsonObject.replace("city",scanner.next());
		jaArrayForReplace.add(jsonObject);
		addressBookObject.put("addressBook",jaArrayForReplace);
		addressBookObject.put("addressBook", jsonArray);

		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();

		break;
		case 5:System.out.println("enter state");
		jsonObject.replace("state",scanner.next());
		jaArrayForReplace.add(jsonObject);
		addressBookObject.put("addressBook",jaArrayForReplace);
		addressBookObject.put("addressBook", jsonArray);
		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();

		break;
		case 6:System.out.println("enter zip");
		jsonObject.replace("zip",scanner.next());
		jaArrayForReplace.add(jsonObject);
		addressBookObject.put("addressBook",jaArrayForReplace);
		addressBookObject.put("addressBook", jsonArray);

		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();

		break;
		case 7:System.out.println("enter mobile number");
		jsonObject.replace("mobile",scanner.next());
		jaArrayForReplace.add(jsonObject);
		addressBookObject.put("addressBook",jaArrayForReplace);
		addressBookObject.put("addressBook", jsonArray);

		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();

		break;
		default:
			System.out.println("invalid");
			break;
		}



	}
	/**
	 * delete method will delete the person from the address book
	 * @param firstName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void delete(String firstName) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONArray jaArrayForReplace=new JSONArray();
		JSONObject addressBookObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONArray jsonArray= (JSONArray) object.get("addressBook");
		JSONObject jsonObject=new JSONObject();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject=(JSONObject) jsonArray.get(i);
			if(jsonObject.get("firstName").equals(firstName)) {
				jsonObject=(JSONObject) jsonArray.remove(i);
				jaArrayForReplace.add(jsonObject);
				addressBookObject.put("addressBook",jaArrayForReplace);
				addressBookObject.put("addressBook", jsonArray);
				FileWriter fileWriter=new FileWriter("addressBook.json");
				fileWriter.write(addressBookObject.toJSONString());
				fileWriter.close();
			}
			}
		
	}
	/**
	 * search method will search the person by name and gives basic information
	 * @param firstName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void search(String firstName) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONArray jaArrayForReplace=new JSONArray();
		JSONObject addressBookObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONArray jsonArray= (JSONArray) object.get("addressBook");
		JSONObject jsonObject=new JSONObject();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject=(JSONObject) jsonArray.get(i);
			if(jsonObject.get("firstName").equals(firstName)) {
				System.out.println("here are some information:");
				lastName=(String) jsonObject.get("lastName");
				mobile=(String) jsonObject.get("mobile");
				address=(String) jsonObject.get("address");
				city=(String) jsonObject.get("city");
				System.out.println(firstName+" "+lastName+" "+" "+mobile+" "+address+" "+city);
			}
		}

	}
	/**
	 * print method will print the json record of all persons in address book
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void print() throws FileNotFoundException, IOException, ParseException {
		//System.out.println(bookList);
		JSONParser jsonParser=new JSONParser();
		Object value=jsonParser.parse(new FileReader("addressBook.json"));
		System.out.println(value);
		JSONParser jsonParser2=new JSONParser();
		JSONObject jsonObject=(JSONObject) jsonParser2.parse(new FileReader("addressBook.json"));
	}
	public void sort() throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONArray jaArrayForReplace=new JSONArray();
		JSONObject addressBookObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONArray jsonArray= (JSONArray) object.get("addressBook");
		JSONObject jsonObject=new JSONObject();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject=(JSONObject) jsonArray.get(i);
			if(jsonObject.get("firstName").equals(firstName)) {
				
			}
		}
	}
	/**
	 * main method will able to display menu for addressBook
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {

		AddressBook addressBook=new AddressBook();
		while(true) {
			System.out.println("\nMenu \n1.addPerson\n2.getPullnameOfPerson\n3.getOtherInformation\n4.getTitle"
					+ "\n5.print\n6.Edit\n7.Search\n8.Delete");
			switch (scanner.nextInt()) {
			
			case 1:	System.out.println("please enter first name,last name,address,city,state,pincode and mobile number:");
					addressBook.addPerson(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());

					break;
			case 2:	System.out.println("please enter index");
					System.out.println("Full name:"+addressBook.getFullNameOfPerson(scanner.nextInt()));

					break;
			case 3:	System.out.println("please enter index");
					String personInfo[]=addressBook.getOtherPersonInformation(scanner.nextInt());
					System.out.println("Others Information");
					for(String person:personInfo)
						System.out.print(person+" ");

					break;
			case 5:addressBook.print();
				break;
			case 6:addressBook.editBook();
				break;
			case 7:	System.out.println("Please enter firstname to search");
					addressBook.search(scanner.next());
					break;
			case 8: System.out.println("please enter the name to delete");
					addressBook.delete(scanner.next());
					break;
			default:
				break;
			}
		}

	}

}

