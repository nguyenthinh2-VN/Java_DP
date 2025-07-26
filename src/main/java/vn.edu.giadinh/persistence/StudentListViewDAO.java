package vn.edu.giadinh.persistence;

import vn.edu.giadinh.business.EconomicsStudent;
import vn.edu.giadinh.business.SoftwareStudent;
import vn.edu.giadinh.business.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentListViewDAO {

    private Connection conn;

    public StudentListViewDAO() throws ClassNotFoundException, SQLException {
        //tạo một kết nối đến database student_k17_1
        String username = "root";
        String password = "123456";
        String url = "jdbc:mysql://localhost:3306/java";
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected!!!");
    }

    public List<StudentDTO> getAll() throws SQLException, ParseException{
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Statement stmt = null;
        ResultSet rs = null;
        //xử lý
        String sql = "SELECT id, name, birthday, major, javaScore,"
                + "htmlScore, cssScore, marketingScore, salesScore "
                + "FROM student";
        //thực thi truy vấn
        //đối tượng Statement
        stmt = conn.createStatement();
        //dùng statement
        rs = stmt.executeQuery(sql);

        //chuyển từ rs => list
        while (rs.next()) {
            StudentDTO dto = new StudentDTO();
            dto.id = rs.getString("id");
            dto.name = rs.getString("name");
            dto.birthDate = fmt.parse(rs.getString("birthday"));
            dto.major = rs.getString("major");
            dto.javaScore = rs.getDouble("javaScore");
            dto.htmlScore = rs.getDouble("htmlScore");
            dto.cssScore = rs.getDouble("cssScore");

            dto.marketingScore = rs.getDouble("marketingScore");
            dto.salesScore = rs.getDouble("salesScore");

            list.add(dto);

        }

        conn.close();
        stmt.close();
        rs.close();

        return list;
    }

}