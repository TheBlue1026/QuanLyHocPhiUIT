package pages;

import com.uit.project.quanlyhocphiuit.AppFrame;
import com.uit.project.quanlyhocphiuit.Session;
import controllers.HocPhiController;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.HocPhi;
import styles.UIStyle;

public class PaymentFormPanel extends JPanel {

    private InfoCardPanel infoPanel;
    private FixedSizeButton btnPay;

    public PaymentFormPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- Title ---
        JLabel mainTitle = UIStyle.titleLabel("Thanh toán học phí");
        mainTitle.setHorizontalAlignment(SwingConstants.LEFT);
        mainTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // --- Main panel ---
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 30));

        mainPanel.add(mainTitle);

        // --- InfoCardPanel ---
        infoPanel = new InfoCardPanel("0 VND");
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(infoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- Form panel ---
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] labels = {"Họ tên chủ thẻ", "Số thẻ", "Ngày hết hạn", "Số CVV"};
        for (String labelText : labels) {
            JLabel lbl = new JLabel(labelText);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            formPanel.add(lbl);
            formPanel.add(Box.createRigidArea(new Dimension(0, 5)));

            FixedSizeTextField txt = new FixedSizeTextField();
            txt.setAlignmentX(Component.LEFT_ALIGNMENT);
            formPanel.add(txt);
            formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // --- Button ---
        btnPay = new FixedSizeButton("Thanh toán");
        btnPay.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(btnPay);
        
        btnPay.addActionListener(e -> {
            // Lấy thông tin
            // Lấy MSSV từ session
            String mssv = Session.getCurrentMssv();
            if (mssv == null || mssv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập trước khi thanh toán!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }            
            String maHP = infoPanel.getMaHP(); // bạn cần getter cho infoPanel

            // Gọi controller
            HocPhiController controller = new HocPhiController();
            
            boolean success = true;
            try {
                success = controller.payHocPhi(mssv, maHP);
            } catch (SQLException ex) {
                Logger.getLogger(PaymentFormPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (success) {
                // Chuyển sang PaymentSuccess
                Container parent = getParent();
                while (parent != null && !(parent instanceof JFrame)) {
                    parent = parent.getParent();
                }
                if (parent instanceof AppFrame frame) {
                    frame.navigateTo("success"); // tên page hoặc panel PaymentSuccess
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thanh toán thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        mainPanel.add(formPanel);

        // --- Scroll pane ---
        JScrollPane scrollPane = new JScrollPane(mainPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
        
        loadHocPhiFromSession();
    }

    // ================= FixedSizeTextField =================
    static class FixedSizeTextField extends JTextField {
        private final Dimension size = new Dimension(400, 35); // width 400, height 35

        public FixedSizeTextField() {
            super();
            setBorder(new LineBorder(new Color(150, 150, 150), 2, true));
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
            setMargin(new Insets(8, 12, 8, 12));
        }

        @Override
        public Dimension getPreferredSize() { return size; }
        @Override
        public Dimension getMaximumSize() { return size; }
        @Override
        public Dimension getMinimumSize() { return size; }
    }

    // ================= FixedSizeButton =================
    static class FixedSizeButton extends JButton {
        private final Dimension size = new Dimension(400, 40); // width 400, height 40

        public FixedSizeButton(String text) {
            super(text);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 16));
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder());
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, new Color(46, 81, 233),
                    getWidth(), 0, new Color(88, 145, 255));
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public Dimension getPreferredSize() { return size; }
        @Override
        public Dimension getMaximumSize() { return size; }
        @Override
        public Dimension getMinimumSize() { return size; }
    }

    // ================= InfoCardPanel =================
    static class InfoCardPanel extends JPanel {
        private static final int ARC = 20;
        private JLabel lblMaHPValue, lblSoTienValue, lblHocKyValue;
        private final Dimension size = new Dimension(400, 180);

        public InfoCardPanel(String amount) {
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel();
            contentPanel.setOpaque(false);
            contentPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 0, 15, 0);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL; // fill ngang
            gbc.weightx = 1; // chiếm full ngang

            JLabel lblTitle = new JLabel("Thông tin thanh toán");
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblTitle.setForeground(Color.WHITE);
            contentPanel.add(lblTitle, gbc);

            // --- Mã HP ---
            gbc.gridwidth = 1;
            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 10, 5);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel lblMaHP = new JLabel("Mã HP:");
            lblMaHP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblMaHP.setForeground(Color.WHITE);
            contentPanel.add(lblMaHP, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST; // label value sát trái
            lblMaHPValue = new JLabel(amount);
            lblMaHPValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblMaHPValue.setForeground(Color.WHITE);
            contentPanel.add(lblMaHPValue, gbc);

            // --- Số tiền ---
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblSoTien = new JLabel("Số tiền:");
            lblSoTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblSoTien.setForeground(Color.WHITE);
            contentPanel.add(lblSoTien, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            lblSoTienValue = new JLabel(amount);
            lblSoTienValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblSoTienValue.setForeground(Color.WHITE);
            contentPanel.add(lblSoTienValue, gbc);

            // --- Học kỳ ---
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblHocKy = new JLabel("Học kỳ:");
            lblHocKy.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblHocKy.setForeground(Color.WHITE);
            contentPanel.add(lblHocKy, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            lblHocKyValue = new JLabel("");
            lblHocKyValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblHocKyValue.setForeground(Color.WHITE);
            contentPanel.add(lblHocKyValue, gbc);

            add(contentPanel, BorderLayout.NORTH);
        }

        public void setInfo(String maHP, String hocKy, double amount) {
            lblMaHPValue.setText(maHP != null ? maHP : "");
            lblSoTienValue.setText(String.format("%,d VND", (long) amount));
            lblHocKyValue.setText(hocKy != null ? hocKy : "");
            revalidate();
            repaint();
        }

        public String getMaHP() { return lblMaHPValue.getText(); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color base = new Color(46, 81, 233);
            Color lighter = base.brighter();
            g2.setPaint(new GradientPaint(0, 0, base, getWidth(), getHeight(), lighter));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() { return size; }
        @Override
        public Dimension getMaximumSize() { return size; }
        @Override
        public Dimension getMinimumSize() { return size; }
    }

    // ================= Populate info =================
    public void populateFields(String maHP, String hocKy, double amount) {
        if (infoPanel != null) {
            infoPanel.setInfo(maHP, hocKy, amount);
        }
    }

    public static PaymentFormPanel findIn(Container parent) {
        for (Component c : parent.getComponents()) {
            if (c instanceof PaymentFormPanel panel) {
                return panel;
            } else if (c instanceof Container container) {
                PaymentFormPanel found = findIn(container);
                if (found != null) return found;
            }
        }
        return null;
    }
    
    /**
    * Lấy currentMssv và currentMaHP từ Session, gọi getHocPhi và populate infoPanel
    */
   private void loadHocPhiFromSession() {
       String mssv = Session.getCurrentMssv();
       String maHP = Session.getCurrentMaHP();

       if (mssv == null || maHP == null) return;

       HocPhiController controller = new HocPhiController();
       try {
           HocPhi hp = controller.getHocPhi(mssv, maHP);
           if (hp != null) {
               // populate infoPanel
               populateFields(hp.getMaHP(), hp.getHocKy(), hp.getSoTien());
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
}
