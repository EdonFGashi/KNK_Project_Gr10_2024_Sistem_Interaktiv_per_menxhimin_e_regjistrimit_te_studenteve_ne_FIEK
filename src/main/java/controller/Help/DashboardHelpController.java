package controller.Help;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;

public class DashboardHelpController {
    @FXML
    private ImageView helpLogo;

    private Locale currentLocale = new Locale("en");
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
    private void initialize(){
        try {
            this.helpLogo.setImage(new Image(new FileInputStream("Images/info.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
    }
}
