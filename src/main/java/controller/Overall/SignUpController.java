package controller.Overall;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.dto.Overall.UserDto;
import service.Student.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private ImageView imgIcon;

    @FXML
    private PasswordField pwdConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField pwdPassword;




    @FXML
    private void handleSignUp(ActionEvent ae) throws SQLException {
        UserDto userSignUpData = new UserDto(
                this.txtUsername.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        boolean response = UserService.signUp(userSignUpData);

        if(response){
            SESSION.setLoggedUser(UserService.getUserByEmail(this.txtEmail.getText()));
          System.out.println("Okej");
            Navigatior.navigateNewStage(Navigatior.STUDENT_DASHBOARD);
            Navigatior.closeStageAfterDelay(ae, Duration.millis(1));
        }

    }

    @FXML
    public void handleBack(ActionEvent ae){
        Navigatior.navigateNewStage(Navigatior.LOGIN);
        Navigatior.closeStageAfterDelay(ae, Duration.millis(1));
    }



    @FXML
    public void initialize() {
        try {
            this.imgIcon.setImage(new Image(new FileInputStream("Images/UP.png")));

        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

    }
}
