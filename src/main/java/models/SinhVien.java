package models;

public class SinhVien {
    // Thông tin cá nhân
    private String hoTen;
    private int namSinh;
    private String gioiTinh;
    private String soDienThoai;
    private String diaChiTamTru;

    // Thông tin sinh viên
    private String nganhHoc;
    private int namNhapHoc;
    private String maLop;
    private String mssv;
    private String password; // thêm password

    // Thông tin liên lạc khẩn cấp
    private String khanCapHoTen;
    private String khanCapSoDienThoai;
    private String khanCapDiaChi;

    public SinhVien() {}

    public SinhVien(String maSV, String hoTen, int namSinh, String gioiTinh, String soDienThoai,
                     String diaChiTamTru, String nganhHoc, int namNhapHoc, String maLop,
                     String khanCapHoTen, String khanCapSoDienThoai, String khanCapDiaChi, String password) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.diaChiTamTru = diaChiTamTru;
        this.nganhHoc = nganhHoc;
        this.namNhapHoc = namNhapHoc;
        this.maLop = maLop;
        this.mssv = maSV;
        this.khanCapHoTen = khanCapHoTen;
        this.khanCapSoDienThoai = khanCapSoDienThoai;
        this.khanCapDiaChi = khanCapDiaChi;
        this.password = password;
    }

    // --- Getter & Setter ---
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public int getNamSinh() { return namSinh; }
    public void setNamSinh(int namSinh) { this.namSinh = namSinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getDiaChiTamTru() { return diaChiTamTru; }
    public void setDiaChiTamTru(String diaChiTamTru) { this.diaChiTamTru = diaChiTamTru; }

    public String getNganhHoc() { return nganhHoc; }
    public void setNganhHoc(String nganhHoc) { this.nganhHoc = nganhHoc; }

    public int getNamNhapHoc() { return namNhapHoc; }
    public void setNamNhapHoc(int namNhapHoc) { this.namNhapHoc = namNhapHoc; }

    public String getMaLop() { return maLop; }
    public void setMaLop(String maLop) { this.maLop = maLop; }

    public String getMSSV() { return this.mssv; }
    public void setMSSV(String maSV) { this.mssv = maSV; }

    public String getKhanCapHoTen() { return khanCapHoTen; }
    public void setKhanCapHoTen(String khanCapHoTen) { this.khanCapHoTen = khanCapHoTen; }

    public String getKhanCapSoDienThoai() { return khanCapSoDienThoai; }
    public void setKhanCapSoDienThoai(String khanCapSoDienThoai) { this.khanCapSoDienThoai = khanCapSoDienThoai; }

    public String getKhanCapDiaChi() { return khanCapDiaChi; }
    public void setKhanCapDiaChi(String khanCapDiaChi) { this.khanCapDiaChi = khanCapDiaChi; }
}
