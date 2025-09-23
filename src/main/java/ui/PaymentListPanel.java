package ui;

import javax.swing.*;
import styles.UIStyle;

public class PaymentListPanel extends JPanel {
    public PaymentListPanel() {
        setLayout(new java.awt.BorderLayout());
        JLabel title = UIStyle.titleLabel("Payments");
        add(title, java.awt.BorderLayout.NORTH);

        TablePanel table = new TablePanel(new String[]{"ID","Semester","Breakdown","Due Date","Action"});
        table.addRow(new Object[]{"P001","Fall 2025","Tuition Fee","2025-10-15","Pay Now"});
        table.addRow(new Object[]{"P002","Spring 2026","Tuition Fee","2026-03-01","Pending"});
        add(table, java.awt.BorderLayout.CENTER);
    }
}
