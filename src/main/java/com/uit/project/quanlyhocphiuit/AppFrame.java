package com.uit.project.quanlyhocphiuit;

import java.awt.*;
import javax.swing.*;
import ui.*;

public class AppFrame extends JFrame {
    private JPanel rootContainer;   // holds login + main layout
    private JPanel contentArea;     // main content area (inside main layout)
    private SidebarPanel sidebar;   // sidebar inside main layout

    public AppFrame() {
        setTitle("Quản Lý Học Phí UIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        // Root container with CardLayout
        rootContainer = new JPanel(new CardLayout());
        add(rootContainer);

        // --- Login Screen ---
        JPanel loginPanel = new LoginPanel(() -> navigateTo("main")); 
        rootContainer.add(loginPanel, "login");

        // --- Main Layout (sidebar + content) ---
        JPanel mainLayout = new JPanel(new BorderLayout());

        // Sidebar
        sidebar = new SidebarPanel(this::navigateTo);
        mainLayout.add(sidebar, BorderLayout.WEST);

        // Content area with CardLayout
        contentArea = new JPanel(new CardLayout());
        mainLayout.add(contentArea, BorderLayout.CENTER);

        // Register panels inside main content area
        registerPanel("dashboard", new DashboardPanel());
        registerPanel("payments", new PaymentListPanel());
        registerPanel("paymentForm", new PaymentFormPanel());
        registerPanel("confirm", new ConfirmationPanel());
        registerPanel("success", new SuccessPanel());

        rootContainer.add(mainLayout, "main");

        // Start with login screen
        navigateTo("login");
    }

    private void registerPanel(String name, JPanel panel) {
        contentArea.add(panel, name);
    }

    public void navigateTo(String screen) {
        CardLayout rootLayout = (CardLayout) rootContainer.getLayout();

        if ("login".equals(screen)) {
            rootLayout.show(rootContainer, "login");
        } else if ("main".equals(screen)) {
            rootLayout.show(rootContainer, "main");
            showContent("dashboard"); // default screen after login
        } else {
            showContent(screen); // show inside main content area
        }
    }

    private void showContent(String screen) {
        CardLayout cl = (CardLayout) contentArea.getLayout();
        cl.show(contentArea, screen);
    }
}
