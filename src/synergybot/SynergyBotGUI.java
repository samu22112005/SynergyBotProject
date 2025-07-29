package synergybot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SynergyBotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JButton themeToggle;
    private boolean darkMode = false;
    private SynergyBot bot;

    public SynergyBotGUI() {
        bot = new SynergyBot();
        setTitle("SynergyBot 💬");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");
        themeToggle = new JButton("Toggle Theme");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.add(themeToggle, BorderLayout.WEST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> processInput());
        inputField.addActionListener(e -> processInput());
        themeToggle.addActionListener(e -> toggleTheme());

        setLightMode();
        setVisible(true);
    }

    private void processInput() {
        String userInput = inputField.getText().trim();
        if (userInput.isEmpty()) return;

        chatArea.append("You: " + userInput + "\n");

        String response;
        try {
            response = bot.getResponse(userInput);
        } catch (Exception e) {
            response = "Oops! Something went wrong. 😕";
        }

        chatArea.append("Bot: " + response + "\n");
        inputField.setText("");
    }

    private void toggleTheme() {
        darkMode = !darkMode;
        if (darkMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    private void setDarkMode() {
        chatArea.setBackground(Color.DARK_GRAY);
        chatArea.setForeground(Color.WHITE);
        inputField.setBackground(Color.GRAY);
        inputField.setForeground(Color.WHITE);
        sendButton.setBackground(Color.BLACK);
        sendButton.setForeground(Color.WHITE);
        themeToggle.setBackground(Color.BLACK);
        themeToggle.setForeground(Color.WHITE);
    }

    private void setLightMode() {
        chatArea.setBackground(Color.WHITE);
        chatArea.setForeground(Color.BLACK);
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(Color.BLACK);
        sendButton.setBackground(Color.LIGHT_GRAY);
        sendButton.setForeground(Color.BLACK);
        themeToggle.setBackground(Color.LIGHT_GRAY);
        themeToggle.setForeground(Color.BLACK);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SynergyBotGUI::new);
    }
}


