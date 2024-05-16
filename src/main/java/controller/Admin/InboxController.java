package controller.Admin;

import app.Main;
import app.Navigatior;
import app.PopUp;
import controller.SESSION;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Njoftim;
import model.dto.Admin.AddNewNjoftimDto;
import model.filter.NjoftimPagination;
import service.Admin.AdminService;

import java.util.ArrayList;
import java.util.Collections;

public class InboxController{

        @FXML
        protected TextArea fielNjoftimi;

        @FXML
        private Pagination pagPagination;

        @FXML
        protected TextField txtAdminId;

        @FXML
        protected TextField txtNjoftimId;

        private VBox contentVBox = new VBox();
        private final int njoftimePerPage = 2;
        private int totalNjoftime = AdminService.getTotalNjoftime();

        private Njoftim selectedNjoftim;

        private void setPagination(){

        }

        @FXML
        protected void initialize() {
             pagPagination.setPageCount((int) Math.ceil((double) totalNjoftime / njoftimePerPage));
             pagPagination.setPageFactory(this::createPage);
        }

        private VBox createPage(int pageIndex) {

                int offset = (totalNjoftime - pageIndex*this.njoftimePerPage -2);

                ArrayList<Njoftim> njoftimet = merrNjoftimWithPagination(offset, njoftimePerPage);
                Collections.reverse(njoftimet);
                contentVBox.getChildren().clear();
                for (Njoftim njoftim : njoftimet) {
                        try {
                                FXMLLoader loader = new FXMLLoader(Main.class.getResource("admin-inbol-njoftimPane.fxml"));
                                Parent PaneNjoftimi = loader.load();
                                NjoftimPaneController controller = loader.getController();
                                controller.setNjoftimi(njoftim);
                                controller.setController(this);
                                contentVBox.getChildren().add(PaneNjoftimi);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
                return contentVBox;
        }

        private ArrayList<Njoftim> merrNjoftimWithPagination(int offset, int size) {
                if(offset == -1 ){
                        offset = 0;
                        size = 1;
                }
                NjoftimPagination pagination = new NjoftimPagination(offset, size);
                return AdminService.getNjoftimiWithPagination(pagination);
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
                if(AdminService.deleteNjoftim(
                      this.selectedNjoftim.getNjoftimiId()
                )){
                        PopUp.tick(250);
                }
        }

        @FXML
        private void handleEditNjoftim(ActionEvent ae) {
           if(AdminService.editNjoftim(
             new Njoftim(
                     this.selectedNjoftim.getNjoftimiId(),
                     this.fielNjoftimi.getText(),
                     SESSION.getLoggedAdmin().getId()
             )
           )){
                   PopUp.tick(250);
           }
        }

        private void resetFormsForNew(){
                this.txtNjoftimId.setText("");
                this.txtAdminId.setText(Integer.toString(SESSION.getLoggedAdmin().getId()));

        }
        public void setFormsNjoftimi(Njoftim njoftimi){
                this.selectedNjoftim = njoftimi;
                this.txtNjoftimId.setText(Integer.toString(njoftimi.getNjoftimiId()));
                this.txtAdminId.setText(Integer.toString(njoftimi.getAdminId()));
                this.fielNjoftimi.setText(njoftimi.getText());
        }

}
