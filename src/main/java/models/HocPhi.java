package models;

public class HocPhi {
    private String mssv;
    private String maHP;
    private double soTien;
    private String hocKy;
    private String trangThai; // CHUA_TT or DA_TT

    public HocPhi() {}

    public HocPhi(String mssv, String maHP, double soTien, String hocKy, String trangThai) {
        this.mssv = mssv;
        this.maHP = maHP;
        this.soTien = soTien;
        this.hocKy = hocKy;
        this.trangThai = trangThai;
    }

    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }

    public String getMaHP() { return maHP; }
    public void setMaHP(String maHP) { this.maHP = maHP; }

    public double getSoTien() { return soTien; }
    public void setSoTien(double soTien) { this.soTien = soTien; }

    public String getHocKy() { return hocKy; }
    public void setHocKy(String hocKy) { this.hocKy = hocKy; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return String.format("HocPhi{mssv='%s', maHP='%s', soTien=%.2f, hocKy='%s', trangThai='%s'}",
            mssv, maHP, soTien, hocKy, trangThai);
    }
}
