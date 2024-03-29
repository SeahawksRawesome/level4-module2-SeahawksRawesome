package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length() > s2.length()) {
			return s1;
		}
		if(s2.length() > s1.length()) {
			return s2;
		}
		else {
			return "equal";
		}
		//return null;
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		String s2 = s;
		if(s.contains("underscores")) {
			 s2 = s.replace(" ", "_");
			/*for(int i = 0; i < s.length() -1; i++) {
				if(s.substring(i, i+1).equals(" ")) {
					s.replace(s.charAt(i), '_');
					
				}
			}
			*/
		}
	
		return s2;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		char one = 0;
		char two = 0;
		char three = 0;
		for(int i = s1.length() -1; i > 0; i--) {
			if(s1.charAt(i) != ' ') {
				one = s1.charAt(i);
				break;
			}
		}
		for(int i = s2.length() -1; i > 0; i--) {
			if(s2.charAt(i) != ' ') {
				two = s2.charAt(i);
				break;
			}
		}
		for(int i = s3.length() -1; i > 0; i--) {
			if(s3.charAt(i) != ' ') {
				three = s3.charAt(i);
				break;
			}
		}
	String s11 =	s1.trim();
	String s22 =	s2.trim();
	String s33 =	s3.trim();
		if(Character.getNumericValue(one) < Character.getNumericValue(two)) {
			if(Character.getNumericValue(one) < Character.getNumericValue(three)) {
				return s11;
			}
		}
		if(Character.getNumericValue(two) < Character.getNumericValue(one)) {
			if(Character.getNumericValue(two) < Character.getNumericValue(three)) {
				return s22;
			}
		}
		if(Character.getNumericValue(three) < Character.getNumericValue(two)) {
			if(Character.getNumericValue(three) < Character.getNumericValue(one)) {
				return s33;
			}
		}
		return "";
		
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int total = 0;
		char converter;
		int numba;
		for(int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				numba = Character.getNumericValue(s.charAt(i));
				total += numba;
			}
		}
		return total;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int total = 0;
		for(int i = 0; i < s.length() - substring.length() + 1; i++) {
			if(s.substring(i, i + substring.length()).equals(substring)) {
				total++;
			}
		}
		return total;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		String s1 = Utilities.encrypt(s.getBytes(), (byte) key);
		return s1;
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		String s1 = Utilities.decrypt(s, (byte) key);
		return s1;
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int total = 0;
		for(int i = 0; i < s.length() - substring.length(); i++ ) {
			if(s.substring(i, i + substring.length() + 1).endsWith(substring + " ")) {
				total++;
			}
		}
		return total;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int length = 0;
		boolean first = false;
		for(int i = 0; i < s.length() - substring.length(); i++) {
			if(s.substring(i, i+substring.length()).equals(substring)) {
				length = s.length() - i-substring.length();
				break;
			}
			
		}
		return length - substring.length();
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		s.trim();
		s = s.toLowerCase();
		String arr = "";
		String arr2 = "";
		
		
		for(int i = 0; i < s.length(); i++) {
			if(Character.isLetter(s.charAt(i))) {
				//Character.toLowerCase(s.charAt(i));
				arr += s.substring(i, i+1);
				
			}
		}

		for(int i = s.length() -1; i >= 0; i--) {
			if(Character.isLetter(s.charAt(i))) {
				//Character.toLowerCase(s.charAt(i));
				arr2 += s.substring(i, i+ 1);
				
			}
		}
		System.out.println(arr);
		System.out.println(arr2);
		if(arr.equals(arr2)) {
			return true;
		}
		return false;
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
