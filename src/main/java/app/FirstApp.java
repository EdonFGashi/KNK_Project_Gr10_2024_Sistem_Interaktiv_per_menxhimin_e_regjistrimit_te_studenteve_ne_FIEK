package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.DBConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


//Klas e Testimeve (MOS E MIRRNI SERIOZISHT)




public class FirstApp extends Application {

    @Override
    public void start(Stage stage){

       UpLogoAnimate object = new UpLogoAnimate();



        Scene scene = new Scene(object);
        stage.setScene(scene);
        stage.show();

    }


}
