package edu.finki.np.av3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WordCount {
	
	public static void main(String[] args) {
		String fileName = args[0];
		try {
			System.out.printf("%s %s\n", count(System.in), fileName);
		} catch (IOException e) {
			System.out.printf("File %s can not be read\n", fileName);
			System.out.println(e.getMessage());
		}
	}

	public static String count(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		int lines = 0;
		int words = 0;
		int chars = 0;
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;
			++lines;
			String[] wordsArray = line.split("\\s+");
			words += wordsArray.length;
			chars += line.length();
		}
		return String.format("%5d %5d %5d", lines, words, chars);
	}
}
