module com.example.ksushalab310 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ksushalab310 to javafx.fxml;
    exports com.example.ksushalab310;
}