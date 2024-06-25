module librarySystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.main to javafx.graphics;

    exports com.main;
}
