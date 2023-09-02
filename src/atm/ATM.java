package atm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ATM implements ActionListener{

    int TIME_VISIBLE = 3000;// timer for log out

    private int currentAccountNumber; // current user's account number
    private BankDatabase bankDatabase; // account information database
    private CashDispenser cashDispenser; // ATM's cash dispenser

    private int account;
    private int pin;
    private boolean userAuthenticated = false;
    private String accountInfo;
    
    private Withdrawal withdrawal;
    private Transfer transfer;

    //frame
    private JFrame loginframe = new JFrame();
    private JFrame mainframe = new JFrame();
    //panel
    private JPanel login = new JPanel();
    private JPanel main = new JPanel();

    //login page
    private JLabel welcomeLable, accountNumberLable, pinLable;
    private JTextField accountNumberText;
    private JPasswordField pinPass;
    private JButton loginButton, clearButton;

    //Main page
    private JLabel accountStatusLable, accountInfoLable, availableBalanceLable, 
        accountAvailableBalanceLable, totalBalanceLable, accountTotalBalanceLable;
    private JButton withdrawCashButton, transferbButton, exitButton;
    
    //constructor
    public ATM(){
        //init
        this.currentAccountNumber = 0;
        cashDispenser = new CashDispenser(); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database***
        loginScreen();//call login screen
    }

    public void loginScreen(){
        
        disposeAllScreen();//init

        //new frame and panel
        loginframe = new JFrame();
        loginframe.setResizable(false);
        login = new JPanel();

        //init the panel and frame
        loginframe.setSize(400,370);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setTitle("login");
        loginframe.setLocationRelativeTo(null);//center the frame
        
        //welcome
        welcomeLable = new JLabel("Welcome!", JLabel.CENTER);

        //account
        accountNumberLable = new JLabel("Account Number: ");
        accountNumberText = new JTextField(5);

        //pin
        pinLable = new JLabel("PIN: ");
        pinPass = new JPasswordField(5);

        //login
        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        //clear
        clearButton = new JButton("Clear");
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);

        //set size and pos
        login.setLayout(null);
        welcomeLable.setBounds(150,60,100,20);
        accountNumberLable.setBounds(70,120,150,20);
        accountNumberText.setBounds(220,120,120,20); 
        pinLable.setBounds(70,160,150,20); 
        pinPass.setBounds(220,160,120,20);
        loginButton.setBounds(0,260,200,80);
        clearButton.setBounds(200,260,200,80);

        //add lable to panel
        login.add(welcomeLable);
        login.add(accountNumberLable);
        login.add(accountNumberText);
        login.add(pinLable);
        login.add(pinPass);
        login.add(loginButton);
        login.add(clearButton);

        //add panel
        loginframe.add(login, BorderLayout.CENTER);

        //Enter = login
        loginframe.getRootPane().setDefaultButton(loginButton);
        //show frame
        loginframe.setVisible(true);
    }

    // attempts to authenticate user against database
    public void authenticateUser(int account, int pIN) 
    {
        BankDatabase bankDatabase = new BankDatabase(); // create acct info database

        int accountNumber = account;
        int pin = pIN;
        
        // set userAuthenticated to boolean value returned by database
        userAuthenticated = 
            bankDatabase.authenticateUser( accountNumber, pin );
        
        // check whether authentication succeeded
        if ( userAuthenticated )
        {
            this.currentAccountNumber = accountNumber; // save user's account #
            this.accountInfo = bankDatabase.getAccountInfo(currentAccountNumber); //getAccountInfo
        } // end if
        else{
            JOptionPane loginErrorPane = new JOptionPane("Invalid account number or PIN. Please try again.",JOptionPane.ERROR_MESSAGE);
            JDialog dialog = loginErrorPane.createDialog(null, "Error");
            dialog.setModal(false);
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            new Timer(TIME_VISIBLE, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                }
            }).start();
        }
    } // end method authenticateUser

    public void atmScreen(){

        disposeAllScreen();//init

        mainframe = new JFrame();
        mainframe.setResizable(false);
        main = new JPanel();

        //init the panel and frame
        mainframe.setSize(400,370);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setTitle("ATM");
        mainframe.setLocationRelativeTo(null);//center the frame

        //lable
        accountStatusLable = new JLabel("Account Status:");
        accountInfoLable = new JLabel(accountInfo);
        availableBalanceLable = new JLabel("Available Balance:");
        accountAvailableBalanceLable = new JLabel(String.format("$%1$,.2f", bankDatabase.getAvailableBalance(currentAccountNumber)));
        totalBalanceLable = new JLabel("Total Balance:");
        accountTotalBalanceLable = new JLabel(String.format("$%1$,.2f", bankDatabase.getTotalBalance(currentAccountNumber)));
        //button
        withdrawCashButton = new JButton("Withdraw cash");
        withdrawCashButton.setFocusable(false);
        withdrawCashButton.addActionListener(this);
        transferbButton = new JButton("Transfer");
        transferbButton.setFocusable(false);
        transferbButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        //set size and pos
        main.setLayout(null);
        accountStatusLable.setBounds(60,5, 150, 40);
        accountInfoLable.setBounds(200,5, 150, 40);
        availableBalanceLable.setBounds(60,5, 150, 80);
        accountAvailableBalanceLable.setBounds(200,5, 200, 80);
        totalBalanceLable.setBounds(60,25, 150, 80);
        accountTotalBalanceLable.setBounds(200,25, 200, 80);
        //buttons
        withdrawCashButton.setBounds(0,90,400,80);
        transferbButton.setBounds(0,170,400,80);
        exitButton.setBounds(0,250,400,80);

        //add to panel
        main.add(accountStatusLable);
        main.add(accountInfoLable);
        main.add(availableBalanceLable);
        main.add(accountAvailableBalanceLable);
        main.add(totalBalanceLable);
        main.add(accountTotalBalanceLable);
        main.add(withdrawCashButton);
        main.add(transferbButton);
        main.add(exitButton);

        //add main panel
        mainframe.add(main, BorderLayout.CENTER);

        //show frame
        mainframe.setVisible(true);
    }


    public void disposeAllScreen(){
        //init
        loginframe.dispose();
        mainframe.dispose();
        
    }

    public void setCurrentAccountNumberToZero(){//log out
        currentAccountNumber = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //login screen
        String accountSt = accountNumberText.getText();
        String pinSt = pinPass.getText();

        if(e.getSource() == loginButton){
            try {
                //We assume numpad only contain numbers
                for(int i=0;i<5;i++){ //convert string to int
                    account = Integer.valueOf(accountSt).intValue();
                    pin = Integer.valueOf(pinSt).intValue();
                }
                authenticateUser(account, pin);
                if(userAuthenticated){
                    atmScreen();// get into the atm
                    JOptionPane authenticatePane = new JOptionPane("Successful!!", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = authenticatePane.createDialog(null, "Successful");
                    dialog.setModal(false);
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    new Timer((int)(TIME_VISIBLE/1.5), new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        }
                    }).start();
                }
            }catch (Exception x){
                JOptionPane loginErrorPane = new JOptionPane("Invalid account number or PIN. Please try again.", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = loginErrorPane.createDialog(null, "Error");
                dialog.setModal(false);
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                new Timer(TIME_VISIBLE, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    dialog.setVisible(false);
                    }
                }).start();
            }
        }
        if(e.getSource() == clearButton){
            accountNumberText.setText("");
            pinPass.setText("");
        }

        if(e.getSource() == withdrawCashButton){
            //withdrawCashScreen
            withdrawal = new Withdrawal(currentAccountNumber, this, bankDatabase, cashDispenser);
            withdrawal.withdrawalScreen();
        }
        if(e.getSource() == transferbButton){
            transfer = new Transfer(currentAccountNumber, this, bankDatabase);
            transfer.execute();
        }
        if(e.getSource() == exitButton){
            currentAccountNumber = 0; // reset before next ATM session 
            loginScreen();//back to login
            JOptionPane pane = new JOptionPane( "Please take your card!!!", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = pane.createDialog(null, "Exiting the system...");
            dialog.setModal(false);
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            new Timer(TIME_VISIBLE, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                }
            }).start();
        }
    }
}
