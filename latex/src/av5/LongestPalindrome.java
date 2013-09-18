package edu.finki.np.av5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LongestPalindrome {
	public static final String FILENAME = "words.txt";

	public static void main(String[] args) {
		BufferedReader fileReader = null;
		String longest = null;
		try {
			fileReader = new BufferedReader(new FileReader(FILENAME));
			String word;
			while ((word = fileReader.readLine()) != null) {
				if(isPalindrome(word)) {
					if(longest == null) {
						longest = word;
					} else {
						if(word.length() > longest.length()) {
							longest = word;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(longest);
	}
	
	static boolean isPalindrome(String word) {
		int len = word.length();
		for(int i = 0; i < len / 2; i++){
			if(word.charAt(i) != word.charAt(len - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}
