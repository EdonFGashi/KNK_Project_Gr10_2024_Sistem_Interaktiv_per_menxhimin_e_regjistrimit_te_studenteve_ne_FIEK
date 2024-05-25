package controller.Student;

import app.Navigatior;
import controller.Animations.UpLogoAnimate;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StudentRibbonController {

    @FXML
    private AnchorPane addPane;

    @FXML
    private AnchorPane anchorLogo;

    @FXML
    private ImageView imgGoBack;

    @FXML
    private ImageView imgInfoIcon;

    @FXML
    private ImageView imgProfileIcon;

    @FXML
    private ImageView imgTranslate;

    @FXML
    private VBox vboxMainPane;


    private UpLogoAnimate logo = new UpLogoAnimate(50, "FIEK Management", 5, 1);
    @FXML
    private void initialize(){
        Navigatior.navigate(addPane,Navigatior.STUDENT_DASHBOARD);
        SESSION.setCurrentPage("Navigatior.STUDENT_DASHBOARD");
     try {
        if(SESSION.isToggleShqip()){
            this.imgTranslate.setImage(new Image(new FileInputStream("src/main/resources/Images/language-en.png")));
        }
        else {
            this.imgTranslate.setImage(new Image(new FileInputStream("src/main/resources/Images/language-sq.png")));
        }
    } catch (
    FileNotFoundException e) {
        throw new RuntimeException(e);
    }
        try {
            this.imgProfileIcon.setImage(new Image(new FileInputStream("Images/profileIcon.png")));
            this.imgInfoIcon.setImage(new Image(new FileInputStream("Images/info-icon.png")));

        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
        this.anchorLogo.getChildren().add(logo);
        this.anchorLogo.setTranslateY(25);

        // E bon display ni text kur tbohet hover logo e infos
        Tooltip tooltip = new Tooltip("Info");

        Tooltip.install(imgInfoIcon, tooltip);
    }
    @FXML
    void handleBackClick(MouseEvent event) {

    }

    @FXML
    void handleChangeLanguage(ActionEvent event) {

    }

    @FXML
    private void handleGoToProfile(ActionEvent ae){
        Navigatior.navigate(addPane,Navigatior.STUDENT_PROFILE);
        SESSION.setCurrentPage("Navigatior.STUDENT_PROFILE");
    }



    @FXML
    void handleInfoIconClicked(MouseEvent event) {
        Navigatior.navigateNewStage(Navigatior.HELP_STUDENT);
        SESSION.setCurrentPage("Navigatior.HELP_STUDENT");
    }

    @FXML
    private void handleLogoClicked(MouseEvent me){
        Navigatior.navigate(addPane,Navigatior.STUDENT_DASHBOARD);
        SESSION.setCurrentPage("Navigatior.STUDENT_DASHBOARD");
    }
    @FXML
    private void handleStartAnimation(MouseEvent me){
        this.logo.startWithMouse();
    }



    @FXML
    private void handleProfileClick(MouseEvent me){
        Navigatior.navigate(addPane,Navigatior.STUDENT_PROFILE);
        SESSION.setCurrentPage("Navigatior.STUDENT_PROFILE");
    }


    @FXML
    private void handleSignOut(ActionEvent ae){
       this.buttonDummy.fire();
    }
    @FXML
    private void handleChangeLanguage(MouseEvent me){
        SESSION.switchLanguage();
        Navigatior.navigate(me,Navigatior.STUDENT_RIBBON);
        SESSION.setCurrentPage("Navigatior.STUDENT_RIBBON");
    }

    @FXML
    private Button buttonDummy;

    @FXML
    private void handleLogOut(ActionEvent ae){
        Navigatior.navigateNewStage(Navigatior.DASHBOARD);
        SESSION.setCurrentPage("Navigatior.DASHBOARD");
        SESSION.setLoggedAdmin(null);
        Navigatior.closeStageAfterDelay(ae, Duration.millis(10));
    }

}
