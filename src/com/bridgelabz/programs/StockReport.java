/******************************************************************************
 *  
 *  Purpose: To add stocks in .json file and also value of each stock and 
 *  			total value of all stocks
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

import java.io.IOException;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

import com.bridgelabz.util.StockPortfolio;

public class StockReport {

	static Scanner scanner=new Scanner(System.in);
	public static void main(String[] args) throws IOException, ParseException {
		StockPortfolio stockPortfolio=new StockPortfolio();
		while(true) {
			System.out.println("Menu\n1.Add stocks\n2.Dispalay report\n3.exit");
			switch (scanner.nextInt()) {
			case 1:
					stockPortfolio.addStock();
				break;
			case 2:
					stockPortfolio.stockReports();
				break;
			case 3:System.exit(0);
					break;

			default:
				break;
			}
			
			
			
			
		}

	
	}

}
