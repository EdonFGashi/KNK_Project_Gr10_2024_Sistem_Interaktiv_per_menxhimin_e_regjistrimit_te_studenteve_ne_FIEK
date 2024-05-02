module com.example.knk_project_gr10_2024 {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.graphics;

    opens com.example.knk_project_gr10_2024 to javafx.fxml;
    exports com.example.knk_project_gr10_2024;
}