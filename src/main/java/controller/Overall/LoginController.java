package controller.Overall;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.dto.Overall.LoginDto;
import service.Animations.UpLogoAnimate;
import service.Overall.LoginService;

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
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;

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
//        this.logoPaneLoginPage.getChildren().add(logo);
//        this.logoPaneLoginPage.setTranslateX(-60);
//        this.logoPaneLoginPage.setTranslateY(50);

    }

    @FXML
    void handleStartAnimation(MouseEvent event) {
        this.logo.startWithMouse();
    }

    @FXML
    private void handleLogin(ActionEvent event) {

        LoginDto loginDto = new LoginDto(
                this.userEmail.getText(),
                this.userPassword.getText()
        );

        try {
            LoginService.login(loginDto);
            System.out.println("Email: " + this.userEmail.getText());
            System.out.println("Password: " + this.userPassword.getText());
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
