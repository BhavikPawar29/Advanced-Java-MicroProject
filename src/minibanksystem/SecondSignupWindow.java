
package minibanksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


public class SecondSignupWindow extends JFrame implements ActionListener{
    JLabel lbAdditionalD, lbReligion, lbCategory, lbIncome, lbEducation, lbQualification, lbOccupation, lbPANno, lbAadharNo, lbSenior, lbExistAcc;
    JTextField txtPANno, txtAadharNo;
    JComboBox<String> cmbReligion, cmbCategory, cmbIncome, cmbQualification, cmbOccupation;
    JButton btnNext;
    JRadioButton radioExistacc, radioNoExistacc, radioSenior, radioNoSenior;
    ButtonGroup checkExistacc, checkSenior;
    
    String strFormNo;
    
    String strReligion[] = {" ","Hindu", "Muslim", "Sikh", "Christian", "Other"};
    String strCategory[] = {"", "General", "OBC", "SC", "ST", "Other"};
    String strIncome[] = {" ", "Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000"};
    String strEducation[] = {" ", "Non-Graduate", "Graduate", "Post-Graduation", "Doctrate", "Other"};
    String strOccupation[] = {" ", "Salaried", "Self-Employed", "Bussiness", "Student", "Retired" ,"Other"};

    SecondSignupWindow(String strFormNo) {
        this.setTitle("NEW ACCOUNT APPLICATION FORM - FORM 2");
        this.setSize(920, 850);
        this.setLocation(350, 10);
        this.setVisible(true);
        this.setLayout(null);
        
        this.strFormNo = strFormNo;

        lbAdditionalD = new JLabel("Page 2: Additional Details");
        lbAdditionalD.setFont(new Font("Serif", Font.BOLD, 22));
        lbAdditionalD.setBounds(290, 80, 300, 30);
        this.add(this.lbAdditionalD);

        // Additional Details
        lbReligion = new JLabel("Religion:");
        lbReligion.setFont(new Font("Serif", Font.BOLD, 20));
        lbReligion.setBounds(100, 150, 100, 30);
        this.add(this.lbReligion);

        cmbReligion = new JComboBox<>(strReligion);
        cmbReligion.setFont(new Font("Railway", Font.BOLD, 14));
        cmbReligion.setBounds(300, 150, 400, 40);
        cmbReligion.setBackground(Color.WHITE);
        this.add(this.cmbReligion);

        lbCategory = new JLabel("Category:");
        lbCategory.setFont(new Font("Serif", Font.BOLD, 20));
        lbCategory.setBounds(100, 200, 140, 30);
        this.add(this.lbCategory);

        cmbCategory = new JComboBox(strCategory);
        cmbCategory.setFont(new Font("Railway", Font.BOLD, 14));
        cmbCategory.setBounds(300, 200, 400, 40);
        cmbCategory.setBackground(Color.WHITE);
        this.add(this.cmbCategory);

        lbIncome = new JLabel("Income:");
        lbIncome.setFont(new Font("Serif", Font.BOLD, 20));
        lbIncome.setBounds(100, 250, 140, 30);
        this.add(this.lbIncome);

        cmbIncome = new JComboBox(strIncome);
        cmbIncome.setFont(new Font("Railway", Font.BOLD, 14));
        cmbIncome.setBounds(300, 250, 400, 40);
        cmbIncome.setBackground(Color.WHITE);
        this.add(this.cmbIncome);

        lbEducation = new JLabel("Educational");
        lbEducation.setFont(new Font("Serif", Font.BOLD, 20));
        lbEducation.setBounds(100, 300, 140, 30);
        this.add(this.lbEducation);

        lbQualification = new JLabel("Qualification:");
        lbQualification.setFont(new Font("Serif", Font.BOLD, 20));
        lbQualification.setBounds(100, 330, 140, 30);
        this.add(this.lbQualification);

        cmbQualification = new JComboBox(strEducation);
        cmbQualification.setFont(new Font("Railway", Font.BOLD, 14));
        cmbQualification.setBounds(300, 310, 400, 40);
        cmbQualification.setBackground(Color.WHITE);
        this.add(this.cmbQualification);
        
        lbOccupation = new JLabel("Occupation:");
        lbOccupation.setFont(new Font("Serif", Font.BOLD, 20));
        lbOccupation.setBounds(100, 380, 140, 30);
        this.add(this.lbOccupation);

        cmbOccupation = new JComboBox(strOccupation);
        cmbOccupation.setFont(new Font("Railway", Font.BOLD, 14));
        cmbOccupation.setBounds(300, 375, 400, 40);
        cmbOccupation.setBackground(Color.WHITE);
        this.add(this.cmbOccupation);

        lbPANno = new JLabel("PAN Number:");
        lbPANno.setFont(new Font("Serif", Font.BOLD, 20));
        lbPANno.setBounds(100, 440, 190, 40);
        this.add(this.lbPANno);

        txtPANno = new JTextField();
        txtPANno.setFont(new Font("Railway", Font.BOLD, 14));
        txtPANno.setBounds(300, 440, 400, 40);
        this.add(this.txtPANno);

        lbAadharNo = new JLabel("Aadhar Number:");
        lbAadharNo.setFont(new Font("Serif", Font.BOLD, 20));
        lbAadharNo.setBounds(100, 503, 190, 30);
        this.add(this.lbAadharNo);

        txtAadharNo = new JTextField();
        txtAadharNo.setFont(new Font("Railway", Font.BOLD, 14));
        txtAadharNo.setBounds(300, 500, 400, 40);
        this.add(this.txtAadharNo);

        lbExistAcc = new JLabel("Exisiting Account:");
        lbExistAcc.setFont(new Font("Serif", Font.BOLD, 20));
        lbExistAcc.setBounds(100, 560, 160, 30);
        this.add(this.lbExistAcc);

        radioExistacc = new JRadioButton("Yes");
        radioExistacc.setFont(new Font("Railway", Font.BOLD, 16));
        radioExistacc.setBounds(300, 560, 60, 30);
        radioExistacc.setBackground(new Color(245, 245,245));
        this.add(this.radioExistacc);

        radioNoExistacc = new JRadioButton("No");
        radioNoExistacc.setFont(new Font("Railway", Font.BOLD, 16));
        radioNoExistacc.setBounds(400, 560, 50, 30);
        radioNoExistacc.setBackground(new Color(245, 245,245));
        this.add(this.radioNoExistacc);
        
        checkExistacc = new ButtonGroup();
        checkExistacc.add(radioExistacc);
        checkExistacc.add(radioNoExistacc);
        
        lbSenior = new JLabel("Senior Citizen:");
        lbSenior.setFont(new Font("Serif", Font.BOLD, 20));
        lbSenior.setBounds(100, 610, 190, 30);
        this.add(this.lbSenior);

        radioSenior = new JRadioButton("Yes");
        radioSenior.setFont(new Font("Railway", Font.BOLD, 16));
        radioSenior.setBounds(300, 610, 60, 30);
        radioSenior.setBackground(new Color(245, 245, 245));
        this.add(this.radioSenior);

        radioNoSenior = new JRadioButton("No");
        radioNoSenior.setFont(new Font("Railway", Font.BOLD, 16));
        radioNoSenior.setBounds(400, 610, 50, 30);
        radioNoSenior.setBackground(new Color(245, 245, 245));
        this.add(this.radioNoSenior);
        
        checkSenior = new ButtonGroup();
        checkSenior.add(radioSenior);
        checkSenior.add(radioNoSenior);

        btnNext = new JButton("Next");
        btnNext.setBackground(Color.LIGHT_GRAY);
        btnNext.setForeground(Color.DARK_GRAY);
        btnNext.setFont(new Font("Ariel", Font.BOLD, 15));
        btnNext.setBounds(550, 750, 180, 40);
        btnNext.addActionListener(this);
        this.add(this.btnNext);

        getContentPane().setBackground(new Color(245, 245, 245));

        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){

        String strReligion = (String) cmbReligion.getSelectedItem();
        String strCategory = (String) cmbCategory.getSelectedItem();
        String strIncome = (String) cmbIncome.getSelectedItem();
        String strQualification = (String) cmbQualification.getSelectedItem();
        String strOccupation = (String) cmbOccupation.getSelectedItem();
        String strPAN = txtPANno.getText();
        String strAadhar = txtAadharNo.getText();

        String strseniorCitizen = null;
        if(radioSenior.isSelected()){
            strseniorCitizen = "Yes";
        }
        else if(radioNoSenior.isSelected()){
            strseniorCitizen = "No";
        }

        String strExisiting = null;
        if(radioExistacc.isSelected()){
            strExisiting = "Yes";
        }
        else if(radioNoExistacc.isSelected()){
            strExisiting = "No";
        }

        try{
            DataBaseConnection connect = new DataBaseConnection();
            
            String strQuery = "insert into tblsecondsignup values('"+strFormNo+"','"+strReligion+"','"+strCategory+"','"+strIncome+"','"+strQualification+"','"+strOccupation+"','"+strPAN+"','"+strAadhar+"','"+strseniorCitizen+"','"+strExisiting+"')";
            // Execute the query
            connect.stateMen.executeUpdate(strQuery);

            // Close the PreparedStatement and database connection
            connect.stateMen.close();
            connect.conn.close();
            
            //ThirdSignupWindow();
            this.setVisible(false);
            new ThirdSignupWindow(strFormNo).setVisible(true);
            
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            }
    }

    public static void main(String[] args) {
        new SecondSignupWindow("");
    }
}
