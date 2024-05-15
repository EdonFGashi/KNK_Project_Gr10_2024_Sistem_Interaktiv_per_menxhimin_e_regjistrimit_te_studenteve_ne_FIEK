package controller.Help;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SupervisorHelpController {
    @FXML
    private ImageView helpLogo;


    @FXML
    private void initialize(){
        try {
            this.helpLogo.setImage(new Image(new FileInputStream("Images/info.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
    }
}
