package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SimpleGame extends JFrame {

    private JButton targetButton;
    private JLabel scoreLabel, timeLabel;
    private int score;
    private int timeLeft;

    public SimpleGame() {
        super("Simple Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Initialize score and time
        score = 0;
        timeLeft = 30;

        // Create components
        targetButton = new JButton("Click Me!");
        scoreLabel = new JLabel("Score: 0");
        timeLabel = new JLabel("Time Left: " + timeLeft);

        // Set layout of the JFrame
        setLayout(new BorderLayout());
        add(targetButton, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.NORTH);
        add(timeLabel, BorderLayout.SOUTH);

        // Add action listener to target button
        targetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseScore();
            }
        });

        // Create timer to update time left
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeLeft();
            }
        });
        timer.start();

        setVisible(true);
        startGame();
    }

    private void startGame() {
        // Disable target button
        targetButton.setEnabled(false);

        // Show message dialog with instructions
        JOptionPane.showMessageDialog(this, "Click on the 'Click Me!' button to score points within 30 seconds.");

        // Enable target button
        targetButton.setEnabled(true);

        // Reset score and time left
        score = 0;
        timeLeft = 30;

        // Update score and time labels
        updateScoreLabel();
        updateTimeLabel();

        // Start the game by randomly moving the target button
        moveTargetButton();
    }

    private void increaseScore() {
        score++;
        updateScoreLabel();
        moveTargetButton();
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    private void updateTimeLeft() {
        timeLeft--;
        updateTimeLabel();

        if (timeLeft == 0) {
            endGame();
        }
    }

    private void updateTimeLabel() {
        timeLabel.setText("Time Left: " + timeLeft);
    }

    private void endGame() {
        // Disable target button
        targetButton.setEnabled(false);

        // Show message dialog with final score
        JOptionPane.showMessageDialog(this, "Game Over! Your score is " + score);

        // Start a new game
        startGame();
    }

    private void moveTargetButton() {
        Random random = new Random();
        int x = random.nextInt(getWidth() - targetButton.getWidth());
        int y = random.nextInt(getHeight() - targetButton.getHeight());
        targetButton.setLocation(x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleGame());
    }
}
