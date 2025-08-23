package vn.edu.giadinh.presentation;

import vn.edu.giadinh.buiness.RoomViewDTO;

/**
 * RoomDataFormatter - Utility class for formatting room data
 * Handles all data formatting logic for presentation layer
 * Follows Single Responsibility Principle - only responsible for data formatting
 */
public class RoomDataFormatter {

    /**
     * Format room type code to display string
     */
    public String formatRoomType(String type) {
        switch (type) {
            case "LT":
                return "Phòng Lý Thuyết";
            case "MT":
                return "Phòng Máy Tính";
            case "TN":
                return "Phòng Thí Nghiệm";
            default:
                return type;
        }
    }

    /**
     * Format area value to display string
     */
    public String formatArea(double dienTich) {
        return String.format("%.1f m²", dienTich);
    }

    /**
     * Format light count to display string
     */
    public String formatLightCount(int soBongDen) {
        return soBongDen + " bóng";
    }

    /**
     * Format detailed room information based on room type
     */
    public String formatRoomDetails(RoomViewDTO dto) {
        StringBuilder chiTiet = new StringBuilder();

        switch (dto.getType()) {
            case "LT":
                chiTiet.append("Máy chiếu: ").append(dto.isMayChieu() ? "Có" : "Không");
                break;
            case "MT":
                chiTiet.append("Số máy tính: ").append(dto.getSoMayTinh());
                break;
            case "TN":
                chiTiet.append("Chuyên ngành: ").append(dto.getChuyenNganh())
                       .append(", Sức chứa: ").append(dto.getSucChua())
                       .append(", Bồn rửa: ").append(dto.isCoBonRua() ? "Có" : "Không");
                break;
            default:
                chiTiet.append("N/A");
        }

        return chiTiet.toString();
    }

    /**
     * Format Date to display string
     */
    public String formatDate(java.util.Date date) {
        if (date == null) return "";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
