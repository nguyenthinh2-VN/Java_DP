package vn.edu.giadinh.presentation;

import vn.edu.giadinh.business.StudentViewItem;
import vn.edu.giadinh.business.StudentViewModel;

public class ListViewController {
    private StudentViewModel model;

    public void execute() {
        // TODO Auto-generated method stub
        // Yêu cầu model cập nhật DL mới
        StudentViewItem item1 = new StudentViewItem();
        item1.stt = 1;
        item1.id = "1";
        item1.name = "Nguyen Van A";
        model.studentList.add(item1);
    }
}
