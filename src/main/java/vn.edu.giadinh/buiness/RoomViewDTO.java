package vn.edu.giadinh.buiness;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class RoomViewDTO {
    public String maPhong;
    public String tenPhong;
    public String dayNha;
    public Date ngayHD;
    public String type; // Ví dụ: "Phòng Lý Thuyết"
    public String trangThai; // Ví dụ: "Đạt chuẩn"
    
    // Thuộc tính chung
    public double dienTich;
    public int soBongDen;
    
    // Thuộc tính riêng của phòng máy tính
    public int soMayTinh;
    
    // Thuộc tính riêng của phòng lý thuyết
    public boolean mayChieu;
    
    // Thuộc tính riêng của phòng thí nghiệm
    public String chuyenNganh;
    public int sucChua;
    public boolean coBonRua;
}
