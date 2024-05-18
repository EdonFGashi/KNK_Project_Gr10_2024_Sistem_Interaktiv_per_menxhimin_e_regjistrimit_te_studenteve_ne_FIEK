
package controller.Student;

import app.Navigatior;
import app.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.dto.Student.PersonDTO;
import service.Student.personService;

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
    void handleGenerateInfo(MouseEvent event) {

        String personalNumber = txtPersonalNumber.getText();
        PersonDTO person = personService.getPersonByPersonalNumber(personalNumber);

        if (person == null) {
            alert("Personi nuk u gjend në bazën e të dhënave.", "Gabim", "Gabim");

            // Vendos të gjitha fushat si pa vlera
            txtName.setText("");
            txtLastName.setText("");
            txtNationality.setText("");
            txtCity.setText("");
            txtCountry.setText("");

            // Fshij selektimin e radio butonave dhe vendosi datën e lindjes si bosh
            rbuttonFemale.setSelected(false);
            rdbuttonMale.setSelected(false);
            selectBirthday.setValue(null);
            alert("Personi nuk u gjend në bazën e të dhënave.", "Gabim", "Gabim");

            // Kthej këtë pjesë

            System.out.println("Personi nuk u gjet në bazën e të dhënave.");
            return;
        }

        txtName.setText(person.getName());
        txtLastName.setText(person.getLastName());
        txtNationality.setText(person.getNationality());
        txtCity.setText(person.getCity());
        txtCountry.setText(person.getCountry());

        if (person.getGender().equals("Female")) {
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
    void handleGoback(MouseEvent event) {

    }
    @FXML
    void handleNext(MouseEvent event) {

        this.EducationExperienceNavigate = Navigatior.EDUCATION;

        String personalNumber = txtPersonalNumber.getText();
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String nationality = txtNationality.getText();
        String city = txtCity.getText();
        String country = txtCountry.getText();
        String gender = rbuttonFemale.isSelected() ? "Female" : "Male";
        LocalDate localDate = selectBirthday.getValue();
        java.sql.Date birthDate = java.sql.Date.valueOf(localDate);


        PersonDTO studentAplikant = new PersonDTO(personalNumber, name, lastName, nationality, city, country, gender, birthDate);

        personService studentService = new personService();
        studentService.saveStudentAplikant(studentAplikant);


       // alert("Të dhënat e studentit janë ruajtur me sukses.", "Sukses", "Sukses");
        Navigatior.navigate(addPane,EducationExperienceNavigate);
    }
}

