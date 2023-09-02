package atm;
// BankDatabase.java
// Represents the bank account information database 

public class BankDatabase
{
   private Account accounts[]; // array of Accounts

   // no-argument BankDatabase constructor initializes accounts
   public BankDatabase()
   {
      accounts = new Account[ 4 ]; // just 2 accounts for testing
      accounts[ 0 ] = new Account( 12345, 54321, 1000.0, 1200.0 );
      accounts[ 1 ] = new Account( 98765, 56789, 200.0, 200.0 ); 
      //2 more accounts for testing
      accounts[ 2 ] = new ChequeAccount(87654, 45678, 1000, 1400, 0);
      accounts[ 3 ] = new SavingAccount(23456, 65432, 3000, 3000);
      
   } // end no-argument BankDatabase constructor
   
   // retrieve Account object containing specified account number
   private Account getAccount( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( Account currentAccount : accounts )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for
      return null; // if no matching account was found, return null
   } // end method getAccount

   public String getAccountInfo(int accountNumber){
      switch(getAccount(accountNumber).getClass().getName().toString()){
         case "ChequeAccount":return "Cheque Account";
         case "SavingAccount":return "Saving Account";
         case "Account":return "Normal Account";
         default: return "Normal Account";
      }
   }

   public boolean accountexist( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( Account currentAccount : accounts )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber ){
            return true; 
         }
      } // end for
      return false; // if no matching account was found, return false
   } // end method accountexist



   // determine whether user-specified account number and PIN match
   // those of an account in the database
   public boolean authenticateUser( int userAccountNumber, int userPIN )
   {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount( userAccountNumber );

      // if account exists, return result of Account method validatePIN
      if ( userAccount != null )
         return userAccount.validatePIN( userPIN );
      else
         return false; // account number not found, so return false
   } // end method authenticateUser

   // return available balance of Account with specified account number
   public double getAvailableBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getAvailableBalance();
   } // end method getAvailableBalance

   // return total balance of Account with specified account number
   public double getTotalBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getTotalBalance();
   } // end method getTotalBalance

   // credit an amount to Account with specified account number
   public void credit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).credit( amount );
   } // end method credit

   // debit an amount from of Account with specified account number
   public void debit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).debit( amount );
   } // end method debit

   // transfer an amount from Account to Transfer account with specified account and transfer account number
   public void transfer( int userAccountNumber, double amount,int touserAccountNumber )
   {
      getAccount( userAccountNumber ).transferfrom(amount);
      getAccount(touserAccountNumber).transferto(amount);
   } // end method transfer


} // end class BankDatabase