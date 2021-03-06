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
	public static Scanner scanner=new Scanner(System.in);
	public static int k;
	public static boolean flag=true;
	public static void main(String[] args) throws IOException, ParseException {
		
		InventoryFactory inventoryFactory=new InventoryFactory();
		while(flag) {
			try {
				System.out.println("Please choose option 1.Create inventory 2.getInventory prices 3.exit");
				switch (scanner.nextInt()) {

				case 1:
					inventoryFactory.inventory();

					break;
				case 2: 
					inventoryFactory.inventoryPrice();
					break;
				case 3:flag=false;
				break;

				default:
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
		}

	}

}
