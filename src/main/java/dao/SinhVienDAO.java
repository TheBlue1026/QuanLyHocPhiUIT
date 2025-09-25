package dao;

import com.uit.project.quanlyhocphiuit.DBConnection;
import models.SinhVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {

    public List<SinhVien> getAllSinhVien() throws SQLException {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT mssv, hoTen, namSinh, gioiTinh, soDienThoai, diaChiTamTru, " +
                     "nganhHoc, namNhapHoc, lop, khoa, " +
                     "khanCapHoTen, khanCapSoDienThoai, khanCapDiaChi " +
                     "FROM sinhvien";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SinhVien sv = new SinhVien(
                        rs.getString("mssv"),
                        rs.getString("hoTen"),
                        rs.getInt("namSinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("diaChiTamTru"),
                        rs.getString("nganhHoc"),
                        rs.getInt("namNhapHoc"),
                        rs.getString("lop"),
                        rs.getString("khoa"),
                        rs.getString("khanCapHoTen"),
                        rs.getString("khanCapSoDienThoai"),
                        rs.getString("khanCapDiaChi")
                );
                list.add(sv);
            }
        }
        return list;
    }

    public SinhVien getSinhVienByMSSV(String mssv) throws SQLException {
        String sql = "SELECT mssv, hoTen, namSinh, gioiTinh, soDienThoai, diaChiTamTru, " +
                     "nganhHoc, namNhapHoc, lop, khoa, " +
                     "khanCapHoTen, khanCapSoDienThoai, khanCapDiaChi " +
                     "FROM sinhvien WHERE mssv = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SinhVien(
                            rs.getString("mssv"),
                            rs.getString("hoTen"),
                            rs.getInt("namSinh"),
                            rs.getString("gioiTinh"),
                            rs.getString("soDienThoai"),
                            rs.getString("diaChiTamTru"),
                            rs.getString("nganhHoc"),
                            rs.getInt("namNhapHoc"),
                            rs.getString("lop"),
                            rs.getString("khoa"),
                            rs.getString("khanCapHoTen"),
                            rs.getString("khanCapSoDienThoai"),
                            rs.getString("khanCapDiaChi")
                    );
                }
            }
        }
        return null;
    }

    public boolean addSinhVien(SinhVien sv) throws SQLException {
        String sql = "INSERT INTO sinhvien (mssv, hoTen, namSinh, gioiTinh, soDienThoai, diaChiTamTru, " +
                     "nganhHoc, namNhapHoc, lop, khoa, khanCapHoTen, khanCapSoDienThoai, khanCapDiaChi) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getMSSV());
            ps.setString(2, sv.getHoTen());
            ps.setInt(3, sv.getNamSinh());
            ps.setString(4, sv.getGioiTinh());
            ps.setString(5, sv.getSoDienThoai());
            ps.setString(6, sv.getDiaChiTamTru());
            ps.setString(7, sv.getNganhHoc());
            ps.setInt(8, sv.getNamNhapHoc());
            ps.setString(9, sv.getMaLop());
            ps.setString(10, sv.getNganhHoc());
            ps.setString(11, sv.getKhanCapHoTen());
            ps.setString(12, sv.getKhanCapSoDienThoai());
            ps.setString(13, sv.getKhanCapDiaChi());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateSinhVien(SinhVien sv) throws SQLException {
        String sql = "UPDATE sinhvien SET hoTen=?, namSinh=?, gioiTinh=?, soDienThoai=?, diaChiTamTru=?, " +
                     "nganhHoc=?, namNhapHoc=?, lop=?, khoa=?, khanCapHoTen=?, khanCapSoDienThoai=?, khanCapDiaChi=? " +
                     "WHERE mssv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getHoTen());
            ps.setInt(2, sv.getNamSinh());
            ps.setString(3, sv.getGioiTinh());
            ps.setString(4, sv.getSoDienThoai());
            ps.setString(5, sv.getDiaChiTamTru());
            ps.setString(6, sv.getNganhHoc());
            ps.setInt(7, sv.getNamNhapHoc());
            ps.setString(8, sv.getNganhHoc());
            ps.setString(9, sv.getNganhHoc());
            ps.setString(10, sv.getKhanCapHoTen());
            ps.setString(11, sv.getKhanCapSoDienThoai());
            ps.setString(12, sv.getKhanCapDiaChi());
            ps.setString(13, sv.getMSSV());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteSinhVien(String mssv) throws SQLException {
        String sql = "DELETE FROM sinhvien WHERE mssv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            return ps.executeUpdate() > 0;
        }
    }
}
