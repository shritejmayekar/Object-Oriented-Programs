package com.bridgelabz.util;

import java.util.Random;

public class Util {
	public static int i,j;
	public static String [][] savecard=new String[4][13];
	public static void cardShuffle() {

		String [] suit={"Clubs","Diamonds","Hearts","Spades"};
		String[] rank={"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"};
		String[][] cards={{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
				{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
				{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
				{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},};
	int col=randomGeneratorColumn();
	int row=randomGeneratorRow();
	if(cards[row][col]==""){
		cardShuffle();
		return;
	}
		System.out.print(suit[row]+" ");
		System.out.print(cards[row][col]+" "+" rank="+(col+1));
		//savecard[row][col]=cards[row][col];
		System.out.print("|");
		cards[row][col]="";
		
		
	}
	public static void show() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 13; j++) {
				System.out.print("["+savecard[i][j]+"]");
			}
			System.out.println();
		}
	}
	
	
	public static int randomGeneratorColumn() {
		Random random=new Random();
		return random.nextInt(13);
	}
	public static int randomGeneratorRow() {
		Random random=new Random();
		return random.nextInt(4);
	}

}
