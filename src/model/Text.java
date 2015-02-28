/*
* 26-Feb-2015
* Student id 14000590887
* CS1003 Practical2 TextProcessing
*
* This class contains the main text processing methods for generating/outputting nGram information
*/

package model;

import java.io.*;
import java.util.*;

public class Text {

	private static Map<String, Integer> nGramData = new HashMap<String, Integer>(); // key is the string of the nGram, value is the occurrence
	private static Map<String, Integer> keySortednGramData; // key is the string of the nGram, value is the occurrence
	private static Map<String, Integer> valueSortednGramData; // key is the string of the nGram, value is the occurrence
	
	public static List<List<String>> nGrams(int n, File document) // the return List is the list of parsed words - only used for testing 
			throws FileNotFoundException {
		
		// clear all three maps because otherwise they would remember data from previous files
		nGramData.clear();
		if(keySortednGramData != null){
			keySortednGramData.clear();
		}
		if(valueSortednGramData != null){
			valueSortednGramData.clear();
		}
		
		Scanner textScanner = new Scanner(new FileReader(document));
	
		List<List<String>> parsedLines = new ArrayList<List<String>>();
		
		while (textScanner.hasNext()) {
			String line = textScanner.nextLine();
			List<String> parsedWords = new ArrayList<String>();
			String[] words = line.split("[^a-zA-Z-’']|[-’']{2,}"); // correctly delimit into words and remove illegal characters
			for (String word : words) { // for each word in the line
				word = word.toLowerCase();
				
				// the split method might have left apostrophes and hyphens at the start/end of words
				word = word.replaceAll("^\\W+", ""); // this method gets rid of any it finds at the beginning of each word
				word = word.replaceAll("\\W$+", ""); // this method gets rid of any it finds at the end or each word
				
				if(word.length() == 0){
					continue;
				}
				
				analyseWord(n, word); // this method generates nGram data and puts it in the HashMap
				
				parsedWords.add(word);
			}
			parsedLines.add(parsedWords);
			
		}
		textScanner.close();
		
		// create the ordered maps
		keySortednGramData(); 
		valueSortednGramData();
		
		return parsedLines;
	}

	private static void analyseWord(int n, String word) { 
		int numberOfnGrams = word.length() - n + 1; // number of nGrams that fit in the word
		if (numberOfnGrams >= 1) { // only create nGrams for words that are long enough to contain any nGrams
			for (int i = 0; i < numberOfnGrams; i++) { // generate and record nGrams
				String nGram = word.substring(i, i + n);
				recordnGram(nGram); // put the nGrams in the HashMap
			}
		}
	}

	// put the nGram into the HashMap with its occurrence value
	private static void recordnGram(String nGram) {

		if (nGramData.containsKey(nGram)) { // first check if the nGram already exists in the HashMap
			int qty = nGramData.get(nGram); // get the quantity of occurrences so far
			qty++;  
			nGramData.put(nGram, qty); // put the updated data back in the HashMap
		} else {
			nGramData.put(nGram, 1); // if the nGram didn't already exist record the nGram with a quantity of 1
		}

	} 

	public static String mostCommonNGram(){ 
		return valueSortednGramData.keySet().iterator().next(); // gets the key(nGram) of the first element from the valueSorted (occurrence sorted) Map
	}
	
	public static int mostCommonNGramOccurrance(){
		return nGramData.get(mostCommonNGram()); // gets the value(occurrence) corresponding to the most common nGram
	}
	
	// create output csv file
	public static void outputReport(File document) throws FileNotFoundException {
				
		int n = nGramData.keySet().iterator().next().length(); // checks nGram length of the first element in the HashMap to figure out the nGram number
		
		String name = document.getName();
		String outputFilePath = "Output/"+ n +"Grams_" + name;
		
		PrintWriter report = new PrintWriter(outputFilePath);
		
		report.println(n + "Gram,count");
		
		Iterator<String> iter = valueSortednGramData.keySet().iterator();
		
		while(iter.hasNext()){
			String nGram = iter.next();
			Integer occurrence = nGramData.get(nGram);
			report.print(nGram + "," + occurrence);
			report.println();
			
		}
		
		report.close();
		
	}
	
	// take the data from the HashMap "nGramData", order it by key(nGram) and store it in the static field "keySortednGramData"
	private static void keySortednGramData() {
		
		Map<String, Integer> keySorted = new TreeMap<String, Integer>();
		keySorted.putAll(nGramData);
		keySortednGramData = keySorted;
	}

	// take the data from the HashMap "nGramData", order it by value(occurrence) and store it in the static field "valueSortednGramData"
	private static void valueSortednGramData() {

		ValueComparator vc = new ValueComparator(nGramData);
		Map<String, Integer> valueSorted = new TreeMap<String, Integer>(vc); //order the TreeMap by the comparator rules

		valueSorted.putAll(nGramData);
		valueSortednGramData = valueSorted;
		
	}

}
