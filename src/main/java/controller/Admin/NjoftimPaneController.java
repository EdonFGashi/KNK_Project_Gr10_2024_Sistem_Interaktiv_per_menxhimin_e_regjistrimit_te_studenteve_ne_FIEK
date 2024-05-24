package controller.Admin;

import controller.ComunicativeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Njoftim;

import java.util.Locale;

public class NjoftimPaneController extends ComunicativeController {

    @FXML
    private AnchorPane paneNjoftim;

    @FXML
    private TextArea txtTextNjoftim;

    private Njoftim njoftimi;

    private InboxController controller;


    public void setController(InboxController controller) {
        this.controller = controller;
    }

    public void setPaneNjoftim(AnchorPane paneNjoftim) {
        this.paneNjoftim = paneNjoftim;
    }

    public void setTxtTextNjoftim(TextArea txtTextNjoftim) {
        this.txtTextNjoftim = txtTextNjoftim;

    }
    public void setNjoftimi(Njoftim njoftimi) {
        this.njoftimi = njoftimi;
        this.txtTextNjoftim.setText(this.njoftimi.getText());
    }

    @FXML
    void handleNjoftimClick(MouseEvent event) {
       this.controller.setFormsNjoftimi(njoftimi);

    }
    @FXML
    void handeTextClicked(MouseEvent event) {
        this.controller.setFormsNjoftimi(njoftimi);
    }



}
