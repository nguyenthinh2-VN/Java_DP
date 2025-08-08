package vn.edu.giadinh.buiness;

import lombok.Getter;

import java.util.Date;

public class PhongMayTinh extends Room {
    private int soMayTinh;

    public PhongMayTinh(String maPhong, String dayNha, double dienTich, int soBongDen, Date ngayHD, int soMayTinh) {
        super(maPhong, dayNha, dienTich, soBongDen, ngayHD);
        this.soMayTinh = soMayTinh;
    }

    @Override
    public boolean datChuan() {
        // Điều kiện 1: Đủ ánh sáng (chung)
        boolean duAnhSang = (this.soBongDen * 10 >= this.dienTich);

        // Điều kiện 2: Mật độ máy tính phù hợp (riêng)
        boolean duMayTinh = (this.dienTich / this.soMayTinh) <= 1.5;

        return duAnhSang && duMayTinh;
    }

    public int getSoMayTinh() {
        return soMayTinh;
    }
}
