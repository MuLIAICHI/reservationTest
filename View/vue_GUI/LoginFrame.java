package vue_GUI;

import administrateur.TrajetGUI;
import administrateur.DiscountCardGUI;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton, trajetButton, discountCardButton;
    private JLabel usernameLabel, passwordLabel;
    private JPanel mainPanel, loginPanel, buttonPanel;

    public LoginFrame() {
        initializeFrame();
        initializeComponents();
        layoutComponents();
        addActionListeners();
    }

    private void initializeFrame() {
        setTitle("ONCF Train Reservation System - Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(350, 220);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        userTextField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = createButton("Login", new Color(34, 139, 34));
        trajetButton = createButton("Trajet", new Color(70, 130, 180));
        discountCardButton = createButton("Discount Card", new Color(255, 69, 0));
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        mainPanel = new JPanel(new BorderLayout(10, 10));
        loginPanel = new JPanel(new GridLayout(4, 1, 10, 5));
        buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        mainPanel.setBackground(new Color(245, 245, 245));
        loginPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBackground(new Color(245, 245, 245));
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void layoutComponents() {
        loginPanel.add(usernameLabel);
        loginPanel.add(userTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        buttonPanel.add(trajetButton);
        buttonPanel.add(discountCardButton);

        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.add(loginButton, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        trajetButton.setVisible(false);
        discountCardButton.setVisible(false);

        add(mainPanel);
    }

    private void addActionListeners() {
        loginButton.addActionListener(this);
        trajetButton.addActionListener(e -> new TrajetGUI().setVisible(true));
        discountCardButton.addActionListener(e -> new DiscountCardGUI().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = String.valueOf(passwordField.getPassword()); // Hash the password in production

        if (checkLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            toggleLoginComponents(false);
            toggleDashboardComponents(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toggleLoginComponents(boolean visible) {
        usernameLabel.setVisible(visible);
        userTextField.setVisible(visible);
        passwordLabel.setVisible(visible);
        passwordField.setVisible(visible);
        loginButton.setVisible(visible);
    }

    private void toggleDashboardComponents(boolean visible) {
        trajetButton.setVisible(visible);
        discountCardButton.setVisible(visible);
        pack();
        setLocationRelativeTo(null);
    }

    private boolean checkLogin(String username, String password) {
        final String QUERY = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(QUERY)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password); // Hash the password in production

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
