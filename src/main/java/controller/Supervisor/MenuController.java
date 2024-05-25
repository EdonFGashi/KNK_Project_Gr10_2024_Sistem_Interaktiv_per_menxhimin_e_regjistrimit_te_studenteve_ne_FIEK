package controller.Supervisor;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuController {

    @FXML
    private Label txtMenuName;
    @FXML
    private Label txtOption1;
    @FXML
    private Label txtOption2;
    @FXML
    private Label txtOption3;
    @FXML
    private ImageView imgMain;
    @FXML
    private ImageView imgOption1;
    @FXML
    private ImageView imgOption2;
    @FXML
    private ImageView imgOption3;
    @FXML
    private HBox hboxOption1;
    @FXML
    private HBox hboxOption2;
    @FXML
    private HBox hboxOption3;
    @FXML
    private HBox addPane;
    @FXML
    private ImageView imgInfoIcon;
    @FXML
    private Button buttonDummy;

    private String option1Navigate = "";
    private String option2Navigate = "";

    private final String activeSection = "-fx-border-color: white; -fx-border-radius: 15;-fx-border-width: 1.5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);";

    @FXML
    private void initialize() {
        Navigatior.navigate(this.addPane, Navigatior.SUPERVISOR_GRADE_POINTS);
        this.txtMenuName.setText("Supervisor Menu");

        this.txtOption1.setText("Test Grading");
        this.txtOption2.setText("Profile");
        this.txtOption3.setText("Sign Out");
        this.option1Navigate = Navigatior.SUPERVISOR_GRADE_POINTS;
        this.option2Navigate = Navigatior.SUPERVISOR_PROFILE;
        try {
            this.imgMain.setImage(new Image(new FileInputStream("Images/supervisorMenu.png")));
            this.imgOption1.setImage(new Image(new FileInputStream("Images/showAndEdit.png")));
            this.imgOption2.setImage(new Image(new FileInputStream("Images/white-user.png")));
            this.imgOption3.setImage(new Image(new FileInputStream("Images/sign-out-icon.png")));
            this.imgInfoIcon.setImage(new Image(new FileInputStream("Images/info-icon.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

        this.resetActiveSection();
        this.hboxOption1.setStyle(activeSection);

        // E bon display ni text kur tbohet hover logo e infos
        Tooltip tooltip = new Tooltip("Info");

        Tooltip.install(imgInfoIcon, tooltip);

    }

    @FXML
    private void handleInfoIconClicked(MouseEvent me){
        Navigatior.navigateNewStage(Navigatior.HELP_SUPERVISOR);
    }

    @FXML
    private void handleOption1Click(MouseEvent me){
        this.resetActiveSection();
        this.hboxOption1.setStyle(activeSection);
        Navigatior.navigate(this.addPane,option1Navigate);
    }
    @FXML
    private void handleOption2Click(MouseEvent me){
        this.resetActiveSection();
        this.hboxOption2.setStyle(activeSection);
        Navigatior.navigate(this.addPane,option2Navigate);
    }
    @FXML
    private void handleOption3Click(MouseEvent me){
       this.buttonDummy.fire();
    }


    @FXML
    void handleChangeLanguage(MouseEvent event) {

    }

    private void resetActiveSection(){
        this.hboxOption1.setStyle("");
        this.hboxOption2.setStyle("");
        this.hboxOption3.setStyle("");
    }

    @FXML
    private void handleLogOut(ActionEvent ae){
        Navigatior.navigateNewStage(Navigatior.DASHBOARD);
        SESSION.setLoggedSupervisor(null);
        Navigatior.closeStageAfterDelay(ae, Duration.millis(10));
    }
}
