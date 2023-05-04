module com.example.umbrellalabs {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.umbrellalabs to javafx.fxml;
    exports com.example.umbrellalabs;
}