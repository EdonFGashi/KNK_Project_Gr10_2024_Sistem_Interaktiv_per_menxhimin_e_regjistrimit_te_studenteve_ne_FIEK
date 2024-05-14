package controller.Admin;

import app.Navigatior;
import app.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import model.dto.Admin.ResetPasswordDto;
import service.Admin.AdminService;
import service.CustomExceptions.InvalidPassword;

public class ResetPasswordController {
    @FXML
    private PasswordField pwdNewPassword;
    @FXML
    private PasswordField pwdConfirmPassword;

    @FXML
    private void handleResetPassword(ActionEvent ae){
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto(
          this.pwdNewPassword.getText(),
          this.pwdConfirmPassword.getText()
        );

        try{
            AdminService.resetPassword(resetPasswordDto);
            PopUp.tick(200);
            Navigatior.closeStageAfterDelay(ae, Duration.millis(1500));

        }catch(InvalidPassword e){
            PopUp.loading(e.getMessage(),false,"");
        }

    }
}
