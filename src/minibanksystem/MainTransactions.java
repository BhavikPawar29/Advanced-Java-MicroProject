
package minibanksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainTransactions extends JFrame implements ActionListener{
    
    ImageIcon BgImage, backgroundImage;
    Image scaledImage;
    JLabel lbImage, lbSelectTransaction;
    JButton btnDeposit, btnWithdrawl, btnFastCash, btnMiniSatement, btnChangePin, btnCheckBalancce, btnExit;
    
    String strPinNumber; 
    
    MainTransactions(String pinNumber){
        this.setTitle("Main Window");
        this.setSize(900, 900);
        this.setLocation(300, 0);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        
        this.strPinNumber = pinNumber;
    
        BgImage = new ImageIcon(ClassLoader.getSystemResource("icons/background.jpg"));
        scaledImage = BgImage.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(scaledImage);
        lbImage = new JLabel(backgroundImage);
        lbImage.setIcon(backgroundImage); 
        lbImage.setBounds(0, 0, 900, 900);
        this.add(lbImage);
        
        lbSelectTransaction = new JLabel("Please select your Transaction:");
        lbSelectTransaction.setFont(new Font("System", Font.BOLD, 24));
        lbSelectTransaction.setForeground(Color.WHITE);
        lbSelectTransaction.setBounds(225, 170, 700, 35);
        lbImage.add(this.lbSelectTransaction);
        
        
        //Buttons for Transaction
            btnDeposit = new JButton("Deposit");
            btnDeposit.setBounds(90, 350,  250, 40);
            btnDeposit.setFont(new Font("Ariel", Font.BOLD, 16));
            btnDeposit.setBackground(Color.GRAY);
            btnDeposit.setForeground(Color.WHITE);
            btnDeposit.addActionListener(this);
            lbImage.add(btnDeposit);

            btnWithdrawl = new JButton("Cash Withdraw");
            btnWithdrawl.setBounds(560, 350,  250, 45);
            btnWithdrawl.setFont(new Font("Ariel", Font.BOLD, 16));
            btnWithdrawl.setBackground(Color.GRAY);
            btnWithdrawl.setForeground(Color.WHITE);
            btnWithdrawl.addActionListener(this);
            lbImage.add(btnWithdrawl);

            btnFastCash = new JButton("Fast Cash");
            btnFastCash.setBounds(90, 450,  250, 45);
            btnFastCash.setFont(new Font("Ariel", Font.BOLD, 16));
            btnFastCash.setBackground(Color.GRAY);
            btnFastCash.setForeground(Color.WHITE);
            btnFastCash.addActionListener(this);
            lbImage.add(btnFastCash);

            btnMiniSatement = new JButton("Mini-Statement");
            btnMiniSatement.setBounds(560, 450,  250, 45);
            btnMiniSatement.setFont(new Font("Ariel", Font.BOLD, 16));
            btnMiniSatement.setBackground(Color.GRAY);
            btnMiniSatement.setForeground(Color.WHITE);
            btnMiniSatement.addActionListener(this);
            lbImage.add(btnMiniSatement);

            btnChangePin = new JButton("Change Pin");
            btnChangePin.setBounds(90, 550,  250, 45);
            btnChangePin.setFont(new Font("Ariel", Font.BOLD, 16));
            btnChangePin.setBackground(Color.GRAY);
            btnChangePin.setForeground(Color.WHITE);
            btnChangePin.addActionListener(this);
            lbImage.add(btnChangePin);

            btnCheckBalancce = new JButton("Balance Enquiry");
            btnCheckBalancce.setBounds(560, 550,  250, 45);
            btnCheckBalancce.setFont(new Font("Ariel", Font.BOLD, 16));
            btnCheckBalancce.setBackground(Color.GRAY);
            btnCheckBalancce.setForeground(Color.WHITE);
            btnCheckBalancce.addActionListener(this);
            lbImage.add(btnCheckBalancce);

            btnExit = new JButton("Exit");
            btnExit.setBounds(560, 650,  250, 45);
            btnExit.setFont(new Font("Ariel", Font.BOLD, 16));
            btnExit.setBackground(Color.GRAY);
            btnExit.setForeground(Color.WHITE);
            btnExit.addActionListener(this);
            lbImage.add(btnExit);
        
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnExit){
            System.exit(0);
        } else if(ae.getSource() == btnDeposit){
            this.setVisible(false);
            new DepositClass(strPinNumber).setVisible(true);
        } else if (ae.getSource() == btnWithdrawl){
            this.setVisible(false);
            new WithdrawClass(strPinNumber).setVisible(true);
        } else if(ae.getSource() == btnFastCash){
            this.setVisible(false);
            new FastCashClass(strPinNumber).setVisible(true);
        }else if(ae.getSource() == btnChangePin){
            this.setVisible(false);
            new ChangePinClass(strPinNumber).setVisible(true);
        }else if(ae.getSource() == btnCheckBalancce){
            this.setVisible(false);
            new BalanceEnquiryClass(strPinNumber).setVisible(true);
        }else if(ae.getSource() == btnMiniSatement){
            new MiniStatementClass(strPinNumber).setVisible(true);
        }
    }//actionPerformed closed

    public static void main(String[] args) {
        new MainTransactions("");
    }
}
