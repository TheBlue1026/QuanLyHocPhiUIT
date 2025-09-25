package controllers;

import dao.NguoiDungDAO;
import model.NguoiDung;

/**
 * Lớp điều khiển (Controller) cho các chức năng liên quan đến người dùng.
 */
public class NguoiDungController {

    private NguoiDungDAO nguoiDungDAO;

    public NguoiDungController() {
        this.nguoiDungDAO = new NguoiDungDAO();
    }

    /**
     * Phương thức xử lý logic đăng nhập của người dùng.
     * * @param username Tên đăng nhập của người dùng.
     * @param password Mật khẩu của người dùng.
     * @return true nếu đăng nhập thành công, false nếu ngược lại.
     */
    public boolean login(String username, String password) {
        NguoiDung user = nguoiDungDAO.getNguoiDungByTenDangNhap(username);
        
        // Kiểm tra xem người dùng có tồn tại và mật khẩu có khớp không
        if (user != null && user.getMatKhau().equals(password)) {
            // Đăng nhập thành công, bạn có thể lưu thông tin người dùng vào một session hoặc biến toàn cục
            // Ví dụ: SessionManager.setCurrentUser(user);
            System.out.println("Đăng nhập thành công với tài khoản: " + username);
            return true;
        } else {
            // Đăng nhập thất bại
            System.out.println("Đăng nhập thất bại cho tài khoản: " + username);
            return false;
        }
    }
}
