package controller.Admin;

import app.Navigatior;
import app.PopUp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ShkollaMesme;
import model.UserStudent;
import model.*;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.ApproveStudentsDto;
import model.dto.Admin.EditRegisteredStudentDetailsOnDbDto;
import model.dto.Admin.RegisteredStudentDetailsToControllerDto;
import model.filter.StudentFilter;
import repository.StudentRepository;
import service.Admin.AdminService;
import service.Admin.StudentFromAdminService;
import controller.SESSION;
import service.AfatService;
import service.ArkivaDokumenteveService;
import service.Student.StudentService;
import service.StudentPdfCreatorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


//Gjenerimi i vertetimit pdf
    @FXML
    private TextField txtNumriRendor;

    @FXML
    private TextField txtNumriSerik;
    @FXML
    private TextField txtDownloadPath;

    @FXML
    private TextArea txtIssueReason;

    @FXML
    private TextField txtIdStudentit;

    @FXML
    private TextField txtDataLeshimit;


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
               if(SESSION.isToggleShqip()) PopUp.loading("Emaili tashme ekziston",false,"");
               else PopUp.loading("Email already exists",false,"");
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

        this.paneFilterShowHide.setVisible(false);


        this.txtSearch.setOnKeyPressed( e -> {
                 if(e.getCode()== KeyCode.ENTER){
                  this.search();
                 }
        });

    }

    @FXML
    private void handleSearch(ActionEvent ae) {
       this.search();
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        this.search();
    }

    private void search(){
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


//Filter

    @FXML
    private TextField txtEmri;
    @FXML
    private TextField txtMbiemri;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNumriPersonal;
    @FXML
    private TextField txtQyteti;
    @FXML
    private TextField txtShteti;
    @FXML
    private TextField txtGjinia;
    @FXML
    private TextField txtNacionaliteti;
    @FXML
    private DatePicker dataDataLindjes;
    @FXML
    private AnchorPane paneFilterShowHide;

   @FXML
   private void handleShowFilterStudents(ActionEvent ae){
         this.paneFilterShowHide.setVisible(true);
   }
    @FXML
    private void handleFilter(ActionEvent ae){
       String formattedDataLindjes;
       if(dataDataLindjes.getValue() != null){
           LocalDate dataLindjes = this.dataDataLindjes.getValue();
            formattedDataLindjes = dataLindjes.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       }else{
           formattedDataLindjes = "";
       }

        this.userStudentsList = FXCollections.observableArrayList(StudentFromAdminService.filterStudents(
                 new StudentFilter(
                   this.txtNumriPersonal.getText(),
                   this.txtEmail.getText(),
                   this.txtEmri.getText(),
                   this.txtMbiemri.getText(),
                   this.txtNacionaliteti.getText(),
                   this.txtQyteti.getText(),
                   this.txtShteti.getText(),
                   this.txtGjinia.getText(),
                   formattedDataLindjes
                 )
        ));
        this.tableStudent.setItems(this.userStudentsList);

        //Mshile mas nja gjys sekonde
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Node source = (Node) ae.getSource();
                AnchorPane parentPane = (AnchorPane) source.getParent();
                parentPane.setVisible(false);
            }
        }));
        timeline.play();

    }

    @FXML
    private void handleCancelFilter(ActionEvent ae){
        this.paneFilterShowHide.setVisible(false);
    }
    @FXML
    void handleDownload(ActionEvent event) {
        int nrSerik = (int)(Math.random() * (999999 - 100000 + 1)) + 100000;

        LocalDate dataTanishme = LocalDate.now();

        RegisteredStudent studenti = StudentService.getRegisteredStudent(this.selectedUser.getUserId());
        Afat afatiRegjistrimit = AfatService.getAfatiRegjistrimitForStudent(this.selectedUser.getUserId());
        RegisteredStudentDetailsToControllerDto studentData = StudentFromAdminService.getRegisteredStudent(this.selectedUser.getUserId());
        AdminProfileToControllerDto admin = AdminService.getProfileInfo(SESSION.getLoggedUserEmail());
        StudentPdfCreatorService.generatePdf(
                ArkivaDokumenteveService.getLastDocumentId() + 1,
                nrSerik,
                dataTanishme,
                studenti.getGeneratedId(),
                studenti.getEmri(),
                studenti.getMbiemri(),
                studenti.getDataLindjes(),
                studenti.getQyteti(),
                "I rregullt",
                afatiRegjistrimit.getYear(),
                afatiRegjistrimit.getNiveli(),
                studentData.getDepartmentName(),
                studentData.getDepartmentName(),
                this.txtIssueReason.getText(),
                admin.getFirstName() + " " + admin.getLastName(),
                this.txtDownloadPath.getText()
        );

        Arkiva arkivimi = new Arkiva(String.valueOf(nrSerik), studenti.getGeneratedId(), dataTanishme);
        boolean uGjenerua = ArkivaDokumenteveService.arkivoDokumentin(arkivimi);
        if(!uGjenerua){
            PopUp.tick(200);
        } else {
            if(SESSION.isToggleShqip()) PopUp.loading("Vërtetimi nuk u gjenerua!",false,"");
            else PopUp.loading("Verification not succesful",false,"");

        }
    }

    @FXML
    void handleVerify(ActionEvent event) {
        String nrRendorString = this.txtNumriRendor.getText();
        int nrRendor = Integer.parseInt(nrRendorString);
        String nrSerik = this.txtNumriSerik.getText();
        String idStudentit = this.txtIdStudentit.getText();
        String dataLeshimitString = this.txtDataLeshimit.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataLeshimit = LocalDate.parse(dataLeshimitString, formatter);

        Arkiva arkivPerVerifikim = new Arkiva(nrRendor, nrSerik, idStudentit, dataLeshimit);
        boolean eshteOrigjinal = ArkivaDokumenteveService.verifiko(arkivPerVerifikim);
        if(eshteOrigjinal){
            if(SESSION.isToggleShqip()) PopUp.loading("Vërtetimi është oregjinal!",true,"");
            else PopUp.loading("Verification is Oreginal",true,"");

        } else {
            if(SESSION.isToggleShqip()) PopUp.loading("Vërtetimi NUK është oregjinal!",false,"");
            else PopUp.loading("Verification is NOT Oreginal",false,"");
        }
    }

}
