package controller.Admin;

import app.Navigatior;
import app.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.EditAdminProfileDto;
import repository.AdminRepository;
import service.Admin.AdminService;
import controller.SESSION;

import java.io.FileInputStream;
import java.util.Locale;

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

    private Locale currentLocale = new Locale("en");
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        if (currentLocale.getLanguage().equals("en")) {
            currentLocale = new Locale("sq");
        } else {
            currentLocale = new Locale("en");
        }
        //loadLanguage(currentLocale.getLanguage());
    }
    @FXML
    private void initialize(){
        System.out.println("U lexu initialize");
        try {
            this.imgProfilePhoto.setImage(new Image(new FileInputStream("Images/adminProfileImage.png")));
        }catch(Exception e){
            System.out.println("Image not found!");
        }
        this.cancel();

        if(SESSION.getLoggedAdmin() != null) {
            AdminProfileToControllerDto admin = AdminService.getProfileInfo(SESSION.getLoggedAdmin().getEmail());

            if (admin != null) {
                this.txtFirstName.setText(admin.getFirstName());
                this.txtLastName.setText(admin.getLastName());
                this.txtEmail.setText(admin.getEmail());
            } else {
                System.out.println("Data object i admin su lexu");
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
        EditAdminProfileDto editAdminProfileDto = new EditAdminProfileDto(
                oldEmail,
          this.txtFirstName.getText().trim(),
          this.txtLastName.getText().trim(),
                this.txtEmail.getText().trim()
        );
        if(AdminService.savePersonalDetails(editAdminProfileDto)){
             PopUp.tick(200);
        }else{
            if(SESSION.isToggleShqip())
            PopUp.loading("Emaili tashme ekziston",false,"");
            else PopUp.loading("Email already exists",false,"");

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
