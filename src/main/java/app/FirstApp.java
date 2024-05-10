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


 //Navigatior.navigateNewStage("teat.fxml");


        FXMLLoader loader = new FXMLLoader(
                this.getClass().getResource("teat.fxml")
        );

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
           e.printStackTrace();
        }


    }




}
