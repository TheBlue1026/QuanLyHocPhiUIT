package controllers;

import dao.HocPhiDAO;

import java.sql.SQLException;
import java.util.List;
import models.HocPhi;

public class HocPhiController {
    private HocPhiDAO hpDAO = new HocPhiDAO();
    
    /**
     * Lấy 1 học phí của sinh viên có trạng thái "CHUA_TT"
     * @param mssv MSSV sinh viên
     * @return HocPhi chưa thanh toán đầu tiên, hoặc null nếu không có
     */
    public HocPhi getPendingHocPhi(String mssv) {
        try {
            List<HocPhi> list = hpDAO.getHocPhiTheoMSSV(mssv);
            for (HocPhi hp : list) {
                if ("CHUA_TT".equals(hp.getTrangThai())) {
                    return hp; // trả về học phí chưa thanh toán đầu tiên
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // không có học phí chưa thanh toán
    }

    public HocPhi getHocPhi(String mssv, String maHP) throws SQLException {
        return hpDAO.getHocPhiChoSV(mssv, maHP);
    }

    public boolean payHocPhi(String mssv, String maHP) throws SQLException {
        // add business rules here (e.g., check current status) before updating
        return hpDAO.ghiNhanThanhToan(mssv, maHP);
    }
}
