package controller.Admin;

import app.Navigatior;
import app.UpLogoAnimate;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RibbonController {

    @FXML
    private HBox addPane;
    @FXML
    private ImageView imgProfileIcon;
    @FXML
    private ImageView imgInboxIcon;
    @FXML
    private ImageView imgStudentIcon;
    @FXML
    private ImageView imgSupervisorIcon;
    //@FXML
   // private ImageView imgUpLogo;
    @FXML
    private AnchorPane anchorLogo;

    private UpLogoAnimate logo = new UpLogoAnimate();

    @FXML
    private void initialize(){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU);
        try {
            this.imgProfileIcon.setImage(new Image(new FileInputStream("Images/profileIcon.png")));
            this.imgStudentIcon.setImage(new Image(new FileInputStream("Images/studentIcon.png")));
            this.imgSupervisorIcon.setImage(new Image(new FileInputStream("Images/supervisorIcon.png")));
            this.imgInboxIcon.setImage(new Image(new FileInputStream("Images/inboxIcon.png")));
            //this.imgUpLogo.setImage(new Image(new FileInputStream("Images/upLogoNoRing.png")));

            // System.out.println("Image set successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
        this.anchorLogo.getChildren().add(logo);
      //  this.anchorLogo.setStyle("-fx-background-col");
        this.anchorLogo.setTranslateY(25);
    }

    @FXML
    private void handleUpLogoClick(MouseEvent ae){
        //Navigu ne Dashboard
        Navigatior.navigate(this.addPane, "");
    }

    @FXML
    private void handleSupervisorMenagmentClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_SUPERVISORMENU);
    }
    @FXML
    private void handleStudentMenagmentClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU);
    }
    @FXML
    private void handleInboxClick(MouseEvent me){
        Navigatior.navigate(this.addPane, "");
    }
    @FXML
    private void handleProfileClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
    }

    @FXML
    private void handleGoToProfile(ActionEvent ae){

        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
        Navigatior.tick();
    }
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        Navigatior.navigate(this.addPane, "");
    }
    @FXML
    private void handleSignOut(ActionEvent ae){
        Navigatior.navigate(this.addPane, "");
    }

    @FXML
    private void handleLogoClicked(MouseEvent me){
      Navigatior.navigate(this.addPane,Navigatior.DASHBOARD);
    }
    @FXML
    private void handleStartAnimation(MouseEvent me){
        this.logo.start();
    }
    @FXML
    private void handleRegistrationPeriodClick(MouseEvent me){
        Navigatior.navigate(this.addPane,Navigatior.ADMIN_REGISTRATIONMENU);
    }

}
