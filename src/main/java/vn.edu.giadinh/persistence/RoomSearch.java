package vn.edu.giadinh.persistence;

import java.util.List;

public interface RoomSearch {
    List<RoomDTO> searchRoom(String keyword);
}
