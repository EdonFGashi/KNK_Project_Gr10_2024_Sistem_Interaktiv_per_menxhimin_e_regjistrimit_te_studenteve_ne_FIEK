package controller.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import model.dto.Student.StudentApplicantDto;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.File;
import static controller.SESSION.getLoggedUser;

import static controller.SESSION.getLoggedUser;

public class EducationalExperienceController {

    @FXML
    private Button chooseImageButton1;

    @FXML
    private Button chooseImageButton2;

    @FXML
    private Button chooseImageButton3;

    @FXML
    private ImageView imgDiplome;

    @FXML
    private ImageView imgGradeCertificate;

    @FXML
    private ImageView imgIdentification;

    @FXML
    private TextField txtAlbanian;

    @FXML
    private TextField txtAllPoints;

    @FXML
    private TextField txtChosenSubject;

    @FXML
    private TextField txtChosenSubjectPoints;

    @FXML
    private TextField txtEnglish;

    @FXML
    private TextField txtGrade10;

    @FXML
    private TextField txtGrade11;

    @FXML
    private TextField txtGrade12;

    @FXML
    private TextField txtMath;

    @FXML
    private TextField txtSchoolName;

    public File imageFile1;
    public File imageFile2;
    public File imageFile3;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleContinue(ActionEvent event) {
        String ShkMesme = txtSchoolName.getText();
        int Matematike =Integer.parseInt(txtMath.getText());
        int Shqip = Integer.parseInt(txtAlbanian.getText());
        int Anglisht = Integer.parseInt(txtEnglish.getText());
        String LendaZgjedhur = txtChosenSubject.getText();
        int PikatLendesZgjedhur = Integer.parseInt(txtChosenSubjectPoints.getText());
        int PikatGjithsej = Integer.parseInt(txtAllPoints.getText());
        Double Sukses10 = Double.parseDouble(txtGrade10.getText());
        Double Sukses11 = Double.parseDouble(txtGrade11.getText());
        Double Sukses12 = Double.parseDouble(txtGrade12.getText());

        StudentApplicantDto dto = new StudentApplicantDto(getLoggedUser().getId(), ShkMesme, Matematike, Shqip, Anglisht, LendaZgjedhur, PikatLendesZgjedhur,PikatGjithsej,Sukses10,Sukses11,Sukses12,imageFile1,imageFile2,imageFile3);

        try {
            service.Student.StudentApplicantService.processAndSaveData(dto);
            // Trego një mesazh suksesi
        } catch (Exception e) {
            e.printStackTrace();
            // Trego një mesazh gabimi
        }

    }

    @FXML
    void handleFile1(ActionEvent event) {
        imageFile1 = chooseImage(imgGradeCertificate);

    }

    @FXML
    void handleFile2(ActionEvent event) {

        imageFile2 = chooseImage(imgIdentification);
    }

    @FXML
    void handleFile3(ActionEvent event) {

        imageFile3 = chooseImage(imgDiplome);
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
