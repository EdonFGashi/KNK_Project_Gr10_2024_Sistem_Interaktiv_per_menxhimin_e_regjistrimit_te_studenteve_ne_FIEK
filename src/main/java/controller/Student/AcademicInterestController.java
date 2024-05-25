package controller.Student;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.dto.Student.AcademicInterestDto;
import repository.StudentApplicant.StudentApplicantRepository;

import static service.Student.StudentApplicantService.processAcademicInterest;

public class AcademicInterestController {
    @FXML
    private AnchorPane addPane;
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
        if (dept == null || dept1 == null || dept2 == null || dept3 == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please make a selection in each radio button group before applying!");
            alert.showAndWait();
            return;
        }
        
        if (hasDuplicateSelection(dept, dept1, dept2, dept3)) {
          
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Duplicate selection is not allowed in the same radio button group!");
            alert.showAndWait();
            return;
        }

        // Create and populate the DTO
        AcademicInterestDto dto = new AcademicInterestDto(dept, dept1, dept2, dept3);

        // Call the service to process the DTO
       if( processAcademicInterest(dto)){

        if(StudentApplicantRepository.UpdateApplicationStatus(SESSION.getLoggedUser().getId())){
            System.out.println("Tabela u be update!");

        }}
        navigateToNewStage(event, Navigatior.STUDENT_RIBBON);
         Navigatior.closeStageAfterDelay(event, Duration.millis(10));
       SESSION.setCurrentPage(Navigatior.STUDENT_RIBBON);

    }

    private String getSelectedToggleText(ToggleGroup group) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        return selectedRadioButton != null ? selectedRadioButton.getText() : null;
    }

    private boolean hasDuplicateSelection(String... departments) {
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
        Navigatior.navigate(addPane,Navigatior.EDUCATION);
        SESSION.setCurrentPage(Navigatior.EDUCATION);
    }
    private void navigateToNewStage(ActionEvent event, String fxmlPath) {
        Stage stage = new Stage();
        stage.setMaximized(true);
        Navigatior.navigate(stage, fxmlPath);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));

    }
}
