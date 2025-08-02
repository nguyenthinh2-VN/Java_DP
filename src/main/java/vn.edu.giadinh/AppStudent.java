package vn.edu.giadinh;

import java.sql.SQLException;
import java.text.ParseException;

import vn.edu.giadinh.business.StudentListViewUseCase;
import vn.edu.giadinh.presentation.StudentViewModel;
import vn.edu.giadinh.persistence.StudentListViewDAO;
import vn.edu.giadinh.presentation.StudentListViewController;
import vn.edu.giadinh.presentation.StudentListViewUI;

public class AppStudent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentListViewUI view = new StudentListViewUI();
		StudentListViewController controller = null;
		StudentViewModel model = new StudentViewModel();
		view.setViewModel(model);
		StudentListViewUseCase listViewUseCase  = null;
		try {
			StudentListViewDAO listViewDAO = new StudentListViewDAO();
			listViewUseCase = new StudentListViewUseCase(listViewDAO);
			controller = new StudentListViewController(model);
			controller.setListViewUseCase(listViewUseCase);
			
			controller.execute();
			view.setVisible(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
