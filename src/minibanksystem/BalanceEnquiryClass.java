
package minibanksystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class BalanceEnquiryClass extends JFrame implements ActionListener{
    
    ImageIcon BgImage, backgroundImage;
    Image scaledImage;
    JLabel lbImage, lbBalance;
    JButton btnBack;    
    String strPinNumber;
            
    BalanceEnquiryClass(String pinNumber){
        this.setTitle("Balance Enquiry Window");
        this.setSize(800, 800);
        this.setLocation(350, 40);
        //this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        
        this.strPinNumber = pinNumber;
    
        BgImage = new ImageIcon(ClassLoader.getSystemResource("icons/background.jpg"));
        scaledImage = BgImage.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(scaledImage);
        lbImage = new JLabel(backgroundImage);
        lbImage.setIcon(backgroundImage); 
        lbImage.setBounds(0, 0, 800, 800);
        this.add(lbImage);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(500, 600, 150, 40);
        btnBack.setFont(new Font("Ariel", Font.BOLD, 16));
        btnBack.setBackground(Color.GRAY);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        lbImage.add(this.btnBack);
        
        lbBalance = new JLabel("<html>Your current balance in the account is:<br>Rs " + checkBalance() + "</html>");
        lbBalance.setForeground(Color.WHITE);
        lbBalance.setFont(new Font("System", Font.BOLD, 28));
        lbBalance.setBounds(40, 290, 580, 90);
        lbImage.add(this.lbBalance);
        
        
        getContentPane().setBackground(new Color(245, 245, 245));
    }
    
     @Override
    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
        new MainTransactions("strPinNumber").setVisible(true);
    
    }//ActionPerformed close
    
    public int checkBalance(){
        int intBalance = 0;
        try{
            DataBaseConnection connect = new DataBaseConnection();

            ResultSet result = connect.stateMen.executeQuery("select * from tblbank where pin = '" +strPinNumber+ "'");
            
            while(result.next()){
                if(result.getString("type").equals("Deposit")){
                    intBalance += Integer.parseInt(result.getString("amount"));
                }else{
                    intBalance -= Integer.parseInt(result.getString("amount"));
                }
            }
            // Close the PreparedStatement and database connection
            connect.stateMen.close();
            connect.conn.close();
        }catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        return intBalance;
    }//Check Balance close
    public static void main(String[] args) {
        new BalanceEnquiryClass("");
    }
}
