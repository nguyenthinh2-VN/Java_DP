package vn.edu.giadinh;

import vn.edu.giadinh.business.StudentListViewUC;
import vn.edu.giadinh.persistence.StudentListViewDAO;
import vn.edu.giadinh.presentation.StudentListViewUI;

import java.sql.SQLException;
import java.text.ParseException;

public class AppStudent {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StudentListViewUI listViewUI = new StudentListViewUI();
        StudentListViewUC listViewUseCase  = null;
        try {
            StudentListViewDAO listViewDAO = new StudentListViewDAO();
            listViewUseCase = new StudentListViewUC(listViewDAO,
                    listViewUI);
            listViewUseCase.execute();
            listViewUI.setVisible(true);
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