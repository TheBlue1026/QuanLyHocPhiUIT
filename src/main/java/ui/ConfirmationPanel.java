package ui;

import javax.swing.*;
import java.awt.*;
import styles.UIStyle;
import com.uit.project.quanlyhocphiuit.AppFrame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfirmationPanel extends JPanel {
    private JButton backBtn;
    private JButton payNowBtn;

    public ConfirmationPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = UIStyle.titleLabel("Confirm Your Payment");

        JLabel summary = new JLabel("<html><center>"
                + "<b>Payment ID:</b> P001<br>"
                + "<b>Amount:</b> 500 USD<br>"
                + "<b>Provider:</b> Credit Card<br>"
                + "</center></html>");

        backBtn = UIStyle.dangerButton("Back");
        payNowBtn = UIStyle.successButton("Pay Now");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridy = 1;
        add(summary, gbc);

        gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.gridx = 0; add(backBtn, gbc);
        gbc.gridx = 1; add(payNowBtn, gbc);

        // Navigation
        backBtn.addActionListener(e -> {
            Container parent = getParent();
            while (parent != null && !(parent instanceof JFrame)) {
                parent = parent.getParent();
            }
            if (parent instanceof AppFrame frame) {
                try {
                    frame.navigateTo("paymentForm");
                } catch (SQLException ex) {
                    Logger.getLogger(ConfirmationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        payNowBtn.addActionListener(e -> {
            Container parent = getParent();
            while (parent != null && !(parent instanceof JFrame)) {
                parent = parent.getParent();
            }
            if (parent instanceof AppFrame frame) {
                try {
                    frame.navigateTo("success");
                } catch (SQLException ex) {
                    Logger.getLogger(ConfirmationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
