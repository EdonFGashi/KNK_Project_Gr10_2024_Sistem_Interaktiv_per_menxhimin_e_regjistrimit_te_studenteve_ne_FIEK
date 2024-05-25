package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.PasswordHasher;

import java.io.IOException;


//Klas e Testimeve (MOS E MIRRNI SERIOZISHT)




public class FirstApp extends Application {

    @Override
    public void start(Stage stage) {
//        FXMLLoader loader = new FXMLLoader(
//                this.getClass().getResource("admin-inbol-njoftimPane.fxml")
//
//        );


        stage.setMaximized(true);
      //  Navigatior.navigate(stag);

       Navigatior.navigate(stage,AdminPages.ADMIN_RIBBON);

     //    Navigatior.navigate(stage, StudentPages.STUDENT_DASHBOARD);

//   Navigatior.navigate(stage, Navigatior.DASHBOARD);




    }
}
