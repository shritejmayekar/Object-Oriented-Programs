/******************************************************************************
 *  
 *  Purpose: To make inventory of products in .json file and also display
 *  			total value and each product price
 *  		
 *  			
 *  			
 *  @author  Shritej
 *  @version 1.0
 *  @since   12-10-2017
 *
 ******************************************************************************/
package com.bridgelabz.programs;


import java.io.IOException;
import java.util.Scanner;


import org.json.simple.parser.ParseException;

import com.bridgelabz.util.Util;

public class Inventory {
	public static Scanner scanner=new Scanner(System.in);
	public static boolean flag=true;
	public static void main(String[] args) throws IOException, ParseException  {
		while(flag) {
			System.out.println("\nMenu\n1.File create\n2.Inventory\n3.Display\n4.exit");
			switch(scanner.nextInt()) {
			case 1:Util.fileCreate();
			break;
			case 2:Util.inventory();
			break;
			case 3:Util.display();
			break;
			case 4:flag=false;
			break;
			default:System.out.println("invalid");
			break;


			}

		}

	}

}
