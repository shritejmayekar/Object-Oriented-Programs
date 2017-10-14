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
package com.bridgelabz.programs;



import com.bridgelabz.util.Queue;
import com.bridgelabz.util.Util;

public class DeckOfCardQueue {

	public static void main(String[] args) {
		Util player=new Util();
		Queue<Integer> queue=new Queue<>();
		int j=1;
		for(int i=0;i<4;i++)
			queue.Enqueue(i+1);
		while(j<5) {
			System.out.println("player "+j);
			for(int i=0;i<9;i++)
				player.cardGet();
				player.showCardrank();
				j++;
		}
		
		

	}

}
