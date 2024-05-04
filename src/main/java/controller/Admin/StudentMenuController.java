package controller.Admin;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class StudentMenuController {
    @FXML
    private void handleStatisticsClick(MouseEvent me){

    }
    @FXML
    private void handleAddStudentClick(MouseEvent me){
        Navigatior.navigate(me, Navigatior.ADMIN_RIBBON,"");
    }
    @FXML
    private void handleEditStudentClick(MouseEvent me){

    }
}
