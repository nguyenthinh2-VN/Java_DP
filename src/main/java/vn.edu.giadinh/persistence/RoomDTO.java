package vn.edu.giadinh.persistence;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RoomDTO {
    // Thuộc tính chung
    private String maPhong;
    private String dayNha;
    private double dienTich;
    private int soBongDen;
    private Date ngayHD; 
    private String type;

    // Thuộc tính riêng của phòng máy tính
    private int soMayTinh;

    // Thuộc tính riêng của phòng lý thuyết
    private boolean mayChieu; 

    // Thuộc tính riêng của phòng thí nghiệm
    private String chuyenNganh;
    private int sucChua;
    private boolean coBonRua;

    public RoomDTO() {
    }

    // Convenience getters for backward compatibility
    public Date getNgayHoatDong() {
        return ngayHD;
    }
    
    public void setNgayHoatDong(Date ngayHoatDong) {
        this.ngayHD = ngayHoatDong;
    }
    
    public boolean isCoMayChieu() {
        return mayChieu;
    }
    
    public void setCoMayChieu(boolean coMayChieu) {
        this.mayChieu = coMayChieu;
    }
    
    public boolean isCoBonRua() {
        return coBonRua;
    }
}
