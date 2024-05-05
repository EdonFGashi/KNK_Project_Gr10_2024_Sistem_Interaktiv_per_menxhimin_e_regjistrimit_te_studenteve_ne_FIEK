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
    private StackPane addPane;


    private static int activeSection;

    @FXML
    private void initialize(){
        //Varesisht se qka u kliku e fundit kjo e tregon
        if(activeSection == 1) {
            hboxShowStudents.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        } else if (activeSection == 2) {
            hboxAddStudent.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        }else if (activeSection ==3) {
            hboxEditStudent.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        } else if (activeSection ==4) {
            hboxGenerateCredentials.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        }else{
            hboxShowStudents.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        }
    }

    @FXML
    private void handleShowStudentsClick(MouseEvent me){
        Navigatior.addPane(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxShowStudents.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
    }

    @FXML
    private void handleAddStudentClick(MouseEvent me){
        Navigatior.addPane(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxAddStudent.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
    }

    @FXML
    private void handleEditStudentClick(MouseEvent me){
        Navigatior.addPane(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxEditStudent.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
    }

    @FXML
    private void handleGenerateCredentialsClick(MouseEvent me){
        Navigatior.addPane(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxGenerateCredentials.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
    }



    private void setActiveSection(int num) {
        activeSection = num;
        refreshActiveSection();
    }

    private void refreshActiveSection(){
        hboxShowStudents.setStyle("");
        hboxEditStudent.setStyle("");
        hboxAddStudent.setStyle("");
        hboxGenerateCredentials.setStyle("");
    }

}


