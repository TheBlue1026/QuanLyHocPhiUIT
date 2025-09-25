package pages;

import com.uit.project.quanlyhocphiuit.Session;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import styles.UIStyle;
import dao.HocPhiDAO;
import models.HocPhi;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.TablePanel;

public class PaymentListPanel extends JPanel {
    private HocPhiDAO hocPhiDAO;
    private JTable table;

    public PaymentListPanel() throws SQLException {
        hocPhiDAO = new HocPhiDAO();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- Title ---
        JLabel title = UIStyle.titleLabel("Lịch sử đóng tiền học phí");
        title.setOpaque(false); // bỏ nền
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); 
        // padding bottom = 20 để tạo khoảng cách với bảng
        add(title, BorderLayout.NORTH);

        // --- Table ---
        String[] columns = {"Mã HP","Số tiền","Học kỳ","Trạng thái"};
        TablePanel tablePanel = new TablePanel(columns);
        table = tablePanel.getTable();

        // load data
        List<HocPhi> list = hocPhiDAO.getHocPhiTheoMSSV("001");
        for (HocPhi hp : list) {
            tablePanel.addRow(new Object[]{
                hp.getMaHP(),
                hp.getSoTien(),
                hp.getHocKy(),
                hp.getTrangThai().equals("CHUA_TT") ? "Thanh toán ngay" : "Đã thanh toán"
            });
        }

        add(tablePanel, BorderLayout.CENTER);

        // sự kiện click vào cột trạng thái
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                
                if (row >= 0 && col == 3) {
                    String maHP = String.valueOf(table.getValueAt(row, 0));
                    String hocKy = String.valueOf(table.getValueAt(row, 2));
                    Object amtObj = table.getValueAt(row, 1);
                    double amount = 0;
                    if (amtObj instanceof Number) {
                        amount = ((Number) amtObj).doubleValue();
                    } else {
                        try { amount = Double.parseDouble(String.valueOf(amtObj)); } catch (Exception ignored) {}
                    }

                    // tìm AppFrame
                    Container parent = getParent();
                    while (parent != null && !(parent instanceof com.uit.project.quanlyhocphiuit.AppFrame)) {
                        parent = parent.getParent();
                    }
                    if (parent instanceof com.uit.project.quanlyhocphiuit.AppFrame frame) {
                        Session.setCurrentMaHP(maHP);
                        try {
                            frame.navigateTo("paymentForm");
                        } catch (SQLException ex) {
                            Logger.getLogger(PaymentListPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }
}
