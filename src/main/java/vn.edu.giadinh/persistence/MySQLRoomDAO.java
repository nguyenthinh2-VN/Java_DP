package vn.edu.giadinh.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

// Lớp này triển khai interface tổng hợp, do đó nó có cả hai phương thức.
public class MySQLRoomDAO implements RoomGateway {

    private Connection conn;

    // Constructor để thiết lập kết nối
    public MySQLRoomDAO() {
        try {
            // Thay thế bằng thông tin kết nối thực tế của bạn
            String url = "jdbc:mysql://localhost:3306/quanlyphonghoc";
            String user = "root";
            String password = "123456"; // Điền mật khẩu của bạn nếu có
            this.conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            // Trong ứng dụng thực tế, nên xử lý exception này một cách phù hợp hơn.
            // Ví dụ: ném ra một exception tùy chỉnh để các tầng trên xử lý.
            throw new RuntimeException("Cannot connect to the database", e);
        }
    }

    /**
     * Triển khai phương thức lấy tất cả phòng học từ database.
     * @return Danh sách các PhongHocDTO.
     */
    @Override
    public List<RoomDTO> getAll() {
        List<RoomDTO> danhSachPhong = new ArrayList<>();
        String sql = "SELECT * FROM phong_hoc";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                danhSachPhong.add(mapResultSetToDTO(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc ghi log lỗi
        }
        return danhSachPhong;
    }

    /**
     * Triển khai phương thức tìm kiếm phòng học trong database.
     * @param keyword Từ khóa tìm kiếm.
     * @return Danh sách các PhongHocDTO phù hợp.
     */
    @Override
    public List<RoomDTO> searchRoom(String keyword) {
        List<RoomDTO> danhSachPhong = new ArrayList<>();
        String sql = "SELECT * FROM phong_hoc WHERE maPhong LIKE ? OR dayNha LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    danhSachPhong.add(mapResultSetToDTO(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc ghi log lỗi
        }
        return danhSachPhong;
    }

    /**
     * Phương thức tiện ích private để ánh xạ dữ liệu từ ResultSet sang PhongHocDTO.
     * @param rs ResultSet chứa dữ liệu của một phòng học.
     * @return Đối tượng PhongHocDTO đã được điền dữ liệu.
     * @throws SQLException
     */
    private RoomDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        RoomDTO dto = new RoomDTO();

        dto.setMaPhong(rs.getString("maPhong"));
        dto.setDayNha(rs.getString("dayNha"));
        dto.setDienTich(rs.getDouble("dienTich"));
        dto.setSoBongDen(rs.getInt("soBongDen"));
        dto.setNgayHoatDong(rs.getDate("ngayHoatDong"));
        dto.setType(rs.getString("type"));

        // Lấy các thuộc tính riêng, kiểm tra null để tránh lỗi
        if (rs.getObject("soMayTinh") != null) {
            dto.setSoMayTinh(rs.getInt("soMayTinh"));
        }
        if (rs.getObject("coMayChieu") != null) {
            dto.setCoMayChieu(rs.getBoolean("coMayChieu"));
        }
        dto.setChuyenNganh(rs.getString("chuyenNganh"));
        if (rs.getObject("sucChua") != null) {
            dto.setSucChua(rs.getInt("sucChua"));
        }
        if (rs.getObject("coBonRua") != null) {
            dto.setCoBonRua(rs.getBoolean("coBonRua"));
        }

        return dto;
    }

    public static void main(String[] args) {
        new MySQLRoomDAO();
    }
}