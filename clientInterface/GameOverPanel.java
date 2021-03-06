package clientInterface;

import clientComm.GameOverControl;
import data.GameData;
import data.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameOverPanel extends JPanel {
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JLabel resultLabel;
    private JButton exit;
    private JButton playAgain;
    private JPanel labelPanel;
    private JPanel btnPanel;
    private JPanel all;

    public GameOverPanel(GameOverControl gameOverControl, GameData gameData, boolean isPlayer1) {
        statusLabel = new JLabel("Game Over!", JLabel.CENTER);
        statusLabel.setFont(new Font("Serif", Font.BOLD, 16));

        List<Player> players = gameData.getPlayers();

        if (isPlayer1) {
            scoreLabel = new JLabel(
                    players.get(0).getScore() + "/" + gameData.getQuestions().size() + " answered correctly",
                    JLabel.CENTER);
        } else {
            scoreLabel = new JLabel(
                    players.get(1).getScore() + "/" + gameData.getQuestions().size() + " answered correctly",
                    JLabel.CENTER);
        }

        if (players.get(0).getScore() > players.get(1).getScore()) {
            if (isPlayer1) {
                resultLabel = new JLabel("You won!", JLabel.CENTER);
            } else {
                resultLabel = new JLabel("You lost...", JLabel.CENTER);
            }
        } else if (players.get(0).getScore() < players.get(1).getScore()) {
            if (isPlayer1) {
                resultLabel = new JLabel("You lost...", JLabel.CENTER);
            } else {
                resultLabel = new JLabel("You won!", JLabel.CENTER);
            }
        } else {
            resultLabel = new JLabel("It's a tie!", JLabel.CENTER);
        }
        resultLabel.setFont(new Font("Serif", Font.BOLD, 16));

        btnPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        exit = new JButton("Exit");
        exit.addActionListener(gameOverControl);
        playAgain = new JButton("Play Again");
        playAgain.addActionListener(gameOverControl);
        btnPanel.add(exit);
        btnPanel.add(playAgain);

        labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        labelPanel.add(statusLabel);
        labelPanel.add(scoreLabel);
        labelPanel.add(resultLabel);

        // Arrange the components
        all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        all.add(labelPanel);
        all.add(btnPanel);
        this.add(all);
    }
}
