package controller.Student;

import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.dto.Student.MasterApplicantDto;
import model.dto.Student.StudentApplicantDto;

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

    }

    @FXML
    void handleContinue(ActionEvent event) {
        String faculty = txtFaculty.getText();
        Double firstYear = Double.parseDouble(txtFirstYear.getText());
        Double secondYear = Double.parseDouble(txtSecYear.getText());
        Double thirdYear = Double.parseDouble(txtThirdYear.getText());
        String deptName=this.getValue();

        // e shtove SESSION para getLoggedUser()
        MasterApplicantDto dto = new MasterApplicantDto(SESSION.getLoggedUser().getId(),faculty,firstYear,secondYear,thirdYear,imageFile1,imageFile2,deptName);

        try {
            service.Student.StudentApplicantService.processAndSaveMasterData(dto);
            // Trego një mesazh suksesi
        } catch (Exception e) {
            e.printStackTrace();
            // Trego një mesazh gabimi
        }

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
        fileChooser.setTitle("Zgjidh një Foto");
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


}
