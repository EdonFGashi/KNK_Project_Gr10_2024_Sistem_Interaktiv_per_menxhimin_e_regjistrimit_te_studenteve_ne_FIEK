package controller.Admin;

import app.Navigatior;
import service.Animations.UpLogoAnimate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

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
    @FXML
    private ImageView imgRegistrationIcon;

    private UpLogoAnimate logo = new UpLogoAnimate(50, "FIEK Management", 5, 1);

    @FXML
    private void initialize(){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU);
        try {
            this.imgProfileIcon.setImage(new Image(new FileInputStream("Images/profileIcon.png")));
            this.imgStudentIcon.setImage(new Image(new FileInputStream("Images/studentIcon.png")));
            this.imgSupervisorIcon.setImage(new Image(new FileInputStream("Images/supervisorIcon.png")));
            this.imgInboxIcon.setImage(new Image(new FileInputStream("Images/inboxIcon.png")));
            this.imgRegistrationIcon.setImage(new Image(new FileInputStream("Images/registrationIcon.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
        this.anchorLogo.getChildren().add(logo);
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
        this.logo.startWithMouse();
    }
    @FXML
    private void handleRegistrationPeriodClick(MouseEvent me){
        Navigatior.navigate(this.addPane,Navigatior.ADMIN_REGISTRATIONMENU);
    }

}
