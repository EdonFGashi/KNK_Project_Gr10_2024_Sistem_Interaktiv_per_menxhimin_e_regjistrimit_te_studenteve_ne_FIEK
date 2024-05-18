package controller.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

        // Create and populate the DTO
        AcademicInterestDto dto = new AcademicInterestDto(dept, dept1, dept2, dept3);

        // Call the service to process the DTO
        processAcademicInterest(dto);
    }

    private String getSelectedToggleText(ToggleGroup group) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        return selectedRadioButton != null ? selectedRadioButton.getText() : null;
    }


    @FXML
    void handleBack(ActionEvent event) {

    }

}
