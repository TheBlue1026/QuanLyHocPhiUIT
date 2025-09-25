package dao;

import com.uit.project.quanlyhocphiuit.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.SinhVien;
import com.uit.project.quanlyhocphiuit.util.DBConnection;

public class SinhVienDAO {

    private Connection conn;

    public SinhVienDAO() {
        // Lấy kết nối duy nhất từ Singleton DBConnection
        this.conn = DBConnection.getInstance().getConnection();
    }

    // Thêm một sinh viên mới vào cơ sở dữ liệu
    public boolean addSinhVien(SinhVien sv) {
        String sql = "INSERT INTO SINHVIEN (MSSV, HoTen, NgaySinh, DiaChi, Email, MaLop, MaKhoa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getMssv());
            ps.setString(2, sv.getHoTen());
            ps.setDate(3, new java.sql.Date(sv.getNgaySinh().getTime()));
            ps.setString(4, sv.getDiaChi());
            ps.setString(5, sv.getEmail());
            ps.setString(6, sv.getMaLop());
            ps.setString(7, sv.getMaKhoa());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin của một sinh viên
    public boolean updateSinhVien(SinhVien sv) {
        String sql = "UPDATE SINHVIEN SET HoTen = ?, NgaySinh = ?, DiaChi = ?, Email = ?, MaLop = ?, MaKhoa = ? WHERE MSSV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getHoTen());
            ps.setDate(2, new java.sql.Date(sv.getNgaySinh().getTime()));
            ps.setString(3, sv.getDiaChi());
            ps.setString(4, sv.getEmail());
            ps.setString(5, sv.getMaLop());
            ps.setString(6, sv.getMaKhoa());
            ps.setString(7, sv.getMssv());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một sinh viên
    public boolean deleteSinhVien(String mssv) {
        String sql = "DELETE FROM SINHVIEN WHERE MSSV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy một sinh viên theo MSSV
    public SinhVien getSinhVienByMssv(String mssv) {
        String sql = "SELECT * FROM SINHVIEN WHERE MSSV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMssv(rs.getString("MSSV"));
                sv.setHoTen(rs.getString("HoTen"));
                sv.setNgaySinh(rs.getDate("NgaySinh"));
                sv.setDiaChi(rs.getString("DiaChi"));
                sv.setEmail(rs.getString("Email"));
                sv.setMaLop(rs.getString("MaLop"));
                sv.setMaKhoa(rs.getString("MaKhoa"));
                return sv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả sinh viên
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMssv(rs.getString("MSSV"));
                sv.setHoTen(rs.getString("HoTen"));
                sv.setNgaySinh(rs.getDate("NgaySinh"));
                sv.setDiaChi(rs.getString("DiaChi"));
                sv.setEmail(rs.getString("Email"));
                sv.setMaLop(rs.getString("MaLop"));
                sv.setMaKhoa(rs.getString("MaKhoa"));
                list.add(sv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public SinhVien getSinhVienByMSSV(String mssv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
