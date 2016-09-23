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

    public boolean changeCombo(int oldCombination, int newCombination) {
        boolean isCorrect = (this.combination == oldCombination);
        if (isCorrect) {
            this.combination = newCombination;
        }
        return isCorrect;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
