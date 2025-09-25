package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.SinhVien;
import com.uit.project.quanlyhocphiuit.util.DBConnection;

public class BaoCaoDAO {

    private Connection conn;

    public BaoCaoDAO() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    /**
     * Lấy danh sách sinh viên chưa đóng học phí trong một học kỳ và năm học cụ thể.
     */
    public List<SinhVien> getDSSinhVienNoHocPhi(String hocKy, String namHoc) throws SQLException {
        List<SinhVien> danhSach = new ArrayList<>();
        String sql = "SELECT sv.* FROM SinhVien sv " +
                     "INNER JOIN HocPhi hp ON sv.MSSV = hp.MSSV " +
                     "WHERE hp.HocKy = ? AND hp.NamHoc = ? AND hp.TrangThai = 'Chưa đóng'";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hocKy);
            ps.setString(2, namHoc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMssv(rs.getString("MSSV"));
                    sv.setHoTen(rs.getString("HoTen"));
                    // Thêm các trường khác của sinh viên
                    danhSach.add(sv);
                }
            }
        }
        return danhSach;
    }

    /**
     * Lấy tổng số tiền đã thu được trong một học kỳ và năm học.
     * Trả về một Map chứa thông tin tổng số tiền.
     */
    public Map<String, Double> getPhanTichDoanhThu(String hocKy, String namHoc) throws SQLException {
        Map<String, Double> ketQua = new HashMap<>();
        String sql = "SELECT SUM(SoTienDaDong) AS TongTienDaDong, SUM(SoTienPhaiDong - SoTienDaDong) AS TongTienConNo " +
                     "FROM HocPhi WHERE HocKy = ? AND NamHoc = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hocKy);
            ps.setString(2, namHoc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ketQua.put("TongTienDaDong", rs.getDouble("TongTienDaDong"));
                    ketQua.put("TongTienConNo", rs.getDouble("TongTienConNo"));
                }
            }
        }
        return ketQua;
    }
}
