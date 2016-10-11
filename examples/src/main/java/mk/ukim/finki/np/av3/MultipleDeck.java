package mk.ukim.finki.np.av3;

public class MultipleDeck {
    private Deck[] decks;

    public MultipleDeck(int n) {
        decks = new Deck[n];
        for (int i = 0; i < n; ++i) {
            decks[i] = new Deck();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Deck deck : decks) {
            sb.append(deck);
            sb.append("\n");
        }
        return sb.toString();
        // java 8
        /*
        return Arrays.stream(decks)
                .map(Deck::toString)
                .collect(Collectors.joining("\n"));
         */
    }

    public static void main(String[] args) {
        MultipleDeck md = new MultipleDeck(3);
        System.out.println(md);
    }


}
