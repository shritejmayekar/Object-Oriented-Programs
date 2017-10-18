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
/**********************************************************************************************
 * Purpose : To ease the patient and doctor to maintain clinic data and eas to search 
 * 				whenever required 
 * @author Shritej
 * @version 1.0
 * @since 16/10/2017
 ***********************************************************************************************
 */
public class ClinicManagement {
	static Object value;
	static Scanner scanner=new Scanner(System.in);
	static Object doctorName,doctorSpecialist, doctorId;
	static Object doctorAvail;
	static Object patientName;
	static Object patientId,patientNumber,patientAge;
	/**
	 * Filecreate method will create file if not exist
	 * @throws IOException
	 */
	public void FileCreate() throws IOException {
		File file=new File("clinicManagement.json");
		if(file.createNewFile())
			System.out.println("File created successfully");
		else
			System.out.println("File exist");
	}
	/**
	 * doctors method will add doctor record to json file
	 * @throws IOException
	 */
	public void doctors() throws IOException {
		JSONObject clinic=new JSONObject();
		JSONArray doctors=new JSONArray();
		Scanner scanner=new Scanner(System.in);
		System.out.println("please enter no. of doctotors :");
		int sizeOf=scanner.nextInt();
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
			jsonObject.put("doctorAppointment", 0);
			doctors.add(jsonObject);
		}
		clinic.put("clinic", doctors);
		FileWriter fileWriter=new FileWriter("clinicManagement.json");
		fileWriter.write(clinic.toString());
		fileWriter.close();
		System.out.println();
	}
	/**
	 * display method will display values of all doctors
	 * @throws ParseException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void display() throws ParseException, FileNotFoundException, IOException {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();

		value=jsonParser.parse(new FileReader("clinicManagement.json"));
		System.out.println(value);
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			doctorName=  jsonObject.get("doctorName");
			doctorId=  jsonObject.get("doctorId");
			doctorSpecialist=  jsonObject.get("doctorSpecialist");
			doctorAvail= jsonObject.get("doctorAvail");
			System.out.println(doctorName+" "+doctorId+" "+doctorSpecialist+" "+doctorAvail);
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
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			patientName= jsonObject.get("patientName");
			patientId=  jsonObject.get("patientId");
			patientNumber=  jsonObject.get("patientMobile");
			patientAge=jsonObject.get("patientAge");
			System.out.println(patientName+" "+patientId+" "+patientNumber+" "+patientAge);
		}

	}
	/**
	 * search method will search the doctors by name,availability, specialization and Id.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void search() throws FileNotFoundException, IOException, ParseException {
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
	public void patient() throws IOException, ParseException {
		JSONObject clinic=new JSONObject();
		JSONArray patients=new JSONArray();
		Scanner scanner=new Scanner(System.in);
		System.out.println("please enter no. of patients:");
		int sizeOf=scanner.nextInt();
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
		JSONParser jsonParser = new JSONParser();
		/*value=jsonParser.parse(new FileReader("clinicManagementPatient.json"));
		System.out.println(value);*/
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


		default:System.out.println("invalid input");
		break;
		}


	}
	public  void doctorsAppointment() throws FileNotFoundException, IOException, ParseException {
		System.out.println("Please enter the doctor name to take appointment:");
		doctorName=scanner.next();
		if(isAppointmentFull( doctorName)) {
			System.out.println("Appointment is Full\nCheck For tomorrow ");


		}
		else
		{
			System.out.println("Appointment success");
			//ClinicManagement clc=new ClinicManagement();
			//clc.search();
		}


	}
	public boolean isAppointmentFull(Object doctornName) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);
			if(doctornName.equals(jsonObject.get("doctorName"))) {
				doctorAvail= jsonObject.get("doctorAvail");
				if(doctorAvail.equals(5)) {
					return true;

				}
				else {
					/*jsonObject.put("doctorAvail",doctorAvail++);

					clinic.put("clinic", patients);
					FileWriter fileWriter=new FileWriter("clinicManagementPatient.json");
					fileWriter.write(clinic.toString());
					fileWriter.close();*/
				}

			}
		}
		return false;

	}

	public static void main(String[] args) throws IOException, ParseException {
		ClinicManagement clinicManagement=new ClinicManagement();
		clinicManagement.FileCreate();
		//clinicManagement.doctors();
		clinicManagement.display();
		//clinicManagement.patient();
		//clinicManagement.displayPatient();
		while(true)
			//	clinicManagement.search();
			clinicManagement.doctorsAppointment();
		//clinicManagement.searchPatient();

	}

}
