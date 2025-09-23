package ui;

import javax.swing.*;
import java.awt.*;

public class InfoCard extends JPanel {
    public InfoCard(String title, String message) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titleLabel, BorderLayout.NORTH);

        JLabel messageLabel = new JLabel(message, JLabel.CENTER);
        add(messageLabel, BorderLayout.CENTER);
    }
}
