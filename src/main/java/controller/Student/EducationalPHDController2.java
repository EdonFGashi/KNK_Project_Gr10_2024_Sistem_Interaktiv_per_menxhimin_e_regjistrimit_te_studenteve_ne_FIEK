package controller.Student;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.dto.Student.PHDApplicantDto;
import repository.StudentApplicant.StudentApplicantRepository;
import service.Student.StudentApplicantService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EducationalPHDController2 {

    @FXML
    private AnchorPane addPane;

    @FXML
    private ChoiceBox<String> choiseboxDept;

    @FXML
    private Button chooseImageButton1;

    @FXML
    private Button chooseImageButton2;

    @FXML
    private Button chooseImageButton3;

    @FXML
    private ImageView imgViewfile1;

    @FXML
    private ImageView imgViewfile2;

    @FXML
    private ImageView imgViewfile3;

    @FXML
    private TextField txtFaculty;

    @FXML
    private TextField txtSecondYGrade;

    @FXML
    private TextField txtfirstYGrade;

    public File imageFile1;
    public File imageFile2;
    public File imageFile3;

    public void initialize() {
        // Shtoni opsionet në ChoiceBox në momentin e inicializimit të kontrollorit
        choiseboxDept.getItems().addAll("IKS", "EAR", "EE", "TIK");

        // Për të bërë opsionin e parë si default
        choiseboxDept.getSelectionModel().selectFirst();
        addTextFieldListeners();
    }

    public String getValue() {
        return choiseboxDept.getValue();
    }

    @FXML
    void handleFile1(ActionEvent event) {
        imageFile1 = chooseImage(imgViewfile1);
    }

    @FXML
    void handleFile2(ActionEvent event) {
        imageFile2 = chooseImage(imgViewfile2);
    }

    @FXML
    void handleFile3(ActionEvent event) {
        imageFile3 = chooseImage(imgViewfile3);
    }

    @FXML
    void handleApply(ActionEvent event) {


        String Fakulteti = txtFaculty.getText();
        Double Mesatarjaviti1 = Double.parseDouble(txtfirstYGrade.getText());
        Double Mesatarjaviti2 = Double.parseDouble(txtSecondYGrade.getText());
        String deptName = this.getValue();

        PHDApplicantDto dto = new PHDApplicantDto(SESSION.getLoggedUser().getId(), Fakulteti, Mesatarjaviti1, Mesatarjaviti2, imageFile1, imageFile2, imageFile3, deptName);

        try {
            if(StudentApplicantService.processAndSavePHDData(dto)){
                if(StudentApplicantRepository.UpdateApplicationStatus(SESSION.getLoggedUser().getId())) {
                    System.out.println("Tabela u be update!");
                }
                }
            // Trego një mesazh suksesi
        } catch (Exception e) {
            e.printStackTrace();
            // Trego një mesazh gabimi
        }
        navigateToNewStage(event, Navigatior.STUDENT_RIBBON);
        SESSION.setCurrentPage(Navigatior.STUDENT_RIBBON);
    }

    @FXML
    void handleGoback(ActionEvent event) {
Navigatior.navigate(addPane,Navigatior.PERSONAL_INFO);
        SESSION.setCurrentPage(Navigatior.PERSONAL_INFO);
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
      //  Navigatior.closeStageAfterDelay(event, Duration.millis(1));

    }
    private void addTextFieldListeners() {
    txtfirstYGrade.setTextFormatter(createDoubleTextFormatter(6.0, 10.0));
    txtSecondYGrade.setTextFormatter(createDoubleTextFormatter(6.0, 10.0));

    txtfirstYGrade.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtfirstYGrade, 6.0, 10.0));
    txtSecondYGrade.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtSecondYGrade, 6.0, 10.0));
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
