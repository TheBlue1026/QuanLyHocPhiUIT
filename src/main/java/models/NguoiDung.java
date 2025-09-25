package model;

/**
 * Lớp model NguoiDung (User) để biểu diễn dữ liệu từ bảng NGUOIDUNG.
 */
public class NguoiDung {
    private String maND;
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
    private String hoTen;

    // Constructors
    public NguoiDung() {
    }

    public NguoiDung(String maND, String tenDangNhap, String matKhau, String vaiTro, String hoTen) {
        this.maND = maND;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.hoTen = hoTen;
    }

    // Getters and Setters
    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
