package edu.finki.np.av3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
	public static void main(String[] args) {
		StringBuilder result = new StringBuilder();
		for (String filename : args) {
			String wordCount = processFile(filename);
			result.append(wordCount);
		}
		System.out.println(result.toString());
	}

	private static String processFile(String filename) {
		int linesCount = 0;
		int wordsCount = 0;
		int charactersCount = 0;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
			while(true){
				try {
					String line = bufferedReader.readLine();
					// end of steam
					if(line == null) break;
					++linesCount;
					charactersCount += line.length();
					wordsCount += line.split("").length;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("%d %d %d %s", wordsCount, linesCount, charactersCount, filename);
	}
}
