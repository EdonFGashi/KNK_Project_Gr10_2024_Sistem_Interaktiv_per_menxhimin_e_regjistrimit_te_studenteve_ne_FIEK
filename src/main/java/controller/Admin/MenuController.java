package controller.Admin;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import service.SESSION;

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

    private String option1Navigate= "";
    private String option2Navigate ="";
    private String option3Navigate ="";

    private final String activeSection = "-fx-border-color: white; -fx-border-radius: 15;-fx-border-width: 1.5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);";
    @FXML
    private void initialize(){
        String menu = SESSION.getAdminMenu();

        if(menu.equals("Student")){
            this.txtMenuName.setText("Student Menagment");

            this.txtOption1.setText("Aplication");
            this.txtOption2.setText("Show and Edit");
            this.txtOption3.setText("Statistics");
            this.option1Navigate = Navigatior.ADMIN_PROFILE;
            this.option2Navigate = Navigatior.ADMIN_STUDENTMENU_SHOWANDEDIT;
            this.option3Navigate = "";
            try {
                this.imgMain.setImage(new Image(new FileInputStream("Images/studentMenu.png")));
                this.imgOption1.setImage(new Image(new FileInputStream("Images/application.png")));
                this.imgOption2.setImage(new Image(new FileInputStream("Images/showAndEdit.png")));
                this.imgOption3.setImage(new Image(new FileInputStream("Images/statistics.png")));
            } catch (FileNotFoundException e) {
                System.out.println("Image not found");
            }


        }else if(menu.equals("Supervisor")){
            this.txtMenuName.setText("Supervisor Menagment");
            this.txtOption1.setText("Show and Edit");
            this.txtOption2.setText("Add Supervisor");
            this.txtOption3.setText("Aprove Supervisor");
            this.option1Navigate = Navigatior.ADMIN_SUPERVISORMENU_EDITSUPERVISOR;
            this.option2Navigate = Navigatior.ADMIN_SUPERVISORMENU_ADDSUPERVISOR;
            this.option3Navigate = "";
            try {
                this.imgMain.setImage(new Image(new FileInputStream("Images/supervisorMenu.png")));
                this.imgOption1.setImage(new Image(new FileInputStream("Images/showAndEdit.png")));
                this.imgOption2.setImage(new Image(new FileInputStream("Images/addSupervisor.png")));
                this.imgOption3.setImage(new Image(new FileInputStream("Images/aproveSupervisor.png")));
            } catch (FileNotFoundException e) {
                System.out.println("Image not found");
            }


        }else if(menu.equals("Afat")) {
            this.txtMenuName.setText("Registration Menagment");
            this.txtOption1.setText("Show and Edit");
            this.txtOption2.setText("Add new");
            this.txtOption3.setText("Statistics");
            this.option1Navigate = Navigatior.ADMIN_REGISTRATIONMENU_SHOWANDEDIT;
            this.option2Navigate = Navigatior.ADMIN_REGISTRATIONMENU_ADDREGISTRATION;
            this.option3Navigate = "";
            try {
                this.imgMain.setImage(new Image(new FileInputStream("Images/registrationMenu.png")));
                this.imgOption1.setImage(new Image(new FileInputStream("Images/showAndEdit.png")));
                this.imgOption2.setImage(new Image(new FileInputStream("Images/addAfat.png")));
                this.imgOption3.setImage(new Image(new FileInputStream("Images/statistics.png")));
            } catch (FileNotFoundException e) {
                System.out.println("Image not found");
            }

        }
        Navigatior.navigate(addPane,option1Navigate);
        this.hboxOption1.setStyle(activeSection);
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
    private void handleHoverOption1(MouseEvent me){

    }
    @FXML
    private void handleHoverOption2(MouseEvent me){

    }
    @FXML
    private void handleHoverOption3(MouseEvent me){

    }

    private void resetActiveSection(){
        this.hboxOption1.setStyle("");
        this.hboxOption2.setStyle("");
        this.hboxOption3.setStyle("");
    }


}
