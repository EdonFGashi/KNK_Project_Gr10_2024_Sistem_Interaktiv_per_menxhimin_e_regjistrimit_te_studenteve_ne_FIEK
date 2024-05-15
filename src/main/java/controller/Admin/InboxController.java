package controller.Admin;

import app.PopUp;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.dto.Admin.AddNewNjoftimDto;
import service.Admin.AdminService;

public class InboxController {

        @FXML
        private TextArea fielNjoftimi;

        @FXML
        private Pagination pagPagination;

        @FXML
        private TextField txtAdminId;

        @FXML
        private TextField txtNjoftimId;

        @FXML
        private void initialize(){
                this.pagPagination.setPageCount(10);


        }

        @FXML
        private void handleCreateNewNjoftim(ActionEvent ae) {
            this.resetFormsForNew();
            if(!this.fielNjoftimi.getText().isEmpty()){
                    if(AdminService.addNewNjoftim(
                            new AddNewNjoftimDto(
                                    this.fielNjoftimi.getText(),
                                    SESSION.getLoggedAdmin().getId()
                            )
                    )){
                            PopUp.tick(250);
                            //Suksess
                    }
            }
        }

        @FXML
        private void handleDeleteNjoftim(ActionEvent ae) {

        }

        @FXML
        private void handleEditNjoftim(ActionEvent ae) {

        }

        private void resetFormsForNew(){
                this.txtNjoftimId.setText("");
                this.txtAdminId.setText(Integer.toString(SESSION.getLoggedAdmin().getId()));

        }




}
