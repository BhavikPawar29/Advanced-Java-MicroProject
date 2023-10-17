package minibanksystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;


public class WithdrawClass extends JFrame implements ActionListener{
    
    JLabel lbEnterAmt, imgLabel;
    ImageIcon logoImage;
    JTextField txtAmount;
    JButton btnWithdraw, btnBack;
    Image scaledImage;
    
    String strPinNumber;
    
    WithdrawClass(String pinNumber){
        this.setTitle("Withdraw Amount");
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
        
        lbEnterAmt = new JLabel("Enter Amount You Want To Withdraw:");
        lbEnterAmt.setFont(new Font("System", Font.BOLD, 20));
        lbEnterAmt.setForeground(Color.WHITE);
        lbEnterAmt.setBounds(60, 100, 390, 30);
        imgLabel.add(this.lbEnterAmt);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(60, 160, 320, 50);
        txtAmount.setBackground(new Color(245, 245, 245));
        txtAmount.setFont(new Font("System", Font.BOLD, 18));
        imgLabel.add(this.txtAmount);
        
        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(420, 380, 110, 40);
        btnWithdraw.setFont(new Font("Ariel", Font.BOLD, 16));
        btnWithdraw.setBackground(Color.GRAY);
        btnWithdraw.setForeground(Color.WHITE);
        btnWithdraw.addActionListener(this);
        imgLabel.add(this.btnWithdraw);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(420, 440, 110, 40);
        btnBack.setFont(new Font("Ariel", Font.BOLD, 16));
        btnBack.setBackground(Color.GRAY);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        imgLabel.add(this.btnBack);
        
        getContentPane().setBackground(new Color(245, 245, 245));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == btnWithdraw){
            String strDeposit = txtAmount.getText();
            Date date = new Date();
            
            if(strDeposit.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw", "Information Required" ,JOptionPane.WARNING_MESSAGE);
            }else {
               try{
                    DataBaseConnection connect = new DataBaseConnection();

                    String strQuery = "insert into tblbank values('" + strPinNumber + "', '" + date + "', 'Withdraw', '" + strDeposit +"')";
                    //Exexute the query
                    connect.stateMen.executeUpdate(strQuery);

                    JOptionPane.showMessageDialog(null, "Amount Rs." + strDeposit + " Withdraw successfully", "Successful Transaction", JOptionPane.INFORMATION_MESSAGE);
                    
                    this.setVisible(false);
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
        new WithdrawClass("");
    }
}
