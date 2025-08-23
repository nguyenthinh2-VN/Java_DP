package vn.edu.giadinh.presentation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * RoomViewModel - Presentation layer view model
 * Follows MVVM (Model-View-ViewModel) pattern and Observer pattern
 * - Holds List<RoomViewItem> data from Controller
 * - Formats ViewItem data to strings for UI display
 * - Notifies subscribers when data changes
 */
@Getter
public class RoomViewModel extends Publisher {
    public List<RoomViewItem> roomList = new ArrayList<>();

    /**
     * Legacy method for backward compatibility
     * @deprecated Use roomList directly
     */
    @Deprecated
    public List<RoomViewItem> getListRoomView() {
        return roomList;
    }
}
