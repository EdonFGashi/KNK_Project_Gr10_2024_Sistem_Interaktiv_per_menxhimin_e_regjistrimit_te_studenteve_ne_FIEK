package controller.Overall;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DashboardController {

    @FXML
    private ImageView mainImage;
    @FXML
    private Button buttoni_qasja;
    @FXML
    private ImageView infoimg;
    @FXML
    private ImageView changeLanguageIcon;

    @FXML
    private void initialize(){
        try {
            this.mainImage.setImage(new Image(new FileInputStream("Images/fieku.png")));
            this.infoimg.setImage(new Image(new FileInputStream("Images/info-icon.png")));
            this.changeLanguageIcon.setImage(new Image(new FileInputStream("Images/language-icon.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleQasja(ActionEvent event) {
        Navigatior.navigateNewStage(Navigatior.LOGIN);
    }

    @FXML
    public void buttoni_qasja_hover(ActionEvent event) {
        buttoni_qasja.setStyle("-fx-opacity: 0.9;");
    }
}
