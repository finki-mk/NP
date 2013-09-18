package edu.finki.np.av2;

public class MultipleDeck {
	private Deck[] decks;
	
	public MultipleDeck(int size) {
		decks = new Deck[size];
		for(int i = 0; i < size; i++) {
			decks[i] = new Deck();
		}
	}
}
