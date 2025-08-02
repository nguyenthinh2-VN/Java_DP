package vn.edu.giadinh.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.giadinh.persistence.StudentDTO;
import vn.edu.giadinh.persistence.StudentListViewDAO;
import vn.edu.giadinh.presentation.StudentViewItem;

public class StudentListViewUseCase {
	private StudentListViewDAO listViewDAO;
	
	
	public StudentListViewUseCase(StudentListViewDAO listViewDAO) {
		super();
		this.listViewDAO = listViewDAO;
	}
	
	public List<StudentViewDTO> execute() throws SQLException, ParseException {
		List<StudentDTO> listDTO = null;
		List<Student> students = null;
		listDTO = listViewDAO.getAll();
		
		//convert StudentDTO => Student
		students = this.convertToBusinessObjects(listDTO);
		//convert students business to StudentViewModel
		return this.convertToViewDTO(students);
	}
	
	private List<Student> convertToBusinessObjects(List<StudentDTO> dtos) {
		List<Student> students = new ArrayList<>();
		for (StudentDTO dto : dtos) {
			if ("Software".equalsIgnoreCase(dto.major)) {
				students.add(new SoftwareStudent(
					dto.id, dto.name, dto.birthDate,
					dto.javaScore != null ? dto.javaScore : 0,
					dto.htmlScore != null ? dto.htmlScore : 0,
					dto.cssScore != null ? dto.cssScore : 0
				));
			} else if ("Economics".equalsIgnoreCase(dto.major)) {
				students.add(new EconomicsStudent(
					dto.id, dto.name, dto.birthDate,
					dto.marketingScore != null ? dto.marketingScore : 0,
					dto.salesScore != null ? dto.salesScore : 0
				));
			}
		}
		return students;
	}

	private List<StudentViewDTO> convertToViewDTO(List<Student> students) {
		List<StudentViewDTO> listItem = new ArrayList<>();
		for (Student st : students) {
			StudentViewDTO dto = new StudentViewDTO();
			dto.id = st.getId();
			dto.name = st.getName();
			dto.birthDate = st.getBirthDate();
			dto.major = st.getMajor();
			dto.gpa = st.calculateGPA();
			dto.academicRank = st.classifyAcademic();
			listItem.add(dto);
		}
		return listItem;
	}
	
	
	

}
