package edu.finki.np.av2.bank;

public class InterestCheckingAccount 
extends Account implements InterestBearingAccount {
	
	public static final double INTEREST_RATE = .03; // 3%

	public InterestCheckingAccount(String holderName, int number,
			double currentAmount) {
		super(holderName, number, currentAmount);
	}

	@Override
	public void addInterest() {
		addAmount(getCurrentAmount() * INTEREST_RATE);
	}

}
