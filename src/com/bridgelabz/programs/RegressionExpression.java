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

import java.util.Scanner;

public class RegressionExpression {
	public static String name,fullname;
	public static String contactNumber;
	
	
	static Scanner scanner=new Scanner(System.in);
	public static void main(String args[]) {
		String template="Hello <<name>>, We have your full name as <<full name>> in our system. "
				+ "\nyour contact number is 91-xxxxxxxxxx. "
				+ "\nPlease,let us know in case of any clarification Thank you BridgeLabz 01/01/2016.";
				
		System.out.println("Enter name:");
		name=scanner.nextLine();
		System.out.println("Enter full-name:");
		fullname=scanner.nextLine();
		System.out.println("Enter contact number:");
		contactNumber=scanner.next();

		java.util.Date date=new java.util.Date();  
		System.out.println(date);  
		
		template=template.replaceAll("<<name>>", name);
		template=template.replaceAll("<<full name>>", fullname);
		template=template.replaceAll("xxxxxxxxxx", contactNumber);
		//template=template.replace("01/01/2016",date);
		System.out.println(template);
	}
}