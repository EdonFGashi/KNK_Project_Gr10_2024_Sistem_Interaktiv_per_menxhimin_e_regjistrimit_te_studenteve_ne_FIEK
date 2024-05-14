package controller.Admin;

import app.Navigatior;
import app.PopUp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.ShkollaMesme;
import model.UserStudent;
import model.dto.Admin.ApproveStudentsDto;
import model.dto.Admin.EditRegisteredStudentDetailsOnDbDto;
import model.dto.Admin.RegisteredStudentDetailsToControllerDto;
import repository.StudentRepository;
import service.Admin.StudentFromAdminService;
import controller.SESSION;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StudentMenuShowAndEditController {
    @FXML
    private TextField txtSearch;
    @FXML
    private ImageView imgSearch;
    @FXML
    private TableView<UserStudent> tableStudent;
    @FXML
    private Button btnEdit;
    private boolean edit;
    @FXML
    private TableColumn<UserStudent, Integer> columnId;
    @FXML
    private TableColumn<UserStudent, String> columnPersonalNumber;
    @FXML
    private TableColumn<UserStudent, String> columnEmail;
    @FXML
    private TableColumn<UserStudent, String> columnFirstName;
    @FXML
    private TableColumn<UserStudent, String> columnLastName;
    @FXML
    private TableColumn<UserStudent, String> columnNationality;
    @FXML
    private TableColumn<UserStudent, String> columnCity;
    @FXML
    private TableColumn<UserStudent, String> columnNation;
    @FXML
    private TableColumn<UserStudent, String> columnGender;
    @FXML
    private TableColumn<UserStudent, String> columnBirthDate;


    //Aplikimi
    @FXML
    private TextField txtSchoolName;
    @FXML
    private TextField txtMath;
    @FXML
    private TextField txtAlbanian;
    @FXML
    private TextField txtEnglish;
    @FXML
    private TextField txtChosenSubject;
    @FXML
    private TextField txtChosenSubjectPoints;
    @FXML
    private TextField txtAllPoints;
    @FXML
    private TextField txtGrade10;
    @FXML
    private TextField txtGrade11;
    @FXML
    private TextField txtGrade12;

    @FXML
    private ImageView imgGradeCertificate;
    @FXML
    private ImageView imgIdentification;
    @FXML
    private ImageView imgDiplome;
    @FXML
    private Button btnApprove;
    private boolean approve = true;


    @FXML
    private void handleAprove(ActionEvent ae){
      if( StudentRepository.setApprove(
              new ApproveStudentsDto(this.approve,this.selectedUser.getUserId())
      )){
          PopUp.tick(200);
      }

    }
    @FXML
    private void handleDelete(ActionEvent ae){
       if(StudentFromAdminService.deleteStudent(this.selectedUser.getUserId())){
            PopUp.tick(200);
           this.userStudentsList = StudentFromAdminService.searchStudent(this.txtSearch.getText());
           this.tableStudent.setItems(this.userStudentsList);
        }else{
            System.out.println("Nuk u fshi");
        }
    }
    @FXML
    private void handleResetPassword(ActionEvent ae){
        SESSION.setAdmin_reset_PasswordId(this.selectedUser.getUserId());
        SESSION.setAdmin_reset_type("Student");
        Navigatior.navigateNewStage(Navigatior.ADMIN_RESETPASSWORD);
    }

    //Registered Student
    @FXML
    private TextField txtGeneratedEmail;
    @FXML
    private TextField txtGeneratedId;
    @FXML
    private TextField txtDepartment;
    @FXML
    private TextField txtLevel;

   @FXML
   private void handleEdit(ActionEvent ae){
       if(!this.edit) {
           this.btnEdit.setText("Edit");
           if (StudentFromAdminService.editRegisteredStudent(
                   new EditRegisteredStudentDetailsOnDbDto(
                           this.selectedUser.getUserId(),
                           this.txtGeneratedEmail.getText(),
                           this.txtGeneratedId.getText(),
                           this.txtDepartment.getText(),
                           this.txtLevel.getText()
                   )
           )) {
               PopUp.tick(200);
               this.userStudentsList = StudentFromAdminService.searchStudent(this.txtSearch.getText());
               this.tableStudent.setItems(this.userStudentsList);
           } else {
               System.out.println("Edit was not Done");
           }
           this.edit = true;
       }else {
           this.enableForms();
           this.btnEdit.setText("Save");
           this.edit = false;
       }


   }



    @FXML
    private Button btnResetPassword;

   private ObservableList<UserStudent> userStudentsList;
   private UserStudent selectedUser;

    @FXML
    private void initialize(){
        try {
            this.imgSearch.setImage(new Image(new FileInputStream("Images/search.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

        tableStudent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.selectedUser = newSelection;
                this.setTextFields();
                this.setRegisteredStudentForms();
            }
            this.btnResetPassword.setDisable(false);
        });
        this.btnResetPassword.setDisable(true);
        this.txtSearch.setText(SESSION.getAdmin_student_lastSearch());

        this.userStudentsList = StudentFromAdminService.searchStudent(this.txtSearch.getText());
        this.columnId.setCellValueFactory(new PropertyValueFactory<UserStudent,Integer>("userId"));
        this.columnPersonalNumber.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("numriPersonal"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("email"));
        this.columnFirstName.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("emri"));
        this.columnLastName.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("mbiemri"));
        this.columnNationality.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("nacionaliteti"));
        this.columnCity.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("qyteti"));
        this.columnNation.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("shteti"));
        this.columnGender.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("gjinia"));
        this.columnBirthDate.setCellValueFactory(new PropertyValueFactory<UserStudent,String>("dataLindjes"));
        this.tableStudent.setItems(this.userStudentsList);
        this.edit = true;
    }

    @FXML
    private void handleSearch(ActionEvent ae) {
        this.userStudentsList = StudentFromAdminService.searchStudent(this.txtSearch.getText());
        SESSION.setAdmin_student_lastSearch(this.txtSearch.getText().trim());
        this.tableStudent.setItems(this.userStudentsList);
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        this.userStudentsList = StudentFromAdminService.searchStudent(this.txtSearch.getText());
        SESSION.setAdmin_student_lastSearch(this.txtSearch.getText().trim());
        this.tableStudent.setItems(this.userStudentsList);
    }

    private void enableForms() {
       this.txtDepartment.setEditable(true);
       this.txtGeneratedEmail.setEditable(true);
       this.txtGeneratedId.setEditable(true);
       this.txtLevel.setEditable(true);
    }


    private void setTextFields(){

        ShkollaMesme shkollaMesme = StudentRepository.getShkollaMeme(this.selectedUser.getUserId());

      if(shkollaMesme != null){
          this.txtSchoolName.setText(shkollaMesme.getEmriShkolles());
          this.txtMath.setText(Integer.toString(shkollaMesme.getPiketMat()));
          this.txtAlbanian.setText(Integer.toString(shkollaMesme.getPiketGjSh()));
          this.txtEnglish.setText(Integer.toString(shkollaMesme.getPiketAng()));
          this.txtChosenSubject.setText(shkollaMesme.getLendaZgjedhore());
          this.txtChosenSubjectPoints.setText(Integer.toString(shkollaMesme.getPiketZgjedhore()));
          int total = shkollaMesme.getPiketAng()+ shkollaMesme.getPiketGjSh()+ shkollaMesme.getPiketMat()+ shkollaMesme.getPiketZgjedhore();
          this.txtAllPoints.setText(Integer.toString(total));
          this.txtGrade10.setText(Integer.toString(shkollaMesme.getSuksesiKl10()));
          this.txtGrade11.setText(Integer.toString(shkollaMesme.getSuksesiKl11()));
          this.txtGrade12.setText(Integer.toString(shkollaMesme.getSuksesiKl12()));

          setImageToImageView(shkollaMesme.getCertifikataNotave(), this.imgGradeCertificate);
          setImageToImageView(shkollaMesme.getLeternjoftimi(), this.imgIdentification);
          setImageToImageView(shkollaMesme.getDiplomashkolles(), this.imgDiplome);
          if(!shkollaMesme.isApproved()){
            this.approve=true;
            this.btnApprove.setText("Aprove");
          }else{
              this.approve = false;
              this.btnApprove.setText("Dont Aprove");
          }
      }else{
          this.txtSchoolName.setText("");
          this.txtMath.setText("");
          this.txtAlbanian.setText("");
          this.txtEnglish.setText("");
          this.txtChosenSubject.setText("");
          this.txtChosenSubjectPoints.setText("");
          this.txtAllPoints.setText("");
          this.txtGrade10.setText("");
          this.txtGrade11.setText("");
          this.txtGrade12.setText("");

          this.imgGradeCertificate.setImage(null);
          this.imgIdentification.setImage(null);
          this.imgDiplome.setImage(null);

        }

    }

    private void setImageToImageView(Image image, ImageView imageView) {
        if (image != null) {
            imageView.setImage(image);
        } else {
            try {
                imageView.setImage(new Image(new FileInputStream("Images/Error404")));
            }catch(Exception e){
                //
            }
        }
    }

    private void setRegisteredStudentForms(){
        RegisteredStudentDetailsToControllerDto data = StudentFromAdminService.getRegisteredStudent(this.selectedUser.getUserId());
        if(data != null){
            this.txtGeneratedEmail.setText(data.getGeneratedEmail());
            this.txtGeneratedId.setText(data.getGeneratedId());
            this.txtLevel.setText(data.getLevel());
            this.txtDepartment.setText(data.getDepartmentName());
        }else{
            this.txtGeneratedEmail.setText("");
            this.txtGeneratedId.setText("");
            this.txtLevel.setText("");
            this.txtDepartment.setText("");
        }

    }


}
