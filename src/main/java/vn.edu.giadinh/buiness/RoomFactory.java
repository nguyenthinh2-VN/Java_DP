package vn.edu.giadinh.buiness;

import vn.edu.giadinh.persistence.RoomDTO;

public class RoomFactory {
    /**
     * Phương thức static để tạo một đối tượng Room từ một DTO.
     * @param dto Đối tượng Data Transfer Object chứa dữ liệu thô.
     * @return Một đối tượng con của Room (PhongLyThuyet, PhongMayTinh, etc.)
     */
    public static Room createRoom(RoomDTO dto) {
        if (dto == null || dto.getType() == null) {
            return null;
        }

        switch (dto.getType()) {
            case "LT":
                return new PhongLyThuyet(
                        dto.getMaPhong(),
                        dto.getDayNha(),
                        dto.getDienTich(),
                        dto.getSoBongDen(),
                        dto.getNgayHD(),
                        dto.isMayChieu()
                );
            case "MT":
                return new PhongMayTinh(
                        dto.getMaPhong(),
                        dto.getDayNha(),
                        dto.getDienTich(),
                        dto.getSoBongDen(),
                        dto.getNgayHD(),
                        dto.getSoMayTinh()
                );
            case "TN":
                return new PhongThiNghiem(
                        dto.getMaPhong(),
                        dto.getDayNha(),
                        dto.getDienTich(),
                        dto.getSoBongDen(),
                        dto.getNgayHD(),
                        dto.getChuyenNganh(),
                        dto.getSucChua(),
                        dto.isCoBonRua()
                );
            default:
                // Nếu type không hợp lệ, có thể ném ra một exception
                throw new IllegalArgumentException("Loại phòng không hợp lệ: " + dto.getType());
        }
    }
}
