package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.HocPhan;
import com.uit.project.quanlyhocphiuit.util.DBConnection;

public class HocPhanDAO implements IDAO<HocPhan>{
    
    private Connection conn;

    public HocPhanDAO() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    // Thêm một học phần mới
    public boolean addHocPhan(HocPhan hp) {
        String sql = "INSERT INTO HOCPHAN (MaHP, TenHP, SoTinChi, DonGiaTinChi, HocKy, NamHoc, MaGiangVien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hp.getMaHP());
            ps.setString(2, hp.getTenHP());
            ps.setInt(3, hp.getSoTinChi());
            ps.setDouble(4, hp.getDonGiaTinChi());
            ps.setString(5, hp.getHocKy());
            ps.setString(6, hp.getNamHoc());
            ps.setString(7, hp.getMaGiangVien());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Cập nhật thông tin học phần
    public boolean updateHocPhan(HocPhan hp) {
        String sql = "UPDATE HOCPHAN SET TenHP = ?, SoTinChi = ?, DonGiaTinChi = ?, HocKy = ?, NamHoc = ?, MaGiangVien = ? WHERE MaHP = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hp.getTenHP());
            ps.setInt(2, hp.getSoTinChi());
            ps.setDouble(3, hp.getDonGiaTinChi());
            ps.setString(4, hp.getHocKy());
            ps.setString(5, hp.getNamHoc());
            ps.setString(6, hp.getMaGiangVien());
            ps.setString(7, hp.getMaHP());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một học phần
    public boolean deleteHocPhan(String maHP) {
        String sql = "DELETE FROM HOCPHAN WHERE MaHP = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHP);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy một học phần theo mã
    public HocPhan getHocPhanByMaHP(String maHP) {
        String sql = "SELECT * FROM HOCPHAN WHERE MaHP = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HocPhan hp = new HocPhan();
                hp.setMaHP(rs.getString("MaHP"));
                hp.setTenHP(rs.getString("TenHP"));
                hp.setSoTinChi(rs.getInt("SoTinChi"));
                hp.setDonGiaTinChi(rs.getDouble("DonGiaTinChi"));
                hp.setHocKy(rs.getString("HocKy"));
                hp.setNamHoc(rs.getString("NamHoc"));
                hp.setMaGiangVien(rs.getString("MaGiangVien"));
                return hp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả học phần
    public List<HocPhan> getAllHocPhan() {
        List<HocPhan> list = new ArrayList<>();
        String sql = "SELECT * FROM HOCPHAN";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocPhan hp = new HocPhan();
                hp.setMaHP(rs.getString("MaHP"));
                hp.setTenHP(rs.getString("TenHP"));
                hp.setSoTinChi(rs.getInt("SoTinChi"));
                hp.setDonGiaTinChi(rs.getDouble("DonGiaTinChi"));
                hp.setHocKy(rs.getString("HocKy"));
                hp.setNamHoc(rs.getString("NamHoc"));
                hp.setMaGiangVien(rs.getString("MaGiangVien"));
                list.add(hp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean add(HocPhan t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(HocPhan t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HocPhan getById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HocPhan> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
