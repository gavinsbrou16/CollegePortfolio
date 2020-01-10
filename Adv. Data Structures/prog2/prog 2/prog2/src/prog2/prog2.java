package prog2;

import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This is the main class
 * @author mishawnlolis, LSU ID: 89-127-3262
 * @CoAuthor GavinBroussard, LSU ID: 89-589-0468
 *
 */
public class prog2 {

/**
 * Main Method, Puts everythng together
 * @param args
 * @throws FileNotFoundException 
 */
	public static void main(String[] args) throws FileNotFoundException {

		/*
		//inputScanner
		System.out.println("Please choose the file you would like to use for import (Sample File = 'in.txt')");
		Scanner inputScanner = GetInputFile(null); 
		//outputScanner
		String text = "";
		
		//Filling in text
		while (inputScanner.hasNext()) {
			text = text + " " + inputScanner.nextLine();
		}
		*/
		
		String text = "What kind of cars do you eat? I eat only red cars. If it's not red, I send it back";
		
		hTrie.buildTree(text);
		
		System.out.println("Process Complete. Thank you!");
		
	}
	
/** Gets Input File	
 * @param UserPrompt
 * @return
 * @throws FileNotFoundException
 */
	
	/*
	public static Scanner GetInputFile(String UserPrompt) throws FileNotFoundException {
		//variable Initialization
		Scanner input = new Scanner(System.in); //Scanner that will be returned
		String inputString; //The string used to store variables
		File file; // The file
		System.out.println("Enter input filename: ");//Enter file
		inputString = input.nextLine();
		file = new File(inputString);//Making file

			try {
				Scanner inputScan = new Scanner(file);
				return inputScan;
			}
			catch (FileNotFoundException e){
				System.out.println("File specified '"+ inputString + "' does not exist. Would you like to continue? <Y/N>");
				String choice = input.nextLine();
				while (true) {
					if (choice.equalsIgnoreCase("Y")) {
						return GetInputFile(null);
					}
					else if (choice.equalsIgnoreCase("N")) {
						throw e;
					}
					else {
						System.out.println("Please choose 'Y/N'");
						choice = input.nextLine();
					}
				}
			}
	}

*/
}
