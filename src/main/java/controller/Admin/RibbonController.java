package controller.Admin;

import app.Navigatior;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RibbonController {

    @FXML
    private HBox addPane;
    @FXML
    private void initialize(){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU);
    }
    @FXML
    private void handleSupervisorMenagmentClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU);
    }
    @FXML
    private void handleStudentMenagmentClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU);
    }
    @FXML
    private void handleInboxClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_STUDENTMENU);
    }
    @FXML
    private void handleProfileClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_PROFILE);
    }
}
