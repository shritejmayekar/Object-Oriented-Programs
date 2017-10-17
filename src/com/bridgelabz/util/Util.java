package com.bridgelabz.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Util {
	public static int i,j;
	public static Object value, getPrice,getQuatitity,getTotal;
	static 	int [][] savecard=new int[4][13];
	public static String [] suit={"Clubs","Diamonds","Hearts","Spades"};
	public static String[] rank={"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"};
	static String[][] cards={{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
			{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
			{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},
			{"2","3","4","5","6","7","8","9","10","Jack", "Queen", "King", "Ace"},};
	static Scanner scanner=new Scanner(System.in);
	static Queue<String> queue=new Queue<String>();
/**
 * 	cradShuffle method will shuffle cards using  Random class
 */
	public static void cardShuffle() {
		
	
	
	int column=randomGeneratorColumn();
	int rows=randomGeneratorRow();
	if(cards[rows][column]==""){
		cardShuffle();
		return;
	}
		
		System.out.print("["+suit[rows]+" ");
		System.out.print(cards[rows][column]+"]");
		savecard[rows][column]=column+1;
		
		cards[rows][column]="";
		
		
	}
	/**
	 * 	cradGet method will shuffle cards using  Random class
	 */
		public  void cardGet() {
			
		
		
		int column=randomGeneratorColumn();
		int rows=randomGeneratorRow();
		if(cards[rows][column]==""){
			cardShuffle();
			return;
		}
			
			//System.out.print("["+suit[row]+" ");
			//System.out.print(cards[row][col]+"]");
			savecard[rows][column]=column+1;
			
			cards[rows][column]="";
			
			
		}
/**
 * showCarsSorted method will sort cards according to ranks
 */
	public static void showCardSorted() {
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				if(savecard[i][j]!=0) {
					System.out.print("["+suit[i]+" ");
					System.out.print(rank[j]+"]");
					savecard[i][j]=0;
				}
				
			}
			System.out.println();
		}
	}
	/**
	 * showCarsSorted method will sort cards according to ranks
	 */
		public  void showCardrank() {
			int counter=0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 13; j++) {
					if(savecard[i][j]!=0) {
						//System.out.print("["+suit[i]+" ");
						//System.out.print(rank[j]+"]");
						queue.Enqueue("["+suit[i]+" "+rank[j]+"]");
						savecard[i][j]=0;
					}
					
				}
				System.out.println();
			}
			while(counter<4) {
				System.out.println("\n1.want card remove\n2.exit");
				switch(scanner.nextInt()) {
				case 1:System.out.print(queue.Dequee());
					counter++;
					break;
				case 2:System.exit(0);
					break;
				default:System.out.println("invalid");
					break;
				}
			}
		}
	
/**
 * randomGeneratorColumn will generates column randomly	
 * @return
 */
	
	public static int randomGeneratorColumn() {
		Random random=new Random();
		return random.nextInt(13);
	}
/**
 * randomGeneratorRow will generates row randomly
 * @return
 */
	public static int randomGeneratorRow() {
		Random random=new Random();
		return random.nextInt(4);
	}
/**
 * fileCreate method will create .json  file if not exist
 * @throws IOException
 */
	public static void fileCreate() throws IOException {
		File file=new File("demoJson.json");
		if(file.createNewFile())
			System.out.println("succes");
		else
			System.out.println("file already exist");
		
	}
/**
 * inventory method will add products to the inventory
 * @throws IOException
 */
	public static void inventory() throws IOException {
		JSONObject jsonInventory=new JSONObject();
		Scanner scanner=new Scanner(System.in);
			System.out.println("please enter size of Inventory:");
			JSONArray jsonProductArray=new JSONArray();
			int sizeOfInventory=scanner.nextInt();
			
			for(int size=0;size<sizeOfInventory;size++) {
				JSONObject jsonObject=new JSONObject();
				System.out.println("enter the name");
				jsonObject.put("name", scanner.next());
				System.out.println("enter the quantity");
				jsonObject.put("quantity", scanner.nextInt());
				System.out.println("enter the price");
				
				jsonObject.put("price", scanner.nextInt());
				jsonProductArray.add(jsonObject);
			}
		
		jsonInventory.put("inventory",jsonProductArray);
		FileWriter fileWriter=new FileWriter("demoJson.json");
		fileWriter.write(jsonInventory.toString());
		System.out.println();
		fileWriter.close();
	
	}
/**
 * display method will display the .json file  contents to user
 * @throws FileNotFoundException
 * @throws IOException
 * @throws ParseException
 */
	public static void display() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=new JSONObject();
		
		value=jsonParser.parse(new FileReader("demoJson.json"));
		System.out.println(value);
		
		JSONObject object=(JSONObject) jsonParser.parse(new FileReader("demoJson.json"));
	
		JSONArray jsonArray= (JSONArray) object.get("inventory");
		for(int i=0;i<jsonArray.size();i++) {
			jsonObject=(JSONObject) jsonArray.get(i);
			getPrice= jsonObject.get("price");
			getQuatitity= jsonObject.get("quantity");
			getTotal=(long)getPrice*(long)getQuatitity;
			System.out.println("Total amount of "+jsonObject.get("name")+"="+getTotal);
		
		}
		
	}
}
