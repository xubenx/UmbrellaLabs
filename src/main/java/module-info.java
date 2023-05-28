module com.example.umbrellalabs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    exports gui to javafx.graphics;
    opens gui to javafx.fxml;
    opens model to javafx.base;
    opens com.example.umbrellalabs to javafx.fxml;
    exports com.example.umbrellalabs;
}