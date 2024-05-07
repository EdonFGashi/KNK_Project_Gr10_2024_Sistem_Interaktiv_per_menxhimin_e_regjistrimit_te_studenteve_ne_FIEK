module com.example.knk_project_gr10_2024 {
    requires javafx.controls;
    requires javafx.fxml;



    opens controller.Admin to javafx.fxml;
    opens controller.Overall to javafx.fxml;
    opens controller.Supervisor to javafx.fxml;

//    opens app to javafx.fxml;
    opens app to javafx.graphics;
    exports app;
    //   opens com.example.knk_project_gr10_2024 to javafx.fxml;

   //    exports com.example.knk_project_gr10_2024;

}