package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;






//Klas e Testimeve (MOS E MIRRNI SERIOZISHT)




public class FirstApp extends Application {

    @Override
    public void start(Stage stage){

        FXMLLoader ribbon = new FXMLLoader(
                this.getClass().getResource("ribon-test.fxml")
        );

        FXMLLoader manu = new FXMLLoader(
                this.getClass().getResource("menu.fxml")
        );



        try{

           VBox mainPane = new VBox();
           mainPane.getChildren().add(ribbon.load());

        //   AdminMenuNavigator.navigateOnMenu(mainPane,stage,"menu Test.fxml");


        }catch(IOException e){
            System.out.println("Gabim");
        }

        stage.show();

    }
}
