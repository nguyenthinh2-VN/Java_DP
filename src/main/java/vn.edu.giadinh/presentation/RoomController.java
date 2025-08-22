package vn.edu.giadinh.presentation;

import vn.edu.giadinh.buiness.TimKiemPhongUseCase;
import vn.edu.giadinh.buiness.TinhTongPhongUseCase;
import vn.edu.giadinh.persistence.RoomGateway;

/**
 * RoomController - Presentation layer controller
 * Manages the creation and coordination of UI components and business use cases
 * Follows MVP (Model-View-Presenter) pattern
 */
public class RoomController {
    private RoomListViewUI view;
    private TimKiemPhongUseCase timKiemPhongUseCase;
    private TinhTongPhongUseCase tinhTongPhongUseCase;

    /**
     * Constructor - Initialize controller with business layer dependencies
     * @param roomGateway Gateway for data access
     */
    public RoomController(RoomGateway roomGateway) {
        // Initialize use cases
        this.timKiemPhongUseCase = new TimKiemPhongUseCase(roomGateway);
        this.tinhTongPhongUseCase = new TinhTongPhongUseCase(roomGateway);
        
        // Initialize view with use cases
        this.view = new RoomListViewUI(timKiemPhongUseCase, tinhTongPhongUseCase);
    }

    /**
     * Get the view component
     * @return RoomListViewUI instance
     */
    public RoomListViewUI getView() {
        return view;
    }

    /**
     * Initialize and show the application
     */
    public void showApplication() {
        view.hienThiThongBao("Ứng dụng quản lý phòng học đã khởi động.");
        view.hienThiThongBao("Sử dụng chức năng tìm kiếm để tìm phòng theo từ khóa.");
        view.hienThiThongBao("Sử dụng chức năng thống kê để xem báo cáo tổng quan.");
    }
}
