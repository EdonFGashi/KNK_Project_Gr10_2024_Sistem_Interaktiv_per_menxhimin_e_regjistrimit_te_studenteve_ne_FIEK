package controller.Admin;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class StudentMenuController {
    @FXML
    private HBox hboxShowStudents;
    @FXML
    private HBox hboxEditStudent;
    @FXML
    private HBox addPane;
    @FXML
    private HBox hboxStatistics;


    @FXML
    private void initialize(){
        //Varesisht se qka u kliku e fundit kjo e tregon
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU_EDITSTUDENT);
        this.hboxShowStudents.setStyle(Navigatior.cssForActiveSection);
    }

    @FXML
    private void handleShowStudentsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
        refreshActiveSection();
        hboxShowStudents.setStyle(Navigatior.cssForActiveSection);
    }

    @FXML
    private void handleEditStudentClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU_EDITSTUDENT);
        refreshActiveSection();
        hboxEditStudent.setStyle(Navigatior.cssForActiveSection);
    }
    @FXML
    private void handleStatisticsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU_EDITSTUDENT);
        refreshActiveSection();
        hboxStatistics.setStyle(Navigatior.cssForActiveSection);
    }


    private void refreshActiveSection(){
        hboxShowStudents.setStyle("");
        hboxEditStudent.setStyle("");
        hboxStatistics.setStyle("");
    }

}


