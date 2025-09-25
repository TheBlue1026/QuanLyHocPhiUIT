package dao;

import com.uit.project.quanlyhocphiuit.DBConnection;
import models.HocPhi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HocPhiDAO {

    public HocPhi getHocPhiChoSV(String mssv, String maHP) throws SQLException {
        String sql = "SELECT soTien FROM hocphi WHERE mssv=? AND maHP=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            ps.setString(2, maHP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new HocPhi(
                            rs.getString("mssv"),
                            rs.getString("maHP"),
                            rs.getDouble("soTien"),
                            rs.getString("hocKy"),
                            rs.getString("trangThai"));
                }
            }
        }
        return null;
    }
    
        public List<HocPhi> getHocPhiTheoMSSV(String mssv) throws SQLException {
        List<HocPhi> list = new ArrayList<>();
        String sql = "SELECT mssv, maHP, soTien, hocKy, trangThai FROM hocphi WHERE mssv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HocPhi hp = new HocPhi(
                            rs.getString("mssv"),
                            rs.getString("maHP"),
                            rs.getDouble("soTien"),
                            rs.getString("hocKy"),
                            rs.getString("trangThai"));
                    list.add(hp);
                }
            }
        }
        return list;
    }

    public List<HocPhi> getHocPhiNoTheoHocKy(String hocKy) throws SQLException {
        List<HocPhi> list = new ArrayList<>();
        String sql = "SELECT mssv, maHP, soTien, hocKy, trangThai FROM hocphi WHERE hocKy=? AND trangThai='CHUA_TT'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hocKy);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HocPhi hp = new HocPhi(
                            rs.getString("mssv"),
                            rs.getString("maHP"),
                            rs.getDouble("soTien"),
                            rs.getString("hocKy"),
                            rs.getString("trangThai"));
                    list.add(hp);
                }
            }
        }
        return list;
    }

    public boolean ghiNhanThanhToan(String mssv, String maHP) throws SQLException {
        String sql = "UPDATE hocphi SET trangThai='DA_TT' WHERE mssv=? AND maHP=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            ps.setString(2, maHP);
            return ps.executeUpdate() > 0;
        }
    }

    // Utility: add a hocphi row (for testing)
    public boolean addHocPhi(HocPhi hp) throws SQLException {
        String sql = "INSERT INTO hocphi (mssv, maHP, soTien, hocKy, trangThai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hp.getMssv());
            ps.setString(2, hp.getMaHP());
            ps.setDouble(3, hp.getSoTien());
            ps.setString(4, hp.getHocKy());
            ps.setString(5, hp.getTrangThai());
            return ps.executeUpdate() > 0;
        }
    }
}
