package controller.Student;

import controller.SESSION;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Afat;
import service.Student.StudentApplicantService;

import java.util.HashMap;
import java.util.Map;

import static controller.SESSION.setDeptLevel;

public class StudentDashboardController {
    ObservableList<String> nivelet = FXCollections.observableArrayList("Bachelor", "Master", "Doctorature");

    @FXML
    private ChoiceBox<String> choiseChoseAfat;

    @FXML
    private ChoiceBox<String> choiseChoseLevel;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private TableColumn<?, ?> columnFirstName;

    @FXML
    private TableColumn<?, ?> columnLastName;

    @FXML
    private TableView<?> tableStudent;

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
}
