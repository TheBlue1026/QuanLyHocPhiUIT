package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import com.uit.project.quanlyhocphiuit.AppFrame;
import com.uit.project.quanlyhocphiuit.Session;
import styles.UIStyle;

public class InfoCard extends JPanel {
    private static final int ARC = 20;

    // Labels để hiển thị thông tin
    private JLabel lblTitle;
    private JLabel lblSoTien;
    private JButton payNowBtn;

    // ✅ Biến lưu nội dung hiện tại
    private String currentMaHP;
    private String currentHocKy;
    private double currentSoTien;

    public InfoCard(String defaultAmount) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false); 
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        setPreferredSize(new Dimension(300, 160));
        setMaximumSize(new Dimension(300, 160));
        setMinimumSize(new Dimension(300, 160));

        lblTitle = new JLabel("Bạn có khoản học phí cần thanh toán!");
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblTitle);

        lblSoTien = new JLabel(defaultAmount);
        lblSoTien.setFont(new Font("Arial", Font.BOLD, 28));
        lblSoTien.setForeground(Color.WHITE);
        lblSoTien.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblSoTien.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblSoTien);

        payNowBtn = UIStyle.secondaryButton("Thanh toán ngay");
        payNowBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        payNowBtn.addActionListener(e -> {
            Container root = SwingUtilities.getWindowAncestor(this);
            if (root instanceof AppFrame frame) {
                Session.setCurrentMaHP(currentMaHP);
                frame.navigateTo("paymentForm");
            }
        });
        add(payNowBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color base = new Color(46, 81, 233);
        Color lighter = base.brighter();
        GradientPaint gradient = new GradientPaint(0, 0, base, getWidth(), getHeight(), lighter);
        g2.setPaint(gradient);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), ARC, ARC));
        g2.dispose();
    }

    /**
     * Cập nhật thông tin học phí lên InfoCard
     */
    public void setInfo(String maHP, String hocKy, double soTien) {
        currentMaHP = maHP;
        currentHocKy = hocKy;
        currentSoTien = soTien;

        if (maHP != null && hocKy != null) {
            lblSoTien.setText(String.format("%,.0f VND", soTien));
            lblTitle.setText("Bạn có khoản học phí cần thanh toán!");
            payNowBtn.setVisible(true);
        } else {
            lblTitle.setText("<html><center>Chưa đến kì đóng học phí.<br>Hiện tại bạn chưa có khoản nào cần thanh toán.<br>Vui lòng kiểm tra sau!</center></html>");
            lblSoTien.setText("");
            payNowBtn.setVisible(false);
        }
        revalidate();
        repaint();
    }

    // ✅ Getter để lấy lại giá trị đã lưu
    public String getCurrentMaHP() { return currentMaHP; }
    public String getCurrentHocKy() { return currentHocKy; }
    public double getCurrentSoTien() { return currentSoTien; }
}
