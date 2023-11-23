import java.awt.*;
import javax.swing.*;

public class MainQuiz extends JFrame {
    JLabel lbTimer;
    JLabel lbQuestion;
    JRadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    JButton btnNext, btnSubmit;
    JLabel lbInstruction;

    private void initializeUI() {
        setTitle("Quiz");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints con = new GridBagConstraints();
        setLayout(new GridBagLayout());

        lbTimer = new JLabel();
        setConstraints(con, 0, 0, 1, 1);
        add(lbTimer, con);

        lbQuestion = new JLabel();
        setConstraints(con, 0, 1, 2, 1);
        add(lbQuestion, con);

        rbOption1 = new JRadioButton();
        rbOption2 = new JRadioButton();
        rbOption3 = new JRadioButton();
        rbOption4 = new JRadioButton();

        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(rbOption1);
        optionGroup.add(rbOption2);
        optionGroup.add(rbOption3);
        optionGroup.add(rbOption4);

        setConstraints(con, 0, 2, 2, 1);
        add(rbOption1, con);
        setConstraints(con, 0, 3, 2, 1);
        add(rbOption2, con);
        setConstraints(con, 0, 4, 2, 1);
        add(rbOption3, con);
        setConstraints(con, 0, 5, 2, 1);
        add(rbOption4, con);

        btnNext = new JButton("Next");
        setConstraints(con, 2, 6, 1, 1);
        add(btnNext, con);

        btnSubmit = new JButton("Submit");
        setConstraints(con, 3, 6, 1, 1);
        add(btnSubmit, con);

        lbInstruction = new JLabel("Instructions\n 1. Once You click on next you cannot attempt that question again. \n 2. After answering all the questions click on 'Submit' Button");
        setConstraints(con, 0, 7, 4, 1);
        add(lbInstruction, con);
        
        setVisible(true);
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
         new MainQuiz().initializeUI();
    }
}
