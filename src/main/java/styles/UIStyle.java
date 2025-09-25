package styles;

import java.awt.*;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * Centralized UI style configuration for the Tuition Fee App.
 * Use this to apply consistent look & feel across panels.
 */
public class UIStyle {
    // 🎨 Colors
    public static final Color PRIMARY_COLOR   = new Color(46, 81, 233);   // Deep Blue
    public static final Color ACCENT_COLOR    = new Color(199, 208, 232); // Light gray-blue
    public static final Color SECONDARY_COLOR = new Color(100, 100, 100); // Gray
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color ERROR_COLOR      = new Color(204, 0, 0);    // Red

    // 🔤 Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    // 🎨 Accent Color (for sidebar, highlights, etc.)

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
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        // quan trọng để hiển thị màu nền
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);

        return button;
    }

    public static JButton secondaryButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Nền trắng
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Vẽ viền
                g2.setColor(PRIMARY_COLOR);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(BUTTON_FONT);
        button.setForeground(PRIMARY_COLOR);
        button.setContentAreaFilled(false); // không fill mặc định
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setOpaque(false);

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
        button.setForeground(SECONDARY_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
                button.setForeground(PRIMARY_COLOR);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(ACCENT_COLOR);
                button.setForeground(SECONDARY_COLOR);
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
    
    public static final Color TABLE_HEADER_BG = Color.WHITE;
    public static final Color TABLE_HEADER_FG = new Color(66, 66, 66);
    public static final Font TABLE_HEADER_FONT = new Font("Arial", Font.BOLD, 13);

    public static final Color TABLE_ROW_BG = Color.WHITE;
    public static final Color TABLE_ROW_ALT_BG = new Color(250, 250, 250);
    public static final Color TABLE_ROW_HOVER_BG = new Color(240, 240, 240); // hover xám nhạt
    public static final Color TABLE_ROW_BORDER = new Color(160, 160, 160);   // border đậm

    public static void applyTableStyle(JTable table) {
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(44); // cao đủ để hiển thị padding top/bottom

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
                lbl.setBackground(Color.decode("#F8FAFB"));
                lbl.setOpaque(true);
                lbl.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(200, 200, 200)));
                lbl.setBorder(BorderFactory.createCompoundBorder(
                        lbl.getBorder(),
                        BorderFactory.createEmptyBorder(12, 6, 12, 6) // padding top/bottom
                ));
                return lbl;
            }
        });

        // Body styling
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, false, row, column);
                lbl.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)), 
                                BorderFactory.createEmptyBorder(14, 6, 14, 6) // top, left, bottom, right
                        ));                
                lbl.setOpaque(true);
                return lbl;
            }
        };
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

}
