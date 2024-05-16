package controller.Admin;

import app.Navigatior;
import controller.Animations.UpLogoAnimate;
import controller.BackAndForth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import controller.SESSION;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RibbonController {

    @FXML
    private AnchorPane addPane;
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
    @FXML
    private ImageView imgInfoIcon;

    @FXML
    private Button btnBack;
    @FXML
    private Button btnForth;

    private UpLogoAnimate logo = new UpLogoAnimate(50, "FIEK Management", 5, 1);

    @FXML
    private void initialize(){
        if(BackAndForth.getIndex() == 0){
            SESSION.setAdminMenu("Student");
            Navigatior.navigate(this.addPane, Navigatior.ADMIN_MENU);
            BackAndForth.addOnePage(Navigatior.ADMIN_MENU);
        }else{
            this.navigateAndSaveState(Navigatior.ADMIN_MENU);
        }


        try {
            this.imgProfileIcon.setImage(new Image(new FileInputStream("Images/profileIcon.png")));
            this.imgStudentIcon.setImage(new Image(new FileInputStream("Images/studentIcon.png")));
            this.imgSupervisorIcon.setImage(new Image(new FileInputStream("Images/supervisorIcon.png")));
            this.imgInboxIcon.setImage(new Image(new FileInputStream("Images/inboxIcon.png")));
            this.imgRegistrationIcon.setImage(new Image(new FileInputStream("Images/registrationIcon.png")));
            this.imgInfoIcon.setImage(new Image(new FileInputStream("Images/info-icon.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
        this.anchorLogo.getChildren().add(logo);
        this.anchorLogo.setTranslateY(25);

        // E bon display ni text kur tbohet hover logo e infos
        Tooltip tooltip = new Tooltip("Info");

        Tooltip.install(imgInfoIcon, tooltip);

        if(BackAndForth.getIndex() <= 1){
            this.btnBack.setDisable(true);
        }
        if(BackAndForth.getIndex() == BackAndForth.getMaxIndex()){
            this.btnForth.setDisable(true);
        }
    }

    @FXML
    private void handleUpLogoClick(MouseEvent ae){
        //Navigu ne Dashboard
        Navigatior.navigate(this.addPane, Navigatior.DASHBOARD);
    }

    @FXML
    private void handleSupervisorMenagmentClick(MouseEvent me){
        SESSION.setAdminMenu("Supervisor");
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_MENU);
        BackAndForth.addOnePage(Navigatior.ADMIN_MENU);

    }
    @FXML
    private void handleStudentMenagmentClick(MouseEvent me){
        SESSION.setAdminMenu("Student");
        this.navigateAndSaveState(Navigatior.ADMIN_MENU);
    }
    @FXML
    private void handleInboxClick(MouseEvent me){
        this.navigateAndSaveState(Navigatior.ADMIN_INBOX);
    }
    @FXML
    private void handleProfileClick(MouseEvent me){
        this.navigateAndSaveState(Navigatior.ADMIN_PROFILE);
    }

    @FXML
    private void handleGoToProfile(ActionEvent ae){
        this.navigateAndSaveState(Navigatior.ADMIN_PROFILE);
    }
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
       // Navigatior.navigate(this.addPane, "");
        //
    }
    @FXML
    private void handleSignOut(ActionEvent ae){
        Navigatior.navigate(ae,Navigatior.LOGIN);
    }

    @FXML
    private void handleLogoClicked(MouseEvent me){
        this.navigateAndSaveState(Navigatior.DASHBOARD);
    }
    @FXML
    private void handleStartAnimation(MouseEvent me){
        this.logo.startWithMouse();
    }
    @FXML
    private void handleRegistrationPeriodClick(MouseEvent me){
        SESSION.setAdminMenu("Afat");
        this.navigateAndSaveState(Navigatior.ADMIN_MENU);
    }

    @FXML
    private void handleInfoIconClicked(MouseEvent me){
        Navigatior.navigateNewStage(Navigatior.HELP_ADMIN);
    }


    @FXML
    void handleBackClick(ActionEvent event) {
         Navigatior.navigate(this.addPane, BackAndForth.gotoPreviousPage());
        System.out.println("Index:" +BackAndForth.getIndex());
        System.out.println("This is the back page: "+ BackAndForth.gotoPreviousPage());
    }

    @FXML
    void handleForthClick(ActionEvent event) {
        Navigatior.navigate(this.addPane, BackAndForth.gotoForthPage());
        System.out.println("Index:" +BackAndForth.getIndex());
        System.out.println("This is the forth page: "+ BackAndForth.gotoForthPage());

    }


    private void navigateAndSaveState(String page){
        Navigatior.navigate(this.addPane,page);
        BackAndForth.addOnePage(page);
        System.out.println("Added Page!!"+page);
        System.out.println("Index:" +BackAndForth.getIndex());
        System.out.println("Max Index:" +BackAndForth.getMaxIndex());
        if(BackAndForth.getIndex() == 0){
            this.btnBack.setDisable(true);
        }else{
            this.btnBack.setDisable(false);
        }
        if(BackAndForth.getIndex() == BackAndForth.getMaxIndex()){
            this.btnForth.setDisable(true);
        }else{
            this.btnForth.setDisable(false);
        }
    }

}


