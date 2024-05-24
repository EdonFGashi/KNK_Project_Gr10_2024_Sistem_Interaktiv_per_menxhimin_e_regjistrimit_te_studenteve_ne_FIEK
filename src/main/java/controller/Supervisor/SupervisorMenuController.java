package controller.Supervisor;

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

public class SupervisorMenuController {
    @FXML
    private HBox hboxShowSupervisors;
    @FXML
    private HBox hboxEditSupervisors;
    @FXML
    private HBox hboxAddSupervisor;
    @FXML
    private HBox hboxAproveSupervisor;
    @FXML
    private HBox addPane;


    private final String cssForActiveSection = "-fx-background-color: #A5CEF2; -fx-background-radius:10px;";

    @FXML
    private void initialize(){
        //Varesisht se qka u kliku e fundit kjo e tregon
        Navigatior.navigate(this.addPane, Navigatior.SUPERVISOR_PROFILE);
        hboxShowSupervisors.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleShowSupervisorsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.SUPERVISOR_PROFILE);
        refreshActiveSection();
        hboxShowSupervisors.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleAddSupervisorClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_SUPERVISORMENU_ADDSUPERVISOR);
        refreshActiveSection();
        hboxAddSupervisor.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleEditSupervisorsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_SUPERVISORMENU_EDITSUPERVISOR);
        refreshActiveSection();
        hboxEditSupervisors.setStyle(cssForActiveSection);
    }

    @FXML
    private void handleApproveSupervisorClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_SUPERVISORMENU_APPROVESUPERVISORFROMSEMS);
        refreshActiveSection();
        hboxAproveSupervisor.setStyle(cssForActiveSection);
    }


    private void refreshActiveSection(){
        hboxShowSupervisors.setStyle("");
        hboxEditSupervisors.setStyle("");
        hboxAddSupervisor.setStyle("");
        hboxAproveSupervisor.setStyle("");
    }

}


