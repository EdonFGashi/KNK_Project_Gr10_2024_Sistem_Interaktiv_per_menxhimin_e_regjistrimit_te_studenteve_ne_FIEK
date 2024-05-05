package controller.Admin;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class RibbonController {
    @FXML
    private void handleSupervisorMenagmentClick(MouseEvent me){
        Navigatior.navigate(me,Navigatior.ADMIN_RIBBON,"");
    }
    @FXML
    private void handleStudentMenagmentClick(MouseEvent me){
        Navigatior.navigate(me,Navigatior.ADMIN_RIBBON, Navigatior.ADMIN_STUDENTMENU, Navigatior.ADMIN_STUDENTMENU_ADDSTUDENT);
    }
    @FXML
    private void handleInboxClick(MouseEvent me){
        Navigatior.navigate(me,Navigatior.ADMIN_RIBBON,"");
    }
    @FXML
    private void handleProfileClick(MouseEvent me){
        Navigatior.navigate(me,Navigatior.ADMIN_RIBBON,Navigatior.ADMIN_PROFILE);
    }
}
