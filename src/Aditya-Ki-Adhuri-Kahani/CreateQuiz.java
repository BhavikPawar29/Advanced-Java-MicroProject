import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CreateQuiz extends JFrame implements ActionListener {

    JLabel lbInstruction, lbQuizId, lbQuizTitle, lbCourseTitle, lbCourseCode, lbClass, lbQuestionNo, lbDuration;
    JTextField tfQuizId, tfQuizTitle, tfCourseTitle, tfCourseCode, tfQuestionNo;
    JComboBox<String> cbClass, cbDuration;
    JButton btnCreate;

    Connection con = null;
    PreparedStatement ps = null;

    public void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzaordersystem", "root", "root");

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public CreateQuiz() {
        this.createConnection();
        this.setTitle("Create Quiz");
        this.setSize(600, 600);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        GridBagConstraints cons = new GridBagConstraints();

        this.lbInstruction = new JLabel("Enter the data below to create a new quiz", JLabel.LEFT);
        this.setConstraints(cons, 0, 0, 4, 1, GridBagConstraints.CENTER);
        this.add(this.lbInstruction, cons);

        this.lbQuizId = new JLabel("Quiz Id");
        this.setConstraints(cons, 0, 1, 1, 1, GridBagConstraints.EAST);
        this.add(this.lbQuizId, cons);
        this.tfQuizId = new JTextField(20);
        this.setConstraints(cons, 1, 1, 1, 1, GridBagConstraints.WEST);
        this.add(this.tfQuizId, cons);

        this.lbQuizTitle = new JLabel("Quiz Title");
        this.setConstraints(cons, 2, 1, 1, 1, GridBagConstraints.EAST);
        this.add(this.lbQuizTitle, cons);
        this.tfQuizTitle = new JTextField(30);
        this.setConstraints(cons, 3, 1, 1, 1, GridBagConstraints.WEST);
        this.add(this.tfQuizTitle, cons);

        this.lbCourseTitle = new JLabel("Course Title");
        this.setConstraints(cons, 0, 2, 1, 1, GridBagConstraints.EAST);
        this.add(this.lbCourseTitle, cons);
        this.tfCourseTitle = new JTextField(60);
        this.setConstraints(cons, 1, 2, 3, 1, GridBagConstraints.WEST);
        this.add(this.tfCourseTitle, cons);

        this.lbCourseCode = new JLabel("Course Code");
        this.setConstraints(cons, 0, 3, 1, 1, GridBagConstraints.EAST);
        this.add(this.lbCourseCode, cons);
        this.tfCourseCode = new JTextField(10);
        this.setConstraints(cons, 1, 3, 1, 1, GridBagConstraints.WEST);
        this.add(this.tfCourseCode, cons);

        this.lbClass = new JLabel("Class");
        this.setConstraints(cons, 2, 3, 1, 1, GridBagConstraints.EAST);
        this.add(this.lbClass, cons);
        this.cbClass = new JComboBox<>();
        this.cbClass.addItem("FYCO");
        this.cbClass.addItem("SYCO");
        this.cbClass.addItem("TYCO");
        this.setConstraints(cons, 3, 3, 1, 1, GridBagConstraints.WEST);
        this.add(this.cbClass, cons);

        this.lbQuestionNo = new JLabel("Number of Questions");
        this.setConstraints(cons, 0, 4, 1, 1, GridBagConstraints.EAST);
        this.add(this.lbQuestionNo, cons);
        this.tfQuestionNo = new JTextField(20);
        this.setConstraints(cons, 1, 4, 1, 1, GridBagConstraints.WEST);
        this.add(this.tfQuestionNo, cons);

        this.lbDuration = new JLabel("Duration of Test");
        this.setConstraints(cons, 2, 4, 1, 1, GridBagConstraints.EAST);
        this.add(this.lbDuration, cons);
        this.cbDuration = new JComboBox<>();
        this.cbDuration.addItem("30 Minutes");
        this.cbDuration.addItem("1 Hour");
        this.cbDuration.addItem("2 Hours");
        this.cbDuration.addItem("2 Hours 30 Minutes");
        this.cbDuration.addItem("3 Hours");
        this.setConstraints(cons, 3, 4, 1, 1, GridBagConstraints.WEST);
        this.add(this.cbDuration, cons);

        this.btnCreate = new JButton("Create the test");
        this.btnCreate.addActionListener(this);
        this.setConstraints(cons, 0, 5, 4, 1, GridBagConstraints.CENTER);
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

    public void setConstraints(GridBagConstraints cons, int x, int y, int w, int h, int anchor) {
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = w;
        cons.gridheight = h;
        cons.ipadx = 5;
        cons.ipady = 5;
        cons.weightx = 0.5;
        cons.weighty = 0.5;
        cons.anchor = anchor;
        cons.insets = new Insets(5, 5, 5, 5);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateQuiz());
    }
}
