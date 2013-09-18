package edu.finki.np.av2;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
	public static final int SIZE = 52;
	
	private PlayingCard[] cards;
	private boolean[] isUsed;
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
		isUsed = new boolean[SIZE];
		for(int i = 0; i < SIZE; i++) {
			isUsed[i] = false;
		}
		totalUsed = 0;
	}
	
	public void shuffle() {
		List<PlayingCard> lista = Arrays.asList(cards);
		Collections.shuffle(lista);
		cards = lista.toArray(cards);
	}
	
	public PlayingCard deal() {
		if(totalUsed < SIZE) {
			Random random = new Random();
			int cardIndex =random.nextInt(SIZE); 
			if(!isUsed[cardIndex]) {
				isUsed[cardIndex] = true;
				totalUsed++;
				return cards[cardIndex];
			} else {
				return deal();
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
		deck.shuffle();
		System.out.println(deck);
		/*for(int i = 0; i < SIZE; i++) {
			System.out.println("DEAL: " + deck.deal());			
		}*/
	}
}
