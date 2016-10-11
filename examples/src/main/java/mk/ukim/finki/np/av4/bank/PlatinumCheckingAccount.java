package mk.ukim.finki.np.av4.bank;

public class PlatinumCheckingAccount extends Account implements
        InterestBearingAccount {

    public PlatinumCheckingAccount(String holderName, int number, double currentAmount) {
        super(holderName, number, currentAmount);
    }

    @Override
    public void addInterest() {
        addAmount(getCurrentAmount() * InterestCheckingAccount.INTEREST_RATE * 2);
    }

}
