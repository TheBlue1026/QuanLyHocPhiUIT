package com.uit.project.quanlyhocphiuit;

import pages.SuccessPanel;
import pages.PaymentFormPanel;
import pages.PaymentListPanel;
import pages.UserInfoPanel;
import pages.DashboardPanel;
import pages.LoginPanel;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.*;

public class AppFrame extends JFrame {

    private JPanel rootContainer;   // CardLayout chứa login + main
    private JPanel contentArea;     // CardLayout bên trong main layout
    private SidebarPanel sidebar;

    public AppFrame() throws SQLException {
        setTitle("Quản Lý Học Phí UIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null); // đặt giữa màn hình

        // Root container với CardLayout
        rootContainer = new JPanel(new CardLayout());
        add(rootContainer);

        initLogin();
        initMainLayout();

        // Khởi đầu hiển thị login
        navigateTo("login");
    }

    // === Khởi tạo login panel ===
    private void initLogin() {
        LoginPanel loginPanel = new LoginPanel(() -> {
            try {
                navigateTo("main");
            } catch (SQLException ex) {
                Logger.getLogger(AppFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        rootContainer.add(loginPanel, "login");
    }

    // === Khởi tạo main layout ===
    private void initMainLayout() throws SQLException {
        // Main layout (sidebar + content)
        JPanel mainLayout = new JPanel(new BorderLayout());
        mainLayout.setBackground(Color.WHITE);

        // Sidebar wrapper để padding
        JPanel sidebarWrapper = new JPanel(new BorderLayout());
        sidebarWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidebarWrapper.setOpaque(false);
    
        sidebar = new SidebarPanel(screen -> {
            try {
                navigateTo(screen);
            } catch (SQLException ex) {
                Logger.getLogger(AppFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sidebarWrapper.add(sidebar, BorderLayout.CENTER);
        mainLayout.add(sidebarWrapper, BorderLayout.WEST);

        // Content area với CardLayout
        contentArea = new JPanel(new CardLayout());
        contentArea.setBackground(Color.WHITE);
        contentArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Đăng ký các panel con
        registerContentPanel("dashboard", new DashboardPanel());
        registerContentPanel("payments", new PaymentListPanel());
        registerContentPanel("paymentForm", new PaymentFormPanel());
        registerContentPanel("user", new UserInfoPanel());
        registerContentPanel("confirm", new ConfirmationPanel());
        registerContentPanel("success", new SuccessPanel());

        mainLayout.add(contentArea, BorderLayout.CENTER);

        rootContainer.add(mainLayout, "main");
    }

    // Đăng ký panel vào content area
    private void registerContentPanel(String name, JPanel panel) {
        contentArea.add(panel, name);
    }

    // === Điều hướng giữa các màn hình ===
    // Xóa registerContentPanel cũ
    // private void registerContentPanel(String name, JPanel panel) { ... }  // không cần nữa
    public void navigateTo(String screen) throws SQLException {
        CardLayout rootLayout = (CardLayout) rootContainer.getLayout();

        switch (screen) {
            case "login":
                // Xóa panel login cũ nếu có
                for (Component comp : rootContainer.getComponents()) {
                    if ("login".equals(comp.getName())) {
                        rootContainer.remove(comp);
                        break;
                    }
                }
                // Tạo panel login mới
                JPanel loginPanel = new LoginPanel(() -> {
            try {
                navigateTo("main");
            } catch (SQLException ex) {
                Logger.getLogger(AppFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                loginPanel.setName("login");
                rootContainer.add(loginPanel, "login");
                rootLayout.show(rootContainer, "login");
                break;

            case "main":
                rootLayout.show(rootContainer, "main");
                showContent("dashboard"); // default panel sau login
                break;

            default:
                showContent(screen);
                break;
        }

        rootContainer.revalidate();
        rootContainer.repaint();
    }

    // Hiển thị panel trong content area (initialize lại mỗi lần)
    private void showContent(String screen) throws SQLException {
        // Xóa panel cũ nếu đã tồn tại
        for (Component comp : contentArea.getComponents()) {
            if (comp.getName() != null && comp.getName().equals(screen)) {
                contentArea.remove(comp);
                break;
            }
        }

        // Tạo panel mới dựa theo screen
        JPanel panel;
        switch (screen) {
            case "dashboard":
                panel = new DashboardPanel();
                break;
            case "payments":
                panel = new PaymentListPanel();
                break;
            case "paymentForm":
                panel = new PaymentFormPanel();
                break;
            case "user":
                panel = new UserInfoPanel();
                break;
            case "confirm":
                panel = new ConfirmationPanel();
                break;
            case "success":
                panel = new SuccessPanel();
                break;
            default:
                panel = new JPanel();
        }

        panel.setName(screen); // gắn tên để dễ remove lần sau
        contentArea.add(panel, screen);

        // Hiển thị panel mới
        CardLayout cl = (CardLayout) contentArea.getLayout();
        cl.show(contentArea, screen);

        contentArea.revalidate();
        contentArea.repaint();
    }

}
