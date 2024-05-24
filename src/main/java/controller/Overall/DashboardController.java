package controller.Overall;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.dto.Admin.comunicateControllerdto;

import javafx.scene.text.Text;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DashboardController {

    @FXML
    private ImageView mainImage;
    @FXML
    private Button buttoni_qasja;
    @FXML
    private ImageView infoimg;
    @FXML
    private ImageView changeLanguageIcon;

//    private ResourceBundle bundle;
    private Locale currentLocale = new Locale("en");

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
    private void handleChangeLanguage(ActionEvent ae){
        if (currentLocale.getLanguage().equals("en")) {
            currentLocale = new Locale("sq");
        } else {
            currentLocale = new Locale("en");
        }
        //loadLanguage(currentLocale.getLanguage());
    }

    @FXML
    public void handleQasja(ActionEvent event) {

        comunicateControllerdto data = Navigatior.loadAndReturnController(Navigatior.LOGIN);

        Parent loginPane = data.getParent();

        LoginController controller = (LoginController) data.getController();

        controller.setDashboardController(this);

        Navigatior.navigateNewStageByPane(loginPane);

    }

    @FXML
    public void buttoni_qasja_hover(MouseEvent event) {
        buttoni_qasja.setStyle("-fx-opacity: 0.9;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }

    @FXML
    public void buttoni_qasja_exit(MouseEvent event) {

        buttoni_qasja.setStyle("-fx-opacity: 0.5;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }
}
