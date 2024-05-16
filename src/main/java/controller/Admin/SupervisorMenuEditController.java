package controller.Admin;


import app.Navigatior;
import app.PopUp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.SupervisorTableModel;
import model.dto.Supervisor.SupervisorEditDto;
import repository.Supervisor.SupervisorRepository;
import controller.SESSION;
import service.Supervisor.SupervisorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SupervisorMenuEditController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private ImageView imgSearch;
    @FXML
    private TableView<SupervisorTableModel> tableSupervisor;
    @FXML
    private Button btnEdit;
    private boolean edit;
    @FXML
    private TableColumn<SupervisorTableModel, Integer> columnId;
    @FXML
    private TableColumn<SupervisorTableModel, String> columnEmail;
    @FXML
    private TableColumn<SupervisorTableModel, String> columnFirstName;
    @FXML
    private TableColumn<SupervisorTableModel, String> columnLastName;
    @FXML
    private Button btnResetPassword;


    private ObservableList<SupervisorTableModel> supervisorList;
    private SupervisorTableModel selectedSupervisor;

    @FXML
    private void initialize(){
        try {
            this.imgSearch.setImage(new Image(new FileInputStream("Images/search.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

        tableSupervisor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
               this.selectedSupervisor = newSelection;
                this.setTextFields();
            }
            this.btnResetPassword.setDisable(false);
        });
        this.disableForms();
        this.btnResetPassword.setDisable(true);
        this.txtSearch.setText(SESSION.getAdmin_supervisor_lastSearch());
        this.supervisorList = SupervisorService.searchMbikqyresi(this.txtSearch.getText());
        this.columnId.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,Integer>("mbikqyresiId"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("firstName"));
        this.columnFirstName.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("lastName"));
        this.columnLastName.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("email"));
        this.setColumns();
        this.edit = true;
    }
    @FXML
    private void handleSearch(ActionEvent ae) {
        this.supervisorList = SupervisorService.searchMbikqyresi(this.txtSearch.getText());
        SESSION.setAdmin_supervisor_lastSearch(this.txtSearch.getText().trim());
        this.setColumns();
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        this.supervisorList = SupervisorService.searchMbikqyresi(this.txtSearch.getText());
        SESSION.setAdmin_supervisor_lastSearch(this.txtSearch.getText().trim());
        this.setColumns();
    }

    @FXML
    private void handleEdit(ActionEvent ae){
        if(edit){
            this.enableForms();
            this.btnEdit.setText("Save");
            this.edit = false;
        }else{


            if(SupervisorRepository.editMbikqyresi(
                    new SupervisorEditDto(
                            Integer.parseInt(this.txtId.getText()),
                            this.txtFirstName.getText(),
                            this.txtLastName.getText(),
                            this.txtEmail.getText()
                    )
            )
            ){
                PopUp.tick(150);
            }else{
                System.out.println("Edit not succesful");
            }
            this.supervisorList = SupervisorService.searchMbikqyresi(SESSION.getAdmin_supervisor_lastSearch());
            this.setColumns();
            this.disableForms();
            this.btnEdit.setText("Edit");
            this.edit = true;
        }
    }
    @FXML
    private void handleResetPassword(ActionEvent ae){
        SESSION.setAdmin_reset_PasswordId(Integer.parseInt(this.txtId.getText()));
        SESSION.setAdmin_reset_type("Supervisor");
        Navigatior.navigateNewStage(Navigatior.ADMIN_RESETPASSWORD);
    }

    @FXML
    private void handleDelete(ActionEvent ae){
        if(SupervisorRepository.deleteSupervisor(Integer.parseInt(this.txtId.getText()))){
            PopUp.tick(150);
            this.supervisorList = SupervisorService.searchMbikqyresi(SESSION.getAdmin_supervisor_lastSearch());
            this.setColumns();
        }else{
            System.out.println("Nuk u fshi");
        }
    }

    private void setTextFields(){
        this.txtId.setText(Integer.toString(this.selectedSupervisor.getMbikqyresiId()));
        this.txtEmail.setText(this.selectedSupervisor.getEmail());
        this.txtFirstName.setText(this.selectedSupervisor.getFirstName());
        this.txtLastName.setText(this.selectedSupervisor.getLastName());
    }

    private void setColumns(){
        this.columnId.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,Integer>("mbikqyresiId"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("email"));
        this.columnFirstName.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("firstName"));
        this.columnLastName.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("lastName"));
        this.tableSupervisor.setItems(this.supervisorList);
    }

    private void enableForms() {
        this.txtId.setDisable(true);
        this.txtEmail.setDisable(false);
        this.txtFirstName.setDisable(false);
        this.txtLastName.setDisable(false);
    }

    private void disableForms() {
        this.txtId.setDisable(true);
        this.txtEmail.setDisable(true);
        this.txtFirstName.setDisable(true);
        this.txtLastName.setDisable(true);
    }


}
