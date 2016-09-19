package mk.ukim.finki.np.av2.bank;

public class PlatinumCheckingAccount extends Account implements
        InterestBearingAccount {

    public PlatinumCheckingAccount(String holderName, int number,
                                   double currentAmount) {
        super(holderName, number, currentAmount);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addInterest() {
        addAmount(getCurrentAmount() * InterestCheckingAccount.INTEREST_RATE
                * 2);
    }

}
