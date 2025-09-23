package ui;

import javax.swing.*;
import java.awt.*;
import styles.UIStyle;
import com.uit.project.quanlyhocphiuit.AppFrame;

public class SuccessPanel extends JPanel {
    private final JButton backToDashboardBtn;

    public SuccessPanel() {
        setLayout(new BorderLayout());

        JLabel successMsg = UIStyle.titleLabel("✅ Payment Successful!");
        successMsg.setHorizontalAlignment(SwingConstants.CENTER);

        backToDashboardBtn = UIStyle.primaryButton("Back to Dashboard");

        add(successMsg, BorderLayout.CENTER);
        add(backToDashboardBtn, BorderLayout.SOUTH);

        backToDashboardBtn.addActionListener(e -> {
            Container parent = getParent();
            while (parent != null && !(parent instanceof JFrame)) {
                parent = parent.getParent();
            }
            if (parent instanceof AppFrame frame) {
                frame.navigateTo("dashboard");
            }
        });
    }
}
