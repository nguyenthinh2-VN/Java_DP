package vn.edu.giadinh.buiness;

import vn.edu.giadinh.persistence.RoomDTO;
import vn.edu.giadinh.persistence.RoomGateway;

import java.util.ArrayList;
import java.util.List;

public class TimKiemPhongUseCase {
    private final RoomGateway roomGateway;

    public TimKiemPhongUseCase(RoomGateway roomGateway) {
        this.roomGateway = roomGateway;
    }

    //Nhận vào 1 chuỗi keyword, trả về danh sách các RoomViewDTO
    public List<RoomViewDTO> execute(String keyword) {
        List<RoomDTO> persistenceDTOs = roomGateway.searchRoom(keyword);
        List<Room> businessObjects = convertToBusinessObjects(persistenceDTOs);
        return convertToViewDTOs(businessObjects);
    }

    //Chuyển đổi List<RoomDTO> sang List<Room>
    private List<Room> convertToBusinessObjects(List<RoomDTO> dtos) {
        List<Room> rooms = new ArrayList<>();
        for (RoomDTO dto : dtos) {
            rooms.add(RoomFactory.createRoom(dto));
        }
        return rooms;
    }


    //Chuyển đổi List<Room> sang List<RoomViewDTO> và chuyển lên tầng Presentation
    private List<RoomViewDTO> convertToViewDTOs(List<Room> rooms) {
        List<RoomViewDTO> viewDTOs = new ArrayList<>();
        for (Room room : rooms) {
            RoomViewDTO viewDTO = new RoomViewDTO();

            // Ánh xạ các thuộc tính chung
            viewDTO.setMaPhong(room.getMaPhong());
            viewDTO.setDayNha(room.getDayNha());
            viewDTO.setNgayHD(room.getNgayHD());
            viewDTO.setDienTich(room.getDienTich());
            viewDTO.setSoBongDen(room.getSoBongDen());

            // Ánh xạ theo loại phòng và thuộc tính đặc biệt
            if (room instanceof PhongLyThuyet) {
                PhongLyThuyet phongLT = (PhongLyThuyet) room;
                viewDTO.setType("LT");
                viewDTO.setMayChieu(phongLT.isMayChieu());
            } else if (room instanceof PhongMayTinh) {
                PhongMayTinh phongMT = (PhongMayTinh) room;
                viewDTO.setType("MT");
                viewDTO.setSoMayTinh(phongMT.getSoMayTinh());
            } else if (room instanceof PhongThiNghiem) {
                PhongThiNghiem phongTN = (PhongThiNghiem) room;
                viewDTO.setType("TN");
                viewDTO.setChuyenNganh(phongTN.getChuyenNganh());
                viewDTO.setSucChua(phongTN.getSucChua());
                viewDTO.setCoBonRua(phongTN.isCoBonRua());
            }

            viewDTO.setTrangThai(room.datChuan() ? "Đạt chuẩn" : "Không đạt chuẩn");

            viewDTOs.add(viewDTO);
        }
        return viewDTOs;
    }
}
