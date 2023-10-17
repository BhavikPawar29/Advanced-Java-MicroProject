package minibanksystem;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DepositClass extends JFrame implements ActionListener{
    
    JLabel lbEnterAmt, imgLabel;
    ImageIcon logoImage;
    JTextField txtAmount;
    JButton btnDeposit, btnBack;
    Image scaledImage;
    
    String strPinNumber;
    
    DepositClass(String pinNumber){
        this.setTitle("Deposit Amount");
        this.setSize(600, 600);
        this.setLocation(450, 100);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        
       this.strPinNumber = pinNumber;
        //imgLabel.setBounds(0, 0, 100, 100);
        
        logoImage = new ImageIcon(ClassLoader.getSystemResource("icons/background.jpg"));
        scaledImage = logoImage.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(scaledImage);
        imgLabel = new JLabel();
        imgLabel.setIcon(logoImage); 
        imgLabel.setBounds(0, 0, 600, 600);
        this.add(imgLabel);
        
        lbEnterAmt = new JLabel("Enter Amount You Want To Deposit:");
        lbEnterAmt.setFont(new Font("System", Font.BOLD, 20));
        lbEnterAmt.setForeground(Color.WHITE);
        lbEnterAmt.setBounds(60, 100, 390, 30);
        imgLabel.add(this.lbEnterAmt);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(60, 160, 320, 50);
        txtAmount.setBackground(new Color(245, 245, 245));
        txtAmount.setFont(new Font("System", Font.BOLD, 18));
        imgLabel.add(this.txtAmount);
        
        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(420, 380, 100, 40);
        btnDeposit.setFont(new Font("Ariel", Font.BOLD, 16));
        btnDeposit.setBackground(Color.GRAY);
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.addActionListener(this);
        imgLabel.add(this.btnDeposit);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(420, 440, 100, 40);
        btnBack.setFont(new Font("Ariel", Font.BOLD, 16));
        btnBack.setBackground(Color.GRAY);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        imgLabel.add(this.btnBack);
        
        getContentPane().setBackground(new Color(245, 245, 245));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == btnDeposit){
            String strDeposit = txtAmount.getText();
            Date date = new Date();
            
            if(strDeposit.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit", "Information Required" ,JOptionPane.WARNING_MESSAGE);
            }else {
               try{
                    DataBaseConnection connect = new DataBaseConnection();

                    String strQuery = "insert into tblbank values('" + strPinNumber + "', '" + date + "', 'Deposit', '" + strDeposit +"')";
                    //Exexute the query
                    connect.stateMen.executeUpdate(strQuery);
            
                    JOptionPane.showMessageDialog(null, "Amount Rs." + strDeposit + " Deposited successfully", "Amount Deposited", JOptionPane.INFORMATION_MESSAGE);
                    new MainTransactions(strPinNumber).setVisible(true);
                    
                    // Close the PreparedStatement and database connection
                    connect.stateMen.close();
                    connect.conn.close();
                }catch(SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    }
            }
        }else if(ae.getSource() ==  btnBack){
            this.setVisible(false);
            new MainTransactions(strPinNumber).setVisible(true);
        }
    }//actionPerformed closed
    
    public static void main(String[] args) {
        new DepositClass("");
    }
}