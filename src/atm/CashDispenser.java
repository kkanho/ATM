package atm;
// CashDispenser.java
// Represents the cash dispenser of the ATM

public class CashDispenser 
{
   // the default initial number of bills in the cash dispenser
   private final static int INITIAL_COUNT = 500;
   private int count100, count500, count1000; // number of $100, $500, $1000 bills remaining
   
   // no-argument CashDispenser constructor initializes count to default
   public CashDispenser()
   {
      count100 = INITIAL_COUNT;
      count500 = INITIAL_COUNT;
      count1000 = INITIAL_COUNT;// set count attribute to default
   } // end CashDispenser constructor

   // simulates dispensing of specified amount of cash
   public void dispenseCash( int amount )
   {
       int billsRequired100=0, billsRequired500=0, billsRequired1000=0;// Set up variables about the number of bills needed for $100, $500, $1000
       if(amount >= 1000){// When requested amount requried $1000 bills
           
        billsRequired1000=amount/1000-amount%1000/1000;// Calculate number of $1000 bills needed
        amount -= billsRequired1000*1000;// Calculate how much more amount is needed after subtracting the value of $1000 bills withdrawned
        
        billsRequired500=amount/500-amount%500/1000;// Calculate number of $500 bills needed
        amount -= billsRequired500*500;// Calculate how much more amount is needed after subtracting the value of $500 bills withdrawned
        
        billsRequired100=amount/100-amount%100/1000;// Calculate number of $100 bills needed
        
        }else if(amount>=500){// When requested amount requried $500 bills
            
            billsRequired500=amount/500-amount%500/1000;// Calculate number of $500 bills needed
            amount -= billsRequired500*500;// Calculate how much more amount is needed after subtracting the value of $500 bills withdrawned
            
            billsRequired100=amount/100-amount%100/1000;// Calculate number of $100 bills needed                
        
        } else { // When requested amount only requried $100 bills
            billsRequired100=amount/100-amount%100/1000;// Calculate number of $100 bills needed
        }
        
        
      count100 -= billsRequired100;
      count500 -= billsRequired500;
      count1000 -= billsRequired1000;// update the count of bills
    
    }// end method dispenseCash

   // indicates whether cash dispenser can dispense desired amount
   public boolean isSufficientCashAvailable( int amount )
   {
      int billsRequired100=0, billsRequired500=0, billsRequired1000=0;
       if(amount >= 1000){// When requested amount requried $1000 bills
           
        billsRequired1000=amount/1000-amount%1000/1000;// Calculate number of $1000 bills needed
        amount -= billsRequired1000*1000;// Calculate how much more amount is needed after subtracting the value of $1000 bills withdrawned
        
        billsRequired500=amount/500-amount%500/1000;// Calculate number of $500 bills needed
        amount -= billsRequired500*500;// Calculate how much more amount is needed after subtracting the value of $500 bills withdrawned
        
        billsRequired100=amount/100-amount%100/1000;// Calculate number of $100 bills needed
        
        }else if(amount>=500){// When requested amount requried $500 bills
            
            billsRequired500=amount/500-amount%500/1000;// Calculate number of $500 bills needed
            amount -= billsRequired500*500;// Calculate how much more amount is needed after subtracting the value of $500 bills withdrawned
            
            billsRequired100=amount/100-amount%100/1000;// Calculate number of $100 bills needed                
        
        } else { // When requested amount only requried $100 bills
            billsRequired100=amount/100-amount%100/1000;// Calculate number of $100 bills needed
        }              
      if ( count100 >= billsRequired100&&count500 >= billsRequired500&&count1000 >= billsRequired1000  )
         return true; // enough bills available
      else 
         return false; // not enough bills available
   } // end method isSufficientCashAvailable
} // end class CashDispenser