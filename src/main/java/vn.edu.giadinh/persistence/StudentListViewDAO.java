package vn.edu.giadinh.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentListViewDAO {

	private Connection conn;

	public StudentListViewDAO() throws ClassNotFoundException, SQLException {
		// tạo một kết nối đến database student_k17_1
		// tải driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/java";
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connected!!!");
	}

	public List<StudentDTO> getAll() throws SQLException, ParseException {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Statement stmt = null;
		ResultSet rs = null;
		// xử lý
		String sql = "SELECT id, name, birthday, major, javaScore," + "htmlScore, cssScore, marketingScore, salesScore "
				+ "FROM student";
		// thực thi truy vấn
		// đối tượng Statement
		stmt = conn.createStatement();
		// dùng statement
		rs = stmt.executeQuery(sql);

		// chuyển từ rs => list
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
		stmt.close();
		rs.close();
		conn.close();
		System.out.println("Found: " + list.size());
		return list;
	}

	public static void main(String[] args) {
		try {
			new StudentListViewDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
