package vn.edu.giadinh.business;

import vn.edu.giadinh.persistence.StudentDTO;
import vn.edu.giadinh.persistence.StudentListViewDAO;
import vn.edu.giadinh.presentation.StudentListViewUI;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentListViewUC {
    private StudentListViewDAO listViewDAO;
    private StudentListViewUI listViewUI;


    public StudentListViewUC(StudentListViewDAO listViewDAO,
                                  StudentListViewUI listViewUI) {
        super();
        this.listViewDAO = listViewDAO;
        this.listViewUI = listViewUI;
    }

    public void execute() throws SQLException, ParseException {
        List<StudentDTO> listDTO = null;
        List<Student> students = null;
        listDTO = listViewDAO.getAll();
        StudentViewModel model = null;

        //convert StudentDTO => Student
        students = convertToBusinessObjects(listDTO);
        listViewUI.showList(model);

        model = convertToViewModel(students);
        listViewUI.showList(model);
    }

    //convert DTO => Student
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

    private StudentViewModel convertToViewModel(List<Student> students) {
        StudentViewModel model = new StudentViewModel();
        List<StudentViewItem> itemList = new ArrayList<StudentViewItem>();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

        int stt = 1;

        for (Student student : students) {
            StudentViewItem item = new StudentViewItem();
            item.stt = stt++;
            item.id = student.getId();
            item.name = student.getName();
            item.birthDate = fmt.format(student.getBirthDate());
            item.major = student.getMajor();
            item.gpa = String.format("%.2f", student.calculateGPA());
            item.academicRank = student.classifyAcademic();
        }
        model.studentList = itemList;

        return model;
    }

}