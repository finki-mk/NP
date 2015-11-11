package edu.finki.np.av2;

public class MultipleDeck {
	private Deck[] decks;
	
	public MultipleDeck(int n) {
		decks = new Deck[n];
		for(int i = 0; i < n; ++i) {
			decks[i] = new Deck();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Deck deck : decks) {
			sb.append(deck);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		MultipleDeck md = new MultipleDeck(3);
		System.out.println(md);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
