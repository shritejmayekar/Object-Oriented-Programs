package com.bridgelabz.programs;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import com.bridgelabz.util.Util;

public class InventoryManagement {
	static Scanner scanner=new Scanner(System.in);
	static int k;
	public static void main(String[] args) throws IOException, ParseException {
		
		InventoryFactory inventoryFactory=new InventoryFactory();
		//Object[] object=new Object[10];
	//	Object inventoryinventoryFactory.inventoryPrice();
		while(true) {
			System.out.println("Please choose option 1.Create inventory 2.getInventory prices 3.exit");
			switch (scanner.nextInt()) {
				
			case 1:
					inventoryFactory.inventory();
					
				break;
			case 2: 
					inventoryFactory.inventoryPrice();
						break;
			case 3:System.exit(0);
				break;

			default:
				break;
			}
		}
		
	}

}
