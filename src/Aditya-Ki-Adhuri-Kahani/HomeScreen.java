import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HomeScreen extends JFrame implements ActionListener {

    JButton btnCreate, btnResult, btnStart;

    public HomeScreen() {
        super("Quiz Application");
        this.setLayout(null);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        int buttonWidth = 200;
        int buttonHeight = 60;
        int horizontalGap = 20;
        int verticalGap = 40;

        this.btnCreate = new JButton("Create New Quiz");
        this.btnCreate.addActionListener(this);
        this.btnCreate.setBounds(300, 150, buttonWidth, buttonHeight);
        this.add(btnCreate);

        this.btnStart = new JButton("Start the Quiz");
        this.btnStart.addActionListener(this);
        this.btnStart.setBounds(300, 250, buttonWidth, buttonHeight);
        this.add(btnStart);

        this.btnResult = new JButton("View Result");
        this.btnResult.addActionListener(this);
        this.btnResult.setBounds(300, 350, buttonWidth, buttonHeight);
        this.add(btnResult);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreate) {
            this.setVisible(false);
            new CreateQuiz().setVisible(true);
        }

        if (e.getSource() == btnStart) {
            this.setVisible(false);
            StartQuiz startQuiz = new StartQuiz();
            startQuiz.setVisible(true);
        }

        if (e.getSource() == btnResult) {
            this.setVisible(false);
            // ViewResult viewResult = new ViewResult();
            // viewResult.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeScreen());
    }
}
