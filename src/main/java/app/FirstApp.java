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
//        try {
//            Scene scene = new Scene(loader.load());
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Exception");
//        }


      //Shko te Admini direkt

        stage.setMaximized(true);
      //  Navigatior.navigate(stag);
      Navigatior.navigate(stage,AdminPages.ADMIN_RIBBON);

        // Navigatior.navigate(stage,StudentPages.STUDENT_DASHBOARD);
      //  Navigatior.navigate(stage, Navigatior.DASHBOARD);

//    }


//    public static void main(String[] args) {
//
//        String salt = PasswordHasher.generateSalt();
//        System.out.println(salt);
//        System.out.println(PasswordHasher.generateSaltedHash("Isaku1234",salt));
//    }


    }
}
