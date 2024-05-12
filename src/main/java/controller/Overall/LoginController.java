package controller.Overall;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.Animations.UpLogoAnimate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginController {
    @FXML
    private ImageView imgUpLogo;
    @FXML
    private AnchorPane logoPaneLoginPage;
    @FXML
    private ImageView loginLogo;
    @FXML
    private ImageView usernameLogo;
    @FXML
    private ImageView passwordLogo;
    private UpLogoAnimate logo = new UpLogoAnimate(120, "Regjistrimi për student", 7, 1);
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
        this.logoPaneLoginPage.getChildren().add(logo);
        this.logoPaneLoginPage.setTranslateX(-60);
        this.logoPaneLoginPage.setTranslateY(50);

    }

    @FXML
    void handleStartAnimation(MouseEvent event) {
        this.logo.startWithMouse();
    }


}
