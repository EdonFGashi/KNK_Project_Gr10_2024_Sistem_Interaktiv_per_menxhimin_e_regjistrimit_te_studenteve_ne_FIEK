package controller.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.dto.Student.AcademicInterestDto;
import static service.Student.StudentApplicantService.processAcademicInterest;

public class AcademicInterestController {

    @FXML
    private RadioButton rbEAR;

    @FXML
    private RadioButton rbEAR1;

    @FXML
    private RadioButton rbEAR2;

    @FXML
    private RadioButton rbEAR3;

    @FXML
    private RadioButton rbElektro;

    @FXML
    private RadioButton rbElektro1;

    @FXML
    private RadioButton rbElektro2;

    @FXML
    private RadioButton rbElektro3;

    @FXML
    private RadioButton rbIKS;

    @FXML
    private RadioButton rbIKS1;

    @FXML
    private RadioButton rbIKS2;

    @FXML
    private RadioButton rbIKS3;

    @FXML
    private RadioButton rbTIK;

    @FXML
    private RadioButton rbTIK1;

    @FXML
    private RadioButton rbTIK2;

    @FXML
    private RadioButton rbTIK3;

    @FXML
    private ToggleGroup tgDept;

    @FXML
    private ToggleGroup tgDept1;

    @FXML
    private ToggleGroup tgDept2;

    @FXML
    private ToggleGroup tgDept3;

    @FXML
    void handleApply(ActionEvent event) {
        String dept = getSelectedToggleText(tgDept);
        String dept1 = getSelectedToggleText(tgDept1);
        String dept2 = getSelectedToggleText(tgDept2);
        String dept3 = getSelectedToggleText(tgDept3);

        // Check for duplicate selections
        if (hasDuplicateSelection(dept, dept1, dept2, dept3)) {
            // Create an Alert with the error type
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // Set the alert title
            alert.setTitle("Error");
            // Set the alert message
            alert.setContentText("Duplicate selection is not allowed in the same radio button group!");
            // Show the alert
            alert.showAndWait();
            return;
        }

        // Create and populate the DTO
        AcademicInterestDto dto = new AcademicInterestDto(dept, dept1, dept2, dept3);

        // Call the service to process the DTO
        processAcademicInterest(dto);
    }

    private String getSelectedToggleText(ToggleGroup group) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        return selectedRadioButton != null ? selectedRadioButton.getText() : null;
    }

    private boolean hasDuplicateSelection(String... departments) {
        // Check if any duplicate selections are found
        for (int i = 0; i < departments.length; i++) {
            for (int j = i + 1; j < departments.length; j++) {
                if (departments[i] != null && departments[i].equals(departments[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    @FXML
    void handleBack(ActionEvent event) {
        // Add functionality for handling back button action
    }
}
