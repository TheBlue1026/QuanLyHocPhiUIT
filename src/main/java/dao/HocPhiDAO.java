package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.HocPhi;
import com.uit.project.quanlyhocphiuit.util.DBConnection;
import java.sql.Date;

public class HocPhiDAO {

    private Connection conn;

    public HocPhiDAO() {
        this.conn = DBConnection.getInstance().getConnection();
    }
    
    public boolean addHocPhi(HocPhi hp) {
        String sql = "INSERT INTO HOCPHI (MaHP_SV, MSSV, MaHP, SoTienPhaiDong, SoTienDaDong, NgayTaoKhoanPhi, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hp.getMaHP_SV());
            ps.setString(2, hp.getMssv());
            ps.setString(3, hp.getMaHP());
            ps.setDouble(4, hp.getSoTienPhaiDong());
            ps.setDouble(5, hp.getSoTienDaDong());
            ps.setDate(6, (Date) hp.getNgayTaoKhoanPhi()); 
            ps.setString(7, hp.getTrangThai());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateHocPhi(HocPhi hp) {
        String sql = "UPDATE HOCPHI SET MSSV = ?, MaHP = ?, SoTienPhaiDong = ?, SoTienDaDong = ?, NgayTaoKhoanPhi = ?, TrangThai = ? WHERE MaHP_SV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hp.getMssv());
            ps.setString(2, hp.getMaHP());
            ps.setDouble(3, hp.getSoTienPhaiDong());
            ps.setDouble(4, hp.getSoTienDaDong());
            ps.setDate(5, (Date) hp.getNgayTaoKhoanPhi()); 
            ps.setString(6, hp.getTrangThai());
            ps.setString(7, hp.getMaHP_SV());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public HocPhi getHocPhiByMaHP_SV(String maHP_SV) {
        String sql = "SELECT * FROM HOCPHI WHERE MaHP_SV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHP_SV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HocPhi hp = new HocPhi();
                hp.setMaHP_SV(rs.getString("MaHP_SV"));
                hp.setMssv(rs.getString("MSSV"));
                hp.setMaHP(rs.getString("MaHP"));
                hp.setSoTienPhaiDong(rs.getDouble("SoTienPhaiDong"));
                hp.setSoTienDaDong(rs.getDouble("SoTienDaDong"));
                hp.setNgayTaoKhoanPhi(rs.getDate("NgayTaoKhoanPhi"));
                hp.setTrangThai(rs.getString("TrangThai"));
                return hp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HocPhi> getAllHocPhi() {
        List<HocPhi> list = new ArrayList<>();
        String sql = "SELECT * FROM HOCPHI";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocPhi hp = new HocPhi();
                hp.setMaHP_SV(rs.getString("MaHP_SV"));
                hp.setMssv(rs.getString("MSSV"));
                hp.setMaHP(rs.getString("MaHP"));
                hp.setSoTienPhaiDong(rs.getDouble("SoTienPhaiDong"));
                hp.setSoTienDaDong(rs.getDouble("SoTienDaDong"));
                hp.setNgayTaoKhoanPhi(rs.getDate("NgayTaoKhoanPhi"));
                hp.setTrangThai(rs.getString("TrangThai"));
                list.add(hp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HocPhi> getHocPhiTheoMSSV(String mssv) throws SQLException {
    List<HocPhi> danhSachHocPhi = new ArrayList<>();
    String sql = "SELECT * FROM HocPhi WHERE MSSV = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, mssv);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HocPhi hp = new HocPhi();
                hp.setMaHocPhi(rs.getString("MaHocPhi"));
                hp.setMSSV(rs.getString("MSSV"));
                hp.setHocKy(rs.getString("HocKy"));
                hp.setNamHoc(rs.getString("NamHoc"));
                hp.setSoTienPhaiDong(rs.getDouble("SoTienPhaiDong"));
                hp.setSoTienDaDong(rs.getDouble("SoTienDaDong"));
                hp.setTrangThai(rs.getString("TrangThai"));
                danhSachHocPhi.add(hp);
            }
        }
    }
    
    return danhSachHocPhi;
}

    public HocPhi getHocPhiChoSV(String mssv, String maHP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean ghiNhanThanhToan(String mssv, String maHP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
