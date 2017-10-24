package com.bridgelabz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/********************************************************************************
 * Purpose : Different static methods to implement different tasks like cardShuffle
 * 				and file create ,etc
 * @author Shritej
 * @version 1.0
 * @since 8-10-2017
 *********************************************************************************
 */
public class Util {
	//Declaration
	public static int i,j;
	public String firstName,lastName,address,city,state,zip,mobile,title;
	public	static int size;
	public static Scanner scanner=new Scanner(System.in);
	public static Object doctorName,doctorSpecialist, doctorId;
	public	static Object doctorAvail;
	public	static Object patientName;
	public	static Object patientId,patientNumber,patientAge;
	public static Object value, getPrice,getQuatitity,getTotal;
	public static 	int [][] savecard=new int[4][13];
	public static String [] suit={"Clubs","Diamonds","Hearts","Spades"};
	public static String[] rank={"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"};
	public	static String[][] cards={{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
			{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
			{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
			{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},};

	public static Queue<String> queue=new Queue<String>();
	/**
	 * 	cradShuffle method will shuffle cards using  Random class
	 */
	public static void cardShuffle() {
		int column=randomGeneratorColumn();
		int rows=randomGeneratorRow();
		if(cards[rows][column]==""){
			cardShuffle();
			return;
		}
		System.out.print("["+suit[rows]+" ");
		System.out.print(cards[rows][column]+"]");
		savecard[rows][column]=column+1;
		cards[rows][column]="";
	}
	/**
	 * 	cradGet method will shuffle cards using  Random class
	 */
	public  void cardGet() {
		int column=randomGeneratorColumn();
		int rows=randomGeneratorRow();
		if(cards[rows][column].equals("")){
			cardShuffle();
			return;
		}
		savecard[rows][column]=column+1;
		cards[rows][column]="";
	}
	/**
	 * showCarsSorted method will sort cards according to ranks
	 */
	public static void showCardSorted() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				if(savecard[i][j]!=0) {
					System.out.print("["+suit[i]+" ");
					System.out.print(rank[j]+"]");
					savecard[i][j]=0;
				}

			}
			System.out.println();
		}
	}
	/**
	 * showCarsSorted method will sort cards according to ranks
	 */
	public  void showCardrank() {
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				if(savecard[i][j]!=0) {
					queue.Enqueue("["+suit[i]+" "+rank[j]+"]");
					savecard[i][j]=0;
				}

			}

			System.out.println();
		}


		System.out.println("\n1.want card remove\n2.exit");
		switch(scanner.nextInt()) {
		case 1:queue.display();
		while(!queue.isEmpty())
			queue.Dequee();
		/*System.out.print(queue.Dequee());*/

		
		break;
		case 2:
		break;
		default:System.out.println("invalid");
		break;
		}

	}

	/**
	 * randomGeneratorColumn will generates column randomly	
	 * @return
	 */

	public static int randomGeneratorColumn() {
		Random random=new Random();
		return random.nextInt(13);
	}
	/**
	 * randomGeneratorRow will generates row randomly
	 * @return
	 */
	public static int randomGeneratorRow() {
		Random random=new Random();
		return random.nextInt(4);
	}
	/**
	 * fileCreate method will create .json  file if not exist
	 * @throws IOException
	 */
	public static void fileCreate() throws IOException {
		File file=new File("demoJson.json");
		if(file.createNewFile())
			System.out.println("succes");
		else
			System.out.println("file already exist");

	}
	/**
	 * inventory method will add products to the inventory
	 * @throws IOException
	 */
	public static void inventory() throws IOException {
		JSONObject jsonInventory=new JSONObject();
		Scanner scanner=new Scanner(System.in);
		System.out.println("please enter size of Inventory:");
		JSONArray jsonProductArray=new JSONArray();
		int sizeOfInventory=scanner.nextInt();

		for(int size=0;size<sizeOfInventory;size++) {
			JSONObject jsonObject=new JSONObject();
			System.out.println("enter the name");
			jsonObject.put("name", scanner.next());
			System.out.println("enter the quantity");
			jsonObject.put("quantity", scanner.nextInt());
			System.out.println("enter the price");

			jsonObject.put("price", scanner.nextInt());
			jsonProductArray.add(jsonObject);
		}

		jsonInventory.put("inventory",jsonProductArray);
		FileWriter fileWriter=new FileWriter("demoJson.json");
		fileWriter.write(jsonInventory.toString());
		System.out.println();
		fileWriter.close();

	}
	/**
	 * display method will display the .json file  contents to user
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void display() throws FileNotFoundException, IOException, ParseException {

		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();

		value=jsonParser.parse(new FileReader("demoJson.json"));
		System.out.println(value);

		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("demoJson.json"));

		JSONArray jsonArray= (JSONArray) object.get("inventory");
		for(int i=0;i<jsonArray.size();i++) {
			jsonObject=(JSONObject) jsonArray.get(i);
			getPrice= jsonObject.get("price");
			getQuatitity= jsonObject.get("quantity");
			getTotal=(long)getPrice*(long)getQuatitity;
			System.out.println("Total amount of "+jsonObject.get("name")+"="+getTotal);

		}

	}
	/**
	 * regrex method will replace content matching  the pattern
	 * @param template
	 * @param name
	 * @param fullname
	 * @param contactNumber
	 * @param date
	 */
	public static void regrex(String template,String name,String fullname,String contactNumber,String date) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime(); 
		date=dateFormat.format(today);  
		template=template.replaceAll("<<name>>", name);
		template=template.replaceAll("<<full name>>", fullname);
		template=template.replaceAll("xxxxxxxxxx", contactNumber);
		template=template.replaceAll("01/01/2016",date);
		System.out.println(template);
	}

	/**
	 * Filecreate method will create file if not exist
	 * @throws IOException
	 */
	public void FileCreate() throws IOException {
		File fileDoctor=new File("clinicManagement.json");
		File filePatient=new File("clinicManagementPatient.json");
		File fileAppointment=new File("clinicManagementAppointment.json");
		File filePopular=new File("clinicPopular.json");
		//file created for doctor
		if(fileDoctor.createNewFile())
			System.out.println("File created successfully for doctor");
		else
			System.out.println("File exist");
		//file created for patient
		if(filePatient.createNewFile())
			System.out.println("File created successfully for patient");
		else
			System.out.println("File exist");
		//file created for patient-doctor appointment
		if(fileAppointment.createNewFile())
			System.out.println("File created successfully for Appointment");
		else
			System.out.println("File exist");
		//file created for popular doctor
		if(filePopular.createNewFile())
			System.out.println("File created successfully for popular doctor");
		else
			System.out.println("File exist");

	}
	/**
	 * doctors method will add doctor record to json file
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void addDoctors() throws IOException, ParseException {
		JSONObject clinic=new JSONObject();
		JSONArray doctors=new JSONArray();
		Scanner scanner=new Scanner(System.in);
		System.out.println("please enter no. of doctotors :");
		int sizeOf=scanner.nextInt();

		BufferedReader bufferedReader = new BufferedReader(new FileReader("clinicManagement.json"));
		if(bufferedReader.readLine()==null) {
			bufferedReader.close();
			for (int i = 0; i < sizeOf; i++) {
				JSONObject jsonObject=new JSONObject();
				System.out.print("Enter the doctor name:");
				jsonObject.put("doctorName",scanner.next());
				System.out.print("Enter the doctor Id:");
				jsonObject.put("doctorId",scanner.next());
				System.out.print("Enter the doctor 's Specialization:");
				jsonObject.put("doctorSpecialist",scanner.next());
				System.out.print("Enter the doctor 's Availaibility:");
				jsonObject.put("doctorAvail",scanner.next());
				jsonObject.put("doctorAppointment", 5);
				doctors.add(jsonObject);
			}
			clinic.put("clinic", doctors);
			FileWriter fileWriter=new FileWriter("clinicManagement.json");
			fileWriter.write(clinic.toString());
			fileWriter.close();
			System.out.println();
		}
		else {
			JSONObject jsonObjectParse=new JSONObject();
			JSONParser jsonParser=new JSONParser();
			jsonObjectParse=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
			JSONArray jaArray=(JSONArray) jsonObjectParse.get("clinic");
			bufferedReader.close();
			for (int i = 0; i < sizeOf; i++) {
				JSONObject jsonObject=new JSONObject();
				System.out.print("Enter the doctor name:");
				jsonObject.put("doctorName",scanner.next());
				System.out.print("Enter the doctor Id:");
				jsonObject.put("doctorId",scanner.next());
				System.out.print("Enter the doctor 's Specialization:");
				jsonObject.put("doctorSpecialist",scanner.next());
				System.out.print("Enter the doctor 's Availaibility:");
				jsonObject.put("doctorAvail",scanner.next());
				jsonObject.put("doctorAppointment", 5);
				jaArray.add(jsonObject);
			}
			clinic.put("clinic", jaArray);
			FileWriter fileWriter=new FileWriter("clinicManagement.json");
			fileWriter.write(clinic.toString());
			fileWriter.close();
			System.out.println();


		}
	}
	/**
	 * displayDoctor method will display values of all doctors
	 * @throws ParseException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void displayDoctor() throws ParseException, FileNotFoundException, IOException {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();

		value=jsonParser.parse(new FileReader("clinicManagement.json"));
		System.out.println(value);
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		System.out.println("\n|DoctorName|DoctorId|Specilialist|DoctorAvailable|");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			doctorName=  jsonObject.get("doctorName");
			doctorId=  jsonObject.get("doctorId");
			doctorSpecialist=  jsonObject.get("doctorSpecialist");
			doctorAvail= jsonObject.get("doctorAvail");
			System.out.println("\t"+doctorName+"\t"+doctorId+"\t"+doctorSpecialist+"\t"+doctorAvail);
		}

	}
	/**
	 * display method will display values of all patient
	 * @throws ParseException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void displayPatient() throws ParseException, FileNotFoundException, IOException {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();

		value=jsonParser.parse(new FileReader("clinicManagementPatient.json"));
		System.out.println(value);
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagementPatient.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		System.out.println("\n|PatientName|patientId|patientNumber|patientAge|");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			patientName= jsonObject.get("patientName");
			patientId=  jsonObject.get("patientId");
			patientNumber=  jsonObject.get("patientMobile");
			patientAge=jsonObject.get("patientAge");
			System.out.println("\t"+patientName+"\t"+patientId+"\t"+patientNumber+"\t"+patientAge);
		}

	}
	/**
	 * searchDoctor method will search the doctors by name,availability, specialization and Id.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void searchDoctor() throws FileNotFoundException, IOException, ParseException {
		System.out.println("Please Search Doctor by\n1.Name\n2.Id\n3.Specialization\n4.Availiability");
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		String parameterMatch="";
		switch (scanner.nextInt()) {

		case 1:System.out.println("Please enter doctor name:");
		parameterMatch=scanner.next();
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);

			if(jsonObject.get("doctorName").equals(parameterMatch)) {
				doctorName= (String) jsonObject.get("doctorName");
				doctorId= (String) jsonObject.get("doctorId");
				doctorSpecialist= (String) jsonObject.get("doctorSpecialist");
				doctorAvail= jsonObject.get("doctorAvail");
				System.out.println(doctorName+" "+doctorId+" "+doctorSpecialist+" "+doctorAvail);

			}
		}


		break;

		case 2:
			System.out.println("Please enter doctor Id:");
			parameterMatch=scanner.next();
			for(int i=0;i<jsonArray2.size();i++) {
				jsonObject=(JSONObject) jsonArray2.get(i);
				if(jsonObject.get("doctorId").equals(parameterMatch)) {
					doctorName= (String) jsonObject.get("doctorName");
					doctorId= (String) jsonObject.get("doctorId");
					doctorSpecialist= (String) jsonObject.get("doctorSpecialist");
					doctorAvail= jsonObject.get("doctorAvail");
					System.out.println(doctorName+" "+doctorId+" "+doctorSpecialist+" "+doctorAvail);
				}
			}
			break;
		case 3:
			System.out.println("Please enter doctor Specialist:");
			parameterMatch=scanner.next();
			for(int i=0;i<jsonArray2.size();i++) {
				jsonObject=(JSONObject) jsonArray2.get(i);
				if(jsonObject.get("doctorSpecialist").equals(parameterMatch)) {
					doctorName= (String) jsonObject.get("doctorName");
					doctorId= (String) jsonObject.get("doctorId");
					doctorSpecialist= (String) jsonObject.get("doctorSpecialist");
					doctorAvail= jsonObject.get("doctorAvail");
					System.out.println(doctorName+" "+doctorId+" "+doctorSpecialist+" "+doctorAvail);
				}
			}
			break;
		case 4:
			System.out.println("Please enter doctor Availability:");
			parameterMatch=scanner.next();
			for(int i=0;i<jsonArray2.size();i++) {
				jsonObject=(JSONObject) jsonArray2.get(i);
				if(jsonObject.get("doctorAvail").equals(parameterMatch)) {
					doctorName= (String) jsonObject.get("doctorName");
					doctorId= (String) jsonObject.get("doctorId");
					doctorSpecialist= (String) jsonObject.get("doctorSpecialist");
					doctorAvail= jsonObject.get("doctorAvail");
					System.out.println(doctorName+" "+doctorId+" "+doctorSpecialist+" "+doctorAvail);
				}
			}
			break;
		default:System.out.println("invalid input");
		break;
		}

	}
	/**
	 * addPatient method will add Patients to record
	 * @throws IOException
	 * @throws ParseException
	 */
	public void addPatient() throws IOException, ParseException {
		JSONObject clinic=new JSONObject();
		JSONArray patients=new JSONArray();

		System.out.println("please enter no. of patients:");
		int sizeOf=scanner.nextInt();
		BufferedReader bufferedReader=new BufferedReader(new FileReader("clinicManagementPatient.json"));
		if(bufferedReader.readLine()==null) {
			bufferedReader.close();
			for (int i = 0; i < sizeOf; i++) {
				JSONObject jsonObject=new JSONObject();
				System.out.print("Enter the patient name:");
				jsonObject.put("patientName",scanner.next());
				System.out.print("Enter the patient Id:");
				jsonObject.put("patientId",scanner.next());
				System.out.print("Enter the mobile number:");
				jsonObject.put("patientMobile",scanner.next());
				System.out.print("Enter the age of patient:");
				jsonObject.put("patientAge",scanner.nextInt());
				patients.add(jsonObject);
			}
			clinic.put("clinic", patients);
			FileWriter fileWriter=new FileWriter("clinicManagementPatient.json");
			fileWriter.write(clinic.toString());
			fileWriter.close();
			System.out.println();

		}
		else {
			bufferedReader.close();
			JSONObject jsonObjectParse=new JSONObject();
			JSONParser jsonParser=new JSONParser();
			jsonObjectParse=(JSONObject) jsonParser.parse(new FileReader("clinicManagementPatient.json"));
			JSONArray jsonArray=(JSONArray) jsonObjectParse.get("clinic");
			for (int i = 0; i < sizeOf; i++) {
				JSONObject jsonObject=new JSONObject();
				System.out.print("Enter the patient name:");
				jsonObject.put("patientName",scanner.next());
				System.out.print("Enter the patient Id:");
				jsonObject.put("patientId",scanner.next());
				System.out.print("Enter the mobile number:");
				jsonObject.put("patientMobile",scanner.next());
				System.out.print("Enter the age of patient:");
				jsonObject.put("patientAge",scanner.nextInt());
				jsonArray.add(jsonObject);
			}
			clinic.put("clinic",jsonArray);
			FileWriter fileWriter=new FileWriter("clinicManagementPatient.json");
			fileWriter.write(clinic.toString());
			fileWriter.close();
			System.out.println();
		}
	}
	/**
	 * searchPatient method will search the patient by name,availability, specialization and Id.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void searchPatient() throws FileNotFoundException, IOException, ParseException {
		System.out.println("Please Search Patient by\n1.Name\n2.Id\n3.Mobile number");
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagementPatient.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		String parameterMatch="";
		switch (scanner.nextInt()) {

		case 1:System.out.println("Please enter patient name:");
		parameterMatch=scanner.next();
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);

			if(jsonObject.get("patientName").equals(parameterMatch)) {
				patientName = (String) jsonObject.get("patientName");
				patientId= jsonObject.get("patientId");
				patientNumber= jsonObject.get("patientMobile");
				patientAge=(long) jsonObject.get("patientAge");
				System.out.println(patientName+" "+patientId+" "+patientNumber+" "+patientAge);
			}
		}
		break;

		case 2:System.out.println("Please enter patient Id:");
		parameterMatch=scanner.next();
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			if(jsonObject.get("patientId").equals(parameterMatch)) {
				patientName = (String) jsonObject.get("patientName");
				patientId= jsonObject.get("patientId");
				patientNumber= jsonObject.get("patientMobile");
				patientAge=(long) jsonObject.get("patientAge");
				System.out.println(patientName+" "+patientId+" "+patientNumber+" "+patientAge);
			}
		}
		break;

		case 3:System.out.println("Please enter patient mobile number:");
		parameterMatch=scanner.next();
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);

			if(jsonObject.get("patientMobile").equals(parameterMatch)) {
				patientName = (String) jsonObject.get("patientName");
				patientId= jsonObject.get("patientId");
				patientNumber= jsonObject.get("patientMobile");
				patientAge=(long) jsonObject.get("patientAge");
				System.out.println(patientName+" "+patientId+" "+patientNumber+" "+patientAge);

			}
		}
		break;

		default:System.out.println("invalid input");
		break;
		}


	}
	/**
	 * doctorsAppointment method makes appoitnment of doctors for patients
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public  void doctorsAppointment() throws FileNotFoundException, IOException, ParseException {
		System.out.println("Please enter the doctor name to take appointment:");
		doctorName=scanner.next();
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		JSONArray jaArrayForReplace=new JSONArray();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			if(doctorName.equals(jsonObject.get("doctorName"))) {
				doctorAvail= jsonObject.get("doctorAppointment");
				if((long)doctorAvail==0) {
					System.out.println("Doctor's Appointment is full");

				}
				else {

					doctorAppointmentList(jsonObject.get("doctorName"),jsonObject.get("doctorAvail"),jsonObject.get("doctorAppointment"));


					jsonObject.replace("doctorAppointment",(long)doctorAvail-1);
					jaArrayForReplace.add(jsonObject);
					JSONObject jsonObject2=new JSONObject();
					jsonObject2.put("clinic", jaArrayForReplace);
					jsonObject2.put("clinic", jsonArray2);
					System.out.println("Doctors appointment success");
					FileWriter fileWriter=new FileWriter("clinicManagement.json");
					fileWriter.write(jsonObject2.toJSONString());
					fileWriter.close();
					break;
				}
			}
		}


	}
	/**
	 * doctorAppointmentList method will make list of doctor assigned and patient as record
	 * @param doctorName
	 * @param shift
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */

	@SuppressWarnings("unchecked")
	public void doctorAppointmentList(Object doctorName,Object shift,Object appointment) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser2=new JSONParser();
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObjectAppointment=new JSONObject();
		JSONObject jsonObjectMain=new JSONObject();


		JSONObject jsonObjectSetData=new JSONObject();
		BufferedReader bufferedReader = new BufferedReader(new FileReader("clinicManagementAppointment.json"));
		if(bufferedReader.readLine()==null) {
			bufferedReader.close();
			System.out.print("please enter your name:");
			jsonObjectSetData.put("patientName",scanner.next());
			jsonObjectSetData.put("doctorName",doctorName);
			jsonObjectSetData.put("doctorShifts",shift);
			jsonObjectSetData.put("doctorAppointments", appointment);
			jsonArray.add(jsonObjectSetData);
			jsonObjectMain.put("clinic", jsonArray);
			FileWriter fileWriterAppointment=new FileWriter("clinicManagementAppointment.json",false);
			fileWriterAppointment.write(jsonObjectMain.toString());
			fileWriterAppointment.close();
			addRecord();

		}
		else {
			bufferedReader.close();
			jsonObjectAppointment=(JSONObject) jsonParser2.parse(new FileReader("clinicManagementAppointment.json"));
			jsonArray=(JSONArray) jsonObjectAppointment.get("clinic");
			System.out.print("please enter your name:");
			jsonObjectSetData.put("patientName",scanner.next() );
			jsonObjectSetData.put("doctorName",doctorName);
			jsonObjectSetData.put("doctorShifts",shift);
			jsonObjectSetData.put("doctorAppointments",appointment);

			jsonArray.add(jsonObjectSetData);

			jsonObjectMain.put("clinic", jsonArray);

			FileWriter fileWriterAppointment=new FileWriter("clinicManagementAppointment.json",false);
			fileWriterAppointment.write(jsonObjectMain.toString());
			fileWriterAppointment.close();
			addRecord();
		}

	}
	/**
	 * addRecord method add patient records if not present
	 * @throws IOException
	 * @throws ParseException
	 */
	public void addRecord() throws IOException, ParseException {
		System.out.println("Is Your new patient true/false");
		if(scanner.nextBoolean())
			addPatient();
		else
			return;

	}
	/**
	 * doctorPopular method finds popularity of doctor
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void doctorPopular() throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonObjectParse=new JSONObject();
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		jsonObjectParse=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
		JSONArray jsonArray=(JSONArray) jsonObjectParse.get("clinic");

		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject=(JSONObject) jsonArray.get(i);
			long value=(long) jsonObject.get("doctorAppointment");
			if(value==0) 
				System.out.println("Most popular Doctor:"+jsonObject.get("doctorName"));
			else if(value==1) 
				System.out.println("Moderate popular Doctor:"+jsonObject.get("doctorName"));
			else if(value==2)
				System.out.println("less popular Doctor:"+jsonObject.get("doctorName"));
			else if(value==3)
				System.out.println("new  Doctor:"+jsonObject.get("doctorName"));
			else if(value==4)
				System.out.println("new  Doctor:"+jsonObject.get("doctorName"));
		}
	}
	/**
	 * AdressBook method create address book and initialize
	 * @throws IOException
	 */
	public void addressBook() throws IOException {
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

				JSONObject jsonObject2Put=new JSONObject();
				jsonObject2Put.put("firstName", firstName);
				jsonObject2Put.put("lastName", lastName);
				jsonObject2Put.put("address", address);
				jsonObject2Put.put("city", city);
				jsonObject2Put.put("state", state);
				jsonObject2Put.put("zip", zip);
				jsonObject2Put.put("mobile", mobile);
				System.out.println(jsonObject2Put);
				jsonArrayy.add(jsonObject2Put);
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
	/**
	 * getTitle of book
	 * @return
	 */
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
				size--;
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
		boolean flag=false;
		JSONParser jsonParser=new JSONParser();
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
				System.out.println("\nFound person\n"+firstName+" "+lastName+" "+" "+mobile+" "+address+" "+city);
				flag=true;
			}
		}
		if(flag==false)
			System.out.println("Name not found");

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
		JSONArray jsonArray=(JSONArray) jsonObject.get("addressBook");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject2=new JSONObject();
			jsonObject2=(JSONObject) jsonArray.get(i);

			firstName=(String) jsonObject2.get("firstName");
			lastName=(String) jsonObject2.get("lastName");
			mobile=(String) jsonObject2.get("mobile");
			address=(String) jsonObject2.get("address");
			city=(String) jsonObject2.get("city");
			System.out.println(firstName+" "+lastName+" "+" "+mobile+" "+address+" "+city);

		}
	}
	/**
	 * sortByName method will sort address Book according to Name in ascending order
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void sortByName() throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject addressBookObject=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONArray jsonArray= (JSONArray) addressBookObject.get("addressBook");

		//using bubble sort to sort names
		for (int i = 0; i < jsonArray.size(); i++) {

			for (int j = i+1; j < jsonArray.size(); j++) {
				JSONObject jsonObjectWordOne=(JSONObject) jsonArray.get(i);
				JSONObject jsonObjectWordTwo=(JSONObject) jsonArray.get(j);

				String stringOne=(String) jsonObjectWordOne.get("firstName");

				String stringTwo=(String) jsonObjectWordTwo.get("firstName");

				if(stringOne.compareToIgnoreCase(stringTwo)>0) {
					jsonArray.set(i, jsonObjectWordTwo);
					jsonArray.set(j, jsonObjectWordOne);
				}

			}

		}
		addressBookObject.put("addressBook", jsonArray);
		FileWriter fileWriter=new FileWriter("addressBook.json");
		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();
	}
	/**
	 * sortByZip method will sort addressBook according to Zip in ascending order
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void sortByZip() throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject addressBookObject=(JSONObject) jsonParser.parse(new FileReader("addressBook.json"));
		JSONArray jsonArray= (JSONArray) addressBookObject.get("addressBook");

		//using bubble sort to sort
		for (int i = 0; i < jsonArray.size(); i++) {

			for (int j = i+1; j < jsonArray.size(); j++) {
				//getting first and second zip of array
				JSONObject jsonObjectWordOne=(JSONObject) jsonArray.get(i);
				JSONObject jsonObjectWordTwo=(JSONObject) jsonArray.get(j);

				String stringOne=(String) jsonObjectWordOne.get("zip");

				String stringTwo=(String) jsonObjectWordTwo.get("zip");
				//comparing values
				if(stringOne.compareToIgnoreCase(stringTwo)>0) {
					jsonArray.set(i, jsonObjectWordTwo);
					jsonArray.set(j, jsonObjectWordOne);
				}

			}

		}
		addressBookObject.put("addressBook", jsonArray);
		FileWriter fileWriter=new FileWriter("addressBook.json");
		fileWriter.write(addressBookObject.toJSONString());
		fileWriter.close();
	}
}
