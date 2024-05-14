package controller.Student;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StudentMenuController {

    @FXML
    private HBox hboxAcademic;

    @FXML
    private HBox hboxEducation;

    @FXML
    private HBox hboxaddPane;

    @FXML
    private HBox hbxPersinfo;

    @FXML
    private ImageView imgAcademic;

    @FXML
    private ImageView imgEducation;

    @FXML
    private ImageView imgPrsinfo;



    @FXML
    private Label txtAcademic;

    @FXML
    private Label txtEducation;

    @FXML
    private Label txtPrsinfo;

    @FXML
    private AnchorPane logoPane;
    private String personalinfoNavigate="";
    private String EducationExperienceNavigate="";
    private String AcademinInterestNavigate="";

    private final String activeSection = "-fx-border-color: white; -fx-border-radius: 15;-fx-border-width: 1.5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);";


    @FXML
    private void initialize() {

        this.personalinfoNavigate = Navigatior.ADMIN_PROFILE;
        this.EducationExperienceNavigate = Navigatior.EDUCATION;
        this.AcademinInterestNavigate = Navigatior.ACADEMIC;
        try {
            this.imgPrsinfo.setImage(new Image(new FileInputStream("Images/information.png")));
            this.imgEducation.setImage(new Image(new FileInputStream("Images/Education.png")));
            this.imgAcademic.setImage(new Image(new FileInputStream("Images/graduation.png")));
//            this.imgUp.setImage(new Image(new FileInputStream("Images/Up.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
        Navigatior.navigate(hboxaddPane,personalinfoNavigate);
        this.hbxPersinfo.setStyle(activeSection);

    }


    @FXML
    void handleOptionClick1(MouseEvent me) {
        this.resetActiveSection();
        this.hbxPersinfo.setStyle(activeSection);
        Navigatior.navigate(this.hboxaddPane,personalinfoNavigate);
    }

    @FXML
    void handleOptionClick2(MouseEvent event) {
        this.resetActiveSection();
        this.hboxEducation.setStyle(activeSection);
        Navigatior.navigate(this.hboxaddPane,EducationExperienceNavigate);
    }

    @FXML
    void handleOptionClick3(MouseEvent event) {
        this.resetActiveSection();
        this.hboxAcademic.setStyle(activeSection);
        Navigatior.navigate(this.hboxaddPane,AcademinInterestNavigate);
    }

    private void resetActiveSection(){
        this.hboxAcademic.setStyle("");
        this.hboxEducation.setStyle("");
        this.hbxPersinfo.setStyle("");
    }


    @FXML
    void handleLogoClicked(MouseEvent event) {

    }
}










