package controller.Student;

import app.Navigatior;
import controller.SESSION;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import model.ShkollaMesme;
import model.dto.Student.StudentApplicantDto;
import repository.StudentRepository;
import service.Student.StudentApplicantService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import static controller.SESSION.getLoggedUser;

public class EducationalExperienceController {

    @FXML
    private AnchorPane addpane;

    @FXML
    private Button chooseImageButton1;

    @FXML
    private Button chooseImageButton2;

    @FXML
    private Button chooseImageButton3;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnContinue;

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

    private boolean Save=true;

    @FXML
    void initialize() {
        addTextFieldListeners();
        this.setTextFields();
        calculateTotalPoints();  
        txtAllPoints.setDisable(true);
    }

    @FXML
    void handleBack(ActionEvent event) {
Navigatior.navigate(addpane,Navigatior.PERSONAL_INFO);
SESSION.setCurrentPage(Navigatior.PERSONAL_INFO);
    }

    @FXML
    void handleContinue(ActionEvent event) {

        if(this.txtSchoolName.isDisabled()){
            Navigatior.navigate(addpane, Navigatior.ACADEMIC);
            SESSION.setCurrentPage(Navigatior.ACADEMIC);
            return;
        }
       if (!allFieldsAreFilled()) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning");
           alert.setHeaderText(null);
           alert.setContentText("Please fill in all fields before continuing!");
           alert.showAndWait();
           return;
       }
if(this.Save){
        String ShkMesme = txtSchoolName.getText();
        int Matematike = Integer.parseInt(txtMath.getText());
        int Shqip = Integer.parseInt(txtAlbanian.getText());
        int Anglisht = Integer.parseInt(txtEnglish.getText());
        String LendaZgjedhur = txtChosenSubject.getText();
        int PikatLendesZgjedhur = Integer.parseInt(txtChosenSubjectPoints.getText());
        int PikatGjithsej = Integer.parseInt(txtAllPoints.getText());
        Double Sukses10 = Double.parseDouble(txtGrade10.getText());
        Double Sukses11 = Double.parseDouble(txtGrade11.getText());
        Double Sukses12 = Double.parseDouble(txtGrade12.getText());

        StudentApplicantDto dto = new StudentApplicantDto(SESSION.getLoggedUser().getId(), ShkMesme, Matematike, Shqip, Anglisht, LendaZgjedhur, PikatLendesZgjedhur, PikatGjithsej, Sukses10, Sukses11, Sukses12, imageFile1, imageFile2, imageFile3);

        try {
            StudentApplicantService.processAndSaveData(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }}
        Navigatior.navigate(addpane, Navigatior.ACADEMIC);
SESSION.setCurrentPage(Navigatior.ACADEMIC);
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

    // private void addTextFieldListeners() {
    //     txtAlbanian.textProperty().addListener((observable, oldValue, newValue) -> calculateTotalPoints());
    //     txtEnglish.textProperty().addListener((observable, oldValue, newValue) -> calculateTotalPoints());
    //     txtMath.textProperty().addListener((observable, oldValue, newValue) -> calculateTotalPoints());
    //     txtChosenSubjectPoints.textProperty().addListener((observable, oldValue, newValue) -> calculateTotalPoints());
    // }

    private void calculateTotalPoints() {
        try {
            int albanianPoints = Integer.parseInt(txtAlbanian.getText());
            int englishPoints = Integer.parseInt(txtEnglish.getText());
            int mathPoints = Integer.parseInt(txtMath.getText());
            int chosenSubjectPoints = Integer.parseInt(txtChosenSubjectPoints.getText());
            int totalPoints = albanianPoints + englishPoints + mathPoints + chosenSubjectPoints;
            txtAllPoints.setText(String.valueOf(totalPoints));
        } catch (NumberFormatException e) {
            txtAllPoints.setText("");
        }
    }

    private boolean allFieldsAreFilled() {
        return !txtSchoolName.getText().isEmpty() &&
                !txtMath.getText().isEmpty() &&
                !txtAlbanian.getText().isEmpty() &&
                !txtEnglish.getText().isEmpty() &&
                !txtChosenSubject.getText().isEmpty() &&
                !txtChosenSubjectPoints.getText().isEmpty() &&
                !txtGrade10.getText().isEmpty() &&
                !txtGrade11.getText().isEmpty() &&
                !txtGrade12.getText().isEmpty() &&
                imageFile1 != null &&
                imageFile2 != null &&
                imageFile3 != null;
    }

    private void setTextFields(){

        ShkollaMesme shkollaMesme = StudentRepository.getShkollaMeme(SESSION.getLoggedUser().getId());

        if(shkollaMesme != null){
            this.txtSchoolName.setText(shkollaMesme.getEmriShkolles());
            this.txtMath.setText(Integer.toString(shkollaMesme.getPiketMat()));
            this.txtAlbanian.setText(Integer.toString(shkollaMesme.getPiketGjSh()));
            this.txtEnglish.setText(Integer.toString(shkollaMesme.getPiketAng()));
            this.txtChosenSubject.setText(shkollaMesme.getLendaZgjedhore());
            this.txtChosenSubjectPoints.setText(Integer.toString(shkollaMesme.getPiketZgjedhore()));
            int total = shkollaMesme.getPiketAng()+ shkollaMesme.getPiketGjSh()+ shkollaMesme.getPiketMat()+ shkollaMesme.getPiketZgjedhore();
            this.txtAllPoints.setText(Integer.toString(total));
            this.txtGrade10.setText(Integer.toString(shkollaMesme.getSuksesiKl10()));
            this.txtGrade11.setText(Integer.toString(shkollaMesme.getSuksesiKl11()));
            this.txtGrade12.setText(Integer.toString(shkollaMesme.getSuksesiKl12()));

            setImageToImageView(shkollaMesme.getCertifikataNotave(), this.imgGradeCertificate);
            setImageToImageView(shkollaMesme.getLeternjoftimi(), this.imgIdentification);
            setImageToImageView(shkollaMesme.getDiplomashkolles(), this.imgDiplome);
            this.disableAllFieldsAndButtons();
            this.Save=false;

        }else{
            this.txtSchoolName.setText("");
            this.txtMath.setText("");
            this.txtAlbanian.setText("");
            this.txtEnglish.setText("");
            this.txtChosenSubject.setText("");
            this.txtChosenSubjectPoints.setText("");
            this.txtAllPoints.setText("");
            this.txtGrade10.setText("");
            this.txtGrade11.setText("");
            this.txtGrade12.setText("");

            this.imgGradeCertificate.setImage(null);
            this.imgIdentification.setImage(null);
            this.imgDiplome.setImage(null);

        }

    }
    private void setImageToImageView(Image image, ImageView imageView) {
        if (image != null) {
            imageView.setImage(image);
        } else {
            try {
                imageView.setImage(new Image(new FileInputStream("Images/Error404")));
            }catch(Exception e){
                //
            }
        }
    }

    private void disableAllFieldsAndButtons() {
        chooseImageButton1.setDisable(true);
        chooseImageButton2.setDisable(true);
        chooseImageButton3.setDisable(true);
        txtAlbanian.setDisable(true);
        txtAllPoints.setDisable(true);
        txtChosenSubject.setDisable(true);
        txtChosenSubjectPoints.setDisable(true);
        txtEnglish.setDisable(true);
        txtGrade10.setDisable(true);
        txtGrade11.setDisable(true);
        txtGrade12.setDisable(true);
        txtMath.setDisable(true);
        txtSchoolName.setDisable(true);

    }

    private void addTextFieldListeners() {
    txtAlbanian.setTextFormatter(createIntegerTextFormatter(0, 25));
    txtEnglish.setTextFormatter(createIntegerTextFormatter(0, 25));
    txtMath.setTextFormatter(createIntegerTextFormatter(0, 25));
    txtChosenSubjectPoints.setTextFormatter(createIntegerTextFormatter(0, 25));
    txtGrade10.setTextFormatter(createDoubleTextFormatter(1.0, 5.0));
    txtGrade11.setTextFormatter(createDoubleTextFormatter(1.0, 5.0));
    txtGrade12.setTextFormatter(createDoubleTextFormatter(1.0, 5.0));

    txtAlbanian.textProperty().addListener((observable, oldValue, newValue) -> {
        validateTextField(txtAlbanian, 0, 25);
        calculateTotalPoints();
    });
    txtEnglish.textProperty().addListener((observable, oldValue, newValue) -> {
        validateTextField(txtEnglish, 0, 25);
        calculateTotalPoints();
    });
    txtMath.textProperty().addListener((observable, oldValue, newValue) -> {
        validateTextField(txtMath, 0, 25);
        calculateTotalPoints();
    });
    txtChosenSubjectPoints.textProperty().addListener((observable, oldValue, newValue) -> {
        validateTextField(txtChosenSubjectPoints, 0, 25);
        calculateTotalPoints();
    });
    txtGrade10.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtGrade10, 1.0, 5.0));
    txtGrade11.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtGrade11, 1.0, 5.0));
    txtGrade12.textProperty().addListener((observable, oldValue, newValue) -> validateTextField(txtGrade12, 1.0, 5.0));
}

private void validateTextField(TextField textField, double min, double max) {
    try {
        double value = Double.parseDouble(textField.getText());
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
    Tooltip tooltip = new Tooltip("Vlera duhet të jetë midis " + min + " dhe " + max);
    tooltip.setStyle("-fx-background-color: red; -fx-text-fill: white;");
    textField.setTooltip(tooltip);
}

private void removeErrorStyle(TextField textField) {
    textField.setStyle("");
    textField.setTooltip(null);
}
private TextFormatter<Integer> createIntegerTextFormatter(int min, int max) {
    return new TextFormatter<>(change -> {
        if (change.getControlNewText().isEmpty()) {
            return change;
        }
        try {
            int value = Integer.parseInt(change.getControlNewText());
            if (value >= min && value <= max) {
                return change;
            }
        } catch (NumberFormatException e) {
            // Not a valid integer
        }
        return null;
    });
}

private TextFormatter<Double> createDoubleTextFormatter(double min, double max) {
    return new TextFormatter<>(change -> {
        if (change.getControlNewText().isEmpty()) {
            return change;
        }
        try {
            double value = Double.parseDouble(change.getControlNewText());
            if (value >= min && value <= max) {
                return change;
            }
        } catch (NumberFormatException e) {
            // Not a valid double
        }
        return null;
    });
  }
}
