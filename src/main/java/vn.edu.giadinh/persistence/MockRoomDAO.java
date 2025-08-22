package vn.edu.giadinh.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Mock implementation of RoomGateway for testing purposes
 * This class provides sample data without requiring a database connection
 */
public class MockRoomDAO implements RoomGateway {

    private List<RoomDTO> sampleRooms;

    public MockRoomDAO() {
        initializeSampleData();
    }

    /**
     * Initialize sample room data for testing
     */
    private void initializeSampleData() {
        sampleRooms = new ArrayList<>();
        
        // Sample Phòng Lý Thuyết
        RoomDTO room1 = new RoomDTO();
        room1.setMaPhong("LT001");
        room1.setDayNha("A");
        room1.setDienTich(50.0);
        room1.setSoBongDen(6);
        room1.setNgayHD(new Date());
        room1.setType("LT");
        room1.setMayChieu(true);
        sampleRooms.add(room1);

        RoomDTO room2 = new RoomDTO();
        room2.setMaPhong("LT002");
        room2.setDayNha("A");
        room2.setDienTich(60.0);
        room2.setSoBongDen(5);
        room2.setNgayHD(new Date());
        room2.setType("LT");
        room2.setMayChieu(false);
        sampleRooms.add(room2);

        // Sample Phòng Máy Tính
        RoomDTO room3 = new RoomDTO();
        room3.setMaPhong("MT001");
        room3.setDayNha("B");
        room3.setDienTich(45.0);
        room3.setSoBongDen(5);
        room3.setNgayHD(new Date());
        room3.setType("MT");
        room3.setSoMayTinh(30);
        sampleRooms.add(room3);

        RoomDTO room4 = new RoomDTO();
        room4.setMaPhong("MT002");
        room4.setDayNha("B");
        room4.setDienTich(40.0);
        room4.setSoBongDen(6);
        room4.setNgayHD(new Date());
        room4.setType("MT");
        room4.setSoMayTinh(20);
        sampleRooms.add(room4);

        // Sample Phòng Thí Nghiệm
        RoomDTO room5 = new RoomDTO();
        room5.setMaPhong("TN001");
        room5.setDayNha("C");
        room5.setDienTich(55.0);
        room5.setSoBongDen(6);
        room5.setNgayHD(new Date());
        room5.setType("TN");
        room5.setChuyenNganh("Hóa học");
        room5.setSucChua(25);
        room5.setCoBonRua(true);
        sampleRooms.add(room5);

        RoomDTO room6 = new RoomDTO();
        room6.setMaPhong("TN002");
        room6.setDayNha("C");
        room6.setDienTich(50.0);
        room6.setSoBongDen(4);
        room6.setNgayHD(new Date());
        room6.setType("TN");
        room6.setChuyenNganh("Vật lý");
        room6.setSucChua(20);
        room6.setCoBonRua(false);
        sampleRooms.add(room6);
    }

    @Override
    public List<RoomDTO> getAll() {
        return new ArrayList<>(sampleRooms);
    }

    @Override
    public List<RoomDTO> searchRoom(String keyword) {
        List<RoomDTO> results = new ArrayList<>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAll();
        }
        
        String searchKeyword = keyword.toLowerCase().trim();
        
        for (RoomDTO room : sampleRooms) {
            // Tìm kiếm theo mã phòng (chính xác hoặc chứa từ khóa)
            if (room.getMaPhong() != null && 
                room.getMaPhong().toLowerCase().contains(searchKeyword)) {
                results.add(room);
                continue;
            }
            
            // Tìm kiếm theo dãy nhà
            if (room.getDayNha() != null && 
                room.getDayNha().toLowerCase().contains(searchKeyword)) {
                results.add(room);
                continue;
            }
            
            // Tìm kiếm theo loại phòng
            if (room.getType() != null) {
                String typeText = "";
                switch (room.getType()) {
                    case "LT":
                        typeText = "lý thuyết";
                        break;
                    case "MT":
                        typeText = "máy tính";
                        break;
                    case "TN":
                        typeText = "thí nghiệm";
                        break;
                }
                if (typeText.contains(searchKeyword)) {
                    results.add(room);
                    continue;
                }
            }
            
            // Tìm kiếm theo chuyên ngành (cho phòng thí nghiệm)
            if (room.getChuyenNganh() != null && 
                room.getChuyenNganh().toLowerCase().contains(searchKeyword)) {
                results.add(room);
            }
        }
        
        return results;
    }
}
