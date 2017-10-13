package com.bridgelabz.programs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StockReport {
// Stock Names, Number of Share, Share Price. Print a Stock Report with total value of each Stock and the total value of Stock.
	public  int numberOfShare,totalValueOfStock,totalValue;
	public  int  sharePrice;
	public  String  stockName;
	public  void addStock() throws IOException {
		Scanner scanner=new Scanner(System.in);
		System.out.print("please enter the stock name:");
		stockName=scanner.next();
		System.out.println("please enter the number of share");
		numberOfShare=scanner.nextInt();
		System.out.println("price of share");
		sharePrice=scanner.nextInt();
		File file=new File("stockreport.txt");
		if(file.createNewFile())
			System.out.println("file created");
		else
			System.out.println("file is exits");
		FileWriter fileWriter=new FileWriter(file);
		fileWriter.write(stockName+" "+numberOfShare+" "+sharePrice);
		fileWriter.close();
	}
	
	public static void main(String[] args) throws IOException {
		StockReport stockReport1=new StockReport();
		StockReport stockReport2=new StockReport();
		stockReport1.addStock();
		stockReport2.addStock();
	}

}
