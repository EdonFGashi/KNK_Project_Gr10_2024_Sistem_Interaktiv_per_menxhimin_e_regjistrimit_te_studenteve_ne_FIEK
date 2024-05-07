package controller.Admin;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private PasswordField pwdPassword;

    @FXML
    private void handleEdit(ActionEvent ae){
        this.txtName.setDisable(false);
        this.txtSurname.setDisable(false);
        this.txtEmail.setDisable(false);
        this.txtPhone.setDisable(false);
        this.pwdPassword.setDisable(false);

    }

    @FXML
    private void handleChangePassword(ActionEvent ae){
        Navigatior.navigateNewStage(Navigatior.CHANGEPASSWORD);
    }



}
