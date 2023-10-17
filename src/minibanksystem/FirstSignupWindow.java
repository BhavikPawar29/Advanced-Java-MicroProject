
package minibanksystem;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import com.toedter.calendar.JDateChooser;

public class FirstSignupWindow extends JFrame implements ActionListener{

    long random;
    
    JLabel lbFormNo, lbPersonalD,lbName, lbFatName, lbDateOB,  lbGender, lbEmailAdd, lbMarital, lbAddress, lbCity, lbState, lbPinCode;
    JTextField txtName, txtFatName,txtEmail, txtPinCode;
    JTextArea txaAddress;
    JButton btnNext;
    JRadioButton radioMale, radioFemale, radioMarried, radioUnmarried, radiOther;
    JComboBox<String> cmbCity, cmbState; 
    JDateChooser dateChooser;
    ButtonGroup genderBtngroup, maritalBtngroup;

    // Arrays for states and cities
    String[] states = { "","Andhra Pradesh", "Arunachal Pradesh", "Assam", /* Add more states */ };
    String[] cities = { "","Hyderabad", "Vijayawada", "Visakhapatnam", /* Add more cities */ };

    FirstSignupWindow(){

        this.setTitle("NEW ACCOUNT APPLICATION FORM - FORM 1");
        this.setSize(920, 850);
        this.setLocation(350, 10);
        this.setVisible(true);
        this.setLayout(null);
        
        Random rnNumber = new Random();
        random = Math.abs((rnNumber.nextLong() % 9000L) + 1000L);

        lbFormNo =  new JLabel("APPLICATION FORM NO. " + random);
        lbFormNo.setFont(new Font("Serif", Font.BOLD, 38));
        lbFormNo.setBounds(140, 20, 600,40);
        this.add(this.lbFormNo);

        lbPersonalD =  new JLabel("Page 1: Personal Details");
        lbPersonalD.setFont(new Font("Serif", Font.BOLD, 22));
        lbPersonalD.setBounds(290, 80, 300,30);
        this.add(this.lbPersonalD);
        
        //Personal Details
            lbName =  new JLabel("Name:");
            lbName.setFont(new Font("Serif", Font.BOLD, 20));
            lbName.setBounds(100, 140, 100,30);
            this.add(this.lbName);
            
            txtName = new JTextField();
            txtName.setFont(new Font("Railway", Font.BOLD, 14));
            txtName.setBounds(300, 140, 400, 40);
            this.add(this.txtName);

            //Father's Name
            lbFatName =  new JLabel("Father's Name:");
            lbFatName.setFont(new Font("Serif", Font.BOLD, 20));
            lbFatName.setBounds(100, 190, 140,30);
            this.add(this.lbFatName);
            
            txtFatName = new JTextField();
            txtFatName.setFont(new Font("Railway", Font.BOLD, 14));
            txtFatName.setBounds(300, 190, 400, 40);
            this.add(this.txtFatName);

            //Date of Birth
            lbDateOB =  new JLabel("Date of Birth:");
            lbDateOB.setFont(new Font("Serif", Font.BOLD, 20));
            lbDateOB.setBounds(100, 240, 140,30);
            this.add(this.lbDateOB);
            
            dateChooser = new JDateChooser();
            dateChooser.setBounds(300, 240, 200, 40);
            dateChooser.setFont(new Font("Railway", Font.BOLD, 14));
            this.add(this.dateChooser);

            //Gender
            lbGender =  new JLabel("Gender:");
            lbGender.setFont(new Font("Serif", Font.BOLD, 20));
            lbGender.setBounds(100, 290, 140,30);
            this.add(this.lbGender);
            
            radioMale = new JRadioButton("Male");
            radioMale.setFont(new Font("Railway", Font.BOLD, 16));
            radioMale.setBackground(new Color(245, 245, 245));
            radioMale.setBounds(300, 290,  60, 30);
            this.add(this.radioMale);
            
            radioFemale = new JRadioButton("Female");
            radioFemale.setFont(new Font("Railway", Font.BOLD, 16));
            radioFemale.setBackground(new Color(245, 245, 245));
            radioFemale.setBounds(450, 290,  80, 30);
            this.add(this.radioFemale);
            
            genderBtngroup = new ButtonGroup();
            genderBtngroup.add(radioMale);
            genderBtngroup.add(radioFemale);

            //Email Address
            lbEmailAdd =  new JLabel("Email Address:");
            lbEmailAdd.setFont(new Font("Serif", Font.BOLD, 20));
            lbEmailAdd.setBounds(100, 340, 140,30);
            this.add(this.lbEmailAdd);
            
            txtEmail = new JTextField();
            txtEmail.setFont(new Font("Railway", Font.BOLD, 14));
            txtEmail.setBounds(300, 340, 400, 40);
            this.add(this.txtEmail);

            //Marital Status
            lbMarital =  new JLabel("Marital Status:");
            lbMarital.setFont(new Font("Serif", Font.BOLD, 20));
            lbMarital.setBounds(100, 390, 140,30);
            this.add(this.lbMarital);
            
            radioMarried = new JRadioButton("Married");
            radioMarried.setFont(new Font("Railway", Font.BOLD, 16));
            radioMarried.setBackground(new Color(245, 245, 245));
            radioMarried.setBounds(300, 390,  100, 30);
            this.add(this.radioMarried);
            
            radioUnmarried = new JRadioButton("Unmarried");
            radioUnmarried.setFont(new Font("Railway", Font.BOLD, 16));
            radioUnmarried.setBackground(new Color(245, 245, 245));
            radioUnmarried.setBounds(450, 390,  110, 30);
            this.add(this.radioUnmarried);
            
            radiOther = new JRadioButton("Other");
            radiOther.setFont(new Font("Railway", Font.BOLD, 16));
            radiOther.setBackground(new Color(245, 245, 245));
            radiOther.setBounds(630, 390,  100, 30);
            this.add(this.radiOther);
            
            maritalBtngroup = new ButtonGroup();
            maritalBtngroup.add(radioMarried);
            maritalBtngroup.add(radioUnmarried);
            maritalBtngroup.add(this.radiOther);

            //Address Details
            lbAddress = new JLabel("Residential Address:");
            lbAddress.setFont(new Font("Serif", Font.BOLD, 20));
            lbAddress.setBounds(100, 440, 190, 30);
            this.add(this.lbAddress);

            txaAddress = new JTextArea();
            txaAddress.setFont(new Font("Railway", Font.BOLD, 14));
            txaAddress.setBounds(300, 440, 400, 100); 
            txaAddress.setLineWrap(true);
            txaAddress.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
            this.add(this.txaAddress);
            
            lbState = new JLabel("State:");
            lbState.setFont(new Font("Serif", Font.BOLD, 20));
            lbState.setBounds(100, 560, 140, 30);
            this.add(this.lbState);
            
            cmbState = new JComboBox<>(states); 
            cmbState.setFont(new Font("Railway", Font.BOLD, 14));
            cmbState.setBounds(300, 560, 400, 40);
            cmbState.setBackground(Color.WHITE);
            this.add(cmbState);
            
            lbCity = new JLabel("City:");
            lbCity.setFont(new Font("Serif", Font.BOLD, 20));
            lbCity.setBounds(100, 610, 140, 30);
            this.add(this.lbCity);
            
            cmbCity = new JComboBox<>(cities); // Initialize with the cities array
            cmbCity.setFont(new Font("Railway", Font.BOLD, 14));
            cmbCity.setBounds(300, 610, 400, 40);
            cmbCity.setBackground(Color.WHITE);
            this.add(cmbCity);

            lbPinCode = new JLabel("PIN Code:");
            lbPinCode.setFont(new Font("Serif", Font.BOLD, 20));
            lbPinCode.setBounds(100, 670, 140, 40);
            this.add(this.lbPinCode);
            
            txtPinCode = new JTextField();
            txtPinCode.setFont(new Font("Railway", Font.BOLD, 14));
            txtPinCode.setBounds(300, 670, 400, 40);
            this.add(this.txtPinCode);

            btnNext = new JButton("Next");
            btnNext.setBackground(Color.LIGHT_GRAY);
            btnNext.setForeground(Color.DARK_GRAY);
            btnNext.setFont(new Font("Ariel", Font.BOLD, 15));
            btnNext.setBounds(550, 730, 180, 40);
            btnNext.addActionListener(this);
            this.add(this.btnNext);
            
        getContentPane().setBackground(new Color(245, 245, 245));
        
        this.setVisible(true);

    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        String fromNo = "" + random; //long
        String strName = txtName.getText();
        String strFname  = txtFatName.getText();
        String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();

        String strGender = null;
        if(radioMale.isSelected()){
            strGender = "Male";
        } else if(radioFemale.isSelected()){
            strGender = "Female";
        }

        String strEmail = txtEmail.getText();

        String strMarital = null;
        if(radioMarried.isSelected()){
            strMarital = "Married";
        }else if(radioUnmarried.isSelected()){
            strMarital = "Unmarried";
        }else if(radiOther.isSelected()){
            strMarital = "Other";
        }

       String strAddress = txaAddress.getText();
       String strState = (String) cmbState.getSelectedItem(); // Corrected variable name
       String strCity = (String) cmbCity.getSelectedItem();
       String strPin = txtPinCode.getText();


        
            // Create an array of text fields and text areas
                JTextField[] textFields = { txtName, txtFatName, txtEmail, txtPinCode };
                JTextArea[] textAreas = { txaAddress };

                boolean isValid = true;

                // Validate all text fields
                for (JTextField textField : textFields) {
                    if (textField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Warning: " + textField.getName() + " is Required");
                        isValid = false;
                        break; // Exit the loop if any field is empty
                    }
                }

                if (isValid) {
                    // Validate all text areas
                    for (JTextArea textArea : textAreas) {
                        if (textArea.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Warning: " + textArea.getName() + " is Required");
                            isValid = false;
                            break; // Exit the loop if any field is empty
                        }
                    }
                }

    if (isValid) {
        try {
            DataBaseConnection connect = new DataBaseConnection();
            
            String strQuery = "insert into tblSignup values('"+fromNo+"','"+strName+"','"+strFname+"','"+dob+"','"+strGender+"','"+strEmail+"','"+strMarital+"','"+strAddress+"','"+strCity+"','"+strPin+"','"+strState+"')";
            // Execute the query
            connect.stateMen.executeUpdate(strQuery);
        
            // Close the PreparedStatement and database connection
            connect.stateMen.close();
            connect.conn.close();
            
            this.setVisible(false);
            new SecondSignupWindow(fromNo).setVisible(true);
           
        } catch (SQLException e) {
            System.out.println("Error:" + e);
    }
}

    }
    public static void main(String[] args) {
        new FirstSignupWindow();
    }
}

