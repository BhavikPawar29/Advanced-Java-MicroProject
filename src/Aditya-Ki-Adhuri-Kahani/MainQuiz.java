import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class MainQuiz extends JFrame {
    private JLabel timerLabel;
    private JLabel questionLabel;
    private JButton nextButton, submitButton;
    private QuizDatabase quizDatabase;
    private int currentQuestionNumber = 1;

    private void initializeUI() {
        setTitle("Quiz");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());

        timerLabel = new JLabel("Timer Placeholder");
        setConstraints(constraints, 0, 0, 1, 1);
        add(timerLabel, constraints);

        questionLabel = new JLabel("Question Placeholder");
        setConstraints(constraints, 0, 1, 2, 1);
        add(questionLabel, constraints);

        nextButton = new JButton("Next");
        setConstraints(constraints, 2, 6, 1, 1);
        add(nextButton, constraints);

        submitButton = new JButton("Submit");
        setConstraints(constraints, 3, 6, 1, 1);
        add(submitButton, constraints);

        setVisible(true);

        quizDatabase = new QuizDatabase();

        nextButton.addActionListener(e -> {
            String nextQuestion = quizDatabase.getNextQuestion(currentQuestionNumber);
            if (nextQuestion != null) {
                questionLabel.setText(nextQuestion);
                currentQuestionNumber++;
            } else {
                questionLabel.setText("No more questions!");
                nextButton.setEnabled(false);
            }
        });

        submitButton.addActionListener(e -> {
            String selectedAnswer; 
            if (selectedAnswer != null) {
                boolean isSubmitted = quizDatabase.submitAnswer(currentQuestionNumber, selectedAnswer);

                if (isSubmitted) {
                    JOptionPane.showMessageDialog(this, "Answer submitted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to submit answer!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an answer!");
            }
        });

    }

    private void setConstraints(GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
    }

    public static void main(String[] args) {
        new MainQuiz().initializeUI();
    }
}

class QuizDatabase {
    private Connection connection;

    public QuizDatabase() {
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzaordersystem", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNextQuestion(int questionNumber) {
        String query = "SELECT question_text FROM tblQuestions WHERE question_no = ? AND quiz_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, questionNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("question");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        public boolean submitAnswer(int questionNumber, String answer) {
        String query = "UPDATE tblQuestions SET user_answer = ? WHERE question_no = ? AND quiz_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, answer);
            preparedStatement.setInt(2, questionNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     public boolean addQuizDetails(int quizId, String enrollmentNo, String studentName) {
        String query = "INSERT INTO quiz_details (quiz_id, enrollment_no, student_name) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizId);
            preparedStatement.setString(2, enrollmentNo);
            preparedStatement.setString(3, studentName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

}
