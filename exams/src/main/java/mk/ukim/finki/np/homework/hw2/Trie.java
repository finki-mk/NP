package mk.ukim.finki.np.homework.hw2;

public class Trie {
	private static final int SIZE = 28;
	private Trie[] children;
	private boolean isWord;
	
	public Trie(){
		children = new Trie[SIZE];
	}
	
	public void addString(String s) {
		int len = s.length();
		
	}
	
	private static int toIndex(char c) {
		return c - 'a';
	}
	
	private static char toChar(int i) {
		return (char)('a' + i);
	}
}
