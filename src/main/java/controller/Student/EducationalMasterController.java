package controller.Student;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        // Shtoni opsionet në ChoiceBox në momentin e inicializimit të kontrollorit
        choiceBoxDept.getItems().addAll("IKS", "EAR", "EE", "TIK");

        // Për të bërë opsionin e parë si default
        choiceBoxDept.getSelectionModel().selectFirst();
    }

    // Metoda për të marrë opsionin e zgjedhur
    public String getValue() {
        return choiceBoxDept.getValue();
    }

    @FXML
    void handleBack(ActionEvent event) {
Navigatior.navigate(addPane,Navigatior.PERSONAL_INFO);
    }

    @FXML
    void handleContinue(ActionEvent event) {
//        if (!allFieldsAreFilled()) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText(null);
//            alert.setContentText("Please fill in all fields before continuing!");
//            alert.showAndWait();
//            return;
//        }

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
            // Trego një mesazh suksesi
        } catch (Exception e) {
            e.printStackTrace();
            // Trego një mesazh gabimi
        }
        navigateToNewStage(event, Navigatior.STUDENT_RIBBON);

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

    private boolean allFieldsAreFilled() {
        return !txtFaculty.getText().isEmpty() &&
                !txtFirstYear.getText().isEmpty() &&
                !txtSecYear.getText().isEmpty() &&
                !txtThirdYear.getText().isEmpty() &&
                imageFile1 != null &&
                imageFile2 != null;
    }
    private void navigateToNewStage(ActionEvent event, String fxmlPath) {
        Stage stage = new Stage();
        stage.setMaximized(true);
        Navigatior.navigate(stage, fxmlPath);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));

    }
}
