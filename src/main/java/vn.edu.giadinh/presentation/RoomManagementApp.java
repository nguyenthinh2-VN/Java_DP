package vn.edu.giadinh.presentation;

import vn.edu.giadinh.buiness.TimKiemPhongUseCase;
import vn.edu.giadinh.buiness.TinhTongPhongUseCase;
import vn.edu.giadinh.persistence.MockRoomDAO;
import vn.edu.giadinh.persistence.MySQLRoomDAO;

/**
 * RoomManagementApp - Main application class for the Room Management System
 * Now follows proper MVVM/MVP architecture from the diagram
 */
public class RoomManagementApp {
    
    public static void main(String[] args) {
        // Khởi tạo các thành phần theo đúng kiến trúc
        RoomListViewUI listViewUI = null;
        MySQLRoomDAO mySQLRoomDAO = null;
        MockRoomDAO mockRoomDAO;
        TimKiemPhongUseCase timKiemPhongUseCase = null;
        TinhTongPhongUseCase tinhTongPhongUseCase = null;
        
        // Controller và ViewModel (theo sơ đồ kiến trúc)
        RoomViewController controller = null;
        RoomViewModel viewModel = null;
        RoomDataFormatter dataFormatter = null;
        
        try {
            // Thử khởi tạo MySQL DAO trước, nếu lỗi thì dùng Mock DAO
            try {
                mySQLRoomDAO = new MySQLRoomDAO();
                timKiemPhongUseCase = new TimKiemPhongUseCase(mySQLRoomDAO);
                tinhTongPhongUseCase = new TinhTongPhongUseCase(mySQLRoomDAO);
                System.out.println("Đang sử dụng MySQL Database");
            } catch (Exception e) {
                // Nếu MySQL lỗi, dùng Mock DAO
                mockRoomDAO = new MockRoomDAO();
                timKiemPhongUseCase = new TimKiemPhongUseCase(mockRoomDAO);
                tinhTongPhongUseCase = new TinhTongPhongUseCase(mockRoomDAO);
                System.out.println("Đang sử dụng Mock Database (Dữ liệu mẫu)");
            }
            
            // Tạo ViewModel và Formatter theo đúng kiến trúc MVVM/MVP
            viewModel = new RoomViewModel();
            dataFormatter = new RoomDataFormatter();
            
            // Tạo Controller với dependency injection (Presentation layer)
            controller = new RoomViewController(viewModel, timKiemPhongUseCase, tinhTongPhongUseCase, dataFormatter);
            
            // Tạo UI với Controller và ViewModel (không phụ thuộc trực tiếp vào Use Cases)
            listViewUI = new RoomListViewUI(controller, viewModel);
            

            
            // Hiển thị ứng dụng
            listViewUI.setVisible(true);
            listViewUI.hienThiThongBao("Ứng dụng quản lý phòng học đã khởi động.");
            listViewUI.hienThiThongBao("Sử dụng chức năng tìm kiếm để tìm phòng theo mã phòng.");
            listViewUI.hienThiThongBao("Sử dụng chức năng thống kê để xem báo cáo tổng quan.");
            listViewUI.hienThiThongBao("Kiến trúc MVVM/MVP đã được áp dụng đúng theo sơ đồ.");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khởi tạo ứng dụng: " + e.getMessage());
        }
    }
}
