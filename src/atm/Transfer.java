package atm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Transfer extends Transaction implements ActionListener{
    private int transferaccount; // to one account

    // transfer section
    private JFrame transferframe;
    private JPanel transfer;

    private JLabel transferLabel, amountLabel;
    private JTextField amountField, accountField;
    private JButton yesButton, noButton;

    // Transfer constructor
    public Transfer( int userAccountNumber, ATM atmScreen, BankDatabase atmBankDatabase)
    {
        super( userAccountNumber, atmScreen, atmBankDatabase );// initialize superclass variables
    }// end Transfer constructor

    public void execute(){

        transferScreen();

    } // end method execute
    

    private void transferScreen() {

        ATM screen = getScreen();
        screen.disposeAllScreen();

        transferframe = new JFrame();
        transferframe.setResizable(false);
        transfer = new JPanel();

        //init the panel and frame
        transferframe.setSize(400,370);
        transferframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        transferframe.setTitle("Transfer");
        transferframe.setLocationRelativeTo(null);//center the frame

        //trasnfer amount
        amountLabel = new JLabel("Enter transfer amount: ");
        amountField = new JTextField(5);

        //account number
        transferLabel = new JLabel("Enter transfer account number: ");
        accountField = new JTextField(5);

        //confirmation
        yesButton = new JButton("Confirm");
        yesButton.setFocusable(false);
        yesButton.addActionListener(this);
        noButton = new JButton("Return");
        noButton.setFocusable(false);
        noButton.addActionListener(this);

        //set size and pos
        transfer.setLayout(null);
        amountLabel.setBounds(100, 90, 200, 20);
        amountField.setBounds(100, 120, 200, 20);
        transferLabel.setBounds(100, 150, 200, 20);
        accountField.setBounds(100, 180, 200, 20);
        yesButton.setBounds(0,250,200,80);
        noButton.setBounds(200,250,200,80);
        
        transfer.add(amountLabel);
        transfer.add(amountField);
        transfer.add(transferLabel);
        transfer.add(accountField);
        transfer.add(yesButton);
        transfer.add(noButton);
        transferframe.add(transfer, BorderLayout.CENTER);

        transferframe.getContentPane().add(transfer);
        
        //add transfer panel
        transferframe.add(transfer, BorderLayout.CENTER);

        //show frame
        transferframe.setLocationRelativeTo(null);//center the frame
        transferframe.setVisible(true);
        
    }

    private void transfer(){
        BankDatabase bankDatabase = getBankDatabase(); // get reference
        ATM screen = getScreen(); // get reference

        String amountinput = amountField.getText();
        String accountinput = accountField.getText();
        String temp;
        double amount = 0;
        int ac = 0;
        
        if (amountinput.compareTo(accountinput) > 0){
            temp = amountinput;
        }else temp = accountinput;

        try {
            //We assume numpad only contain numbers
            for(int i=0; i<temp.length(); i++){ //convert string to int
                amount = Double.valueOf(amountinput).doubleValue();
                ac = Integer.valueOf(accountinput).intValue();
            }
            if ( amount < 0 || amount == 0)
            {
                JOptionPane.showMessageDialog(transfer, "Please enter valid Numbers and Decimal point only.");
            }
            else if ( amount*1000%10 != 0)
            {
                JOptionPane.showMessageDialog(transfer, "Only 2 Decimal digits is allowed. Please try again.");
            }
            else if ( bankDatabase.getAvailableBalance(getAccountNumber()) < amount){
                JOptionPane.showMessageDialog(transfer, "Insufficient fund. Please try again.");
            }
            else if ( ac == getAccountNumber() ) {
                JOptionPane.showMessageDialog(transfer, "Self transfer currently is not permitted. Please try again.");
            }
            else if ( bankDatabase.accountexist(ac) != true ){
                JOptionPane.showMessageDialog(transfer, "Invalid transfer account number. Please try again.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Transfer successful.");
                bankDatabase.transfer(getAccountNumber(), amount, ac);
                transferframe.dispose();
                screen.atmScreen();//back to atm
            }
            //System.out.print("\n" + amount + "," + ac);
        }catch (Exception x){
            JOptionPane.showMessageDialog(transfer, "Only Numbers and Decimal point are allowed. Please try again.");
        }
    }

    public void transferdispose(){
        transferframe.dispose();
    }
    
    private void promptForConfirmation() {
        ATM screen = getScreen(); // get reference to screen
        screen.disposeAllScreen();

        int reply = JOptionPane.showConfirmDialog(null, "Confirm your transfer transaction?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            transfer();
        } else {
            JOptionPane.getRootFrame().dispose();
        }
    }

    public int getTransferaccount() {
        return this.transferaccount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == yesButton){
            promptForConfirmation();
        }
        if(e.getSource() == noButton){
            transferframe.dispose();
            ATM screen = getScreen();
            screen.atmScreen();
        }
    }

}
