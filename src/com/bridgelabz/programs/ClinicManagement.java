package com.bridgelabz.programs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import javax.xml.crypto.Data;

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
	 */
	public void addDoctors() throws IOException {
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
			jsonObject.put("doctorAppointment", 5);
			doctors.add(jsonObject);
		}
		clinic.put("clinic", doctors);
		FileWriter fileWriter=new FileWriter("clinicManagement.json");
		fileWriter.write(clinic.toString());
		fileWriter.close();
		System.out.println();
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
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void addPatient() throws IOException, ParseException {
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

					doctorAppointmentList(jsonObject.get("doctorName"),jsonObject.get("doctorAvail"));


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
	public void doctorAppointmentList(Object doctorName,Object shift) throws FileNotFoundException, IOException, ParseException {
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
			jsonArray.add(jsonObjectSetData);
			jsonObjectMain.put("clinic", jsonArray);
			FileWriter fileWriterAppointment=new FileWriter("clinicManagementAppointment.json",false);
			fileWriterAppointment.write(jsonObjectMain.toString());
			fileWriterAppointment.close();


		}
		else {

			jsonObjectAppointment=(JSONObject) jsonParser2.parse(new FileReader("clinicManagementAppointment.json"));
			jsonArray=(JSONArray) jsonObjectAppointment.get("clinic");
			System.out.print("please enter your name:");
			jsonObjectSetData.put("patientName",scanner.next() );
			jsonObjectSetData.put("doctorName",doctorName);
			jsonObjectSetData.put("doctorShifts",shift);
			jsonArray.add(jsonObjectSetData);

			jsonObjectMain.put("clinic", jsonArray);
			FileWriter fileWriterAppointment=new FileWriter("clinicManagementAppointment.json",false);
			fileWriterAppointment.write(jsonObjectMain.toString());
			fileWriterAppointment.close();
		}

	}
	public static void main(String[] args) throws IOException, ParseException {
		ClinicManagement clinicManagement=new ClinicManagement();
		clinicManagement.FileCreate();

		while(true) {
			System.out.print("\nMenu\n1.Add doctors\n2.dispay doctor\n3.Add patient\n4.display patient"
					+ "\n5.search doctor\n6.take doctor appointment\n7.search patient");
			switch (scanner.nextInt()) {
			case 1:System.out.println("please enter doctors schedule for today");
			clinicManagement.addDoctors();
			break;
			case 2:System.out.println("display doctor");
			clinicManagement.displayDoctor();
			break;
			case 3:System.out.println("add patients information");
			clinicManagement.addPatient();
			break;
			case 4:System.out.println("display patients");
			clinicManagement.displayPatient();
			break;
			case 5:System.out.println("search doctor");
			clinicManagement.searchDoctor();
			break;
			case 6:System.out.println("take doctors appointment");
			clinicManagement.doctorsAppointment();
			break;
			case 7:System.out.println("search patient");
			clinicManagement.searchPatient();
			break;

			default:System.out.print("invalid");
			break;
			}


		}
	}

}
