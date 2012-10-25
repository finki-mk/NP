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
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				linesCount++;
				String[] words = line.split("\\s+");
				wordsCount += words.length;
				charactersCount += line.length();
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return String.format("%d %d %d\n", linesCount, wordsCount,
				charactersCount);
	}
}
