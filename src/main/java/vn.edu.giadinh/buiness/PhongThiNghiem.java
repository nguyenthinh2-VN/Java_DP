package vn.edu.giadinh.buiness;

import lombok.Getter;

import java.util.Date;

public class PhongThiNghiem extends Room{
    private String chuyenNganh;
    private int sucChua;
    private boolean coBonRua;

    public PhongThiNghiem(String maPhong, String dayNha, double dienTich, int soBongDen, Date ngayHD, String chuyenNganh, int sucChua, boolean coBonRua) {
        super(maPhong, dayNha, dienTich, soBongDen, ngayHD);
        this.chuyenNganh = chuyenNganh;
        this.sucChua = sucChua;
        this.coBonRua = coBonRua;
    }

    @Override
    public boolean datChuan() {
        // Điều kiện 1: Đủ ánh sáng (chung)
        boolean duAnhSang = (this.soBongDen * 10 >= this.dienTich);
        
        // Điều kiện 2: Có bồn rửa (riêng cho phòng thí nghiệm)
        return duAnhSang && this.coBonRua;
    }

    // Getters riêng
    public boolean hasCoBonRua() {
        return coBonRua;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public int getSucChua() {
        return sucChua;
    }

    public boolean isCoBonRua() {
        return coBonRua;
    }
}
