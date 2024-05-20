package controller.Overall;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Admin;
import model.dto.Overall.LoginDto;
import controller.Animations.UpLogoAnimate;
import org.jfree.util.Log;
import service.Admin.AdminService;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.Overall.LoginService;
import service.Student.StudentService;
import service.Student.UserService;
import service.Supervisor.SupervisorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

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
    private TextField userPasswordVisible;
    @FXML
    private CheckBox showHidePasswordCheckbox;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private ImageView eyeIcon;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize(){
        // E vendos checboxin mi sy
        anchorPane.getChildren().remove(showHidePasswordCheckbox);
        anchorPane.getChildren().add(showHidePasswordCheckbox);

        userPasswordVisible.setVisible(false);
        userPasswordVisible.setManaged(false);

        userPassword.visibleProperty().bind(showHidePasswordCheckbox.selectedProperty().not());
        userPassword.managedProperty().bind(showHidePasswordCheckbox.selectedProperty().not());

        userPasswordVisible.visibleProperty().bind(showHidePasswordCheckbox.selectedProperty());
        userPasswordVisible.managedProperty().bind(showHidePasswordCheckbox.selectedProperty());

        // Lidh userPassword me userPasswordVisible, ku nese ndryshon njona ndryshon edhe tjetra
        userPassword.textProperty().bindBidirectional(userPasswordVisible.textProperty());
        try {
            this.imgUpLogo.setImage(new Image(new FileInputStream("Images/upLogoNoRing.png")));
            this.loginLogo.setImage(new Image(new FileInputStream("Images/blue-users.png")));
            this.usernameLogo.setImage(new Image(new FileInputStream("Images/blue-user.png")));
            this.passwordLogo.setImage(new Image(new FileInputStream("Images/blue-key.png")));
            this.eyeIcon.setImage(new Image(new FileInputStream("Images/eye-icon.png")));

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

            if (Objects.equals(LoginService.login(loginDto), "admin")){

                Stage stage = new Stage();
                stage.setMaximized(true);
                Navigatior.navigate(stage, Navigatior.ADMIN_RIBBON);
                //Admini mu ru ne session
                SESSION.setLoggedAdmin(AdminService.getAdminByEmail(this.userEmail.getText()));
                System.out.println(SESSION.getLoggedAdmin().getFirstName());
                Navigatior.closeStageAfterDelay(event, Duration.millis(1));

            } else if (Objects.equals(LoginService.login(loginDto), "supervisor")) {

                Stage stage = new Stage();
                stage.setMaximized(true);
                Navigatior.navigate(stage, Navigatior.SUPERVISOR_MENU);
                //Mbikqyresi mu ru ne session
                SESSION.setLoggedSupervisor(SupervisorService.getSupervisorByEmail(this.userEmail.getText()));
                System.out.println(SESSION.getLoggedSupervisor().getFirstName());
                Navigatior.closeStageAfterDelay(event, Duration.millis(1));
            } else if (Objects.equals(LoginService.login(loginDto), "student")) {

                Stage stage = new Stage();
                stage.setMaximized(true);
                Navigatior.navigate(stage, Navigatior.STUDENT_DASHBOARD);
                SESSION.setLoggedUser(UserService.getUserByEmail(this.userEmail.getText()));
                System.out.println(SESSION.getLoggedUser().getFirstName());
                Navigatior.closeStageAfterDelay(event, Duration.millis(1));
            }
            else {
                System.out.println("Nuk jeni i kyqur!");
                System.out.println("---------------------");
                throw new InvalidEmail("Invalid credentials");
            }

        }catch (InvalidPassword | InvalidEmail e){
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);

        }

    }
    @FXML
    void handleApliko(ActionEvent event) {
        Navigatior.navigateNewStage(Navigatior.STUDENT_SIGNUP);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));
    }


}
