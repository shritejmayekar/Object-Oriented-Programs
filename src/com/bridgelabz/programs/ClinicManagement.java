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

import com.bridgelabz.util.Util;
/**********************************************************************************************
 * Purpose : To ease the patient and doctor to maintain clinic data and eas to search 
 * 				whenever required 
 * @author Shritej
 * @version 1.0
 * @since 16/10/2017
 ***********************************************************************************************
 */
public class ClinicManagement {
	public static Scanner scanner=new Scanner(System.in);
	public static boolean flag=true;
	public static void main(String[] args) throws IOException, ParseException {
		Util clinicManagement=new Util();
		clinicManagement.FileCreate();

		while(true) {
			System.out.print("\nMenu\n1.Add doctors\n2.dispay doctor\n3.Add patient\n4.display patient"
					+ "\n5.search doctor\n6.take doctor appointment\n7.search patient\n8.Popular Doctor\n9.Exit");
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
			case 8:System.out.println("Popular doctors");
			clinicManagement.doctorPopular();
			break;
			case 9:flag=false;
			break;
			default:System.out.print("invalid");
			break;
			}


		}
	}

}
