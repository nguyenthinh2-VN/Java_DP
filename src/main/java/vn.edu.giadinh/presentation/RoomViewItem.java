package vn.edu.giadinh.presentation;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * RoomViewItem - Simple data container for UI display
 * Contains only formatted String data for presentation
 * Controller handles conversion from raw business data to formatted strings
 */
@Getter
@AllArgsConstructor
public class RoomViewItem {
    public String maPhong;
    public String dayNha;
    public String loaiPhong;         // Formatted: "Phòng Lý Thuyết"
    public String dienTich;          // Formatted: "50.0 m²"
    public String soBongDen;         // Formatted: "6 bóng"
    public String chiTietPhong;      // Formatted: "Máy chiếu: Có"
    public String trangThai;         // Formatted: "Đạt chuẩn"
    public String ngayHoatDong;      // Formatted: "23/08/2025"
}
