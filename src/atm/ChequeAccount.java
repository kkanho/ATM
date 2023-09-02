package atm;

public class ChequeAccount extends Account{

    private static double limitPerCheque = 10000.00;

    public ChequeAccount( int theAccountNumber, int thePIN, 
        double theAvailableBalance, double theTotalBalance, double 
        theLimitPerCheque)
    {
        super(theAccountNumber,thePIN,theAvailableBalance,theTotalBalance);
        limitPerCheque = theLimitPerCheque;
    }
    
    public double getLimitPerCheque()
    {
        return limitPerCheque;
    }

    public void setLimitPerCheque(double newLimitPerCheque)
    {
        limitPerCheque = newLimitPerCheque;
    }
}
