
package minibanksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Authentication extends JFrame implements ActionListener {
    
    JLabel imgLabel, lbWelcome, lbCardno, lbPinCode;
    JTextField txtCardno;
    JPasswordField txtPinCode;
    JButton btnLogIN, btnSignUP, btnClear;
    ImageIcon logoImage;
    Image scaledImage;
    
    public Authentication(){
        this.setTitle("AUTHENTICATION WINDOW");
        this.setSize(800, 480);
        
        this.setLayout(null);

        imgLabel = new JLabel();
        imgLabel.setBounds(0, 0, 800, 480);
        
        logoImage = new ImageIcon(ClassLoader.getSystemResource("icons/background.jpg"));
        scaledImage = logoImage.getImage().getScaledInstance(800, 480, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(scaledImage);
        
        imgLabel.setIcon(logoImage); 
        this.add(imgLabel);

        lbWelcome = new JLabel("Welcome To iBanking");
        lbWelcome.setBounds(200,  40, 350, 40);
        lbWelcome.setFont(new Font("Serif", Font.PLAIN, 35));
        lbWelcome.setForeground(Color.WHITE);
        imgLabel.add(this.lbWelcome);

        lbCardno = new JLabel("Card No");
        lbCardno.setBounds(120,  150, 150, 30);
        lbCardno.setFont(new Font("Serif", Font.PLAIN, 25));
        lbCardno.setForeground(Color.WHITE);
        imgLabel.add(this.lbCardno);

        txtCardno = new JTextField();
        txtCardno.setBounds(300, 150,  230, 30);
        txtCardno.setFont(new Font("Ariel", Font.BOLD, 20));
        imgLabel.add(this.txtCardno);

        lbPinCode = new JLabel("PIN");
        lbPinCode.setBounds(120,  215, 200, 30);
        lbPinCode.setFont(new Font("Serif", Font.PLAIN, 25));
        lbPinCode.setForeground(Color.WHITE);
        imgLabel.add(this.lbPinCode);

        txtPinCode = new JPasswordField();
        txtPinCode.setBounds(300, 215, 230, 30);
        txtPinCode.setFont(new Font("Ariel", Font.BOLD, 25));
        imgLabel.add(this.txtPinCode);

        btnLogIN = new JButton("Log In");
        btnLogIN.setBounds(300, 300, 100, 30);
        btnLogIN.setFont(new Font("Ariel", Font.BOLD, 14));
        btnLogIN.setBackground(Color.GRAY);
        btnLogIN.setForeground(Color.WHITE);
        btnLogIN.addActionListener(this);
        imgLabel.add(this.btnLogIN);

        btnClear = new JButton("Clear");
        btnClear.setBounds(430, 300, 100, 30);
        btnClear.setFont(new Font("Ariel", Font.BOLD, 16));
        btnClear.setBackground(Color.GRAY);
        btnClear.setForeground(Color.WHITE);
        btnClear.addActionListener(this);
        imgLabel.add(this.btnClear);

        btnSignUP = new JButton("Sign Up");
        btnSignUP.setBounds(300, 350, 230, 30);
        btnSignUP.setFont(new Font("Ariel", Font.BOLD, 16));
        btnSignUP.setBackground(Color.GRAY);
        btnSignUP.setForeground(Color.WHITE);
        btnSignUP.addActionListener(this);
        imgLabel.add(this.btnSignUP);

        this.setLocation(300, 250);
        this.setVisible(true);
    }
    
     @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.btnLogIN) {
           try{
           DataBaseConnection connect = new DataBaseConnection();
           
            String strCarno = txtCardno.getText();
            String strPinCode = txtPinCode.getText();
            
                String strQuery =  "select * from tbllogin where cardno = '"+strCarno+"' and pinNo = '"+strPinCode+"'";
                
                ResultSet result;
                // Execute the query
                result = connect.stateMen.executeQuery(strQuery);
                
                    if(result.next()){
                        this.setVisible(false);
                        new MainTransactions(strPinCode).setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin", "Incorrect Information" ,JOptionPane.WARNING_MESSAGE);
                    }
           }catch(SQLException e){
               System.out.println("Error: " + e.getMessage());
           }
            
            
        } else if (ae.getSource() == this.btnSignUP) {
            this.setVisible(false);
            new FirstSignupWindow().setVisible(true);
        } else if (ae.getSource() == this.btnClear) {
            txtCardno.setText("");
            txtPinCode.setText("");
        }
    }

    
    public static void main(String[] args) {
        
            new Authentication();
    }
}
