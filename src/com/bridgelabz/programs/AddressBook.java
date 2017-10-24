package com.bridgelabz.programs;

import java.io.IOException;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

import com.bridgelabz.util.Util;
/**
 * Purpose: To create an address book , where we can add,edit,delete or sort address book
 * @author Shritej
 * @version 1.0
 * @since 17-10-2017
 *
 */
public class AddressBook {
	public static Scanner scanner=new Scanner(System.in);
	public static boolean flag=true;
	/**
	 * main method will able to display menu for addressBook
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {

		Util addressBook=new Util();
		addressBook.addressBook();
		while(flag) {
			System.out.println("\nMenu \n1.addPerson\n2.getPullnameOfPerson\n3.getOtherInformation\n4.getTitle"
					+ "\n5.print\n6.Edit\n7.Search\n8.Delete\n9.SortByName\n10.sortByzip\n11.Exit");
			switch (scanner.nextInt()) {

			case 1:	System.out.println("please enter first name,last name,address,city,state,pincode and mobile number:");
			addressBook.addPerson(scanner.next(),
					scanner.next(), scanner.next(), scanner.next(),
					scanner.next(), scanner.next(), scanner.next());

			break;
			case 2:	System.out.println("please enter index");
			System.out.println("Full name:"+addressBook.getFullNameOfPerson(scanner.nextInt()));

			break;
			case 3:	System.out.println("please enter index");
			String personInfo[]=addressBook.getOtherPersonInformation(scanner.nextInt());
			System.out.println("Others Information");
			for(String person:personInfo)
				System.out.print(person+" ");

			break;
			case 4:System.out.println("title:"+addressBook.getTitle());
			break;
			case 5:addressBook.print();
			break;
			case 6:addressBook.editBook();
			break;
			case 7:	System.out.println("Please enter firstname to search");
			addressBook.search(scanner.next());
			break;
			case 8: System.out.println("please enter the name to delete");
			addressBook.delete(scanner.next());
			break;
			case 9:System.out.println("sorted by name");
			addressBook.sortByName();
			addressBook.print();
			break;
			case 10:System.out.println("sorted by zip");
			addressBook.sortByZip();
			addressBook.print();
			break;
			case 11:flag=false;
			break;
			default:System.out.println("Inavalid");
			break;
			}
		}

	}

}

