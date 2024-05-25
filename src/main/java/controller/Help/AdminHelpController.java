package controller.Help;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;

public class AdminHelpController {

    @FXML
    private ImageView helpLogo;
    @FXML
    private ImageView helpProfileImg;

    private Locale currentLocale = new Locale("en");


    @FXML
    private void initialize(){
        try {
            this.helpLogo.setImage(new Image(new FileInputStream("Images/info.png")));
            this.helpProfileImg.setImage(new Image(new FileInputStream("Images/admin-ribbon.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
    }
}
