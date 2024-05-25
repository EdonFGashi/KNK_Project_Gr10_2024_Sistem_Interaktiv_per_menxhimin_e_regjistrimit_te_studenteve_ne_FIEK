
package controller.Student;

import app.Navigatior;
import app.PopUp;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.UserStudent2;
import model.dto.Student.PersonDTO;
import service.Student.StudentApplicantService;
import service.Student.UserService;
import service.Student.personService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;
import java.util.Date;

public class PersonalInfoControler {
    @FXML
    private AnchorPane addPane;
    @FXML
    private Button btmnext;

    @FXML
    private Button btnGenerateinfo;

    @FXML
    private Button btnGoback;

    @FXML
    private RadioButton rbuttonFemale;

    @FXML
    private RadioButton rdbuttonMale;

    @FXML
    private DatePicker selectBirthday;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtPersonalNumber;
    private String EducationExperienceNavigate="";
    @FXML
    void handleAprove(ActionEvent event) {

    }
    @FXML
    private void initialize(){
        int userId=SESSION.getLoggedUser().getId();
        UserStudent2 user= StudentApplicantService.getUserById(userId);
        if(user!=null){
            this.fillFields(user);
            this.disableFields();
            this.txtPersonalNumber.setDisable(true);
            this.btnGenerateinfo.setDisable(true);
        }

    }

    @FXML
    void handleGenerateInfo(MouseEvent event) {

        String personalNumber = txtPersonalNumber.getText();
        PersonDTO person = personService.getPersonByPersonalNumber(personalNumber);

        if (person == null) {
          //  alert("Personi nuk u gjend në bazën e të dhënave.", "Gabim", "Gabim");
            // Vendos të gjitha fushat si pa vlera

            alert("Personi nuk u gjend në bazën e të dhënave.", "Gabim", "Gabim");
            this.disableFields();
            this.resetFields();
            return;
        }

        txtName.setText(person.getName());
        txtLastName.setText(person.getLastName());
        txtNationality.setText(person.getNationality());
        txtCity.setText(person.getCity());
        txtCountry.setText(person.getCountry());

        if (person.getGender().equals("F")) {
            rbuttonFemale.setSelected(true);
            rbuttonFemale.setDisable(true); // Vendos female radio butonin si i deaktivizuar
            rdbuttonMale.setDisable(true); // Deaktivizo male radio butonin
        } else {
            rdbuttonMale.setSelected(true);
            rdbuttonMale.setDisable(true); // Vendos male radio butonin si i deaktivizuar
            rbuttonFemale.setDisable(true); // Deaktivizo female radio butonin
        }

        selectBirthday.setValue(person.getBirthDate().toLocalDate());
        this.selectBirthday.setDisable(true);
        this.txtPersonalNumber.setDisable(true);
        PopUp.tick(250);
    }

    public static void alert(String message, String title, String header) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    void handleGoback(ActionEvent event) {
        try{
            navigateToNewStage(event, Navigatior.STUDENT_RIBBON);
        }catch (Exception e){
            System.out.println("Error go back"+e.getMessage());
        }
    }
    @FXML
    void handleNext(MouseEvent event) {

        this.EducationExperienceNavigate = Navigatior.EDUCATION;
if(btnGenerateinfo.isDisable())

{
    switch (SESSION.getDeptLevel()) {
        case "BSC" : Navigatior.navigate(addPane, Navigatior.EDUCATION);
            break;
        case "MSC" : Navigatior.navigate(addPane, Navigatior.EDUCATION_MASTER);
            break;
        case "PHD" : Navigatior.navigate(addPane, Navigatior.EDUCATION_PHD);
    }
   return;
}


        String personalNumber = txtPersonalNumber.getText();
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String nationality = txtNationality.getText();
        String city = txtCity.getText();
        String country = txtCountry.getText();
        String gender = rbuttonFemale.isSelected() ? "F" : "M";
        LocalDate localDate = selectBirthday.getValue();
        java.sql.Date birthDate = java.sql.Date.valueOf(localDate);


        PersonDTO studentAplikant = new PersonDTO(personalNumber, name, lastName, nationality, city, country, gender, birthDate);

        personService studentService = new personService();
        studentService.saveStudentAplikant(studentAplikant);

        System.out.println("session dept"+SESSION.getDeptLevel());
        switch (SESSION.getDeptLevel()) {
            case "BSC" : Navigatior.navigate(addPane, Navigatior.EDUCATION);
            break;
            case "MSC" : Navigatior.navigate(addPane, Navigatior.EDUCATION_MASTER);
            break;
            case "PHD" : Navigatior.navigate(addPane, Navigatior.EDUCATION_PHD);
        }

       // alert("Të dhënat e studentit janë ruajtur me sukses.", "Sukses", "Sukses");

    }
    private void fillFields(UserStudent2 person) {
        txtPersonalNumber.setText(person.getNumriPersonal());
        txtName.setText(person.getEmri());
        txtLastName.setText(person.getMbiemri());
        txtNationality.setText(person.getNacionaliteti());
        txtCity.setText(person.getQyteti());
        txtCountry.setText(person.getShteti());

        if (person.getGjinia().equalsIgnoreCase("F")) {
            rbuttonFemale.setSelected(true);
        } else {
            rdbuttonMale.setSelected(true);
        }

        selectBirthday.setValue(person.getDataLindjes());
    }

    private void resetFields() {
        txtName.clear();
        txtLastName.clear();
        txtNationality.clear();
        txtCity.clear();
        txtCountry.clear();
        rbuttonFemale.setSelected(false);
        rdbuttonMale.setSelected(false);
        selectBirthday.setValue(null);
    }

    private void disableFields() {

        rbuttonFemale.setDisable(true);
        rdbuttonMale.setDisable(true);
        selectBirthday.setDisable(true);
    }
    private void navigateToNewStage(ActionEvent event, String fxmlPath) {
        Stage stage = new Stage();
        stage.setMaximized(true);
        Navigatior.navigate(stage, fxmlPath);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));

    }
}

