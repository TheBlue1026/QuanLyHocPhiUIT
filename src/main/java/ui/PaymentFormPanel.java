package ui;

import javax.swing.*;
import java.awt.*;
import styles.UIStyle;
import com.uit.project.quanlyhocphiuit.AppFrame;

public class PaymentFormPanel extends JPanel {
    private JTextField paymentIdField;
    private JTextField amountField;
    private JComboBox<String> providerBox;
    private JButton confirmBtn;

    public PaymentFormPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = UIStyle.titleLabel("Payment Form");

        paymentIdField = new JTextField("P001");
        paymentIdField.setEditable(false);

        amountField = new JTextField("500 USD");
        amountField.setEditable(false);

        providerBox = new JComboBox<>(new String[]{"Bank Transfer", "Credit Card", "PayPal"});
        confirmBtn = UIStyle.primaryButton("Confirm Payment");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0; add(new JLabel("Payment ID:"), gbc);
        gbc.gridx = 1; add(paymentIdField, gbc);

        gbc.gridy = 2; gbc.gridx = 0; add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1; add(amountField, gbc);

        gbc.gridy = 3; gbc.gridx = 0; add(new JLabel("Provider:"), gbc);
        gbc.gridx = 1; add(providerBox, gbc);

        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        add(confirmBtn, gbc);

        // Example: confirm action → go to confirmation screen
        confirmBtn.addActionListener(e -> {
            Container parent = getParent();
            while (parent != null && !(parent instanceof JFrame)) {
                parent = parent.getParent();
            }
            if (parent instanceof AppFrame frame) {
                frame.navigateTo("confirm");
            }
        });
    }
}
