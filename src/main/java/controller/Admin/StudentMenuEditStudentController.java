package controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentMenuEditStudentController {
  @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPersonalNumber;
    @FXML
    private TextField txtPersonalEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAccepted;
    @FXML
    private TextField txtNationality;
    @FXML
    private TextField txtSupervisorId;
    @FXML
    private TextField txtDepartament;
    @FXML
    private TextField txtGeneratedId;
    @FXML
    private TextField txtGeneratedEmail;
    @FXML
    private TextField txtYearOfStudy;
    @FXML
    private PasswordField pwdResetPassword;

 @FXML
 private void initialize(){

    this.disable();
 }

   @FXML
    private void handleSearchEmail(ActionEvent ae){



   }
    @FXML
    private void handleSearchId(ActionEvent ae){

    }
    @FXML
    private void handleSavePersonalInfo(ActionEvent ae){

    }
    @FXML
    private void handleSaveRegisteredInfo(ActionEvent ae){

    }
    @FXML
    private void handleResetPassword(ActionEvent ae){

    }

    private void disable() {
            txtFirstName.setEditable(false);
            txtLastName.setEditable(false);
            txtPersonalNumber.setEditable(false);
            txtPersonalEmail.setEditable(false);
            txtPhone.setEditable(false);
            txtAccepted.setEditable(false);
            txtNationality.setEditable(false);
            txtSupervisorId.setEditable(false);
            txtDepartament.setEditable(false);
            txtGeneratedId.setEditable(false);
            txtGeneratedEmail.setEditable(false);
            txtYearOfStudy.setEditable(false);
            pwdResetPassword.setEditable(false);
   }
    private void enable() {
        txtFirstName.setEditable(true);
        txtLastName.setEditable(true);
        txtPersonalNumber.setEditable(true);
        txtPersonalEmail.setEditable(true);
        txtPhone.setEditable(true);
        txtAccepted.setEditable(true);
        txtNationality.setEditable(true);
        txtSupervisorId.setEditable(true);
        txtDepartament.setEditable(true);
        txtGeneratedId.setEditable(true);
        txtGeneratedEmail.setEditable(true);
        txtYearOfStudy.setEditable(true);
        pwdResetPassword.setEditable(true);
    }

}
