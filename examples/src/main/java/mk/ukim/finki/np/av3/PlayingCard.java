package mk.ukim.finki.np.av3;

public class PlayingCard {
    public enum TYPE {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUBS
    }

    private TYPE type;
    private int rank;

    public PlayingCard(TYPE type, int rank) {
        this.type = type;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.format("%d %s", rank, type.toString());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rank;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayingCard other = (PlayingCard) obj;
        if (rank != other.rank)
            return false;
        if (type != other.type)
            return false;
        return true;
    }


}
