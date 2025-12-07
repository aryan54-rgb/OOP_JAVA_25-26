import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class HerbGame extends JFrame {
    private static ArrayList<Herb> herbs;
    private static int currentIndex;
    private static boolean hintUsed;
    private static int postHintChances;
    private static int score;

    private static JLabel imageLabel;
    private static JTextField answerField;
    private static JButton submitButton, hintButton, nextButton;
    private static JLabel feedbackLabel, scoreLabel;

    public HerbGame(ArrayList<Herb> herbs) {
        this.herbs = herbs;

        setTitle("🌿 Herb Identification Game 🌿");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for image
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(200, 255, 200));
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(imageLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for input and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(220, 255, 220));
        answerField = new JTextField(20);
        submitButton = new JButton("Submit");
        hintButton = new JButton("Hint");
        nextButton = new JButton("Next Herb");

        centerPanel.add(new JLabel("Your Guess: "));
        centerPanel.add(answerField);
        centerPanel.add(submitButton);
        centerPanel.add(hintButton);
        centerPanel.add(nextButton);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(new Color(180, 255, 180));
        feedbackLabel = new JLabel("Welcome! Try to identify the herb.", JLabel.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 14));

        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        bottomPanel.add(feedbackLabel);
        bottomPanel.add(scoreLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load first herb
        loadHerb();

        // Button actions
        submitButton.addActionListener(e -> checkAnswer());
        hintButton.addActionListener(e -> showHint());
        nextButton.addActionListener(e -> nextHerb());
        nextButton.setEnabled(false);

        setVisible(true);
    }

    private void loadHerb() {
        Herb herb = herbs.get(currentIndex);

        // Load image dynamically based on herb name
        String imageName = herb.getName().toLowerCase().replace(" ", "_") + ".png";

        java.net.URL imageUrl = getClass().getResource("/images/" + imageName);

        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image img = icon.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setIcon(null);
            feedbackLabel.setText("⚠ Image not found: /images/" + imageName);
        }

        answerField.setText("");
        feedbackLabel.setText("Try to identify this herb! (" + (currentIndex + 1) + "/" + herbs.size() + ")");
        submitButton.setEnabled(true);
        hintButton.setEnabled(true);
        nextButton.setEnabled(false);
        hintUsed = false;
        postHintChances = 2;
    }

    private void checkAnswer() {
        Herb herb = herbs.get(currentIndex);
        String userAnswer = answerField.getText().trim();

        if (userAnswer.equalsIgnoreCase(herb.getName())) {
            if (!hintUsed) {
                score += 3; // First try correct
                feedbackLabel.setText("✅ Correct! +3 points!");
            } else {
                score += 2; // Correct after hint
                feedbackLabel.setText("✅ Correct (after hint)! +2 points!");
            }

            updateScore();
            submitButton.setEnabled(false);
            hintButton.setEnabled(false);
            nextButton.setEnabled(true);
        } else {
            if (!hintUsed) {
                feedbackLabel.setText("❌ Wrong! Click Hint for a clue.");
            } else {
                postHintChances--;
                if (postHintChances > 0) {
                    feedbackLabel.setText("❌ Wrong! " + postHintChances + " chance(s) left after hint.");
                } else {
                    revealAnswer();
                }
            }
        }
    }

    private void showHint() {
        Herb herb = herbs.get(currentIndex);
        JOptionPane.showMessageDialog(this, "💡 Hint: " + herb.getHint());
        hintUsed = true;
        postHintChances = 2;
    }

    private void revealAnswer() {
        Herb herb = herbs.get(currentIndex);
        String info = "Herb Name: " + herb.getName() +
                "\nScientific Name: " + herb.getScientificName() +
                "\nUses: " + herb.getUses() +
                "\nFun Fact: " + herb.getFunFact() +
                "\n\nNo points earned this round.";
        JOptionPane.showMessageDialog(this, info);
        submitButton.setEnabled(false);
        hintButton.setEnabled(false);
        nextButton.setEnabled(true);
        updateScore();
    }

    private void nextHerb() {
        currentIndex++;
        if (currentIndex < herbs.size()) {
            loadHerb();
        } else {
            JOptionPane.showMessageDialog(this, "🎉 Game Over!\nYour Final Score: " + score);
            System.exit(0);
        }
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }
}