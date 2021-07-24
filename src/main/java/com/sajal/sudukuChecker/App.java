package com.sajal.sudukuChecker;
import java.io.File;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class App {
	private static final Logger logger = LogManager.getLogger(App.class);
	public static void main(String args[]) {
		if(args.length==0) {
			System.out.println("Invalid");
			logger.error("File Does not exists in command line argument");
		} else {
			String filePath = args[0];
			if(filePath == null) {
				System.out.println("Invalid");
				logger.error("File Does not exists in command line argument");
			} else {
				boolean run = true;
				int[][] data = new int[9][9]; 
				try {
					File file =  new File(filePath);
					Scanner sc = new Scanner(file);
					int r = 0;
					
					while (run && sc.hasNextLine()) {
						String line = sc.next();
						String elements[] = line.split(",");
						if(elements.length != 9) {
							System.out.println("Invalid");
							run = false;
							logger.error("Elements at line are not 9");
							break;
						} else {
							for(int i=0;i<9;i++) {
								try {
									int refElement = Integer.parseInt(elements[i]);
									if(refElement < 1 || refElement > 9) {
										System.out.println("Invalid");
										logger.error("Input value does not lie in the valid range from 1 to 9");
										run = false;
										break;
									}
									data[r][i] = refElement;
								} catch(Exception e) {
									System.out.println("Invalid");
									logger.error(e.getMessage());
									run = false;
									break;
								}
								
							}
						}
					}
				} catch(Exception e) {
					System.out.println("Invalid");
					logger.error("Issue with file IO");
					logger.error(e.getMessage());
				}
				
				if(run) {
					boolean isSudukoValid = true;
					// row checker
					for(int row = 0; isSudukoValid && row < 9; row++)
					   for(int col = 0;isSudukoValid &&  col < 8; col++)
					      for(int col2 = col + 1; isSudukoValid &&  col2 < 9; col2++)
					         if(data[row][col]==data[row][col2])
					        	 {
					        	 	isSudukoValid = false;
					        	 	System.out.println("Invalid");
					        	 	logger.error("ROW: Duplicate value found at row: " + row + " column: " + col2);
					        	 }

					// column checker
					for(int col = 0; isSudukoValid && col < 9; col++)
					   for(int row = 0;isSudukoValid &&  row < 8; row++)
					      for(int row2 = row + 1;isSudukoValid &&  row2 < 9; row2++)
					         if(data[row][col]==data[row2][col])
				        	 {
					        	 	isSudukoValid = false;
					        	 	System.out.println("Invalid");
					        	 	logger.error("COLUMN: Duplicate value found at row: " + row2 + " column: " + col);
					        	 }


					// grid checker
					for(int row = 0; isSudukoValid && row < 9; row += 3)
					   for(int col = 0;isSudukoValid &&  col < 9; col += 3)
					      // row, col is start of the 3 by 3 grid
					      for(int pos = 0;isSudukoValid &&  pos < 8; pos++)
					         for(int pos2 = pos + 1;isSudukoValid &&  pos2 < 9; pos2++)
					            if(data[row + pos%3][col + pos/3]==data[row + pos2%3][col + pos2/3])
					        	 {
					        	 	isSudukoValid = false;
					        	 	System.out.println("Invalid");
					        	 	logger.error("GRID: Duplicate value found at row: " + row + " column: " + col);
					        	 }
					
					if(isSudukoValid == true) {
						System.out.println("Valid");
					}

				}
			}
		}
		
	}
}
