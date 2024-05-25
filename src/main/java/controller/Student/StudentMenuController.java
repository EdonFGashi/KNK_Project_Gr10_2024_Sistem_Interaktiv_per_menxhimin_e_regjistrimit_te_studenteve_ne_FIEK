package controller.Student;

import app.Navigatior;
import controller.SESSION;
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
    private ImageView imgUp;


    @FXML
    private Label txtAcademic;

    @FXML
    private Label txtEducation;

    @FXML
    private Label txtPrsinfo;

    @FXML
    private ImageView imgTranslate;

    @FXML
    private AnchorPane logoPane;
    private String personalinfoNavigate="";
    private String EducationExperienceNavigate="";
    private String AcademinInterestNavigate="";

    private final String activeSection = "-fx-border-color: white; -fx-border-radius: 15;-fx-border-width: 1.5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);";



    @FXML
    private void initialize() {
        String menu = SESSION.getDeptLevel();

        try {
            if(SESSION.isToggleShqip()){
                this.imgTranslate.setImage(new Image(new FileInputStream("src/main/resources/Images/language-en.png")));
            }
            else {
                this.imgTranslate.setImage(new Image(new FileInputStream("src/main/resources/Images/language-sq.png")));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        switch (menu) {
            case "BSC" -> {
                this.txtPrsinfo.setText("Personal Information");
                this.txtEducation.setText("Educational Experience");
                this.txtAcademic.setText("Academic Interest");
                this.personalinfoNavigate = Navigatior.PERSONAL_INFO;
                this.EducationExperienceNavigate = Navigatior.EDUCATION;
                this.AcademinInterestNavigate = Navigatior.ACADEMIC;
                try {
                    this.imgPrsinfo.setImage(new Image(new FileInputStream("Images/information.png")));
                    this.imgEducation.setImage(new Image(new FileInputStream("Images/Education.png")));
                    this.imgAcademic.setImage(new Image(new FileInputStream("Images/graduation.png")));
                    this.imgUp.setImage(new Image(new FileInputStream("Images/Up.png")));
                } catch (FileNotFoundException e) {
                    System.out.println("Image not found");
                }
            }
            case "MSC" -> {
                this.txtPrsinfo.setText("Personal Information");
                this.txtEducation.setText("Educational Experience");
                this.txtAcademic.setText("");
                this.personalinfoNavigate = Navigatior.PERSONAL_INFO;
                this.EducationExperienceNavigate = Navigatior.EDUCATION_MASTER;

                try {
                    this.imgPrsinfo.setImage(new Image(new FileInputStream("Images/information.png")));
                    this.imgEducation.setImage(new Image(new FileInputStream("Images/Education.png")));
                    this.imgUp.setImage(new Image(new FileInputStream("Images/Up.png")));
                } catch (FileNotFoundException e) {
                    System.out.println("Image not found");
                }}
            case "PHD" -> {
                this.txtPrsinfo.setText("Personal Information");
                this.txtEducation.setText("Educational Experience");
                this.txtAcademic.setText("");
                this.personalinfoNavigate = Navigatior.PERSONAL_INFO;
                this.EducationExperienceNavigate = Navigatior.EDUCATION_PHD;

                try {
                    this.imgPrsinfo.setImage(new Image(new FileInputStream("Images/information.png")));
                    this.imgEducation.setImage(new Image(new FileInputStream("Images/Education.png")));
                    this.imgUp.setImage(new Image(new FileInputStream("Images/Up.png")));
                } catch (FileNotFoundException e) {
                    System.out.println("Image not found");
                }}
        }

        Navigatior.navigate(hboxaddPane,personalinfoNavigate);

//      if(SESSION.getCurrentPage().isEmpty()){
//
//      Navigatior.navigate(hboxaddPane,personalinfoNavigate);
//        SESSION.setCurrentPage(personalinfoNavigate);
//     }else{
//            Navigatior.navigate(hboxaddPane, SESSION.getCurrentPage());
//       }



    }


   @FXML
   private void handleTranslate(MouseEvent me){
       SESSION.switchLanguage();
       Navigatior.navigate(me, Navigatior.STUDENT_MENU);
       SESSION.setCurrentPage(Navigatior.STUDENT_MENU);
   }

    @FXML
    void handleOptionClick1(MouseEvent me) {

    }

    @FXML
    void handleOptionClick2(MouseEvent event) {

    }

    @FXML
    void handleOptionClick3(MouseEvent event) {

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










