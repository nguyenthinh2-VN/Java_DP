module com.example.projectjavadp_k17 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;

    opens com.example.projectjavadp_k17 to javafx.fxml;
    exports com.example.projectjavadp_k17;

}