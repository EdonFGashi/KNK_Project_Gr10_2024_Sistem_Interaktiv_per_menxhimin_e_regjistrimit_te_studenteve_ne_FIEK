package controller.Student;

import app.Navigatior;
import controller.SESSION;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Afat;
import model.User;
import model.dto.Student.ApplicationStatusDto;
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
    private ChoiceBox<String> choiseChoseAfat;

    @FXML
    private ChoiceBox<String> choiseChoseLevel;

    @FXML
    private TableColumn<ApplicationStatusDto, String> columnApplicationName;
    @FXML
    private TableColumn<ApplicationStatusDto, LocalDateTime> columnLastEdited;
    @FXML
    private TableColumn<ApplicationStatusDto, String> columnStatus;
    @FXML
    private TableColumn<ApplicationStatusDto, Button> columnedit;
    @FXML
    private TableView<ApplicationStatusDto> tableStudent;
    private ObservableList<ApplicationStatusDto> applicationStatusList = FXCollections.observableArrayList();

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

        // Initial population based on the default or initial level
        updateAfatChoiceBox(SESSION.getDeptLevel());

        // Disable the "Fillo Aplikimin" button if level and afat are not selected
        btnFilloAplikimin.setDisable(true);
        choiseChoseLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFilloAplikiminButtonState());
        choiseChoseAfat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFilloAplikiminButtonState());

        // Configure table columns
        columnApplicationName.setCellValueFactory(new PropertyValueFactory<>("applicationName"));
        columnLastEdited.setCellValueFactory(new PropertyValueFactory<>("editTime"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("submissionStatus"));

        // Load existing applications for the user
        loadExistingApplications();

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
        btnFilloAplikimin.setDisable(choiseChoseLevel.getValue() == null || choiseChoseAfat.getValue() == null);
    }
    @FXML
    void handleFilloAplikimin(ActionEvent event) {
        User user = SESSION.getLoggedUser();// Assuming this method gets the user ID from session
        int userID=1;
        String applicationName = choiseChoseLevel.getValue() + " - " + choiseChoseAfat.getValue();


        ApplicationStatusDto appStatus = new ApplicationStatusDto(userID, "processing", LocalDateTime.now(), applicationName);

        try {
            UserService.saveAplicStatus(appStatus);
            loadExistingApplications(); // Refresh the table
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception (e.g., show error message to user)
        }
        Navigatior.navigate(addPane,Navigatior.STUDENT_MENU);
    }

    private void loadExistingApplications() {
        // Fetch and load existing applications for the user
        // This is a placeholder. You need to implement the method to fetch data from the database.
        // Example:
        // List<ApplicationStatusDto> applications = applicationStatusService.getApplicationsForUser(SESSION.getUserID());
        // applicationStatusList.setAll(applications);

        tableStudent.setItems(applicationStatusList);
    }}
