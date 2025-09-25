package controllers;

import com.uit.project.quanlyhocphiuit.Session;
import dao.SinhVienDAO;
import models.SinhVien;

import java.sql.SQLException;
import java.util.List;

public class SinhVienController {
    private SinhVienDAO svDAO = new SinhVienDAO();
    
     /**
     * Thực hiện login
     * @param mssv MSSV nhập từ người dùng
     * @param password password nhập từ người dùng
     * @return true nếu login thành công
     */
    public boolean login(String mssv, String password) {
        try {
            SinhVien sv = svDAO.getSinhVienByMSSV(mssv);
            
            if (sv != null && sv.getPassword() != null && sv.getPassword().equals(password)) {
                // lưu MSSV vào Session
                Session.setCurrentMssv(sv.getMSSV());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SinhVien> getSV() throws SQLException {
        return svDAO.getAllSinhVien();
    }

    public SinhVien getChiTietSV(String mssv) throws SQLException {
        return svDAO.getSinhVienByMSSV(mssv);
    }

    public boolean addSV(SinhVien sv) throws SQLException {
        // you can add validation here (e.g., check duplicate mssv)
        return svDAO.addSinhVien(sv);
    }

    public boolean updateSV(SinhVien sv) throws SQLException {
        return svDAO.updateSinhVien(sv);
    }

    public boolean deleteSV(String mssv) throws SQLException {
        return svDAO.deleteSinhVien(mssv);
    }
    
    public void logout() {
        Session.clear();
    }
}
