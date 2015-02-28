/*
* 26-Feb-2015
* Student id 14000590887
* CS1003 Practical2 TextProcessing
*
* This class is a comparator that is useful for sorting a TreeMap numerically by value
* 
* this code was inspired by this Stack Overflow page 
* http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
*/

package model;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<String> {
	Map<String, Integer> base; // base is the original HashMap (nGramData) that needs to be sorted

	public ValueComparator(Map<String, Integer> base) {
		this.base = base; 
	}

	public int compare(String a, String b) {
		if (base.get(a) > base.get(b)) { // the values (occurrence) from the base HashMap
			return -1;
		} else if (base.get(a) == base.get(b)) { // if the values are equal then sort alphabetically by key (nGram)
			if (a.compareTo(b) <= 0) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
