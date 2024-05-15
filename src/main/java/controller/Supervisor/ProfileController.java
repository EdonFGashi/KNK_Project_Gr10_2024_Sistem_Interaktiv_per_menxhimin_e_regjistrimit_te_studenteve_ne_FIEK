package controller.Supervisor;

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
import service.Admin.AdminService;

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
    private void initialize(){
        try {
            this.imgProfilePhoto.setImage(new Image(new FileInputStream("Images/adminProfileImage.png")));
        }catch(Exception e){
            System.out.println("Image not found!");
        }
        this.disable();
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
    private void handleChangePassword(ActionEvent ae){

        Navigatior.navigateNewStage(Navigatior.CHANGEPASSWORD);
    }
    public void disable(){
        this.txtFirstName.setDisable(true);
        this.txtLastName.setDisable(true);
        this.txtEmail.setDisable(true);
    }

}
