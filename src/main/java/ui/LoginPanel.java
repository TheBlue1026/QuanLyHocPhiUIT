package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import styles.UIStyle;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // ✅ constructor nhận callback
    public LoginPanel(Runnable onLoginSuccess) {
        setLayout(new BorderLayout());

        // ==== Center form wrapper ====
        JPanel wrapper = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = UIStyle.titleLabel("Student Login");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = UIStyle.primaryButton("Login");

        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        wrapper.add(title, gbc);

        // Username
        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        wrapper.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        wrapper.add(usernameField, gbc);

        // Password
        gbc.gridy = 2; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        wrapper.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        wrapper.add(passwordField, gbc);

        // Login button
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        wrapper.add(loginButton, gbc);

        add(wrapper, BorderLayout.CENTER);

        // ✅ gắn callback khi bấm Login
        loginButton.addActionListener(e -> {
            // TODO: ở đây bạn có thể validate username/password
            onLoginSuccess.run();
        });
    }

    // Getter methods
    public String getUsername() {
        return usernameField.getText().trim();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public void addLoginAction(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
