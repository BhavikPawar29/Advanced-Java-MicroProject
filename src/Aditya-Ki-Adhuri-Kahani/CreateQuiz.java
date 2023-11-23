//package quizproject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

import java.lang.reflect.Array;

public class CreateQuiz extends JFrame implements ActionListener {

    JLabel lbInstruction;
    JLabel lbQuizId, lbQuizTitle, lbCourseTitle, lbCourseCode, lbClass, lbQuesionNo, lbDuration;
    JTextField tfQuizId, tfQuizTitle, tfCourseTitle, tfCourseCode, tfQuestionNo;
    JComboBox cbClass, cbDuration;
    JButton btnCreate;

    Connection con = null;
    PreparedStatement ps = null;

    public void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzaordersystem","root", "root");

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public CreateQuiz() {
        this.createConnection();
        this.setTitle("Create Quiz");
        this.setSize(850, 350);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        GridBagConstraints cons = new GridBagConstraints();

        this.lbInstruction = new JLabel("Enter the data below to create New Quiz", JLabel.LEFT);
        this.setConstraints(cons, 0, 0, 4, 1);
        this.add(this.lbInstruction, cons);

        this.lbQuizId = new JLabel("Quiz Id");
        this.setConstraints(cons, 0, 1, 1, 1);
        this.add(this.lbQuizId, cons);
        this.tfQuizId = new JTextField(20);
        this.setConstraints(cons, 1, 1, 1, 1);
        this.add(this.tfQuizId, cons);

        this.lbQuizTitle = new JLabel("Quiz Title");
        this.setConstraints(cons, 2, 1, 1, 1);
        this.add(this.lbQuizTitle, cons);
        this.tfQuizTitle = new JTextField(30);
        this.setConstraints(cons, 3, 1, 1, 1);
        this.add(this.tfQuizTitle, cons);

        this.lbCourseTitle = new JLabel("Couse Title");
        this.setConstraints(cons, 0, 2, 1, 1);
        this.add(this.lbCourseTitle, cons);
        this.tfCourseTitle = new JTextField(60);
        this.setConstraints(cons, 1, 2, 3, 1);
        this.add(this.tfCourseTitle, cons);

        this.lbCourseCode = new JLabel("Course Code");
        this.setConstraints(cons, 0, 3, 1, 1);
        this.add(this.lbCourseCode, cons);
        this.tfCourseCode = new JTextField(10);
        this.setConstraints(cons, 1, 3, 1, 1);
        this.add(this.tfCourseCode, cons);

        this.lbClass = new JLabel("Class");
        this.setConstraints(cons, 2, 3, 1, 1);
        this.add(this.lbClass, cons);
        this.cbClass = new JComboBox();
        this.cbClass.addItem("FYCO");
        this.cbClass.addItem("SYCO");
        this.cbClass.addItem("TYCO");
        this.setConstraints(cons, 3, 3, 1, 1);
        this.add(this.cbClass, cons);

        this.lbQuesionNo = new JLabel("Number of Questions");
        this.setConstraints(cons, 0, 4, 1, 1);
        this.add(this.lbQuesionNo, cons);
        this.tfQuestionNo = new JTextField(20);
        this.setConstraints(cons, 1, 4, 1, 1);
        this.add(this.tfQuestionNo, cons);

        this.lbDuration = new JLabel("Duration of Test");
        this.setConstraints(cons, 2, 4, 1, 1);
        this.add(this.lbDuration, cons);
        this.cbDuration = new JComboBox();
        this.cbDuration.addItem("30 Minutes");
        this.cbDuration.addItem("1 Hour");
        this.cbDuration.addItem("2 Hours");
        this.cbDuration.addItem("2 Hours 30 Minutes");
        this.cbDuration.addItem("3 Hours");
        this.setConstraints(cons, 3, 4, 1, 1);
        this.add(this.cbDuration, cons);

        this.btnCreate = new JButton("Create the test");
        this.btnCreate.addActionListener(this);
        this.setConstraints(cons, 0, 5, 4, 0);
        this.add(this.btnCreate, cons);

    }

    @Override
public void actionPerformed(ActionEvent e) {
    try {
        ps = con.prepareStatement("INSERT INTO tblQuiz VALUES (?,?,?,?,?,?,?)");
        ps.setInt(1, Integer.parseInt(this.tfQuizId.getText()));
        ps.setString(2, this.tfQuizTitle.getText());
        ps.setString(3, this.tfCourseTitle.getText()); 
        ps.setString(4, this.tfCourseCode.getText());
        ps.setString(5, this.cbClass.getSelectedItem().toString());
        ps.setInt(6, Integer.parseInt(this.tfQuestionNo.getText())); 
        ps.setString(7, this.cbDuration.getSelectedItem().toString()); 

        int i = ps.executeUpdate();
        if (i == 1) {
            JOptionPane.showMessageDialog(this, "Quiz added.");
        }
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    new AddQuestions(Integer.parseInt(this.tfQuizId.getText()), Integer.parseInt(this.tfQuestionNo.getText()));
}


    public void setConstraints(GridBagConstraints con, int x, int y, int w, int h) {

        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = x;
        con.gridy = y;
        con.gridwidth = w;
        con.gridheight = h;
        con.ipadx = 0;
        con.ipady = 0;
        con.weightx = 0.5;
        con.weighty = 0.5;
    }

    public static void main(String[] args) {
        new CreateQuiz();
    }
}