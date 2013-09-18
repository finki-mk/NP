package edu.finki.np.ex1;

public class Word {
	private String word;
	private String lang;

	public Word(String word, String lang) {
		this.word = word;
		this.lang = lang;
	}

	public String getWord() {
		return word;
	}

	public String getLang() {
		return lang;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", word, lang);
	}

}

class WordLengthFilter implements Filter<Word, Integer> {

	@Override
	public boolean isFiltered(Word word, Integer i) {
		return word.getWord().length() <= i;
	}

}

class WordLanguageFilter implements Filter<Word, String> {

	@Override
	public boolean isFiltered(Word elem, String criterium) {
		return elem.getLang().equals(criterium);
	}

}
