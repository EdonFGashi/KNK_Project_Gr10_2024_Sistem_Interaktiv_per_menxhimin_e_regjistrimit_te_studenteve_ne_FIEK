package controller.Supervisor;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class RibbonController {
    @FXML
    private void handleSupervisorMenagmentClick(MouseEvent me){
        Navigatior.navigate(me,Navigatior.SUPERVISOR_RIBBON,"");
    }
    @FXML
    private void handleStudentMenagmentClick(MouseEvent me){
        Navigatior.navigate(me,Navigatior.SUPERVISOR_RIBBON, Navigatior.SUPERVISOR_MENU);
    }
    @FXML
    private void handleInboxClick(MouseEvent me){
        Navigatior.navigate(me,Navigatior.SUPERVISOR_RIBBON,"");
    }
    @FXML
    private void handleProfileClick(MouseEvent me){
        Navigatior.navigate(me, Navigatior.SUPERVISOR_RIBBON,Navigatior.SUPERVISOR_MENU);
    }
}
