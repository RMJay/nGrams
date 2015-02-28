/*
* 26-Feb-2015
* Student id 14000590887
* CS1003 Practical2 TextProcessing
*
* Three JUnit tests are conducted on a test file "src/test/obnoxiousWords.txt"
*/

package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import model.Text;

import org.junit.Test;

public class JUnit {

	@Test
	public void test1() { // Word parsing is printed to the terminal output so you can see if the parsing is working properly
		File document = new File("src/test/obnoxiousWords.txt");
		try {
			
			for (List<String> line : Text.nGrams(1, document)){ // It makes no difference that n=1, it could be anything
				for(String word: line){
					System.out.print(word + ".");
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			
		}
	}
	
	@Test
	public void test2() {
		File document = new File("src/test/obnoxiousWords.txt");
		try {
			Text.nGrams(2, document);
			String mostCommon2Gram = Text.mostCommonNGram();
			int mostCommon2GramOccurance = Text.mostCommonNGramOccurrance();
			
			assertEquals(mostCommon2Gram, "or");
			assertEquals(mostCommon2GramOccurance, 10);
			
		} catch (FileNotFoundException e) {
			
		}
	}
	
	@Test
	public void test3() {
		File document = new File("src/test/obnoxiousWords.txt");
		try {
			Text.nGrams(3, document);
			String mostCommon3Gram = Text.mostCommonNGram();
			int mostCommon3GramOccurance = Text.mostCommonNGramOccurrance();
			
			assertEquals(mostCommon3Gram, "ord");
			assertEquals(mostCommon3GramOccurance, 8);
			
		} catch (FileNotFoundException e) {
			
		}
	}

}
