package vn.edu.giadinh.buiness;
import lombok.Getter;

import java.util.Date;

@Getter
public class PhongLyThuyet extends Room {
    private boolean mayChieu;

    public PhongLyThuyet(String maPhong, String dayNha, double dienTich, int soBongDen, Date ngayHD, boolean mayChieu) {
        super(maPhong, dayNha, dienTich, soBongDen, ngayHD); // Bỏ tenPhong
        this.mayChieu = mayChieu;
    }

    @Override
    public boolean datChuan() {
        // Điều kiện 1: Đủ ánh sáng (chung)
        boolean duAnhSang = (this.soBongDen * 10 >= this.dienTich);

        // Điều kiện 2: Có máy chiếu (riêng)
        return duAnhSang && this.mayChieu;
    }

    // Getter riêng
    public boolean hasMayChieu() {
        return mayChieu;
    }
}
