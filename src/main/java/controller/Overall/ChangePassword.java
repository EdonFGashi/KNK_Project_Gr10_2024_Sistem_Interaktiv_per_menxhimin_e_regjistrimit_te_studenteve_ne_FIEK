package controller.Overall;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import model.dto.Overall.ChangePasswordDto;
import service.Admin.AdminService;



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
                "hey100",
                this.pwdCurrentPassword.getText(),
                this.pwdNewPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        if(AdminService.changePassword(change)){
            //U nderrua me sukses
            System.out.println("U nderrua Passwordi me sukses");
            Navigatior.closeStageAfterDelay(ae, Duration.millis(3000));
            Navigatior.loading(true);
        }else{
            //Nuk u nderrua Passwordi ka gabim dikun
            Navigatior.loading(false);
        }


    }



}
