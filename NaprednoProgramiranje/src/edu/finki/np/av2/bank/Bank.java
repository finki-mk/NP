package edu.finki.np.av2.bank;

public class Bank {
	private Account[] accounts;
	private int totalAccounts;
	
	public Bank(int totalAccounts) {
		this.totalAccounts = totalAccounts;
		 accounts = new Account[totalAccounts];
	}
}
