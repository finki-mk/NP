package mk.ukim.finki.np.av2;

public class CombinationLock {
    private int combination;
    private boolean isOpen;

    public CombinationLock(int combination) {
        this.combination = combination;
        this.isOpen = false;
    }

    public boolean open(int combination) {
        this.isOpen = (this.combination == combination);
        return this.isOpen;
    }

    public boolean changeCombo(int combination, int newCombination) {
        boolean isCorrect = (this.combination == combination);
        if (isCorrect) {
            this.combination = newCombination;
        }
        return isCorrect;
    }

}
