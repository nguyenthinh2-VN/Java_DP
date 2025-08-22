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

            // Ánh xạ các thuộc tính
            viewDTO.setMaPhong(room.getMaPhong());
            viewDTO.setDayNha(room.getDayNha()); // Giả sử Room có getter này

            if (room instanceof PhongLyThuyet) {
                viewDTO.setType("Phòng Lý Thuyết");
            } else if (room instanceof PhongMayTinh) {
                viewDTO.setType("Phòng Máy Tính");
            } else if (room instanceof PhongThiNghiem) {
                viewDTO.setType("Phòng Thí Nghiệm");
            }

            viewDTO.setTrangThai(room.datChuan() ? "Đạt chuẩn" : "Không đạt chuẩn");

            viewDTOs.add(viewDTO);
        }
        return viewDTOs;
    }
}
