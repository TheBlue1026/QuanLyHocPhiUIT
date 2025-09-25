package ui;

import pages.UserInfoPanel;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import styles.UIStyle;
import com.uit.project.quanlyhocphiuit.AppFrame;
import com.uit.project.quanlyhocphiuit.Session;

public class SidebarPanel extends JPanel {
    public SidebarPanel(Consumer<String> onNavigate) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 0));
        setOpaque(false); // quan trọng để thấy bo góc
        
        // ✅ Padding top 20px, trái/phải/dưới là 0
        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


        // ==== Navigation buttons ====
        JPanel navContainer = new JPanel();
        navContainer.setLayout(new BoxLayout(navContainer, BoxLayout.Y_AXIS));
        navContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        navContainer.setOpaque(false);

        navContainer.add(UIStyle.sidebarButton("Trang chủ"));
        navContainer.getComponent(0).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onNavigate.accept("dashboard");
            }
        });

        navContainer.add(UIStyle.sidebarButton("Lịch sử đóng tiền"));
        navContainer.getComponent(1).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onNavigate.accept("payments");
            }
        });

        navContainer.add(UIStyle.sidebarButton("Thông tin sinh viên"));
        navContainer.getComponent(2).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onNavigate.accept("user");
                Container root = SwingUtilities.getWindowAncestor(navContainer);
                if (root instanceof AppFrame frame) {
                    UserInfoPanel panel = UserInfoPanel.findUserInfoPanel(frame);
                    if (panel != null) {
                        panel.populate();
                    }
                }
            }
        });

        add(navContainer);

        // ==== Spacer pushes logout to bottom ====
        add(Box.createVerticalGlue());

        // ==== Logout button ====
        JButton logoutBtn = UIStyle.sidebarButton("Đăng xuất");
        logoutBtn.setForeground(Color.RED);
        logoutBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoutBtn.addActionListener(e -> {
            Session.clear();
            onNavigate.accept("login");
        });

        add(Box.createVerticalStrut(10));
        add(logoutBtn);
        add(Box.createVerticalStrut(10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // nền sidebar + bo góc
        g2.setColor(new Color(245, 245, 245)); // màu giống UIStyle
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 20px radius

        g2.dispose();
    }
}
