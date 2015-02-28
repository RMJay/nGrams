/*
* 26-Feb-2015
* Student id 14000590887
* CS1003 Practical2 TextProcessing
*
* This is the class with the Main method"
*/

import model.Text;

import java.io.*;

import exceptions.InvalidArgumentException;

public class Ngrams {

	public static void main(String[] args) {
		
		try{
			// Parse the command line arguments
			File document = document(args);
			int n = nArg(args);
			
			//generate and store nGrams
			Text.nGrams(n, document, false); // false = don't print the word parsing to the terminal
			
			//create output csv file
			Text.outputReport(document);
			
		} catch (FileNotFoundException e) {
			System.err.println("Document can't be found:");
		} catch (InvalidArgumentException | NumberFormatException e) {
			System.err.println("Invalid command line argument:");
			System.err.println("expected args: [nGram num] [filePath]");
			System.err.println("eg: java TextProcessing 2 \"My file.txt\"");
			System.err.println("NB. remember to enclose your file path in quotes if it has spaces");
		}
	}
	
	// get the second command line argument and interpret it as a filepath
	private static File document(String[] args) throws InvalidArgumentException{ 
		
		File document;
		
		if (args.length == 2){
			document = new File(args[1]);
		} else {
			throw new InvalidArgumentException();
		} 
		
		return document;
		
	}

	// get the first command line argument and interpret it as the nGram N-number
	private static int nArg(String[] args) throws NumberFormatException{
		int n = Integer.parseInt(args[0]);
		if (n < 1){
			throw new NumberFormatException();
		}
		return n;
		
	}
		
}
