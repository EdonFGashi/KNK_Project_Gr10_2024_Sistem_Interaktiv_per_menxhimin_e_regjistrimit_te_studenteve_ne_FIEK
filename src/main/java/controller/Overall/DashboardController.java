package controller.Overall;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {
    @FXML
    private void handleQasja(ActionEvent ae){

        Navigatior.navigateNewStage(Navigatior.LOGIN);

    }
}
