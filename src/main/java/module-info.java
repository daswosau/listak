module com.example.listak {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.listak to javafx.fxml;
    exports com.example.listak;
}