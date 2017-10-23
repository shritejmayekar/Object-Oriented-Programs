package com.bridgelabz.programs;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import com.bridgelabz.util.Util;
/************************************************************************************
 * Purpose: To implement inventory to add products and find cost of each inventory
 * @author shritej
 * @version 1.0
 * @since 17-10-2017
 *
 **************************************************************************************/
public class InventoryManagement {
	static Scanner scanner=new Scanner(System.in);
	static int k;
	public static void main(String[] args) throws IOException, ParseException {

		InventoryFactory inventoryFactory=new InventoryFactory();
		//Object[] object=new Object[10];
		//	Object inventoryinventoryFactory.inventoryPrice();
		while(true) {
			try {
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
		}

	}

}
