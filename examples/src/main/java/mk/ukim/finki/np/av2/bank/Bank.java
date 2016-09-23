package mk.ukim.finki.np.av2.bank;

public class Bank {
    private Account[] accounts;
    private int totalAccounts;
    private int max;

    public Bank(int max) {
        this.totalAccounts = 0;
        this.max = max;
        accounts = new Account[max];
    }

    public void addAccount(Account account) {
        if (totalAccounts == accounts.length) {
            Account[] old = accounts;
            max *= 2;
            accounts = new Account[max];
            for (int i = 0; i < old.length; i++) {
                accounts[i] = old[i];
            }
        }
        accounts[totalAccounts++] = account;
    }

    public double totalAssets() {
        double sum = 0;
        for (Account account : accounts) {
            sum += account.getCurrentAmount();
        }
        return sum;
    }

    public void addInterest() {
        for (Account account : accounts) {
            if (account instanceof InterestBearingAccount) {
                InterestBearingAccount iba = (InterestBearingAccount) account;
                iba.addInterest();
            }
        }
    }
}
