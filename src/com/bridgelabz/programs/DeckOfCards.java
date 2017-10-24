
package com.bridgelabz.programs;

import java.util.Scanner;

import com.bridgelabz.util.Util;
/******************************************************************************

 *  Purpose: To display 9 deck of card from 52 to four players and sorting it
 *  			according to rank and using Queue
 *  			
 *  		
 *  			
 *  @author  Shritej
 *  @version 1.0
 *  @since   12-10-2017
 *
 ******************************************************************************/
public class DeckOfCards {

	/**
	 * 	
	 * "Clubs"			2 3 4 5 6 7 8 9 10 j q k a 
	 * "Diamonds"		2 3 4 5 6 7 8 9 10 j q k a 
	 *  "Hearts"		2 3 4 5 6 7 8 9 10 j q k a 
	 *  "Spades"		2 3 4 5 6 7 8 9 10 j q k a 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public static Scanner scanner=new Scanner(System.in);
	public static void main(String[] args) {

		while(true) {
			System.out.println("\nOptions\n1.Get Cards to play\n2.Exit");
			switch(scanner.nextInt()) {
			case 1:	for(int j=0;j<4;j++) {
				System.out.println("\nPlayer "+(j+1)+"\n");
				for(int i=0;i<9;i++)
					Util.cardShuffle();
				System.out.println("\n\n");
				Util.showCardSorted();
			}	

			break;
			case 2:System.exit(0);
			break;

			default:System.out.print("invalid ");
			break;


			}
		}

	}

}
