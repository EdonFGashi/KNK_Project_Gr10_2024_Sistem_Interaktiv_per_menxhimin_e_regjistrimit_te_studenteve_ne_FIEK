package controller.Admin;

import app.Navigatior;
import app.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Admin;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.EditAdminProfileDto;
import repository.AdminRepository;
import service.Admin.AdminService;
import service.SESSION;

import java.io.FileInputStream;

public class ProfileController {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;

    @FXML
    private ImageView imgProfilePhoto;

    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonSave;
    private boolean toogle;
    @FXML
    private void initialize(){
        try {
            this.imgProfilePhoto.setImage(new Image(new FileInputStream("Images/adminProfileImage.png")));
        }catch(Exception e){
            System.out.println("Image not found!");
        }
        this.cancel();
        AdminProfileToControllerDto admin = AdminService.getProfileInfo(SESSION.getLoggedUserEmail());

        if(admin != null) {
            this.txtFirstName.setText(admin.getFirstName());
            this.txtLastName.setText(admin.getLastName());
            this.txtEmail.setText(admin.getEmail());
        }else{
            System.out.println("Data object i admin su lexu");
        }
        System.out.println("U lexu initialize");
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
         String  oldEmail = "jon@admin.uni-pr.edu";
        EditAdminProfileDto editAdminProfileDto = new EditAdminProfileDto(
                oldEmail,
          this.txtFirstName.getText().trim(),
          this.txtLastName.getText().trim(),
                this.txtEmail.getText().trim()
        );
        if(AdminRepository.savePersonalDetails(editAdminProfileDto)){
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
        this.txtFirstName.setDisable(true);
        this.txtLastName.setDisable(true);
        this.txtEmail.setDisable(true);
    }
    public void enable(){
        this.txtFirstName.setDisable(false);
        this.txtLastName.setDisable(false);
        this.txtEmail.setDisable(false);
    }

    private void cancel(){
        this.buttonEdit.setText("Edit");
        this.buttonSave.setVisible(false);
        this.toogle=true;
        this.disable();
    }


}
