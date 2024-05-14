package controller.Overall;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.dto.Overall.LoginDto;
import controller.Animations.UpLogoAnimate;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
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
    private Label errorMessageLabel;

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

        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        try {

            if (LoginService.login(loginDto)){

                System.out.println("Jeni i kyqur si mbikqyres!");
//                Stage stage = new Stage();
//                Navigatior.navigate(stage, Navigatior.SUPERVISOR_RIBBON);
                Navigatior.closeStageAfterDelay(event, Duration.millis(1));
            } else {
                System.out.println("Nuk jeni i kyqur!");
                System.out.println("---------------------");
            }

        }catch (InvalidPassword | InvalidEmail e){
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);

        }

    }


}
