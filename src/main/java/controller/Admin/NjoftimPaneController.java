package controller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Njoftim;

public class NjoftimPaneController extends InboxController {

    @FXML
    private AnchorPane paneNjoftim;

    @FXML
    private TextArea txtTextNjoftim;

    private Njoftim njoftimi;

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
       super.getSelectedNjoftimi(this.njoftimi);
    }

    @Override
    public void initialize(){

    }


}
