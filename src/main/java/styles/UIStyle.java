package styles;

import java.awt.*;
import javax.swing.*;

/**
 * Centralized UI style configuration for the Tuition Fee App.
 * Use this to apply consistent look & feel across panels.
 */
public class UIStyle {

    // 🎨 Colors
    public static final Color PRIMARY_COLOR   = new Color(0, 102, 204);   // Blue
    public static final Color SECONDARY_COLOR = new Color(100, 100, 100); // Gray
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color ERROR_COLOR      = new Color(204, 0, 0);    // Red

    // 🔤 Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    // 🎨 Accent Color (for sidebar, highlights, etc.)
    public static final Color ACCENT_COLOR = new Color(240, 248, 255); // light blue-gray

    // 🖼️ Apply sidebar background
    public static void applySidebarBackground(JPanel panel) {
        panel.setBackground(ACCENT_COLOR);
    }

    // 🖼️ Apply a uniform background to a panel
    public static void applyPanelBackground(JPanel panel) {
        panel.setBackground(BACKGROUND_COLOR);
    }

    // 📝 Style title labels
    public static JLabel titleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setForeground(PRIMARY_COLOR);
        return label;
    }

    // 📝 Style subtitle labels
    public static JLabel subtitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(SUBTITLE_FONT);
        label.setForeground(SECONDARY_COLOR);
        return label;
    }

    // 📝 Style body text labels
    public static JLabel bodyLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY_FONT);
        return label;
    }

    // ✅ Primary button (main actions like "Login", "Pay Now")
    public static JButton primaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    // Secondary button (less important actions like "Cancel")
    public static JButton secondaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }
    
    // ✅ Success button (e.g. "Confirm", "Pay Now")
    public static JButton successButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 153, 51)); // green
        button.setForeground(Color.WHITE);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    // ❌ Danger button (e.g. "Delete", "Cancel")
    public static JButton dangerButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(204, 0, 0)); // red
        button.setForeground(Color.WHITE);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }
    
    // 📌 Sidebar button (flat style with hover)
    public static JButton sidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(ACCENT_COLOR);
                button.setForeground(Color.BLACK);
            }
        });

        return button;
    }


    // ❌ Error label (for validation messages)
    public static JLabel errorLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY_FONT);
        label.setForeground(ERROR_COLOR);
        return label;
    }

    // 🖊️ Styled text field
    public static JTextField textField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(BODY_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    // 🔒 Styled password field
    public static JPasswordField passwordField(int columns) {
        JPasswordField field = new JPasswordField(columns);
        field.setFont(BODY_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    // 📑 Styled table
    public static JTable styledTable(Object[][] data, Object[] columns) {
        JTable table = new JTable(data, columns);
        table.setFont(BODY_FONT);
        table.setRowHeight(24);
        table.getTableHeader().setFont(SUBTITLE_FONT);
        table.getTableHeader().setBackground(PRIMARY_COLOR);
        table.getTableHeader().setForeground(Color.WHITE);
        return table;
    }
}
