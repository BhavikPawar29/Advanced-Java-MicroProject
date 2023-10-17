
package minibanksystem;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class ThirdSignupWindow extends JFrame implements ActionListener{
    
    JLabel AccountD, lbAccType, lbCardNo, lbNumber, lbPIN, lbCardInfo, lbPINinfo, lbServicesReq;
    JRadioButton rbSaving, rbRecurring, rbCurrent, rbFixedDacc;
    ButtonGroup groupAcc;
    JCheckBox cbATMcard, cbInternet, cbMobile, cbAlerts, cbChequeBook, cbEstatement, cbDeclaration;
    JButton btnSubmit, btnCancel;
    
    String formNo;
    
    ThirdSignupWindow(String formNo){
        this.setTitle("NEW ACCOUNT APPLICATION FORM - FORM 3");
        this.setSize(800, 820);
        this.setLocation(350, 10);
        this.setVisible(true);
        this.setLayout(null);
        
        this.formNo = formNo;
               
        AccountD = new JLabel("Page 3: Account Details");
        AccountD.setFont(new Font("Serif", Font.BOLD, 22));
        AccountD.setBounds(290, 80, 300, 30);
        this.add(this.AccountD);
        
        lbAccType = new JLabel("Account Type");
        lbAccType.setFont(new Font("Railway", Font.BOLD, 18));
        lbAccType.setBounds(100, 140, 200, 30);
        this.add(this.lbAccType);
        
        //Radio Buttons for Account type
        rbSaving = new JRadioButton("Saving Account");
        rbSaving.setFont(new Font("Railway", Font.BOLD, 16));
        rbSaving.setBackground(new Color(245, 245, 245));
        rbSaving.setBounds(100, 180, 150, 20);
        this.add(this.rbSaving);
        
        rbRecurring = new JRadioButton("Recurring Deposit Account");
        rbRecurring.setFont(new Font("Railway", Font.BOLD, 16));
        rbRecurring.setBackground(new Color(245, 245, 245));
        rbRecurring.setBounds(350, 180, 240, 20);
        this.add(this.rbRecurring);
        
        rbCurrent = new JRadioButton("Current Account");
        rbCurrent.setFont(new Font("Railway", Font.BOLD, 16));
        rbCurrent.setBackground(new Color(245, 245, 245));
        rbCurrent.setBounds(100, 220, 150, 20);
        this.add(this.rbCurrent);
        
        rbFixedDacc = new JRadioButton("Fixed Deposit Account");
        rbFixedDacc.setFont(new Font("Railway", Font.BOLD, 16));
        rbFixedDacc.setBackground(new Color(245, 245, 245));
        rbFixedDacc.setBounds(350, 220, 200, 20);
        this.add(this.rbFixedDacc);
        
        groupAcc = new ButtonGroup();
        groupAcc.add(rbSaving);
        groupAcc.add(rbRecurring);
        groupAcc.add(rbCurrent);
        groupAcc.add(rbFixedDacc);
        
        lbCardNo = new JLabel("Card Number");
        lbCardNo.setFont(new Font("Railway", Font.BOLD, 18));
        lbCardNo.setBounds(100, 270, 200, 30);
        this.add(this.lbCardNo);
        
        lbNumber = new JLabel("XXXX-XXXX-XXXX-4190");
        lbNumber.setFont(new Font("Railway", Font.BOLD, 18));
        lbNumber.setBounds(300, 270, 215, 30);
        this.add(this.lbNumber);
        
        lbCardInfo = new JLabel("Your 16 Digit Card Number");
        lbCardInfo.setFont(new Font("Railway", Font.BOLD, 12));
        lbCardInfo.setBounds(300, 300, 240, 20);
        this.add(this.lbCardInfo);
        
        lbPIN = new JLabel("PIN");
        lbPIN.setFont(new Font("Railway", Font.BOLD, 18));
        lbPIN.setBounds(100, 340, 200, 30);
        this.add(this.lbPIN);
        
        lbNumber = new JLabel("XXXX-XXXX-XXXX-4190");
        lbNumber.setFont(new Font("Railway", Font.BOLD, 18));
        lbNumber.setBounds(300, 340, 215, 30);
        this.add(this.lbNumber);
        
        lbPINinfo = new JLabel("Your 4 Digit PIN Number");
        lbPINinfo.setFont(new Font("Railway", Font.BOLD, 12));
        lbPINinfo.setBounds(300, 370, 240, 20);
        this.add(this.lbPINinfo);
        
        lbServicesReq = new JLabel("Services Required:");
        lbServicesReq.setFont(new Font("Railway", Font.BOLD, 18));
        lbServicesReq.setBounds(100, 430, 200, 30);
        this.add(this.lbServicesReq);
        
        //CheckBoxes for Services
        cbATMcard = new JCheckBox("ATM Card");
        cbATMcard.setFont(new Font("Railway", Font.BOLD, 16));
        cbATMcard.setBackground(new Color(245, 245, 245));
        cbATMcard.setBounds(100, 480, 200, 30);
        this.add(this.cbATMcard);
        
        cbInternet = new JCheckBox("Internet Banking");
        cbInternet.setFont(new Font("Railway", Font.BOLD, 16));
        cbInternet.setBackground(new Color(245, 245, 245));
        cbInternet.setBounds(300, 480, 200, 30);
        this.add(this.cbInternet);
        
        cbMobile = new JCheckBox("Mobile Banking");
        cbMobile.setFont(new Font("Railway", Font.BOLD, 16));
        cbMobile.setBackground(new Color(245, 245, 245));
        cbMobile.setBounds(100, 520, 200, 30);
        this.add(this.cbMobile);
        
        cbAlerts = new JCheckBox("Email & SMS Alerts");
        cbAlerts.setFont(new Font("Railway", Font.BOLD, 16));
        cbAlerts.setBackground(new Color(245, 245, 245));
        cbAlerts.setBounds(300, 520, 200, 30);
        this.add(this.cbAlerts);
        
        cbChequeBook = new JCheckBox("Cheque Book");
        cbChequeBook.setFont(new Font("Railway", Font.BOLD, 16));
        cbChequeBook.setBackground(new Color(245, 245, 245));
        cbChequeBook.setBounds(100, 560, 200, 30);
        this.add(this.cbChequeBook);
        
        cbEstatement = new JCheckBox("E-Statement");
        cbEstatement.setFont(new Font("Railway", Font.BOLD, 16));
        cbEstatement.setBackground(new Color(245, 245, 245));
        cbEstatement.setBounds(300, 560, 200, 30);
        this.add(this.cbEstatement);
        
        cbDeclaration = new JCheckBox("I hereby declares that the above details are correct to the best of my knowledge");
        cbDeclaration.setFont(new Font("Railway", Font.BOLD, 10));
        cbDeclaration.setBackground(new Color(245, 245, 245));
        cbDeclaration.setBounds(100, 610, 430, 30);
        this.add(this.cbDeclaration);
        
        
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(250, 690, 100, 30);
        btnSubmit.setFont(new Font("Ariel", Font.BOLD, 14));
        btnSubmit.setBackground(Color.LIGHT_GRAY);
        btnSubmit.setForeground(Color.DARK_GRAY);
        btnSubmit.addActionListener(this);
        this.add(this.btnSubmit);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(400, 690, 100, 30);
        btnCancel.setFont(new Font("Ariel", Font.BOLD, 14));
        btnCancel.setBackground(Color.LIGHT_GRAY);
        btnCancel.setForeground(Color.DARK_GRAY);
        btnCancel.addActionListener(this);
        this.add(this.btnCancel);
        
        getContentPane().setBackground(new Color(245, 245, 245));
    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == btnSubmit) {
        String strAcctype = null;
        if (rbSaving.isSelected()) {
            strAcctype = "Saving Account";
        } else if (rbFixedDacc.isSelected()) {
            strAcctype = "Fixed Deposit Account";
        } else if (rbCurrent.isSelected()) {
            strAcctype = "Current Account";
        } else if (rbRecurring.isSelected()) {
            strAcctype = "Recurring Deposit Account";
        }

        Random random = new Random();
        String strCardNo = "" + Math.abs((random.nextLong() % 900000L) + 504093600000000L);
        String strPINno = "" + Math.abs((random.nextLong() % 9000L));

        String facility = "";
        if (cbATMcard.isSelected()) {
            facility = facility + " ATM Card";
        } else if (cbInternet.isSelected()) {
            facility = facility + " Internet Banking";
        } else if (cbMobile.isSelected()) {
            facility = facility + " Mobile Banking";
        } else if (cbAlerts.isSelected()) {
            facility = facility + " EMAIL Alerts";
        } else if (cbChequeBook.isSelected()) {
            facility = facility + " Cheque Book";
        } else if (cbEstatement.isSelected()) {
            facility = facility + " E-Statement";
        }

        if (!cbDeclaration.isSelected()) {
            JOptionPane.showMessageDialog(this, "You must check the Declaration checkbox to proceed.", "Declaration Required", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                if (strAcctype == null) {
                    JOptionPane.showMessageDialog(null, "Please select the account type", "Missing Information", JOptionPane.WARNING_MESSAGE);
                } else if (facility.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select at least one service.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                }else {
                    DataBaseConnection connect = new DataBaseConnection();

                    String strQuery1 = "insert into tblthirdsignup values('" + formNo + "','" + strAcctype + "','" + strCardNo + "','" + strPINno + "','" + facility + "')";
                    String strQuery2 = "insert into tbllogin values('" + formNo + "','" + strCardNo + "','" + strPINno + "')";

                    connect.stateMen.executeUpdate(strQuery1);
                    connect.stateMen.executeUpdate(strQuery2);

                    JOptionPane.showMessageDialog(null, "Card Number: " + strCardNo + "\n Pin:" + strPINno);

                    // Close the PreparedStatement and database connection
                    connect.stateMen.close();
                    connect.conn.close();
                }
                
                this.setVisible(false);
                new DepositClass(strPINno).setVisible(true);
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    } else if (ae.getSource() == btnCancel) {
        this.setVisible(false);
        new Authentication().setVisible(true);
    }
}
    
    public static void main(String[] args) {
        new ThirdSignupWindow("");
    }
    
}