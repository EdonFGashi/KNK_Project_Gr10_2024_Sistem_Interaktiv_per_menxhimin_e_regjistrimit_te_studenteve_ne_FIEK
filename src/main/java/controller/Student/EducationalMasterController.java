package controller.Student;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.dto.Student.MasterApplicantDto;
import model.dto.Student.StudentApplicantDto;
import repository.StudentApplicant.StudentApplicantRepository;
import service.Student.StudentApplicantService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EducationalMasterController {

    @FXML
    private AnchorPane addPane;

    @FXML
    private Button btnChooseFile1;

    @FXML
    private Button btnChooseFile2;

    @FXML
    private ImageView imgBachelorDegree;

    @FXML
    private ImageView imgIdeentification;

    @FXML
    private TextField txtDept;

    @FXML
    private TextField txtFaculty;

    @FXML
    private TextField txtFirstYear;

    @FXML
    private TextField txtSecYear;

    @FXML
    private TextField txtThirdYear;

    @FXML
    private ChoiceBox<String> choiceBoxDept;

    public File imageFile1;
    public File imageFile2;

    @FXML
    public void initialize() {
        
        choiceBoxDept.getItems().addAll("IKS", "EAR", "EE", "TIK");

       
        choiceBoxDept.getSelectionModel().selectFirst();
        addTextFieldListeners();
    }

    public String getValue() {
        return choiceBoxDept.getValue();
    }

    @FXML
    void handleBack(ActionEvent event) {
Navigatior.navigate(addPane,Navigatior.PERSONAL_INFO);
        SESSION.setCurrentPage(Navigatior.PERSONAL_INFO);
    }

    @FXML
    void handleContinue(ActionEvent event) {


        String faculty = txtFaculty.getText();
        Double firstYear = Double.parseDouble(txtFirstYear.getText());
        Double secondYear = Double.parseDouble(txtSecYear.getText());
        Double thirdYear = Double.parseDouble(txtThirdYear.getText());
        String deptName = this.getValue();

        MasterApplicantDto dto = new MasterApplicantDto(SESSION.getLoggedUser().getId(), faculty, firstYear, secondYear, thirdYear, imageFile1, imageFile2, deptName);

        try {
           if( StudentApplicantService.processAndSaveMasterData(dto)){
               if(StudentApplicantRepository.UpdateApplicationStatus(SESSION.getLoggedUser().getId())) {
                   System.out.println("Tabela u be update!");

               }
               }

        } catch (Exception e) {
            e.printStackTrace();
        }
        navigateToNewStage(event, Navigatior.STUDENT_RIBBON);
        SESSION.setCurrentPage(Navigatior.STUDENT_RIBBON);

    }

    @FXML
    void handleFile1(ActionEvent event) {
        imageFile1 = chooseImage(imgIdeentification);
    }

    @FXML
    void handleFile2(ActionEvent event) {
        imageFile2 = chooseImage(imgBachelorDegree);
    }

    private File chooseImage(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                imageView.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return selectedFile;
    }


    private void navigateToNewStage(ActionEvent event, String fxmlPath) {
        Stage stage = new Stage();
        stage.setMaximized(true);
        Navigatior.navigate(stage, fxmlPath);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));

    }

    private void addTextFieldListeners() {
    txtFirstYear.setTextFormatter(createDoubleTextFormatter(6.0, 10.0));
    txtSecYear.setTextFormatter(createDoubleTextFormatter(6.0, 10.0));
    txtThirdYear.setTextFormatter(createDoubleTextFormatter(6.0, 10.0));

    txtFirstYear.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtFirstYear, 6.0, 10.0));
    txtSecYear.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtSecYear, 6.0, 10.0));
    txtThirdYear.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtThirdYear, 6.0, 10.0));
}



private void validateTextField(TextField textField, double min, double max) {
    String text = textField.getText();
    if (text.isEmpty() || text.equals(".")) {
        applyErrorStyle(textField, min, max);
        return;
    }
    try {
        double value = Double.parseDouble(text);
        if (value < min || value > max) {
            applyErrorStyle(textField, min, max);
        } else {
            removeErrorStyle(textField);
        }
    } catch (NumberFormatException e) {
        applyErrorStyle(textField, min, max);
    }
}

private void applyErrorStyle(TextField textField, double min, double max) {
    textField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    textField.setTooltip(new Tooltip("Value must be between " + min + " and " + max));
}

private void removeErrorStyle(TextField textField) {
    textField.setStyle("");
    textField.setTooltip(null);
}

private TextFormatter<Double> createDoubleTextFormatter(double min, double max) {
    return new TextFormatter<>(change -> {
        if (change.getControlNewText().isEmpty()) {
            return change;
        }
        try {
            String newText = change.getControlNewText();
            if (newText.matches("^[1-9]?$|10(\\.0*)?$")) {
                return change;
            }
        } catch (NumberFormatException e) {
            // Not a valid double
        }
        return null;
    });
}
}
