package controller.Supervisor;

import app.Animation;
import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class SupervisorMenuAddController {
    @FXML
    private Pane animationPane;
    @FXML
    private void initialize(){
      Pane pane1 = Animation.getAnimation();
      pane1.setMaxWidth(100);
      pane1.setMaxHeight(100);
        this.animationPane.getChildren().addAll(pane1);
//        Navigatior.navigate(this.animationPane, String.valueOf(pane1));
//        try {
//            this.imgProfileIcon.setImage(new Image(new FileInputStream("Images/profileIcon.png")));
//            this.imgStudentIcon.setImage(new Image(new FileInputStream("Images/studentIcon.png")));
//            this.imgSupervisorIcon.setImage(new Image(new FileInputStream("Images/supervisorIcon.png")));
//            this.imgInboxIcon.setImage(new Image(new FileInputStream("Images/inboxIcon.png")));
//            this.imgUpLogo.setImage(new Image(new FileInputStream("Images/upLogoNoRing.png")));
//
//            // System.out.println("Image set successfully.");
//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found");
//        }
    }
    @FXML
    private void handleSignUpSupervisor(ActionEvent ae){

    }



}
