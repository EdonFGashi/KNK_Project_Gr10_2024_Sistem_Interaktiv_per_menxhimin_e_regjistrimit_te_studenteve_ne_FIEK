package controller.Supervisor;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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

    private String option1Navigate = "";
    private String option2Navigate = "";
    private String option3Navigate = "";
    private String option4Navigate = "";

    private final String activeSection = "-fx-border-color: white; -fx-border-radius: 15;-fx-border-width: 1.5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);";

    @FXML
    private void initialize() {
        Navigatior.navigate(this.addPane, Navigatior.SUPERVISOR_GRADE_POINTS);
        this.txtMenuName.setText("Supervisor");

        this.txtOption1.setText("Test Grading");
        this.txtOption2.setText("Inbox");
        this.txtOption3.setText("Statistics");
        this.option1Navigate = Navigatior.SUPERVISOR_GRADE_POINTS;
        this.option2Navigate = Navigatior.SUPERVISOR_INBOX;
        this.option3Navigate = Navigatior.SUPERVISOR_STATISTICS;
        this.option4Navigate = Navigatior.SUPERVISOR_PROFILE;
        try {
            this.imgMain.setImage(new Image(new FileInputStream("Images/studentMenu.png")));
            this.imgOption1.setImage(new Image(new FileInputStream("Images/showAndEdit.png")));
            this.imgOption2.setImage(new Image(new FileInputStream("Images/application.png")));
            this.imgOption3.setImage(new Image(new FileInputStream("Images/statistics.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

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
        this.resetActiveSection();
        this.hboxOption3.setStyle(activeSection);
        Navigatior.navigate(this.addPane,option3Navigate);
    }
    @FXML
    private void handleOption4Click(MouseEvent me){
        this.resetActiveSection();
        this.hboxOption4.setStyle(activeSection);
        Navigatior.navigate(this.addPane,option4Navigate);
    }

    @FXML
    private void handleHoverOption1(MouseEvent me){

    }
    @FXML
    private void handleHoverOption2(MouseEvent me){

    }
    @FXML
    private void handleHoverOption3(MouseEvent me){

    }
    @FXML
    private void handleHoverOption4(MouseEvent me){

    }

    private void resetActiveSection(){
        this.hboxOption1.setStyle("");
        this.hboxOption2.setStyle("");
        this.hboxOption3.setStyle("");
        this.hboxOption4.setStyle("");
    }
}
