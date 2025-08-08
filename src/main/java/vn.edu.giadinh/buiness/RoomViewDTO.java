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
}
