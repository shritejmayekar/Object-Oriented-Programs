
package com.bridgelabz.programs;

import com.bridgelabz.util.Queue;
import com.bridgelabz.util.Util;
/******************************************************************************
 *  
 *  Purpose: To display 9 deck of card from 52 to four players and sorting it
 *  			according to rank and using Queue
 *  			
 *  		
 *  			
 *  			
 *  @author  Shritej
 *  @version 1.0
 *  @since   12-10-2017
 *
 ******************************************************************************/
public class DeckOfCardQueue {

	public static void main(String[] args) {
		try {
			Util player=new Util();
			Queue<Integer> queue=new Queue<>();
			int j=1;
			for(int i=0;i<4;i++) {
				//queue.Enqueue(i+1);
			
				System.out.println("\nplayer "+j);
				for(int i1=0;i1<9;i1++) 
					player.cardGet();
				player.showCardrank();
				j++;
			}	
			
		} catch (Exception e) {

			e.printStackTrace();
		}



	}

}
