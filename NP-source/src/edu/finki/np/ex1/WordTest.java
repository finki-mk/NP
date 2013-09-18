package edu.finki.np.ex1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class WordTest {

	public static final String FILENAME = "words_lang.txt";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		String language = scanner.nextLine();
		int length = scanner.nextInt();
		List<Word> words = readWords(new FileInputStream(FILENAME));
		filterCollection(words, new WordLanguageFilter(), language);
		filterCollection(words, new WordLengthFilter(), length);
		for (Word w : words) {
			System.out.println(w);
		}
	}

	static List<Word> readWords(InputStream inputStream) {
		List<Word> words = new ArrayList<Word>();
		Scanner scanner = new Scanner(inputStream);
		String line;
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			String[] parts = line.split(" ");
			Word word = new Word(parts[0], parts[1]);
			words.add(word);
		}
		return words;
	}

	static <T> void filterCollection(List<Word> words, Filter<Word, T> filter,
			T criteria) {
		Iterator<Word> w = words.iterator();
		while (w.hasNext()) {
			if (filter.isFiltered(w.next(), criteria)) {
				w.remove();
			}
		}
	}
}
