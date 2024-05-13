package controller.Overall;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DashboardController {

    @FXML
    private ImageView mainImage;

    @FXML
    private void initialize(){
        try {
            this.mainImage.setImage(new Image(new FileInputStream("Images/rektorati.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleQasja(ActionEvent ae){

        Navigatior.navigateNewStage(Navigatior.LOGIN);

    }
}
