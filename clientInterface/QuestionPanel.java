package clientInterface;

import serverComm.QuestionControl;
import data.GameData;
import data.QuestionData;

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class QuestionPanel extends JPanel {
    private JPanel labelPanel;
    private JLabel questionLabel;
    private JPanel centerPanel;
    private JLabel noAnswers;
    private JLabel yourScore;
    private JLabel opponentScore;
    private JPanel answerPanel;
    private JButton answer1;
    private JButton answer2;
    private JButton answer3;
    private JButton answer4;
    private JPanel grid;

    public QuestionPanel(QuestionControl questionControl, GameData gameData) {
        questionControl.setGameData(gameData);

        QuestionData questionData = gameData.getQuestions().get(gameData.getCurrentQuestion());

        // ---------- Question Label Panel ----------
        labelPanel = new JPanel(new GridLayout(1, 1));

        questionLabel = new JLabel("Question", JLabel.CENTER);
        questionLabel.setText(questionData.getQuestion());
        questionLabel.setFont(new Font("Serif", Font.BOLD, 16));
        questionLabel.setBackground(Color.decode("#fefffe"));
        questionLabel.setOpaque(true);
        questionLabel.setForeground(Color.decode("#323332"));

        labelPanel.add(questionLabel);

        // ---------- Center Panel for No of answers submitted ----------
        centerPanel = new JPanel(new GridLayout(1, 3, 0, 0));

        noAnswers = new JLabel("0 Answers", JLabel.CENTER);
        centerPanel.add(noAnswers);

        yourScore = new JLabel("You: 0");
        opponentScore = new JLabel("Opponent: 0");
        centerPanel.add(yourScore);
        centerPanel.add(opponentScore);

        // ---------- Answers Label Panel ----------
        List<String> options = questionData.getAnswers();
        answerPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        answer1 = new JButton(options.get(0));
        answer2 = new JButton(options.get(1));
        answer3 = new JButton(options.get(2));
        answer4 = new JButton(options.get(3));

        answer1.setPreferredSize(new Dimension(200, 50));
        answer2.setPreferredSize(new Dimension(200, 50));
        answer3.setPreferredSize(new Dimension(200, 50));
        answer4.setPreferredSize(new Dimension(200, 50));

        answer1.setBackground(Color.BLACK);
        answer1.setOpaque(true);
        answer1.setBorderPainted(false);
        answer1.setForeground(Color.WHITE);

        answer2.setBackground(Color.CYAN);
        answer2.setOpaque(true);
        answer2.setBorderPainted(false);
        answer2.setForeground(Color.WHITE);

        answer3.setBackground(Color.MAGENTA);
        answer3.setOpaque(true);
        answer3.setBorderPainted(false);
        answer3.setForeground(Color.WHITE);

        answer4.setBackground(Color.ORANGE);
        answer4.setOpaque(true);
        answer4.setBorderPainted(false);
        answer4.setForeground(Color.WHITE);

        answer1.addActionListener(questionControl);
        answer2.addActionListener(questionControl);
        answer3.addActionListener(questionControl);
        answer4.addActionListener(questionControl);

        questionControl.setBtns(answer1, answer2, answer3, answer4);

        answerPanel.add(answer1);
        answerPanel.add(answer2);
        answerPanel.add(answer3);
        answerPanel.add(answer4);

        // ---------- Arrange the three panels in a grid ----------
        grid = new JPanel(new GridLayout(3, 1, 0, 0));
        grid.add(labelPanel);
        grid.add(centerPanel);
        grid.add(answerPanel);
        this.add(grid);
    }

    public void updateQuestion(QuestionControl questionControl, GameData gameData) {
        questionControl.setGameData(gameData);

        QuestionData questionData = gameData.getQuestions().get(gameData.getCurrentQuestion());
        List<String> options = questionData.getAnswers();

        noAnswers.setText("0 Answers");
        questionLabel.setText(questionData.getQuestion());

        answer1.setBackground(Color.BLACK);
        answer1.setText(options.get(0));
        answer1.setOpaque(true);
        answer1.setBorderPainted(false);
        answer1.setForeground(Color.WHITE);

        answer2.setBackground(Color.CYAN);
        answer2.setText(options.get(1));
        answer2.setOpaque(true);
        answer2.setBorderPainted(false);
        answer2.setForeground(Color.WHITE);

        answer3.setBackground(Color.MAGENTA);
        answer3.setText(options.get(2));
        answer3.setOpaque(true);
        answer3.setBorderPainted(false);
        answer3.setForeground(Color.WHITE);

        answer4.setBackground(Color.ORANGE);
        answer4.setText(options.get(3));
        answer4.setOpaque(true);
        answer4.setBorderPainted(false);
        answer4.setForeground(Color.WHITE);
    }

    public void updateGameStats(String stats, boolean isPlayer1) {
        String[] statVals = stats.split(";");
        boolean statForPlayer1 = Boolean.parseBoolean(statVals[0]);

        noAnswers.setText(statVals[1]);
        if (statForPlayer1) {
            if (isPlayer1) {
                yourScore.setText("You: " + statVals[2]);
            } else {
                opponentScore.setText("Opponent: " + statVals[2]);
            }
        } else {
            if (isPlayer1) {
                opponentScore.setText("Opponent: " + statVals[2]);
            } else {
                yourScore.setText("You: " + statVals[2]);
            }
        }
    }
}
