package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;






//Klas e Testimeve (MOS E MIRRNI SERIOZISHT)




public class FirstApp extends Application {

    @Override
    public void start(Stage stage){

        FXMLLoader ribbon = new FXMLLoader(
                this.getClass().getResource("overall-error404.fxml")
        );


        try{
            Scene scene = new Scene(ribbon.load());
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }



    }
}
