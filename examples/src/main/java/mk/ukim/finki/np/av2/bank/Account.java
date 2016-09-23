package mk.ukim.finki.np.av2.bank;

public abstract class Account {
    
    private String holderName;
    private int number;
    private double currentAmount;

    public Account(String holderName, int number, double currentAmount) {
        this.holderName = holderName;
        this.number = number;
        this.currentAmount = currentAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void addAmount(double amount) {
        currentAmount += amount;
    }

    public void withdrawAmount(double amount) {
        currentAmount -= amount;
    }

}
