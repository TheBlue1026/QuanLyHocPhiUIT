package dao;

import com.uit.project.quanlyhocphiuit.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.NguoiDung;


public class NguoiDungDAO implements IDAO<NguoiDung>{
    
    private Connection conn;

    public NguoiDungDAO() {
        this.conn = DBConnection.getInstance().getConnection();
    }
    
    /**
     * Phương thức tìm kiếm người dùng theo tên đăng nhập.
     * * @param tenDangNhap Tên đăng nhập cần tìm.
     * @return Đối tượng NguoiDung nếu tìm thấy, ngược lại trả về null.
     */
    public NguoiDung getNguoiDungByTenDangNhap(String tenDangNhap) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE TenDangNhap = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NguoiDung user = new NguoiDung();
                user.setMaND(rs.getString("MaND"));
                user.setTenDangNhap(rs.getString("TenDangNhap"));
                user.setMatKhau(rs.getString("MatKhau"));
                user.setVaiTro(rs.getString("VaiTro"));
                user.setHoTen(rs.getString("HoTen"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn database: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(NguoiDung t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(NguoiDung t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NguoiDung getById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NguoiDung> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
