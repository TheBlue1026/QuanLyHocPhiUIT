package ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import styles.UIStyle;

public class SidebarPanel extends JPanel {
    public SidebarPanel(Consumer<String> onNavigate) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 0));

        // ==== Navigation buttons ====
        JPanel navContainer = new JPanel(new GridLayout(0, 1, 0, 5));
        navContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navContainer.add(createNavButton("Dashboard", "dashboard", onNavigate));
        navContainer.add(createNavButton("Payment List", "payments", onNavigate));
        navContainer.add(createNavButton("Settings", "settings", onNavigate));
        navContainer.add(createNavButton("Your Info", "info", onNavigate));

        add(navContainer, BorderLayout.CENTER);

        // ==== Logout button at bottom ====
        JButton logoutBtn = UIStyle.dangerButton("Logout");
        logoutBtn.addActionListener(e -> onNavigate.accept("login"));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.add(logoutBtn);

        add(bottom, BorderLayout.SOUTH);
    }

    private JButton createNavButton(String text, String target, Consumer<String> onNavigate) {
        JButton btn = UIStyle.primaryButton(text);
        btn.addActionListener(e -> onNavigate.accept(target));
        return btn;
    }
}
