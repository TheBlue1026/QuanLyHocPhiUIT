package pages;

import javax.swing.*;
import java.awt.*;
import controllers.HocPhiController;
import models.HocPhi;
import com.uit.project.quanlyhocphiuit.Session;
import ui.InfoCard;
import styles.UIStyle;

public class DashboardPanel extends JPanel {

    private InfoCard infoCard;
    private JLabel emptyLabel;
    private HocPhiController hocPhiController;

    public DashboardPanel() {
        hocPhiController = new HocPhiController();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title
        JLabel title = UIStyle.titleLabel("Trang chủ");
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Center panel
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setAlignmentX(Component.LEFT_ALIGNMENT); // optional, text/InfoCard thẳng hàng trái

        // InfoCard (ẩn trước)
        infoCard = new InfoCard("0 VND");
        infoCard.setVisible(false);

        // Empty placeholder
        emptyLabel = new JLabel(
            "<html><div style='text-align:center;'>"
            + "Hiện tại bạn không có học phí cần đóng.<br>"
            + "Vui lòng kiểm tra lại lịch học phí định kỳ để không bỏ lỡ kỳ thanh toán.<br>"
            + "Cảm ơn bạn đã sử dụng hệ thống quản lý học phí."
            + "</div></html>"
        );
        emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emptyLabel.setForeground(Color.GRAY);
        emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Thêm trực tiếp, không dùng glue
        center.add(infoCard);
        center.add(Box.createVerticalStrut(10)); // khoảng cách giữa card & label
        center.add(emptyLabel);

        add(center, BorderLayout.CENTER);

        loadHocPhi();
    }

    private void loadHocPhi() {
        String mssv = Session.getCurrentMssv();
        if (mssv == null) return;

        HocPhi pending = hocPhiController.getPendingHocPhi(mssv);

        if (pending != null) {
            infoCard.setInfo(pending.getMaHP(), pending.getHocKy(), pending.getSoTien());
            infoCard.setVisible(true);
            emptyLabel.setVisible(false);
        } else {
            infoCard.setVisible(false);
            emptyLabel.setVisible(true);
        }
    }
}
