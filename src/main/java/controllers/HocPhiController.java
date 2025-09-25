package controllers;

import dao.HocPhiDAO;
import models.HocPhi;
import java.util.Collections;
import java.util.List;
import java.sql.SQLException;

public class HocPhiController {

    private HocPhiDAO dao;

    public HocPhiController() {
        this.dao = new HocPhiDAO();
    }

    public List<HocPhi> getHocPhiTheoMSSV(String mssv) {
        try {
            return dao.getHocPhiTheoMSSV(mssv);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean payHocPhi(String mssv, String maHP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public HocPhi getHocPhi(String mssv, String maHP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public HocPhi getPendingHocPhi(String mssv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
