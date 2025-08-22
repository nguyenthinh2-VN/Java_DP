package vn.edu.giadinh.presentation;

import java.sql.SQLException;

import vn.edu.giadinh.buiness.TimKiemPhongUseCase;
import vn.edu.giadinh.buiness.TinhTongPhongUseCase;
import vn.edu.giadinh.persistence.MockRoomDAO;
import vn.edu.giadinh.persistence.MySQLRoomDAO;
import vn.edu.giadinh.persistence.RoomGateway;

/**
 * RoomManagementApp - Main application class for the Room Management System
 */
public class RoomManagementApp {
    
    public static void main(String[] args) {
        // Khởi tạo các thành phần
        RoomListViewUI listViewUI = null; // Sẽ khởi tạo sau khi có use cases
        MySQLRoomDAO mySQLRoomDAO = null;
        MockRoomDAO mockRoomDAO;
        TimKiemPhongUseCase timKiemPhongUseCase = null;
        TinhTongPhongUseCase tinhTongPhongUseCase = null;
        RoomController controller = null;
        
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
            
            // Tạo UI với các use cases đã khởi tạo
            listViewUI = new RoomListViewUI(timKiemPhongUseCase, tinhTongPhongUseCase);
            
            // Hiển thị ứng dụng
            listViewUI.setVisible(true);
            listViewUI.hienThiThongBao("Ứng dụng quản lý phòng học đã khởi động.");
            listViewUI.hienThiThongBao("Sử dụng chức năng tìm kiếm để tìm phòng theo mã phòng.");
            listViewUI.hienThiThongBao("Sử dụng chức năng thống kê để xem báo cáo tổng quan.");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khởi động ứng dụng: " + e.getMessage());
        }
    }
}
