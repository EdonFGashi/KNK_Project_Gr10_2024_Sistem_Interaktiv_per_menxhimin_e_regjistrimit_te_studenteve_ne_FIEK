package controller.Admin;


import app.PopUp;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dto.Supervisor.SupervisorCreateInterfaceDto;
import controller.Animations.UpLogoAnimate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import service.Supervisor.SupervisorService;

public class SupervisorMenuAddController {
    @FXML
    private Pane animationPane;
    @FXML
    private void initialize(){

      UpLogoAnimate pane1 = new UpLogoAnimate(100, "Loading...", 10, 2);
      pane1.setMaxWidth(100);
      pane1.setMaxHeight(100);
      pane1.start();
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
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPasswordSupervisorSignUp;
    @FXML
    private PasswordField pwdConfirmPasswordSupervisorSignUp;
    @FXML
    private void handleSignUpSupervisor(ActionEvent ae){
        SupervisorCreateInterfaceDto addNewSupervisor = new SupervisorCreateInterfaceDto(
                this.txtFirstname.getText(),
                this.txtLastname.getText(),
                this.txtEmail.getText(),
                this.pwdPasswordSupervisorSignUp.getText(),
                this.pwdConfirmPasswordSupervisorSignUp.getText()
        );
        boolean supervisorCreated = SupervisorService.signUp(addNewSupervisor);
        PopUp.tick(250);

    }

    @FXML
    private void handleClearSignUpSupervisor(ActionEvent ae){
        this.txtFirstname.setText("");
        this.txtLastname.setText("");
        this.txtEmail.setText("");
        this.pwdPasswordSupervisorSignUp.setText("");
        this.pwdConfirmPasswordSupervisorSignUp.setText("");
    }


}
