package controller.Student;

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
import model.dto.Student.PHDApplicantDto;
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
        if (!allFieldsAreFilled()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields before continuing!");
            alert.showAndWait();
            return;
        }

        String Fakulteti = txtFaculty.getText();
        Double Mesatarjaviti1 = Double.parseDouble(txtfirstYGrade.getText());
        Double Mesatarjaviti2 = Double.parseDouble(txtSecondYGrade.getText());
        String deptName = this.getValue();

        PHDApplicantDto dto = new PHDApplicantDto(SESSION.getLoggedUser().getId(), Fakulteti, Mesatarjaviti1, Mesatarjaviti2, imageFile1, imageFile2, imageFile3, deptName);

        try {
            StudentApplicantService.processAndSavePHDData(dto);
            // Trego një mesazh suksesi
        } catch (Exception e) {
            e.printStackTrace();
            // Trego një mesazh gabimi
        }
    }

    @FXML
    void handleGoback(ActionEvent event) {

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
                !txtfirstYGrade.getText().isEmpty() &&
                !txtSecondYGrade.getText().isEmpty() &&
                imageFile1 != null &&
                imageFile2 != null &&
                imageFile3 != null;
    }
}
