//package quizproject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StartQuiz extends JFrame implements ActionListener{

    JLabel lbInstruction;
    JLabel lbQuizId, lbEnrollmentNo, lbStudentName;
    JTextField tfQuizId, tfEnrollmentNo, tfStudentName;
    JButton btnStartQuiz;
    
    Connection con = null;
    PreparedStatement ps = null;

    public StartQuiz(){
        super("Start Quiz");
        this.setSize(700, 250);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        GridBagConstraints cons = new GridBagConstraints();

        this.lbInstruction = new JLabel("Enter the following details to start the test. ");
        setConstraints(cons, 0, 0, 4, 1);
        this.add(this.lbInstruction, cons);

        this.lbQuizId = new JLabel("Enter Quiz Id");
        setConstraints(cons, 0, 1, 1, 1);
        this.add(this.lbQuizId, cons);
        this.tfQuizId = new JTextField();
        setConstraints(cons, 1, 1, 1, 1);
        this.add(this.tfQuizId, cons);

        this.lbEnrollmentNo = new JLabel("Enter enrollment no:");
        setConstraints(cons, 2, 1, 1, 1);
        this.add(this.lbEnrollmentNo, cons);
        this.tfEnrollmentNo = new JTextField();
        setConstraints(cons, 3, 1, 1, 1);
        this.add(this.tfEnrollmentNo, cons);

        this.lbStudentName = new JLabel("Enter Student Name: ");
        setConstraints(cons, 0, 2, 1, 1);
        this.add(this.lbStudentName, cons);
        this.tfStudentName = new JTextField();
        setConstraints(cons, 1, 2, 3, 1);
        this.add(this.tfStudentName, cons);

        this.btnStartQuiz = new JButton("Start the Quiz");
        setConstraints(cons, 0, 3, 4, 1);
        this.btnStartQuiz.addActionListener(this);
        this.add(this.btnStartQuiz, cons);
        
        createConnection();
    }
    
    public void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzaordersystem", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
     
    //@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStartQuiz) {
            try {
                String quizId = tfQuizId.getText();
                String enrollmentNo = tfEnrollmentNo.getText();
                String studentName = tfStudentName.getText();

                ps = con.prepareStatement("INSERT INTO quiz_details (quiz_id, enrollment_no, student_name) VALUES (?, ?, ?)");
                ps.setString(1, quizId);
                ps.setString(2, enrollmentNo);
                ps.setString(3, studentName);

                int i = ps.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Quiz details added to the database.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add quiz details.");
                }

                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
    
    public void setConstraints(GridBagConstraints cons, int x, int y, int w, int h) {

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = w;
        cons.gridheight = h;
        cons.ipadx = 0;
        cons.ipady = 0;
        cons.weightx = 0.5;
        cons.weighty = 0.5;
    }

    public static void main(String[] args) {
        new StartQuiz();
    }
}