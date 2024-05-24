package controller.Overall;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.dto.Overall.UserDto;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.Student.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Locale;

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
    private Label errorMessageLabel;



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
    private void handleSignUp(ActionEvent ae) throws SQLException, InvalidEmail, InvalidPassword {

        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        try {
            UserDto userSignUpData = new UserDto(
                    this.txtUsername.getText(),
                    this.txtEmail.getText(),
                    this.pwdPassword.getText(),
                    this.pwdConfirmPassword.getText()
            );
            System.out.println("Bout to try signup");
            boolean response = UserService.signUp(userSignUpData);
            System.out.println("Sign up was succesful");

            if(response){
                SESSION.setLoggedUser(UserService.getUserByEmail(this.txtEmail.getText()));
                System.out.println("Okej");
                Navigatior.navigateNewStage(Navigatior.STUDENT_DASHBOARD);
                Navigatior.closeStageAfterDelay(ae, Duration.millis(1));
            }
        } catch (SQLException | InvalidEmail | InvalidPassword e){
            System.out.println("Inside catch at SignUpController");
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);
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
