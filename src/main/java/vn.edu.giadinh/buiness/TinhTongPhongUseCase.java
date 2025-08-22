package vn.edu.giadinh.buiness;

import vn.edu.giadinh.persistence.RoomDTO;
import vn.edu.giadinh.persistence.RoomGateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TinhTongPhongUseCase {
    private final RoomGateway roomGateway;

    public TinhTongPhongUseCase(RoomGateway roomGateway) {
        this.roomGateway = roomGateway;
    }

    public Map<String, Integer> execute() {
        List<RoomDTO> allRooms = roomGateway.getAll();
        Map<String, Integer> statistics = new HashMap<>();
        
        // Initialize counters
        statistics.put("Phòng Lý Thuyết", 0);
        statistics.put("Phòng Máy Tính", 0);
        statistics.put("Phòng Thí Nghiệm", 0);
        statistics.put("Tổng số phòng", 0);
        statistics.put("Phòng đạt chuẩn", 0);
        statistics.put("Phòng không đạt chuẩn", 0);
        
        // Count rooms by type and standard compliance
        for (RoomDTO dto : allRooms) {
            // Count by type
            switch (dto.getType()) {
                case "LT":
                    statistics.put("Phòng Lý Thuyết", statistics.get("Phòng Lý Thuyết") + 1);
                    break;
                case "MT":
                    statistics.put("Phòng Máy Tính", statistics.get("Phòng Máy Tính") + 1);
                    break;
                case "TN":
                    statistics.put("Phòng Thí Nghiệm", statistics.get("Phòng Thí Nghiệm") + 1);
                    break;
            }
            
            // Count total rooms
            statistics.put("Tổng số phòng", statistics.get("Tổng số phòng") + 1);
            
            // Check if room meets standards
            Room room = RoomFactory.createRoom(dto);
            if (room != null) {
                if (room.datChuan()) {
                    statistics.put("Phòng đạt chuẩn", statistics.get("Phòng đạt chuẩn") + 1);
                } else {
                    statistics.put("Phòng không đạt chuẩn", statistics.get("Phòng không đạt chuẩn") + 1);
                }
            }
        }
        
        return statistics;
    }
}
