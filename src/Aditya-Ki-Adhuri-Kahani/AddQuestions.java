//package quizproject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddQuestions extends JFrame implements ActionListener{

    JLabel lbInstruction1,lbInstruction2;

    JLabel lbQuizId,lbQuestionNo;
    JTextField tfQuizID,tfQuestionNo;

    JLabel lbQuestion,lbOption1,lbOption2,lbOption3,lbOption4;
    JTextField tfQuestion,tfOption1,tfOption2,tfOption3,tfOption4;

    JLabel lbCorrectOption,lbMarks;
    JComboBox cbCorrectOption,cbMarks;
    
    int quizId,noOfQuestions,questionNo=1;

    JButton btnAddQuestion;

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

    public AddQuestions(int quizID,int noOfQuestions){
        this.quizId=quizID;
        this.noOfQuestions=noOfQuestions;

        this.setTitle("Add Questions");
        this.setSize(850,600);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        GridBagConstraints cons = new GridBagConstraints();

        this.lbInstruction1=new JLabel("Enter following information to enter questions to quiz");
        this.setConstraints(cons,0,0,6,1);
        this.add(this.lbInstruction1,cons);

        this.lbQuizId=new JLabel("Quiz ID");
        this.setConstraints(cons, 0, 1, 1, 1);
        this.add(this.lbQuizId,cons);
        this.tfQuizID=new JTextField(Integer.toString(this.quizId));
        this.tfQuizID.setEditable(false);
        this.setConstraints(cons, 1, 1, 2, 1);
        this.add(this.tfQuizID,cons);

        this.lbQuestionNo=new JLabel("Question No");
        this.setConstraints(cons, 3, 1, 1, 1);
        this.add(this.lbQuestionNo,cons);
        this.tfQuestionNo=new JTextField(Integer.toString(this.questionNo));
        this.tfQuestionNo.setEditable(false);
        this.setConstraints(cons, 4, 1, 2, 1);
        this.add(this.tfQuestionNo,cons);

        this.lbQuestion=new JLabel("Enter Question ");
        this.setConstraints(cons, 0, 2, 1, 1);
        this.add(this.lbQuestion,cons);
        this.tfQuestion=new JTextField();
        this.setConstraints(cons, 1, 2, 5, 1);
        this.add(this.tfQuestion,cons);

        this.lbOption1=new JLabel("Enter Option 1");
        this.setConstraints(cons, 0, 3, 1, 1);
        this.add(this.lbOption1,cons);
        this.tfOption1=new JTextField();
        this.setConstraints(cons, 1, 3, 5, 1);
        this.add(this.tfOption1,cons);

        this.lbOption2=new JLabel("Enter Option 3");
        this.setConstraints(cons, 0, 4, 1, 1);
        this.add(this.lbOption2,cons);
        this.tfOption2=new JTextField();
        this.setConstraints(cons, 1, 4, 5, 1);
        this.add(this.tfOption2,cons);

        this.lbOption3=new JLabel("Enter Option 3");
        this.setConstraints(cons, 0, 5, 1, 1);
        this.add(this.lbOption3,cons);
        this.tfOption3=new JTextField();
        this.setConstraints(cons, 1, 5, 5, 1);
        this.add(this.tfOption3,cons);

         this.lbOption4=new JLabel("Enter Option 4");
        this.setConstraints(cons, 0, 6, 1, 1);
        this.add(this.lbOption4,cons);
        this.tfOption4=new JTextField();
        this.setConstraints(cons, 1, 6, 5, 1);
        this.add(this.tfOption4,cons);

        this.lbCorrectOption=new JLabel("Correct Option");
        this.setConstraints(cons, 0, 7, 1, 1);
        this.add(this.lbCorrectOption,cons);
        this.cbCorrectOption=new JComboBox();
        this.cbCorrectOption.addItem(1);
        this.cbCorrectOption.addItem(2);
        this.cbCorrectOption.addItem(3);
        this.cbCorrectOption.addItem(4);
        this.setConstraints(cons, 1, 7, 2, 1);
        this.add(this.cbCorrectOption,cons);

        this.lbMarks=new JLabel("Marks/Points:");
        this.setConstraints(cons, 3, 7, 1, 1);
        this.add(this.lbMarks,cons);
        this.cbMarks=new JComboBox();
        this.cbMarks.addItem(1);
        this.cbMarks.addItem(2);
        this.setConstraints(cons, 4, 7, 2, 1);
        this.add(this.cbMarks,cons);

        this.btnAddQuestion=new JButton("Add the Question");
        this.setConstraints(cons, 0, 8, 6, 1);
         this.btnAddQuestion.addActionListener(this);
        this.add(this.btnAddQuestion,cons);

        this.lbInstruction2=new JLabel("While entering questions and options beware of special symbols such as single quotes (') use escape sequence such as \\' for entering them. ");
        this.setConstraints(cons, 0, 9, 6, 1);
        this.add(this.lbInstruction2,cons);
        
        this.createConnection();
        
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            ps = con.prepareCall("INSERT INTO tblQuestions VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(this.tfQuestionNo.getText()));
            ps.setString(2, this.tfQuestion.getText());
            ps.setString(3, this.tfOption1.getText());
            ps.setString(4, this.tfOption2.getText());
            ps.setString(5, this.tfOption3.getText());
            ps.setString(6, this.tfOption4.getText()); 
            ps.setInt(7, Integer.parseInt(this.cbCorrectOption.getSelectedItem().toString()));
            ps.setInt(8, Integer.parseInt(this.cbMarks.getSelectedItem().toString()));
            ps.setInt(9, Integer.parseInt(this.tfQuizID.getText()));
            ps.setInt(10, this.quizId);

            int i = ps.executeUpdate();
            System.out.println("executeUpdate()");
            if (i == 1) {
                JOptionPane.showMessageDialog(this, "Question added.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
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
        new AddQuestions(12345, 20);
    }
    
}
