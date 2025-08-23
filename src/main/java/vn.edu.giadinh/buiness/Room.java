package vn.edu.giadinh.buiness;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public abstract class Room {
    protected String maPhong;
    protected String dayNha;
    protected double dienTich;
    protected int soBongDen;
    protected Date ngayHD;

    public Room(String maPhong, String dayNha, double dienTich, int soBongDen, Date ngayHD) {
        this.maPhong = maPhong;
        this.dayNha = dayNha;
        this.dienTich = dienTich;
        this.soBongDen = soBongDen;
        this.ngayHD = ngayHD;
    }

    public abstract boolean datChuan();

    // Getters
    public String getMaPhong() {
        return maPhong;
    }
    
    public String getDayNha() {
        return dayNha;
    }
    
    public Date getNgayHD() {
        return ngayHD;
    }

    public double getDienTich() {
        return dienTich;
    }

    public int getSoBongDen() {
        return soBongDen;
    }
}
