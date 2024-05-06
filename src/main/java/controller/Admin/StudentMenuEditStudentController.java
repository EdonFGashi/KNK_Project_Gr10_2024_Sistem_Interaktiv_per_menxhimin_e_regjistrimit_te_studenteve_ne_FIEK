package controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dto.Admin.EditStudentDto;
import model.dto.Admin.SaveRegisteredStudentInfoDto;
import model.dto.Admin.SaveStudentPersonalInfoDto;
import service.Admin.StudentFromAdminService;

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
    private Label labelStatus;

 @FXML
 private void initialize(){

    this.disable();
 }

   @FXML
    private void handleSearchEmail(ActionEvent ae){
       EditStudentDto editStudentDto = StudentFromAdminService.searchStudentEmail((this.txtSearch.getText()).trim());
       if(editStudentDto == null){
           this.labelStatus.setText("Student not Found");
           this.labelStatus.setStyle("-fx-background-color:red;");
       }else{
           this.labelStatus.setText("Student Found Succesfuly");
           this.labelStatus.setStyle("-fx-background-color: green;");
         setFields(editStudentDto);
       }
   }
    @FXML
    private void handleSearchId(ActionEvent ae){

    }
    @FXML
    private void handleSavePersonalInfo(ActionEvent ae){
       SaveStudentPersonalInfoDto saveStudentPersonalInfoDto = new SaveStudentPersonalInfoDto(
         this.txtFirstName.getText(),
               this.txtLastName.getText(),
               this.txtPersonalNumber.getText(),
               this.txtPersonalEmail.getText(),
               this.txtPhone.getText(),
               this.txtNationality.getText(),
               this.txtAccepted.getText(),
               this.txtSupervisorId.getText()
               );
      if(StudentFromAdminService.saveStudentPersonalInfo(saveStudentPersonalInfoDto)){
          this.labelStatus.setText("Student Edited Succesfuly");
          this.labelStatus.setStyle("-fx-background-color: green;");
      }else{
          this.labelStatus.setText("Student Not Edited");
          this.labelStatus.setStyle("-fx-background-color:red;");
      };
    }

    @FXML
    private void handleSaveRegisteredInfo(ActionEvent ae){
        SaveRegisteredStudentInfoDto saveRegisteredStudentInfoDto = new SaveRegisteredStudentInfoDto(
          this.txtDepartament.getText(),
          this.txtGeneratedEmail.getText(),
          this.txtGeneratedId.getText(),
          this.txtYearOfStudy.getText()
        );
        if(StudentFromAdminService.saveRegisteredStudent(saveRegisteredStudentInfoDto)){
            this.labelStatus.setText("Student Edited Succesfuly");
            this.labelStatus.setStyle("-fx-background-color: green;");
        }else{
            this.labelStatus.setText("Student Not Edited");
            this.labelStatus.setStyle("-fx-background-color:red;");
        };
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
    }

    private void setFields(EditStudentDto editStudentDto){
        this.txtFirstName.setText(editStudentDto.getFirstName());
        this.txtLastName.setText(editStudentDto.getLastName());
        this.txtPersonalNumber.setText(editStudentDto.getPersonalNumber());
        this.txtPersonalEmail.setText(editStudentDto.getPersonalEmail());
        this.txtPhone.setText(editStudentDto.getTelephone());
        this.txtNationality.setText(editStudentDto.getNationality());
        this.txtAccepted.setText(editStudentDto.getAccepted());
        this.txtSupervisorId.setText(editStudentDto.getSupervisorId());
        this.txtDepartament.setText(editStudentDto.getDepartament());
        this.txtGeneratedId.setText(editStudentDto.getGeneratedId());
        this.txtGeneratedEmail.setText(editStudentDto.getGeneratedEmail());
        this.txtYearOfStudy.setText(editStudentDto.getYearOfStudy());
        this.enable();
    }


}
