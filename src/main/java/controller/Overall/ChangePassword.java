package controller.Overall;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import model.dto.Overall.ChangePasswordDto;
import service.Admin.AdminService;
import service.CustomExceptions.InvalidPassword;
import service.SESSION;

public class ChangePassword {
    @FXML
    private PasswordField pwdCurrentPassword;
    @FXML
    private PasswordField pwdNewPassword;
    @FXML
    private PasswordField pwdConfirmPassword;

    public static String email;

    @FXML
    private void handleChangePassword(ActionEvent ae){

        ChangePasswordDto change = new ChangePasswordDto(
                this.pwdCurrentPassword.getText(),
                this.pwdNewPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        try{
            AdminService.changePassword(change);
            System.out.println("U nderrua Passwordi me sukses");
            Navigatior.closeStageAfterDelay(ae, Duration.millis(3000));
            Navigatior.loading("   Changed Password!",true, "");
        }catch(InvalidPassword e){
            Navigatior.loading(e.getMessage(),false, "");
        }


    }



}
