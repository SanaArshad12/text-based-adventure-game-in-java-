import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

 class TextBasedAdventureGameGUI {
    private List<String> inventory = new ArrayList<>();
    private JFrame frame;
    private JTextArea textArea;
    private JButton leftDoorButton;
    private JButton rightDoorButton;
    private JButton takeKeyButton;
    private JButton leaveButton;
    private JButton playAgainButton;

    public TextBasedAdventureGameGUI() {
        frame = new JFrame("Text-based Adventure Game");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER );

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        leftDoorButton = new JButton("Go Left");
        rightDoorButton = new JButton("Go Right");
        takeKeyButton = new JButton("Take Key");
        leaveButton = new JButton("Leave Room");
        playAgainButton = new JButton("Play Again");

        leftDoorButton.addActionListener(e -> {
            textArea.append("\nYou chose to go left.\n");
            room1();
        });

        rightDoorButton.addActionListener(e -> {
            textArea.append("\nYou chose to go right.\n");
            room2();
        });

        takeKeyButton.addActionListener(e -> {
            inventory.add("key");
            textArea.append("You took the key.\n");
            takeKeyButton.setEnabled(false);
        });

        leaveButton.addActionListener(e -> {
            textArea.append("You left the room.\n");
            rightDoor();
        });

        playAgainButton.addActionListener(e -> resetGame());

        buttonPanel.add(leftDoorButton);
        buttonPanel.add(rightDoorButton);
        buttonPanel.add(takeKeyButton);
        buttonPanel.add(leaveButton);
        buttonPanel.add(playAgainButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        showInstructions();

        frame.setVisible(true);
    }

    private void showInstructions() {
        textArea.setText("Welcome to the Text-based Adventure Game!\n");
        textArea.append("You find yourself in a dark room with two doors.\n");
        textArea.append("Your goal is to navigate through rooms, collect items, and avoid dangers to win.\n");
        textArea.append("Follow the on-screen instructions and click the buttons to make choices.\n");
        textArea.append("Good luck!\n\n");
    }

    private void room1() {
        textArea.append("You enter a room with a locked chest.\n");
        textArea.append("You notice a key on the table.\n");
        takeKeyButton.setEnabled(true);
        leaveButton.setEnabled(true);
    }

    private void rightDoor() {
        room2();
    }

    private void room2() {
        textArea.append("You enter a room with a sleeping dragon.\n");
        if (inventory.contains("key")) {
            textArea.append("You use the key to unlock the door and escape quietly.\n");
            room3();
        } else {
            textArea.append("The dragon wakes up and eats you!\n");
            playAgainButton.setEnabled(true);
        }
    }

    private void room3() {
        textArea.append("You enter a room filled with treasures!\n");
        textArea.append("Congratulations, you win!\n");
        playAgainButton.setEnabled(true);
    }

    private void resetGame() {
        inventory.clear();
        showInstructions();
        takeKeyButton.setEnabled(false);
        leaveButton.setEnabled(false);
        playAgainButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextBasedAdventureGameGUI());
    }
}
