package controller.Admin;

import app.Navigatior;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class RegistrationPeriodMenuController {
    @FXML
    private HBox hboxShowDetails;
    @FXML
    private HBox hboxStatistics;
    @FXML
    private HBox hboxAssignSupervisors;
    @FXML
    private HBox addPane;

    @FXML
    private void initialize(){
        Navigatior.navigate(this.addPane, "");
        this.hboxShowDetails.setStyle(Navigatior.cssForActiveSection);
    }

    @FXML
    private void handleShowDetailsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, "");
        refreshActiveSection();
       this.hboxShowDetails.setStyle(Navigatior.cssForActiveSection);
    }

    @FXML
    private void handleStatisticsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, "");
        refreshActiveSection();
        this.hboxStatistics.setStyle(Navigatior.cssForActiveSection);
    }

    @FXML
    private void handleAssignSupervisorsClick(MouseEvent me){
        Navigatior.navigate(this.addPane, Navigatior.ADMIN_REGISTRATIONMENU_ADDREGISTRATION);
        refreshActiveSection();
        this.hboxAssignSupervisors.setStyle(Navigatior.cssForActiveSection);
    }


    private void refreshActiveSection(){
        this.hboxShowDetails.setStyle("");
        this.hboxStatistics.setStyle("");
        this.hboxAssignSupervisors.setStyle("");
    }

}
