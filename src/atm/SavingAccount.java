package atm;

public class SavingAccount extends Account {
    
    private static double interestRate = 0.001;
    
    public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance * (1 + interestRate), theTotalBalance * (1 + interestRate));
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double theInterestRate) {
		interestRate = theInterestRate;
	}

}
