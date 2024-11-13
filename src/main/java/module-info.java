module com.example.figurescarandashi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.figurescarandashi to javafx.fxml;
    exports com.example.figurescarandashi;
}