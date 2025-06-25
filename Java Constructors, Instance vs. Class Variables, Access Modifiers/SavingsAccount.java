public class SavingsAccount extends BankAccount {
    public SavingsAccount(int number, String holder, double balance) {
        super(number, holder, balance);
    }

    public void displayAccountInfo() {
        System.out.println("Account No: " + accountNumber + ", Holder: " + accountHolder);
    }
}