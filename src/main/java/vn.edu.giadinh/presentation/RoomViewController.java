package vn.edu.giadinh.presentation;

import vn.edu.giadinh.buiness.RoomViewDTO;
import vn.edu.giadinh.buiness.TimKiemPhongUseCase;
import vn.edu.giadinh.buiness.TinhTongPhongUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RoomViewController - Presentation layer controller
 * Manages the creation and coordination of UI components and business use cases
 * Follows MVP (Model-View-Presenter) pattern
 */
/**
 * Controller trong kiến trúc MVP/MVVM.
 * Nhận yêu cầu từ View và gọi các UseCase tương ứng để xử lý.
 * Chuyển đổi DTO từ Business layer thành ViewItem cho Presentation layer.
 */
public class RoomViewController {
    private final RoomViewModel viewModel;
    private final TimKiemPhongUseCase timKiemPhongUseCase;
    private final TinhTongPhongUseCase tinhTongPhongUseCase;
    private final RoomDataFormatter formatter;

    // Constructor sử dụng Dependency Injection
    public RoomViewController(RoomViewModel viewModel, TimKiemPhongUseCase timKiemUseCase, TinhTongPhongUseCase tinhTongUseCase, RoomDataFormatter formatter) {
        this.viewModel = viewModel;
        this.timKiemPhongUseCase = timKiemUseCase;
        this.tinhTongPhongUseCase = tinhTongUseCase;
        this.formatter = formatter;
    }

    /**
     * Phương thức execute tổng quát để xử lý luồng dữ liệu theo pattern MVVM/MVP
     * - Controller chuyển đổi DTO thành ViewItem (convertToPresenter)
     * - Controller cập nhật ViewModel với dữ liệu mới
     * - Controller thông báo đến tất cả subscribers thông qua ViewModel
     */
    public void execute(List<RoomViewDTO> viewDTOs) {
        // 1. Controller chuyển đổi DTO thành ViewItem (convertToPresenter)
        List<RoomViewItem> viewItems = convertToPresenter(viewDTOs);
        
        // 2. Controller cập nhật ViewModel với dữ liệu mới
        this.viewModel.roomList = viewItems;
        
        // 3. Controller thông báo đến tất cả subscribers thông qua ViewModel
        this.viewModel.notifySubscribers();
    }

    /**
     * Xử lý logic tìm kiếm phòng.
     * @param keyword Từ khóa do người dùng nhập.
     */
    public void handleTimKiem(String keyword) {
        // 1. Gọi UseCase để lấy dữ liệu nghiệp vụ đã xử lý
        List<RoomViewDTO> viewDTOs = this.timKiemPhongUseCase.execute(keyword);

        // 2. Sử dụng phương thức execute để xử lý luồng dữ liệu
        execute(viewDTOs);
    }

    /**
     * Phương thức timKiem (deprecated - sử dụng handleTimKiem thay thế)
     * @deprecated Use handleTimKiem instead
     */
    @Deprecated
    public void timKiem(String keyword) {
        handleTimKiem(keyword);
    }

    /**
     * Xử lý logic tính tổng và trả về chuỗi kết quả đã định dạng.
     * @return Chuỗi thông báo kết quả thống kê.
     */
    public String handleThongKe() {
        // 1. Gọi UseCase để lấy kết quả
        Map<String, Integer> ketQua = this.tinhTongPhongUseCase.execute();

        // 2. Định dạng kết quả thành một chuỗi String
        StringBuilder message = new StringBuilder("Báo cáo số lượng phòng:\n");
        for (Map.Entry<String, Integer> entry : ketQua.entrySet()) {
            message.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return message.toString();
    }

    /**
     * Chuyển đổi từ RoomViewDTO (Business layer) sang RoomViewItem (Presentation layer)
     * Controller chịu trách nhiệm mapping dữ liệu, sử dụng Formatter để format
     */
    private List<RoomViewItem> convertToPresenter(List<RoomViewDTO> viewDTOs) {
        List<RoomViewItem> viewItems = new ArrayList<>();
        for (RoomViewDTO dto : viewDTOs) {
            // Sử dụng Formatter để format dữ liệu thành String cho UI hiển thị
            String loaiPhongStr = formatter.formatRoomType(dto.getType());
            String dienTichStr = formatter.formatArea(dto.getDienTich());
            String soBongDenStr = formatter.formatLightCount(dto.getSoBongDen());
            String chiTietStr = formatter.formatRoomDetails(dto);
            String ngayHoatDongStr = formatter.formatDate(dto.getNgayHD());

            viewItems.add(new RoomViewItem(
                    dto.getMaPhong(),
                    dto.getDayNha(),
                    loaiPhongStr,
                    dienTichStr,
                    soBongDenStr,
                    chiTietStr,
                    dto.getTrangThai(),
                    ngayHoatDongStr
            ));
        }
        return viewItems;
    }
}
