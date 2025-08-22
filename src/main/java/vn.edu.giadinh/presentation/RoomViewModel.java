package vn.edu.giadinh.presentation;

import vn.edu.giadinh.buiness.RoomViewDTO;

import java.util.Date;

/**
 * RoomViewModel - Presentation layer view model
 * Wraps RoomViewDTO with additional presentation-specific logic
 * Follows MVVM (Model-View-ViewModel) pattern and Observer pattern
 */
public class RoomViewModel extends Publisher {
    private RoomViewDTO roomViewDTO;

    public RoomViewModel(RoomViewDTO roomViewDTO) {
        this.roomViewDTO = roomViewDTO;
    }

    /**
     * Get formatted room code for display
     * @return Formatted room code
     */
    public String getFormattedMaPhong() {
        return roomViewDTO.getMaPhong() != null ? roomViewDTO.getMaPhong().toUpperCase() : "N/A";
    }

    /**
     * Get formatted building name for display
     * @return Formatted building name
     */
    public String getFormattedDayNha() {
        return roomViewDTO.getDayNha() != null ? "Dãy " + roomViewDTO.getDayNha() : "Không xác định";
    }

    /**
     * Get room type with icon
     * @return Room type with appropriate icon
     */
    public String getFormattedType() {
        String type = roomViewDTO.getType();
        if (type == null) return "Không xác định";
        
        switch (type) {
            case "Phòng Lý Thuyết":
                return "📚 " + type;
            case "Phòng Máy Tính":
                return "💻 " + type;
            case "Phòng Thí Nghiệm":
                return "🔬 " + type;
            default:
                return "❓ " + type;
        }
    }

    /**
     * Get status with color indicator
     * @return Status with color indicator
     */
    public String getFormattedTrangThai() {
        String status = roomViewDTO.getTrangThai();
        if (status == null) return "Không xác định";
        
        if (status.equals("Đạt chuẩn")) {
            return "✅ " + status;
        } else {
            return "❌ " + status;
        }
    }

    /**
     * Get formatted date for display
     * @return Formatted date string
     */
    public String getFormattedNgayHD() {
        Date ngayHD = roomViewDTO.getNgayHD();
        if (ngayHD == null) return "Không có thông tin";
        
        return java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT).format(ngayHD);
    }

    /**
     * Check if room meets standards
     * @return true if room meets standards
     */
    public boolean isDatChuan() {
        return "Đạt chuẩn".equals(roomViewDTO.getTrangThai());
    }

    /**
     * Get CSS class for status styling
     * @return CSS class name
     */
    public String getStatusCssClass() {
        return isDatChuan() ? "status-success" : "status-warning";
    }

    // Delegate methods to access original DTO properties
    public String getMaPhong() {
        return roomViewDTO.getMaPhong();
    }

    public String getTenPhong() {
        return roomViewDTO.getTenPhong();
    }

    public String getDayNha() {
        return roomViewDTO.getDayNha();
    }

    public Date getNgayHD() {
        return roomViewDTO.getNgayHD();
    }

    public String getType() {
        return roomViewDTO.getType();
    }

    public String getTrangThai() {
        return roomViewDTO.getTrangThai();
    }

    public RoomViewDTO getRoomViewDTO() {
        return roomViewDTO;
    }

    /**
     * Set new room data and notify subscribers
     * @param roomViewDTO New room data
     */
    public void setRoomViewDTO(RoomViewDTO roomViewDTO) {
        this.roomViewDTO = roomViewDTO;
        // Notify all subscribers when data changes
        notifySubscribers();
    }
}
