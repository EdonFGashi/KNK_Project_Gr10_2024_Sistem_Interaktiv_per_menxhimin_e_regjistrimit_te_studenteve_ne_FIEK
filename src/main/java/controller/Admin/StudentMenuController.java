package controller.Admin;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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

    private static int activeSection;

    @FXML
    private void initialize(){
        if(activeSection == 1) {
            hboxShowStudents.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        } else if (activeSection == 2) {
            hboxAddStudent.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        }else if (activeSection ==3) {
            hboxEditStudent.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        } else if (activeSection ==4) {
            hboxGenerateCredentials.setStyle("-fx-background-color: #A5CEF2; -fx-background-radius:10px;");
        }

    }

    @FXML
    private void handleShowStudentsClick(MouseEvent me){
        setActiveSection(1);
        Navigatior.navigate(me, Navigatior.ADMIN_RIBBON, Navigatior.ADMIN_STUDENTMENU,Navigatior.ADMIN_PROFILE);
    }

    @FXML
    private void handleAddStudentClick(MouseEvent me){
        setActiveSection(2);
        Navigatior.navigate(me, Navigatior.ADMIN_RIBBON, Navigatior.ADMIN_STUDENTMENU,"");
    }

    @FXML
    private void handleEditStudentClick(MouseEvent me){
        setActiveSection(3);
        Navigatior.navigate(me, Navigatior.ADMIN_RIBBON, Navigatior.ADMIN_STUDENTMENU,"");
    }

    @FXML
    private void handleGenerateCredentialsClick(MouseEvent me){
        setActiveSection(4);
        Navigatior.navigate(me, Navigatior.ADMIN_RIBBON, Navigatior.ADMIN_STUDENTMENU,"");
    }

    private void setActiveSection(int num) {
        activeSection = num;
        refreshActiveSection();
    }

    private void refreshActiveSection(){
        hboxShowStudents.setStyle("-fx-background-color: white;");
        hboxEditStudent.setStyle("-fx-background-color: white;");
        hboxAddStudent.setStyle("-fx-background-color: white;");
        hboxGenerateCredentials.setStyle("-fx-background-color: white;");
    }

}


