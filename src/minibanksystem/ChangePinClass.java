
package minibanksystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;


public class ChangePinClass extends JFrame implements ActionListener{
    
    ImageIcon BgImage, backgroundImage;
    Image scaledImage;
    JLabel lbImage, lbConfirmChange, lbNewPin, lbReEnter;
    JPasswordField txtNewPin, txtReEnter;
    JButton btnChange, btnBack;
    
    String strPinNumber;
    
    ChangePinClass(String pinNumber){
        this.setTitle("Change-Pin Window");
        this.setSize(650, 650);
        this.setLocation(400, 100);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        
        this.strPinNumber = pinNumber;
    
        BgImage = new ImageIcon(ClassLoader.getSystemResource("icons/background.jpg"));
        scaledImage = BgImage.getImage().getScaledInstance(650, 650, Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(scaledImage);
        lbImage = new JLabel(backgroundImage);
        lbImage.setIcon(backgroundImage); 
        lbImage.setBounds(0, 0, 650, 650);
        this.add(lbImage);
        
        lbConfirmChange = new JLabel("CHANGE YOUR PIN");
        lbConfirmChange.setForeground(Color.WHITE);
        lbConfirmChange.setFont(new Font("System", Font.BOLD, 18));
        lbConfirmChange.setBounds(140, 140, 180, 40);
        lbImage.add(this.lbConfirmChange);
        
        lbNewPin = new JLabel("New Pin:");
        lbNewPin.setForeground(Color.WHITE);
        lbNewPin.setFont(new Font("System", Font.BOLD, 18));
        lbNewPin.setBounds(40, 220, 180, 30);
        lbImage.add(this.lbNewPin);
        
        txtNewPin = new JPasswordField();
        txtNewPin.setFont(new Font("Railway", Font.BOLD, 25));
        txtNewPin.setBounds(200, 220, 180, 30);
        lbImage.add(this.txtNewPin);
        
        lbReEnter = new JLabel("Re-Enter Pin:");
        lbReEnter.setForeground(Color.WHITE);
        lbReEnter.setFont(new Font("System", Font.BOLD, 18));
        lbReEnter.setBounds(40, 290, 180, 30);
        lbImage.add(this.lbReEnter);
        
        txtReEnter = new JPasswordField();
        txtReEnter.setFont(new Font("Railway", Font.BOLD, 25));
        txtReEnter.setBounds(200, 290, 180, 30);
        lbImage.add(this.txtReEnter);
        
        //Buttons for pin change
        
            btnChange = new JButton("CHANGE");
            btnChange.setBounds(450, 430, 150, 40);
            btnChange.setFont(new Font("Ariel", Font.BOLD, 16));
            btnChange.setBackground(Color.GRAY);
            btnChange.setForeground(Color.WHITE);
            btnChange.addActionListener(this);
            lbImage.add(this.btnChange);
            
            btnBack = new JButton("BACK");
            btnBack.setBounds(450, 500, 150, 40);
            btnBack.setFont(new Font("Ariel", Font.BOLD, 16));
            btnBack.setBackground(Color.GRAY);
            btnBack.setForeground(Color.WHITE);
            btnBack.addActionListener(this);
            lbImage.add(this.btnBack);
        getContentPane().setBackground(new Color(245, 245, 245));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnChange) {
            try {
                String strNewPin = txtNewPin.getText();
                String strReEnter = txtReEnter.getText();

                if (!strNewPin.equals(strReEnter)) {
                    JOptionPane.showMessageDialog(null, "Entered PINs Do Not Match", "Incorrect Information", JOptionPane.ERROR_MESSAGE);
                    return;
                } 
                
                if(strNewPin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter new PIN number", "Incorrect Information", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(strReEnter.equals("")){
                    JOptionPane.showMessageDialog(null, "Please Re-enter new PIN number", "Incorrect Information", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    DataBaseConnection connect = new DataBaseConnection();
                    
                    String strBankQuery = "update tblbank set pin = '"+strReEnter+"' where pin = '"+strPinNumber+"' ";
                    String strLoginQuery = "update tbllogin set pinNo = '"+strReEnter+"' where pinNo = '"+strPinNumber+"' ";
                    String strSignupQuery_3 = "update tblthirdsignup set pinNo = '"+strReEnter+"' where pinNo = '"+strPinNumber+"' ";

                    connect.stateMen.executeUpdate(strBankQuery);
                    connect.stateMen.executeUpdate(strLoginQuery);
                    connect.stateMen.executeUpdate(strSignupQuery_3);
                    
                    JOptionPane.showMessageDialog(null, "PIN successfully changed", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    this.setVisible(false);
                    new MainTransactions(strNewPin).setVisible(true);
                    
                    // Close the PreparedStatement and database connection
                    connect.stateMen.close();
                    connect.conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else if(ae.getSource() == btnBack){
            this.setVisible(false);
            new MainTransactions("").setVisible(true);
        }
    }//ActionPerformed method close

    public static void main(String[] args) {
        new ChangePinClass("");
    }
}
