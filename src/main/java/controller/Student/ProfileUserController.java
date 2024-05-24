package controller.Student;

import app.Navigatior;
import app.PopUp;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.EditAdminProfileDto;
import model.dto.Student.EditUserProfileDto;
import model.dto.Student.UserProfileDto;
import service.Admin.AdminService;
import service.Student.StudentApplicantService;
import service.Student.UserService;

import java.io.FileInputStream;

public class ProfileUserController {

    @FXML
    private ImageView imgProfilePhoto;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonSave;
    private boolean toogle;

    @FXML
    private void initialize(){
        System.out.println("U lexu initialize");
        try {
            this.imgProfilePhoto.setImage(new Image(new FileInputStream("woman.png")));
        }catch(Exception e){
            System.out.println("Image not found!");
        }
        this.cancel();

        if(SESSION.getLoggedAdmin() != null) {
            UserProfileDto user = UserService.getProfileInfo(SESSION.getLoggedUserEmail());

            if (user != null) {
                this.txtUsername.setText(user.getUsername());
                this.txtEmail.setText(user.getEmail());
            } else {
                System.out.println("Data object i userit nuk eshte lexu");
            }
        }
    }

    @FXML
    private void handleEdit(ActionEvent ae){
        if(this.toogle){
            this.buttonSave.setVisible(true);
            this.enable();
            this.buttonEdit.setText("Cancel");
            this.toogle=false;
        }else {
            this.cancel();
        }
    }

    @FXML
    private void handleSave(ActionEvent ae){
        String  oldEmail = SESSION.getLoggedAdmin().getEmail();
        EditUserProfileDto editUserProfileDto = new EditUserProfileDto(
                oldEmail,
                this.txtUsername.getText().trim(),
                this.txtEmail.getText().trim()
        );
        if(UserService.savePersonalDetails(editUserProfileDto)){
            PopUp.tick(200);
        }else{
            System.out.println("Gabim");
        };
        this.cancel();
    }

    @FXML
    private void handleChangePassword(ActionEvent ae){
        Navigatior.navigateNewStage(Navigatior.CHANGEPASSWORD);
    }
    public void disable(){
        this.txtUsername.setDisable(true);
        this.txtEmail.setDisable(true);
    }
    public void enable(){
        this.txtUsername.setDisable(false);
        this.txtEmail.setDisable(false);
    }

    private void cancel(){
        this.buttonEdit.setText("Edit");
        this.buttonSave.setVisible(false);
        this.toogle=true;
        this.disable();
    }

}
