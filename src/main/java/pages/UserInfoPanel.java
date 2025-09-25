package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

import controllers.SinhVienController;
import models.SinhVien;
import com.uit.project.quanlyhocphiuit.Session;
import styles.UIStyle;

/**
 * UserInfoPanel - fixed layout:
 * - TOP: title (UIStyle.titleLabel)
 * - CENTER: contentPanel (GridBagLayout) chứa 3 card bo tròn:
 *     + personalCard  : chiếm cả hàng 1
 *     + studentCard   : chiếm nửa hàng 2 (trái)
 *     + contactCard   : chiếm nửa hàng 2 (phải)
 *
 * Important: dùng BorderLayout cho panel chính (title ở NORTH) và contentPanel ở CENTER.
 */
public class UserInfoPanel extends JPanel {
    private SinhVienController controller;

    // Personal info labels
    private JLabel nameLabel, birthLabel, genderLabel, phoneLabel, addressLabel;

    // Student info labels
    private JLabel majorLabel, admissionYearLabel, classLabel, mssvLabel;

    // Emergency contact labels
    private JLabel emergencyNameLabel, emergencyPhoneLabel, emergencyAddressLabel;

    public UserInfoPanel() {
        controller = new SinhVienController();

        // Main panel uses BorderLayout so title stays at the top
        setLayout(new BorderLayout());
        setBackground(UIStyle.BACKGROUND_COLOR);

        // Title (no background, with bottom padding)
        JLabel title = UIStyle.titleLabel("Thông tin sinh viên");
        title.setOpaque(false);
        title.setBorder(new EmptyBorder(0, 0, 18, 0));
        add(title, BorderLayout.NORTH);

        // Content panel inside CENTER (GridBagLayout to arrange cards)
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // --- Personal Card (span 2 columns) ---
        JPanel personalContent = new JPanel(new GridLayout(5, 2, 10, 10));
        personalContent.setOpaque(false);

        JPanel personalCard = createCard("Thông tin cá nhân", personalContent);
        nameLabel = addKeyValueRow(personalContent, "Họ và tên:");
        birthLabel = addKeyValueRow(personalContent, "Năm sinh:");
        genderLabel = addKeyValueRow(personalContent, "Giới tính:");
        phoneLabel = addKeyValueRow(personalContent, "Số điện thoại:");
        addressLabel = addKeyValueRow(personalContent, "Địa chỉ tạm trú:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // span both columns
        gbc.weighty = 0.45;
        contentPanel.add(personalCard, gbc);

        // --- Student Card (left of second row) ---
        JPanel studentContent = new JPanel(new GridLayout(4, 2, 8, 8));
        studentContent.setOpaque(false);

        JPanel studentCard = createCard("Thông tin ngành học", studentContent);
        majorLabel = addKeyValueRow(studentContent, "Ngành học:");
        admissionYearLabel = addKeyValueRow(studentContent, "Năm nhập học:");
        classLabel = addKeyValueRow(studentContent, "Mã lớp:");
        mssvLabel = addKeyValueRow(studentContent, "Mã số SV:");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 0.27;
        contentPanel.add(studentCard, gbc);

        // --- Emergency Contact Card (right of second row) ---
        JPanel contactContent = new JPanel(new GridLayout(3, 2, 8, 8));
        contactContent.setOpaque(false);

        JPanel contactCard = createCard("Thông tin liên lạc (khẩn cấp)", contactContent);
        emergencyNameLabel = addKeyValueRow(contactContent, "Họ tên:");
        emergencyPhoneLabel = addKeyValueRow(contactContent, "Số điện thoại:");
        emergencyAddressLabel = addKeyValueRow(contactContent, "Địa chỉ:");

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(contactCard, gbc);

        // Initialize default placeholders
        clearFields();
        loadSinhVien();
    }

    /**
     * Tạo 1 card bo góc với title trên đầu và panel nội dung chèn vào center.
     */
    private JPanel createCard(String titleText, JPanel innerContent) {
        RoundedPanel panel = new RoundedPanel(16);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        JLabel titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        innerContent.setOpaque(false);
        panel.add(innerContent, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Thêm một cặp key/value vào panel (GridLayout kiểu 2 cột).
     * Trả về JLabel value để populate sau này.
     */
    private JLabel addKeyValueRow(JPanel container, String key) {
        JLabel keyLabel = new JLabel(key);
        keyLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        keyLabel.setForeground(new Color(80, 80, 80));
        container.add(keyLabel);

        JLabel valueLabel = new JLabel("-");
        valueLabel.setFont(new Font("Arial", Font.BOLD, 13));
        valueLabel.setForeground(new Color(30, 30, 30));
        container.add(valueLabel);

        return valueLabel;
    }

    /**
     * Lấy dữ liệu từ Session -> controller -> fill vào các label
     */
    public void populate() {
        String mssv = Session.getCurrentMssv();
        if (mssv == null) {
            clearFields();
            return;
        }

        try {
            SinhVien sv = controller.getChiTietSV(mssv);
            if (sv != null) {
                nameLabel.setText(sv.getHoTen() != null ? sv.getHoTen() : "-");
                // Nếu model có thêm info, hãy set tương ứng
                birthLabel.setText("-");
                genderLabel.setText("-");
                phoneLabel.setText("-");
                addressLabel.setText("-");

                majorLabel.setText(sv.getNganhHoc() != null ? (String) sv.getNganhHoc() : "-");
                admissionYearLabel.setText("-");
                classLabel.setText(sv.getMaLop() != null ? sv.getMaLop() : "-");
                mssvLabel.setText(sv.getMSSV() != null ? sv.getMSSV() : "-");

                emergencyNameLabel.setText("-");
                emergencyPhoneLabel.setText("-");
                emergencyAddressLabel.setText("-");
            } else {
                clearFields();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            clearFields();
        }
    }

    private void clearFields() {
        nameLabel.setText("-");
        birthLabel.setText("-");
        genderLabel.setText("-");
        phoneLabel.setText("-");
        addressLabel.setText("-");

        majorLabel.setText("-");
        admissionYearLabel.setText("-");
        classLabel.setText("-");
        mssvLabel.setText("-");

        emergencyNameLabel.setText("-");
        emergencyPhoneLabel.setText("-");
        emergencyAddressLabel.setText("-");
    }

    /** Tìm UserInfoPanel trong component tree */
    public static UserInfoPanel findUserInfoPanel(Container root) {
        for (Component comp : root.getComponents()) {
            if (comp instanceof UserInfoPanel panel) {
                return panel;
            } else if (comp instanceof Container c) {
                UserInfoPanel child = findUserInfoPanel(c);
                if (child != null) return child;
            }
        }
        return null;
    }

    // --- RoundedPanel inner class ---
    static class RoundedPanel extends JPanel {
        private final int cornerRadius;

        RoundedPanel(int radius) {
            this.cornerRadius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

            // Thin border
            g2.setColor(new Color(230, 230, 230));
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

            g2.dispose();
        }
    }
    
    /**
    * Lấy MSSV từ Session, gọi controller.getChiTietSV, và populate lên panel
    */
    public void loadSinhVien() {
        String mssv = Session.getCurrentMssv();
        if (mssv == null) {
            clearFields();
            return;
        }

        try {
            SinhVien sv = controller.getChiTietSV(mssv);
            if (sv != null) {
                // --- Personal info ---
                nameLabel.setText(sv.getHoTen() != null ? sv.getHoTen() : "-");
                birthLabel.setText(sv.getNamSinh() > 0 ? String.valueOf(sv.getNamSinh()) : "-");
                genderLabel.setText(sv.getGioiTinh() != null ? (String) sv.getGioiTinh() : "-");
                phoneLabel.setText(sv.getSoDienThoai() != null ? (String) sv.getSoDienThoai() : "-");
                addressLabel.setText(sv.getDiaChiTamTru() != null ? (String) sv.getDiaChiTamTru() : "-");

                // --- Student info ---
                majorLabel.setText(sv.getNganhHoc() != null ? (String) sv.getNganhHoc() : "-");
                admissionYearLabel.setText(sv.getNamNhapHoc() > 0 ? String.valueOf(sv.getNamNhapHoc()) : "-");
                classLabel.setText(sv.getMaLop() != null ? sv.getMaLop() : "-");
                mssvLabel.setText(sv.getMSSV() != null ? sv.getMSSV() : "-");

                // --- Emergency contact ---
                emergencyNameLabel.setText(sv.getKhanCapHoTen() != null ? (String) sv.getKhanCapHoTen() : "-");
                emergencyPhoneLabel.setText(sv.getKhanCapSoDienThoai() != null ? (String) sv.getKhanCapSoDienThoai() : "-");
                emergencyAddressLabel.setText(sv.getKhanCapDiaChi() != null ? (String)sv.getKhanCapDiaChi() : "-");
            } else {
                clearFields();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            clearFields();
        }
    }

}
