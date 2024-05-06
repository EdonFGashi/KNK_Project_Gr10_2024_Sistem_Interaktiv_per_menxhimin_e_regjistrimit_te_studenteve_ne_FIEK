package controller.Admin;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StudentMenuController {
    @FXML
    private HBox hboxShowStudents;
    @FXML
    private HBox hboxEditStudent;
    @FXML
    private HBox hboxAddStudent;
    @FXML
    private HBox hboxGenerateCredentials;
    @FXML
    private HBox addPane;


    private final String cssForActiveSection = "-fx-background-color: #A5CEF2; -fx-background-radius:10px;";

    @FXML
    private void initialize(){
        //Varesisht se qka u kliku e fundit kjo e tregon
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
        hboxShowStudents.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleShowStudentsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxShowStudents.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleAddStudentClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxAddStudent.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleEditStudentClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxEditStudent.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleGenerateCredentialsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxGenerateCredentials.setStyle(cssForActiveSection);
    }


    private void refreshActiveSection(){
        hboxShowStudents.setStyle("");
        hboxEditStudent.setStyle("");
        hboxAddStudent.setStyle("");
        hboxGenerateCredentials.setStyle("");
    }

}


