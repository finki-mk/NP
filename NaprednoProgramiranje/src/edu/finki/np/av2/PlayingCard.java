package edu.finki.np.av2;

public class PlayingCard {
	public static final int MAX_RANK = 13;
	public static final int MIN_RANK = 1;
	public enum SUIT {
		HEARTS,
		DIAMONDS,
		CLUBS,
		SPADES
	}
	
	private SUIT suit;
	private int rank;

	public PlayingCard(SUIT suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.valueOf(rank));
		if(this.suit == SUIT.HEARTS) {
			sb.append("H");
		} else if(this.suit == SUIT.DIAMONDS) {
			sb.append("D");
		} else if(this.suit == SUIT.CLUBS) {
			sb.append("C");
		} else if(this.suit == SUIT.SPADES) {
			sb.append("S");
		}
		return sb.toString();
	}
}
