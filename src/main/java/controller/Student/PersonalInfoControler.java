
package controller.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.dto.Student.PersonDTO;
import service.Student.personService;

public class PersonalInfoControler {

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

    @FXML
    void handleAprove(ActionEvent event) {

    }

    @FXML
    void handleGenerateInfo(MouseEvent event) {
        String personalNumber = txtPersonalNumber.getText();


        PersonDTO person = personService.getPersonByPersonalNumber(personalNumber);

        if (person == null) {

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
        } else {
            rdbuttonMale.setSelected(true);
        }

        selectBirthday.setValue(person.getBirthDate().toLocalDate());
    }

    @FXML
    void handleGoback(MouseEvent event) {

    }

    @FXML
    void handleNext(MouseEvent event) {

    }

}

