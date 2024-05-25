module com.example.knk_project_gr10_2024 {
    requires javafx.controls;
    requires javafx.fxml;



    opens controller.Admin to javafx.fxml;
    opens controller.Overall to javafx.fxml;
    opens controller.Supervisor to javafx.fxml;
    opens controller.StudentStatistics to javafx.fxml;
    opens controller.Student to javafx.fxml;



//    opens app to javafx.fxml;
    opens app to javafx.graphics;
    exports app;
    exports controller.Animations;
    opens controller.Animations to javafx.graphics;
    //   opens com.example.knk_project_gr10_2024 to javafx.fxml;

   //    exports com.example.knk_project_gr10_2024;
    requires java.sql;
    requires jfreechart;
    requires jcommon;

    requires itextpdf;

    opens model to javafx.base;
    opens controller.Help to javafx.fxml;
    opens model.dto.Admin to javafx.base;
    opens model.dto.Supervisor to javafx.base;
    opens model.dto.Student to javafx.base;

}