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

public class ClinicManagementf {
	static Object value;
	static Object doctorName,doctorSpecialist, doctorId;
	static Object doctorAvail;
	static Object patientName;
	static Object patientId,patientNumber,patientAge;
	static Scanner scanner=new Scanner(System.in);
	
	public void fileCreate() throws IOException {
		File file=new File("clinicManagement.json");
		if(file.createNewFile())
			System.out.println("File created successfully");
		else
			System.out.println("File exist");
	}
	public void addDoctor() throws IOException {
		JSONObject clinic=new JSONObject();
		JSONArray doctors=new JSONArray();
		
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
			jsonObject.put("doctorAppointment", new Integer(0));
			doctors.add(jsonObject);
		}
		clinic.put("clinic", doctors);
		FileWriter fileWriter=new FileWriter("clinicManagement.json");
		fileWriter.write(clinic.toString());
		fileWriter.close();
		System.out.println();
		
	}
	public void doctorDisplay() throws ParseException, FileNotFoundException, IOException {
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
	public void doctorAppointment() throws FileNotFoundException, IOException, ParseException {
		System.out.println("Please enter the doctor name");
		String parameterMatch = scanner.next();
		
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("clinicManagement.json"));
		JSONArray jsonArray2= (JSONArray) object.get("clinic");
		for(int i=0;i<jsonArray2.size();i++) {
			jsonObject=(JSONObject) jsonArray2.get(i);

			if(jsonObject.get("doctorName").equals(parameterMatch)) {
				doctorAvail= jsonObject.get("doctorAppointment");
				long doctorFlagSet= (long) doctorAvail;
				if(doctorFlagSet<6l) {
					
					Object count=doctorFlagSet+1;
					jsonObject.replace("doctorAppointment", count);
					jsonArray2.add(jsonObject);
					jsonObject.put("clinic", jsonArray2);
					 
					break;
				}
				System.out.println(doctorName+" "+doctorId+" "+doctorSpecialist+" "+doctorAvail);

			}
			System.out.println(doctorName+" "+doctorId+" "+doctorSpecialist+" "+doctorAvail);
		}
		
	}
	
	public static void main(String args[]) throws IOException, ParseException {
		ClinicManagementf clinicManagementf=new ClinicManagementf();
		clinicManagementf.fileCreate();
		clinicManagementf.addDoctor();
		//clinicManagementf.doctorDisplay();
		clinicManagementf.doctorAppointment();
		clinicManagementf.doctorDisplay();
	}

}
