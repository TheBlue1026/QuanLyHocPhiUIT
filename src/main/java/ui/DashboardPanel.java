package ui;

import javax.swing.*;
import java.awt.*;
import styles.UIStyle;

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(new BorderLayout());
        JLabel title = UIStyle.titleLabel("Dashboard");
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(1, 1));
        center.add(new InfoCard("Payment Alert", "You have 1 payment left this semester. Late payment!"));
        add(center, BorderLayout.CENTER);
    }
}
