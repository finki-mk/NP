package edu.finki.np.av2;
import java.util.Random;

public class Deck {
	public static final int SIZE = 52;
	
	private PlayingCard[] cards;
	private boolean[] used;
	private int totalUsed;

	public Deck() {
		cards = new PlayingCard[SIZE];
		int n = 0;
		for (PlayingCard.SUIT suit : PlayingCard.SUIT.values()) {
			for (int rank = PlayingCard.MIN_RANK; rank <= PlayingCard.MAX_RANK; rank++) {
				cards[n * (PlayingCard.MAX_RANK - PlayingCard.MIN_RANK + 1) + rank
						- 1] = new PlayingCard(suit, rank);
			}
			n++;
		}
		used = new boolean[SIZE];
		for(int i = 0; i < SIZE; i++) {
			used[i] = false;
		}
		totalUsed = 0;
	}
	
	public PlayingCard deal() {
		if(totalUsed < SIZE) {
			Random random = new Random();
			int cardIndex =random.nextInt(SIZE); 
			if(!used[cardIndex]) {
				used[cardIndex] = true;
				totalUsed++;
				return cards[cardIndex];
			} else {
				deal();
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(PlayingCard card : cards) {
			sb.append(card.toString());
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Deck deck = new Deck();
		System.out.println(deck);
		System.out.println("DEAL: " + deck.deal());
	}
}
