//package quizproject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HomeScreen extends JFrame implements ActionListener{

    JButton btnCreate,btnResult,btnStart;

    public HomeScreen(){
        super("Quiz Application");
        this.setLayout(null);
        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.btnCreate=new JButton("Create New Quiz");
        this.btnCreate.addActionListener(this);
        this.btnCreate.setBounds(200,200,200,60);
        this.add(btnCreate);

        this.btnStart=new JButton("Start the Quiz");
        this.btnStart.addActionListener(this);
        this.btnStart.setBounds(800,200,200,60);
        this.add(btnStart);

        this.btnResult=new JButton("View Result");
        this.btnResult.addActionListener(this);
        this.btnResult.setBounds(500,400,200,60);
        this.add(btnResult);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnCreate){
            this.setVisible(false);
            new CreateQuiz();
        }

        if(e.getSource()==btnStart){
            this.setVisible(false);
            StartQuiz sq = new StartQuiz();
        }
                
        if(e.getSource()==btnResult){
            this.setVisible(false);
            ViewResult vr = new ViewResult();
        }
        
    }

    public static void main(String[] args) {
        new HomeScreen();
    }
}