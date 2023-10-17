
package minibanksystem;

import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class FastCashClass extends JFrame implements ActionListener{
    
    ImageIcon BgImage, backgroundImage;
    Image scaledImage;
    JLabel lbImage, lbSelectType;
    JButton btnHundred, btnFiveHundred, btnThousand, btnTwoThousand, btnFiveThousand, btnTenThousand, btnBack;
    String strPinNumber;
    
    FastCashClass(String pinNumber){
        this.setTitle("Fast Cash Window");
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
        
        lbSelectType = new JLabel("Select Withdrawl Amount:");
        lbSelectType.setFont(new Font("System", Font.BOLD, 24));
        lbSelectType.setForeground(Color.WHITE);
        lbSelectType.setBounds(225, 170, 700, 35);
        lbImage.add(this.lbSelectType);
        
        
        //Buttons for Fasd Cash type
            btnHundred = new JButton("Rs 100");
            btnHundred.setBounds(90, 350,  250, 40);
            btnHundred.setFont(new Font("Ariel", Font.BOLD, 16));
            btnHundred.setBackground(Color.GRAY);
            btnHundred.setForeground(Color.WHITE);
            //btnHundred.addActionListener(this);
            lbImage.add(btnHundred);

            btnFiveHundred = new JButton("Rs 500");
            btnFiveHundred.setBounds(560, 350,  250, 45);
            btnFiveHundred.setFont(new Font("Ariel", Font.BOLD, 16));
            btnFiveHundred.setBackground(Color.GRAY);
            btnFiveHundred.setForeground(Color.WHITE);
            btnFiveHundred.addActionListener(this);
            lbImage.add(btnFiveHundred);

            btnThousand = new JButton("Rs 1000");
            btnThousand.setBounds(90, 450,  250, 45);
            btnThousand.setFont(new Font("Ariel", Font.BOLD, 16));
            btnThousand.setBackground(Color.GRAY);
            btnThousand.setForeground(Color.WHITE);
            //btnThousand.addActionListener(this);
            lbImage.add(btnThousand);

            btnTwoThousand = new JButton("Rs 2000");
            btnTwoThousand.setBounds(560, 450,  250, 45);
            btnTwoThousand.setFont(new Font("Ariel", Font.BOLD, 16));
            btnTwoThousand.setBackground(Color.GRAY);
            btnTwoThousand.setForeground(Color.WHITE);
            //btnTwoThousand.addActionListener(this);
            lbImage.add(btnTwoThousand);

            btnFiveThousand = new JButton("Rs 5000");
            btnFiveThousand.setBounds(90, 550,  250, 45);
            btnFiveThousand.setFont(new Font("Ariel", Font.BOLD, 16));
            btnFiveThousand.setBackground(Color.GRAY);
            btnFiveThousand.setForeground(Color.WHITE);
            //btnFiveThousand.addActionListener(this);
            lbImage.add(btnFiveThousand);

            btnTenThousand = new JButton("Rs 10000");
            btnTenThousand.setBounds(560, 550,  250, 45);
            btnTenThousand.setFont(new Font("Ariel", Font.BOLD, 16));
            btnTenThousand.setBackground(Color.GRAY);
            btnTenThousand.setForeground(Color.WHITE);
            //btnTenThousand.addActionListener(this);
            lbImage.add(btnTenThousand);

            btnBack = new JButton("Back");
            btnBack.setBounds(560, 650,  250, 45);
            btnBack.setFont(new Font("Ariel", Font.BOLD, 16));
            btnBack.setBackground(Color.GRAY);
            btnBack.setForeground(Color.WHITE);
            btnBack.addActionListener(this);
            lbImage.add(btnBack);
        
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnBack){
            this.setVisible(false);
            new MainTransactions(strPinNumber).setVisible(true);
        } else {
            String strAmount = ((JButton) ae.getSource()).getText().substring(3);
            
            try{
                DataBaseConnection connect = new DataBaseConnection();
                
                ResultSet result = connect.stateMen.executeQuery("select * from tblbank where pin = '" +strPinNumber+ "'");
                
                int intBalance = 0;
                
                while(result.next()){
                    if(result.getString("type").equals("Deposit")){
                        intBalance += Integer.parseInt(result.getString("amount"));
                    }else{
                        intBalance -= Integer.parseInt(result.getString("amount"));
                    }
                }
            
                if(ae.getSource() != btnBack && intBalance < Integer.parseInt(strAmount)){
                    JOptionPane.showMessageDialog(null, "Amount not sufficient in account", "Insufficient Balance",JOptionPane.ERROR);
                    return;
                }else{
                    Date date = new Date();

                    String strQuery =  "insert into bank values('"+strPinNumber+"', '"+date+"', 'Withdrawl', '"+strAmount+"')";
                    
                    //Execute the query
                    connect.stateMen.executeUpdate(strQuery);
        
                    JOptionPane.showMessageDialog(null, "Rs. "+strAmount+" Debited Successfully from your account");
                
                    this.setVisible(false);
                    new MainTransactions(strPinNumber).setVisible(true);
                    
                    // Close the PreparedStatement and database connection
                    connect.stateMen.close();
                    connect.conn.close();
                }
            }catch(SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
        }
    }//actionPerformed closed
    
    public static void main(String[] args) {
        new FastCashClass("");
    }
}
