package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.PhieuThu;
import com.uit.project.quanlyhocphiuit.util.DBConnection;

public class PhieuThuDAO implements IDAO<PhieuThu>{

    private Connection conn;

    public PhieuThuDAO() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    public boolean add(PhieuThu pt) throws SQLException {
        String sql = "INSERT INTO PhieuThu (maPT, maHocPhi, ngayThu, soTien, ghiChu) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pt.getMaPT());
            ps.setString(2, pt.getMaHocPhi());
            ps.setDate(3, new java.sql.Date(pt.getNgayThu().getTime()));
            ps.setDouble(4, pt.getSoTien());
            ps.setString(5, pt.getGhiChu());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(PhieuThu pt) throws SQLException {
        String sql = "UPDATE PhieuThu SET maHocPhi=?, ngayThu=?, soTien=?, ghiChu=? WHERE maPT=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pt.getMaHocPhi());
            ps.setDate(2, new java.sql.Date(pt.getNgayThu().getTime()));
            ps.setDouble(3, pt.getSoTien());
            ps.setString(4, pt.getGhiChu());
            ps.setString(5, pt.getMaPT());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM PhieuThu WHERE maPT=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public PhieuThu getById(String id) throws SQLException {
        String sql = "SELECT * FROM PhieuThu WHERE maPT=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractPhieuThuFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<PhieuThu> getAll() throws SQLException {
        List<PhieuThu> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuThu";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(extractPhieuThuFromResultSet(rs));
            }
        }
        return list;
    }

    private PhieuThu extractPhieuThuFromResultSet(ResultSet rs) throws SQLException {
        PhieuThu pt = new PhieuThu();
        pt.setMaPT(rs.getString("maPT"));
        pt.setMaHocPhi(rs.getString("maHocPhi"));
        pt.setNgayThu(rs.getDate("ngayThu"));
        pt.setSoTien(rs.getDouble("soTien"));
        pt.setGhiChu(rs.getString("ghiChu"));
        return pt;
    }
}
