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
    public void start(Stage stage){
//       FXMLLoader loader = new FXMLLoader(
//         this.getClass().getResource("admin-studentMenu-showAndEditStudent.fxml")
//
//       );
//       try{
//           Scene scene = new Scene(loader.load());
//           stage.setScene(scene);
//           stage.show();
//
//       }catch(Exception e){
//           System.out.println("Exception");
//       }
        stage.setMaximized(true);
         Navigatior.navigate(stage,AdminPages.ADMIN_RIBBON);

    }




}
