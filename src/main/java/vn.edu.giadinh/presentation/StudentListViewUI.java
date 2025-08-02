package vn.edu.giadinh.presentation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;

public class StudentListViewUI extends JFrame implements Subscriber {
    private JTextField txtSearch;
    private JButton btnAdd;
    private JTable table;
    private DefaultTableModel model;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private StudentViewModel viewModel;

    public StudentListViewUI() {
        super("Danh sách sinh viên");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);

        // Panel top
        JPanel top = new JPanel(new BorderLayout(5,5));
        txtSearch = new JTextField();
        btnAdd = new JButton("Thêm");
        top.add(txtSearch, BorderLayout.CENTER);
        top.add(btnAdd, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // Table
        String[] cols = {"STT","ID","Họ & tên","Ngày sinh","Chuyên ngành","Điểm TB","Xếp loại"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

       

       
    }

    public void setViewModel(StudentViewModel viewModel) {
		this.viewModel = viewModel;
		
		//đăng ký subscriber với publisher
		viewModel.addSubscriber(this);
	}
    
    private void showList(StudentViewModel studentViewModel) {
        model.setRowCount(0);
        for (StudentViewItem item : studentViewModel.studentList) {
            Object[] row = {
                item.stt,
                item.id,
                item.name,
                item.birthDate,
                item.major,
                item.gpa,
                item.academicRank
            };
            model.addRow(row);
        }
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.showList(viewModel);
		
	}

   
}