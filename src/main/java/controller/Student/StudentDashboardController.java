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

    @FXML
    private void initialize() {
        // Shto opsionet në ChoiceBox
        this.choiseChoseLevel.setItems(nivelet);

        // Shto një listener për të dëgjuar ndryshimet në zgjedhje
        this.choiseChoseLevel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Kontrollo zgjedhjen dhe thirr metodën përkatëse
                    if (newValue.equals("Master")) {
                        setDeptLevel("Master");
                        System.out.println(SESSION.getDeptLevel());
                    } else if (newValue.equals("Bachelor")) {
                        setDeptLevel("Bachelor");
                        System.out.println(SESSION.getDeptLevel());
                    } else if (newValue.equals("Doctorature")) {
                        setDeptLevel("Doctorature");
                        System.out.println(SESSION.getDeptLevel());
                    }
                }
            }
        });
    }

    // Metoda për të ruajtur nivelin e zgjedhur

}
