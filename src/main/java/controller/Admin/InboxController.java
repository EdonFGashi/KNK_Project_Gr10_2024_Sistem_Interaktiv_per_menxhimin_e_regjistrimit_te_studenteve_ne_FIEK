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
        private TextArea fielNjoftimi;

        @FXML
        private Pagination pagPagination;

        @FXML
        private TextField txtAdminId;

        @FXML
        private TextField txtNjoftimId;

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

        }

        @FXML
        private void handleEditNjoftim(ActionEvent ae) {

        }

        private void resetFormsForNew(){
                this.txtNjoftimId.setText("");
                this.txtAdminId.setText(Integer.toString(SESSION.getLoggedAdmin().getId()));

        }
        protected void getSelectedNjoftimi(Njoftim njoftimi){
           this.selectedNjoftim = njoftimi;
           this.txtNjoftimId.setText(Integer.toString(this.selectedNjoftim.getNjoftimiId()));
           this.txtAdminId.setText(Integer.toString(this.selectedNjoftim.getAdminId()));
           this.fielNjoftimi.setText(this.selectedNjoftim.getText());
        }



}
