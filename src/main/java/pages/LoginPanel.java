package pages;

import controllers.SinhVienController;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import styles.UIStyle;
import ui.RoundedTextFieldUI;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel(Runnable onLoginSuccess) {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = UIStyle.titleLabel("Đăng nhập");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        // Username label
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel usernameLabel = new JLabel("Mã số sinh viên");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // in đậm
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // khoảng cách 5px tới input
        add(usernameLabel, gbc);

        // Username field
        usernameField = new JTextField(30);
        styleTextField(usernameField);
        gbc.gridy = 2;
        add(usernameField, gbc);

        // Password label
        gbc.gridy = 3;
        JLabel passwordLabel = new JLabel("Mật khẩu");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordLabel, gbc);

        // Password field (an toàn)
        passwordField = new JPasswordField();
        passwordField.setEchoChar('•'); // ký tự bullet hiển thị thay vì text thật
        passwordField.setPreferredSize(new Dimension(300, 35));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2, true));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setMargin(new Insets(8, 12, 8, 12));

        gbc.gridy = 4;
        add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Xác nhận") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(46, 81, 233), getWidth(), 0, new Color(88, 145, 255));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setContentAreaFilled(false);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String mssv = usernameField.getText().trim();
            char[] passwordChars = passwordField.getPassword(); // lấy password an toàn
            String password = new String(passwordChars); // chuyển sang String để so sánh
            // xóa password char array ngay sau khi dùng
            java.util.Arrays.fill(passwordChars, '0');

            SinhVienController controller = new SinhVienController();
            boolean success = controller.login(mssv, password);

            if (success) {
                // redirect sang trang PaymentForm hoặc SuccessPanel
                onLoginSuccess.run();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "MSSV hoặc mật khẩu không đúng",
                    "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

    }

    private void styleTextField(JTextField field) {
        field.setBorder(new LineBorder(new Color(150, 150, 150), 2)); // border dày
        field.setBackground(Color.WHITE);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setMargin(new Insets(8, 12, 8, 12));
        field.setOpaque(false);
        field.setPreferredSize(new Dimension(300, 35));
        field.setUI(new RoundedTextFieldUI()); // bo tròn
    }

    // Getter
    public String getUsername() { return usernameField.getText().trim(); }
    public char[] getPassword() { return passwordField.getPassword(); }
    public void addLoginAction(ActionListener listener) { loginButton.addActionListener(listener); }

}
