package controller;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AdminRibbonController {
    @FXML
    private void handleSupervisorMenagmentClick(MouseEvent me){

    }
    @FXML
    private void handleStudentMenagmentClick(MouseEvent me){
        Navigatior.navigateMenu(me,Navigatior.ADMIN_RIBBON, Navigatior.ADMIN_STUDENTMENU,Navigatior.ADMIN_STUDENTMENU_ADDSTUDENT);
    }
    @FXML
    private void handleInboxClick(MouseEvent me){

    }
    @FXML
    private void handleProfileClick(MouseEvent me){
        
    }
}
