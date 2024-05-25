package controller.Overall;

import app.Navigatior;
import controller.ComunicativeController;
import controller.SESSION;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.dto.Overall.LoginDto;
import controller.Animations.UpLogoAnimate;
import service.Admin.AdminService;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.Overall.LoginService;
import service.Student.UserService;
import service.Supervisor.SupervisorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static controller.SESSION.setLoggedUserEmail;

public class LoginController extends ComunicativeController {
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
    private Button loginBtn;
    @FXML
    private Label timerLabel;


    @FXML
    private ImageView langIcon;
    private Locale currentLocale = new Locale("en");


    private int hours;
    private int minutes;
    private int seconds;

    private final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts = 1;
    private int INITIAL_PENALTY_TIME = 5;

    private DashboardController dashboardController;



    @FXML
    private void initialize(){

        if (!SESSION.getLoginPenalty()){

            seconds = INITIAL_PENALTY_TIME;
        } else {
            seconds = SESSION.getLoginPenaltyTime();
            System.out.println("Seconds from SESSSON: " + seconds);
            loginBtn.setDisable(true);
            timerLabel.setVisible(true);
            startCountdown(SESSION.getLoginRemainingPenaltyTime());
            loginAttempts = SESSION.getLoginAttemptCount() + 1;
        }

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
            this.langIcon.setImage(new Image(new FileInputStream("Images/colored-language-icon.png")));

        } catch (FileNotFoundException fnfe){
            System.out.println("Image not found");
        }
//        this.logoPaneLoginPage.getChildren().add(logo);
//        this.logoPaneLoginPage.setTranslateX(-60);
//        this.logoPaneLoginPage.setTranslateY(50);

//        anchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                handleLogin(new ActionEvent());
//            }
//        });

    }

    @FXML
    private void handleChangeLanguage(MouseEvent ae){
        SESSION.switchLanguage();
        Navigatior.navigate(ae, Navigatior.LOGIN);
    }

    @FXML
    void handleStartAnimation(MouseEvent event) {
        this.logo.startWithMouse();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("Login attempt: " + loginAttempts);

        LoginDto loginDto = new LoginDto(
                this.userEmail.getText(),
                this.userPassword.getText()
        );

        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        try {
            System.out.println("Before login attempts;");
            loginAttempts();
            System.out.println("After login attempts;");

            if (Objects.equals(LoginService.login(loginDto), "admin")){

                //Admini mu ru ne session
                SESSION.setLoggedAdmin(AdminService.getAdminByEmail(this.userEmail.getText()));
                SESSION.setLoginPenalty(false);
                navigateToNewStage(event, Navigatior.ADMIN_RIBBON);
                this.dashboardController.close();


            } else if (Objects.equals(LoginService.login(loginDto), "supervisor")) {

                //Mbikqyresi mu ru ne session
                SESSION.setLoggedSupervisor(SupervisorService.getSupervisorByEmail(this.userEmail.getText()));
                SESSION.setLoginPenalty(false);
                navigateToNewStage(event, Navigatior.SUPERVISOR_MENU);
//                Navigatior.navigateNewStage(Navigatior.SUPERVISOR_MENU);
                System.out.println("Supervisor");
                loginAttempts = 0;
                this.dashboardController.close();


            } else if (Objects.equals(LoginService.login(loginDto), "student")) {

                SESSION.setLoggedUser(UserService.getUserByEmail(this.userEmail.getText()));
                setLoggedUserEmail(SESSION.getLoggedUser().getEmail());
                SESSION.setLoginPenalty(false);
                System.out.println("Id"+SESSION.getLoggedUser().getId());
//                Navigatior.navigateNewStage(Navigatior.STUDENT_DASHBOARD);
                navigateToNewStage(event, Navigatior.STUDENT_RIBBON);
                this.dashboardController.close();

            }
            else {
                System.out.println("Nuk jeni i kyqur!");
                System.out.println("---------------------");
                loginAttempts++;
                throw new InvalidEmail("Invalid credentials");

            }

        }catch (InvalidPassword | InvalidEmail e){
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);

        }
    }


    private void navigateToNewStage(ActionEvent event, String fxmlPath) {
        this.dashboardController.close();
        Stage stage = new Stage();
        stage.setMaximized(true);
        try {
            stage.getIcons().add(new Image(new FileInputStream("Images/upLogoNoRing.png")));
        }catch(IOException e){
            //
        }
        stage.setTitle("Fiek Registration Menagment System");
        Navigatior.navigate(stage, fxmlPath);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));
        System.out.println("Erdh deri para navigatorit");
    }

    @FXML
    void handleApliko(ActionEvent event) {
        Navigatior.navigateNewStage(Navigatior.STUDENT_SIGNUP);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));
    }

    void loginAttempts(){

        if (loginAttempts >= MAX_LOGIN_ATTEMPTS){
            SESSION.setLoginPenalty(true);
            SESSION.setLoginAttemptCount(loginAttempts);
            loginBtn.setDisable(true);
            timerLabel.setVisible(true);
            System.out.println("Seconds w/o attempts: " + seconds);
            seconds *= loginAttempts;
            SESSION.setLoginPenaltyTime(seconds);
            System.out.println("Login attempts: " + loginAttempts);
            System.out.println("Time here in seconds: " + seconds);
            startCountdown(seconds);
        }
    }

    private void startCountdown(int seconds) {

        LocalTime end = LocalTime.now()

                .plusSeconds(seconds);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                java.time.Duration remaining = java.time.Duration.between(LocalTime.now(), end);
                if (remaining.isPositive()) {
                    timerLabel.setText(format(remaining));

                    int remainingSeconds = (int) remaining.getSeconds();
//                    System.out.println("Here is the remaining time: ");
//                    System.out.println(remainingSeconds);
                    SESSION.setLoginRemainingPenaltyTime(remainingSeconds);
                } else {
                    timerLabel.setText(format(java.time.Duration.ZERO));
                    System.out.println("HEREEEEEEEEEEEEEE");
                    loginBtn.setDisable(false);
                    stop();
                }
            }

            private String format(java.time.Duration remaining) {
                return String.format("%02d:%02d:%02d",
                        remaining.toHoursPart(),
                        remaining.toMinutesPart(),
                        remaining.toSecondsPart()
                );
            }
        };


        timer.start();
    }


    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
