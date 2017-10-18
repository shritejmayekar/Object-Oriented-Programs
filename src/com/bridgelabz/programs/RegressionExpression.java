/******************************************************************************
 *  
 *  Purpose: To find pattern and replace string using regrex pattern
 *  			
 *  		
 *  			
 *  			
 *  @author  Shritej
 *  @version 1.0
 *  @since   12-10-2017
 *
 ******************************************************************************/
package com.bridgelabz.programs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.bridgelabz.util.Util;

public class RegressionExpression {
	public static String name,fullname;
	public static String contactNumber,date;


	static Scanner scanner=new Scanner(System.in);
	public static void main(String args[]) {
		String template="Hello <<name>>, We have your full name as <<full name>> in our system. "
				+ "\nyour contact number is 91-xxxxxxxxxx. "
				+ "\nPlease,let us know in case of any clarification Thank you BridgeLabz 01/01/2016.";

		try {
			System.out.println("Enter name:");
			name=scanner.nextLine();
			System.out.println("Enter full-name:");
			fullname=scanner.nextLine();
			System.out.println("Enter contact number:");
			contactNumber=scanner.next();
			Util.regrex(template, name, fullname, contactNumber, date);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
