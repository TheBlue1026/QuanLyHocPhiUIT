package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import com.uit.project.quanlyhocphiuit.AppFrame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import styles.UIStyle;

public class SuccessPanel extends JPanel {
    private final JButton backToDashboardBtn;

    public SuccessPanel() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout()); // center-align

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title lớn in đậm
        JLabel title = UIStyle.titleLabel("Thanh toán thành công");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(title, gbc);

        // Description nhiều dòng, màu xám nhạt
        String descText = "<html><div style='text-align:center; color:#666666;'>"
                + "Cảm ơn bạn đã hoàn tất thanh toán.<br>"
                + "Biên lai và thông tin chi tiết sẽ được gửi vào email của bạn.<br>"
                + "Vui lòng giữ thông tin này để tra cứu sau."
                + "</div></html>";
        JLabel description = new JLabel(descText);
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 20, 10); // thêm khoảng cách dưới description
        add(description, gbc);

        // Button gradient
        backToDashboardBtn = new JButton("Quay lại Trang chủ") {
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
        backToDashboardBtn.setForeground(Color.WHITE);
        backToDashboardBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backToDashboardBtn.setFocusPainted(false);
        backToDashboardBtn.setBorder(BorderFactory.createEmptyBorder());
        backToDashboardBtn.setContentAreaFilled(false);
        backToDashboardBtn.setPreferredSize(new Dimension(250, 40));

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(backToDashboardBtn, gbc);

        // Action listener
        backToDashboardBtn.addActionListener(e -> {
            Container parent = getParent();
            while (parent != null && !(parent instanceof JFrame)) {
                parent = parent.getParent();
            }
            if (parent instanceof AppFrame frame) {
                try {
                    frame.navigateTo("dashboard");
                } catch (SQLException ex) {
                    Logger.getLogger(SuccessPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void addBackAction(ActionListener listener) {
        backToDashboardBtn.addActionListener(listener);
    }
}
