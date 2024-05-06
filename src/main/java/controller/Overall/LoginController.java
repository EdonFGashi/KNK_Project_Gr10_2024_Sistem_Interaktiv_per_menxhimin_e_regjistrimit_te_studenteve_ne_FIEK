package controller.Overall;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginController {
    @FXML
    private ImageView imgUpLogo;
    @FXML
    private ImageView loginLogo;
    @FXML
    private ImageView usernameLogo;
    @FXML
    private ImageView passwordLogo;

    @FXML
    private void initialize(){
        try {
            this.imgUpLogo.setImage(new Image(new FileInputStream("Images/upLogoNoRing.png")));
            this.loginLogo.setImage(new Image(new FileInputStream("Images/blue-users.png")));
            this.usernameLogo.setImage(new Image(new FileInputStream("Images/blue-user.png")));
            this.passwordLogo.setImage(new Image(new FileInputStream("Images/blue-key.png")));

        } catch (FileNotFoundException fnfe){
            System.out.println("Image not found");
        }
    }


}
