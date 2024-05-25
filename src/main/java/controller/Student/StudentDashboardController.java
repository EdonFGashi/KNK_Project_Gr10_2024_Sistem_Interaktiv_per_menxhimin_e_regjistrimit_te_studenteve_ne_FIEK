package controller.Student;

import app.Navigatior;
import controller.SESSION;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Afat;
import model.User;
import model.ApplicationStatus;
import service.Student.StudentApplicantService;
import service.Student.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static controller.SESSION.setDeptLevel;

public class StudentDashboardController {
    ObservableList<String> nivelet = FXCollections.observableArrayList("BSC", "MSC", "PHD");
    @FXML
    private AnchorPane addPane;
    @FXML
    private Label lblNjoftim;
    @FXML
    private ChoiceBox<String> choiseChoseAfat;

    @FXML
    private ChoiceBox<String> choiseChoseLevel;

    @FXML
    private TableColumn<ApplicationStatus, String> columnApplicationName;
    @FXML
    private TableColumn<ApplicationStatus, LocalDateTime> columnLastEdited;
    @FXML
    private TableColumn<ApplicationStatus, String> columnStatus;

    @FXML
    private TableView<ApplicationStatus> tableStudent;
    private ObservableList<ApplicationStatus> applicationStatusList;

    @FXML
    private Button btnFilloAplikimin;
    private ObservableList<Afat> SelectAfatList;

    // Mapping from formatted Afat string to Afat ID
    private Map<String, Integer> afatStringToIdMap = new HashMap<>();

    @FXML
    private void initialize() {

        // Add options to ChoiceBox
        this.choiseChoseLevel.setItems(nivelet);

        // Add a listener to monitor selection changes
        this.choiseChoseLevel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Update the session level
                    setDeptLevel(newValue);
                    System.out.println(SESSION.getDeptLevel());

                    // Fetch and update the Afat list based on the selected level
                    updateAfatChoiceBox(newValue);
                }
            }


        });

        // Add a listener to choiseChoseAfat to store the selected Afat ID
        this.choiseChoseAfat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null && afatStringToIdMap.containsKey(newValue)) {
                    // Store the selected Afat ID
                    SESSION.setAplicantAfatID(afatStringToIdMap.get(newValue));
                    System.out.println(SESSION.getAplicantAfatId());
                }
            }
        });
        System.out.println("SESSION DEPARTAMENTI"+SESSION.getDeptLevel());
        // Initial population based on the default or initial level
        updateAfatChoiceBox(SESSION.getDeptLevel());

        // Disable the "Fillo Aplikimin" button if level and afat are not selected
        btnFilloAplikimin.setDisable(true);
        choiseChoseLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFilloAplikiminButtonState());
        choiseChoseAfat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFilloAplikiminButtonState());

        loadExistingApplications();
        // Load existing applications for the user

    }

    private void updateAfatChoiceBox(String level) {
        this.SelectAfatList = StudentApplicantService.searchAfatByLevel(level);

        ObservableList<String> formattedAfatList = FXCollections.observableArrayList();
        afatStringToIdMap.clear();

        if (SelectAfatList != null && !SelectAfatList.isEmpty()) {
            for (Afat afat : SelectAfatList) {
                String formattedAfat = formatAfat(afat);
                formattedAfatList.add(formattedAfat);
                afatStringToIdMap.put(formattedAfat, afat.getId());
            }
        } else {
            // Add the message when no Afat entries are found
            formattedAfatList.add("nuk ka ndonje afat");
        }

        // Set the items in the ChoiceBox
        choiseChoseAfat.setItems(formattedAfatList);
    }

    private String formatAfat(Afat afat) {
        String heraDescription = afat.getHera().equals("1") ? "afat i parë" : "afat i dytë";
        return afat.getNiveli() + " - " + heraDescription;
    }
    private void updateFilloAplikiminButtonState() {
        boolean shouldDisable = choiseChoseLevel.getValue() == null || choiseChoseAfat.getValue() == null || "Submitted".equals(btnFilloAplikimin.getText());
        btnFilloAplikimin.setDisable(shouldDisable);
        if (shouldDisable) {
            System.out.println("Button should be disabled. Current text: " + btnFilloAplikimin.getText());
        }}
    @FXML
    void handleFilloAplikimin(ActionEvent event) {
        if(btnFilloAplikimin.getText().equals("Fillo Aplikimin")){
        User user = SESSION.getLoggedUser();// Assuming this method gets the user ID from session
        int userID=user.getId();
        String applicationName = choiseChoseLevel.getValue() + " - " + choiseChoseAfat.getValue().substring(5);


        ApplicationStatus appStatus = new ApplicationStatus(userID, "processing", LocalDateTime.now(), applicationName);

        try {
            UserService.saveAplicStatus(appStatus);
            loadExistingApplications(); // Refresh the table
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            // Handle exception (e.g., show error message to user)
        }}
        navigateToNewStage(event, Navigatior.STUDENT_MENU);
    }

    private void loadExistingApplications() {
        // Fetch and load existing applications for the user
        // This is a placeholder. You need to implement the method to fetch data from the database.
        // Example:
        // Configure table columns
        columnApplicationName.setCellValueFactory(new PropertyValueFactory<ApplicationStatus, String>("applicationName"));
        columnLastEdited.setCellValueFactory(new PropertyValueFactory<ApplicationStatus, LocalDateTime>("editTime"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<ApplicationStatus, String>("submissionStatus"));
        User user = SESSION.getLoggedUser();
        int userID = user.getId();
        this.applicationStatusList=UserService.getApplicationsForUser(userID);
        System.out.println(applicationStatusList);
        this. tableStudent.setItems(applicationStatusList);
        updateButtonTextBasedOnTable();

    }
    private void updateButtonTextBasedOnTable() {
        if (tableStudent.getItems().isEmpty()) {
            btnFilloAplikimin.setText("Fillo Aplikimin");
            btnFilloAplikimin.setDisable(true);
        } else {
            boolean isSubmitted = false;
            for (ApplicationStatus application : tableStudent.getItems()) {
                if ("Submitted".equals(application.getSubmissionStatus())) {
                    lblNjoftim.setText("Your Application was Submitted successfully!");
                    btnFilloAplikimin.setText("Submitted");
                    btnFilloAplikimin.setDisable(true);
                    isSubmitted = true;
                    break;
                }
            }
            if (!isSubmitted) {
                btnFilloAplikimin.setText("Vazhdo Aplikimin");
                btnFilloAplikimin.setDisable(false);
            }
            selectValuesForChoiceBoxesBasedOnTableContent();
        }
        updateFilloAplikiminButtonState();
    }


    private void selectValuesForChoiceBoxesBasedOnTableContent() {
        for (ApplicationStatus application : tableStudent.getItems()) {
            String applicationName = application.getApplicationName();
            // Për të gjetur vlerën e nivelit dhe afatit nga emri i aplikacionit
            String[] parts = applicationName.split(" - ");
            String level = parts[0]; // Niveli
            String afatDescription = parts[1]; // Afati (afati i parë ose i dytë)

            // Vërejtje: Kontrolloni nëse këto vlera përputhen me listat tuaja të niveleve dhe afateve
            choiseChoseLevel.setValue(level);
            choiseChoseAfat.setValue(afatDescription);

            // Ruaj nivelin në SESSION
            SESSION.setDeptLevel(level);

            // Gjej dhe ruaj ID-në e afatit në SESSION
            for (Map.Entry<String, Integer> entry : afatStringToIdMap.entrySet()) {
                if (entry.getKey().contains(afatDescription)) {
                    SESSION.setAplicantAfatID(entry.getValue());
                    break;
                }
            }

            choiseChoseLevel.setDisable(true);
            choiseChoseAfat.setDisable(true);
            break; // Ndalo pasi të gjeni një aplikacion me tekst "Vazhdo Aplikimin"
        }
    }
    private void navigateToNewStage(ActionEvent event, String fxmlPath) {
        Stage stage = new Stage();
        stage.setMaximized(true);
        Navigatior.navigate(stage, fxmlPath);
        Navigatior.closeStageAfterDelay(event, Duration.millis(1));

    }

}
