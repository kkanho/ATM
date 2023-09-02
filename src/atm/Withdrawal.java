package atm;
// Withdrawal.java
// Represents a withdrawal ATM transaction
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Withdrawal extends Transaction implements ActionListener
{
  private int amount; // amount to withdraw
  private CashDispenser cashDispenser; // reference to cash dispenser
  private int amountToWithdrawal;
  
  
  private JFrame withdrawalFrame;
  private JButton withdrawalButton200, withdrawalButton400, withdrawalButton800, withdrawalButton1000, withdrawalButton2000, withdrawalButtonCustom, withdrawalButtonReturn;
  private JButton withdrawalButtonClose;
  private JButton confirmButtonCancel, confirmButtonMenu, confirmButtonLogout;
  private JButton customButtonSubmit;
  private JButton messageButtonLogout;
  private JPanel withdrawalPanel, messagePanel, customPanel, withdrawalConfirmPanel;
  private JLabel withdrawalLabel, confirmLabel, confirmLabelamount;
  private JTextField customNumber;

  // Withdrawal constructor
  public Withdrawal( int userAccountNumber, ATM atmScreen, 
      BankDatabase atmBankDatabase, 
      CashDispenser atmCashDispenser )
  {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
      
      // initialize variables
      cashDispenser = atmCashDispenser;
  } // end Withdrawal constructor
  
    // method execute
  public void execute()
  {
      withdrawalScreen();
  } // end method execute
  
    
  //Mainscreen of withdrawal
  public void withdrawalScreen(){
      
    ATM screen = getScreen(); // get screen reference
      
      //Dispose all previous opened JFrame
      screen.disposeAllScreen();
      
      //Create new JFrame and JPanel for withdrawal
      withdrawalFrame = new JFrame();
      withdrawalFrame.setResizable(false);
      withdrawalPanel = new JPanel();
      
      //initialize JFrame
      withdrawalFrame.setSize(400,370);
      withdrawalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      withdrawalFrame.setTitle("Withdrawal");
      withdrawalFrame.setLocationRelativeTo(null);
      
      //initialize all the JButtons
      withdrawalButton200 = new JButton("$200");
      withdrawalButton200.setFocusable(false);
      withdrawalButton200.addActionListener(this);
      withdrawalButton400 = new JButton("$400");
      withdrawalButton400.setFocusable(false);
      withdrawalButton400.addActionListener(this);
      withdrawalButton800 = new JButton("$800");
      withdrawalButton800.setFocusable(false);
      withdrawalButton800.addActionListener(this);
      withdrawalButton1000 = new JButton("$1000");
      withdrawalButton1000.setFocusable(false);
      withdrawalButton1000.addActionListener(this);
      withdrawalButton2000 = new JButton("$2000");
      withdrawalButton2000.setFocusable(false);
      withdrawalButton2000.addActionListener(this);
      withdrawalButtonCustom = new JButton("Custom");
      withdrawalButtonCustom.setFocusable(false);
      withdrawalButtonCustom.addActionListener(this);
      withdrawalButtonReturn = new JButton("Return");
      withdrawalButtonReturn.setFocusable(false);
      withdrawalButtonReturn.addActionListener(this);
      
      //set size and pos of the buttons
      withdrawalButton200.setBounds(0,10,200,80);
      withdrawalButton400.setBounds(200,10,200,80);
      withdrawalButton800.setBounds(0,90,200,80);
      withdrawalButton1000.setBounds(200,90,200,80);
      withdrawalButton2000.setBounds(0,170,200,80);
      withdrawalButtonCustom.setBounds(200,170,200,80);
      withdrawalButtonReturn.setBounds(0,250,400,80);
      
      //add buttons to panel
      withdrawalPanel.setLayout(null);
      withdrawalPanel.add(withdrawalButton200);
      withdrawalPanel.add(withdrawalButton400);
      withdrawalPanel.add(withdrawalButton800);
      withdrawalPanel.add(withdrawalButton1000);
      withdrawalPanel.add(withdrawalButton2000);
      withdrawalPanel.add(withdrawalButtonCustom);
      withdrawalPanel.add(withdrawalButtonReturn);
      
      //add panel to frame
      withdrawalFrame.add(withdrawalPanel, BorderLayout.CENTER);
      
      //set frame to visible
      withdrawalFrame.setVisible(true);
      withdrawalFrame.setLocationRelativeTo(null);
    }
    
    
    
    
  private void customScreen(){
        
      //remove the last panel on frame
      withdrawalFrame.remove(withdrawalPanel);
      withdrawalFrame.setLocationRelativeTo(null);

      //new component for the panel 
      customPanel = new JPanel();
      withdrawalLabel = new JLabel("Please type the amount to withdraw.");
      customNumber = new JTextField();
      customButtonSubmit = new JButton("Submit");
      customButtonSubmit.setFocusable(false);
      customButtonSubmit.addActionListener(this);
      withdrawalButtonClose = new JButton("Cancel");
      withdrawalButtonClose.setFocusable(false);
      withdrawalButtonClose.addActionListener(this);
      
      //set size and pos
      withdrawalLabel.setBounds(100,90,400,80);
      customNumber.setBounds(120,170,200,20);
      customButtonSubmit.setBounds(0,250,200,80);
      withdrawalButtonClose.setBounds(200,250,200,80);
      
      //add to panel
      customPanel.setLayout(null);
      customPanel.add(withdrawalLabel);
      customPanel.add(customNumber);
      customPanel.add(customButtonSubmit);
      customPanel.add(withdrawalButtonClose);
      
      //set size of panel
      customPanel.setPreferredSize(new Dimension(400, 340));
      
      //add to frame
      withdrawalFrame.add(customPanel, BorderLayout.CENTER);
      withdrawalFrame.pack();
      withdrawalFrame.setLocationRelativeTo(null);
    }
  
    //Confirmation screen after user choose the amount to withdraw
    private void confirmScreen(int amountToWithdrawal, String typeOfScreen){
      
      this.amountToWithdrawal = amountToWithdrawal;

      //determine the last panel  
      if (typeOfScreen == "withdrawal")  {
          withdrawalFrame.remove(withdrawalPanel);
        }
      else{
        withdrawalFrame.remove(customPanel);
        }
       
      //init the new panel
      withdrawalFrame.setLocationRelativeTo(null);//center
      withdrawalConfirmPanel = new JPanel();
      withdrawalLabel = new JLabel("Do you confirm to withdraw: $" + String.valueOf(amountToWithdrawal));
      
      confirmButtonCancel = new JButton("Cancel");
      confirmButtonCancel.setFocusable(false);
      confirmButtonCancel.addActionListener(this);
      confirmButtonMenu = new JButton("Confirm and go to menu");
      confirmButtonMenu.setFocusable(false);
      confirmButtonMenu.addActionListener(this);
      confirmButtonLogout = new JButton("Confirm and logout");  
      confirmButtonLogout.setFocusable(false);
      confirmButtonLogout.addActionListener(this);
      
      
      withdrawalLabel.setBounds(50,10,300,80);
      confirmButtonCancel.setBounds(0,90,400,80);
      confirmButtonMenu.setBounds(0,170,400,80);
      confirmButtonLogout.setBounds(0,250,400,80);
      
      
      
      withdrawalConfirmPanel.setLayout(null);
      withdrawalConfirmPanel.add(withdrawalLabel);
      withdrawalConfirmPanel.add(confirmButtonCancel);
      withdrawalConfirmPanel.add(confirmButtonMenu);
      withdrawalConfirmPanel.add(confirmButtonLogout);
      
      
      withdrawalConfirmPanel.setPreferredSize(new Dimension(400,340));
      
      withdrawalFrame.add(withdrawalConfirmPanel, BorderLayout.CENTER);
      withdrawalFrame.pack();
      withdrawalFrame.setLocationRelativeTo(null);  
    
    
    
    
    
    }
    
    //standardized method for printing message 
  private void messageScreen(String message, boolean logout){
      
      //Identify the last Panel on the Frame to remove
      
      withdrawalFrame.remove(withdrawalConfirmPanel);
     
      
        //Initialize new panel and label
      messagePanel = new JPanel();       
      withdrawalLabel = new JLabel(message);
      withdrawalLabel.setBounds(110,90,400,80);
      
      
      messagePanel.setLayout(null);
      messagePanel.add(withdrawalLabel);
      
      //Determine which button to add
      
      if(logout){
        messageButtonLogout = new JButton("Ok");
        messageButtonLogout.setFocusable(false);
       messageButtonLogout.addActionListener(this);
        messageButtonLogout.setBounds(0,170,400,80);
        messagePanel.add(messageButtonLogout);
        
        }else{
        withdrawalButtonReturn = new JButton("Return to menu.");
        withdrawalButtonReturn.setFocusable(false);
        withdrawalButtonReturn.addActionListener(this);
        //set size and pos
        withdrawalButtonReturn.setBounds(0,250,400,80);
        //add to panel
        messagePanel.add(withdrawalButtonReturn);
        
        }
      //Button that will return to menu    
      
      //set panel size
      messagePanel.setPreferredSize(new Dimension(400, 340));
      //add panel to frame
      withdrawalFrame.add(messagePanel, BorderLayout.CENTER);
      withdrawalFrame.pack();
      
    
}
  
  //Check if there if enough balance in user account and if there is enough cash in atm
  private void checkIfEnoughCash(int amount, String Screentype){
      
    BankDatabase bankDatabase = getBankDatabase();
    ATM screen = getScreen();
    double availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() );
    
    if ( amount <= availableBalance )
            {   
              // check whether the cash dispenser has enough money
              if ( cashDispenser.isSufficientCashAvailable( amount ) )
              {
                  // update the account involved to reflect withdrawal
                  confirmScreen(amount,Screentype);
                  
              } // end if
              else // cash dispenser does not have enough cash
              
                //Prompt user
                JOptionPane.showMessageDialog(withdrawalFrame,"Insufficient cash available in the ATM. "
                +"Please choose a smaller amount.");
            } // end if
            else // not enough money available in user's account
            {
              //Prompt user    
              JOptionPane.showMessageDialog(withdrawalFrame,"Insufficient funds in your account. "
              +"Please choose a smaller amount.");
            } // end else
                  
    }


  private void withdrawalProcess(int withdrawAmount, boolean logout){
      
      //get reference from Transaction
      BankDatabase bankDatabase = getBankDatabase();
      ATM screen = getScreen();
      
      // amount available for withdrawal
      amount = withdrawAmount;
      //get the available balance and save to local
      
      // update the account involved to reflect withdrawal
      bankDatabase.debit( getAccountNumber(), amount );
                  
      cashDispenser.dispenseCash( amount ); // dispense cash
      
      // instruct user to take cash
      messageScreen("Please take your card and cash now.",logout);      
    }  
  
  @Override
  public void actionPerformed(ActionEvent e){
        ATM screen = getScreen();
      if(e.getSource() == withdrawalButton200){       
          checkIfEnoughCash(200,"withdrawal");   
        }  
      if(e.getSource() == withdrawalButton400){       
          checkIfEnoughCash(400,"withdrawal");         
                
        }  
      if(e.getSource() == withdrawalButton800){       
          checkIfEnoughCash(800,"withdrawal");         
                
        }  
      if(e.getSource() == withdrawalButton1000){       
          checkIfEnoughCash(1000,"withdrawal");          
                
        }  
      if(e.getSource() == withdrawalButton2000){       
          checkIfEnoughCash(2000,"withdrawal");      
        }    
      if(e.getSource() == withdrawalButtonCustom){
          customScreen();
              
        }  
      if(e.getSource() == withdrawalButtonReturn){       
          withdrawalFrame.dispose();          
          screen.atmScreen();       
        }
      if(e.getSource() == withdrawalButtonClose){       
          withdrawalFrame.dispose();
          
          withdrawalScreen();       
        }
      if(e.getSource() == customButtonSubmit){
        try{
          int selectedAmount =Integer.parseInt(customNumber.getText());
          if(selectedAmount%100 == 0&& selectedAmount!=0 ){
          checkIfEnoughCash(selectedAmount,"Custom");
            }
          else if(selectedAmount!=0){
            JOptionPane.showMessageDialog(withdrawalFrame,"Error! Input can only be multiple of 100. Please select again.");
          }
          else{
            JOptionPane.showMessageDialog(withdrawalFrame,"Error! Input can not be 0. Please select again.");
            }
        }
          catch (Exception x){
            JOptionPane loginErrorPane = new JOptionPane("Invalid amount. Please try again.", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = loginErrorPane.createDialog(null, "Error");
            dialog.setModal(false);
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                }
            }).start();
        }
        }
      if(e.getSource() == messageButtonLogout) {
            withdrawalFrame.dispose();
            // reset before logout and to next ATM session
            screen.setCurrentAccountNumberToZero();
            screen.loginScreen();//back to login
            JOptionPane pane = new JOptionPane( "Exiting the system...", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = pane.createDialog(null, "");
            dialog.setModal(false);
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            new Timer(screen.TIME_VISIBLE, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                }
            }).start();
        }
        
      if(e.getSource() == confirmButtonCancel)
        {
        withdrawalFrame.dispose();
        withdrawalScreen(); 
        
        }
      if(e.getSource() == confirmButtonMenu)
        {
        withdrawalProcess(Integer.parseInt(String.valueOf(amountToWithdrawal)),false);
        }
        if(e.getSource() == confirmButtonLogout)
        {
        withdrawalProcess(Integer.parseInt(String.valueOf(amountToWithdrawal)),true);
        
        }
    
    }
} // end class Withdrawal
