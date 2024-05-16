package controller.Supervisor;

import app.PopUp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Afat;
import model.SupervisorTableModel;
import model.dto.Supervisor.SupervisorEditDto;
import repository.AfatRepository;
import repository.Supervisor.SupervisorRepository;
import service.Admin.AdminService;
import controller.SESSION;
import service.Supervisor.SupervisorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GradePointsController {
    @FXML
    private TextField txtSearch;
    @FXML
    private ImageView imgSearch;
    @FXML
    private TextField supervisorId;
    @FXML
    private TextField studentId;

    @FXML
    private TextField applicationId;
    @FXML
    private TextField piket;

    @FXML
    private TableView<SupervisorTableModel> tableSupervisor;

    @FXML
    private TableColumn<SupervisorTableModel,Integer> columnId;
    @FXML
    private TableColumn<SupervisorTableModel,String> columnEmail;
    @FXML
    private TableColumn<SupervisorTableModel,String> columnFirstName;
    @FXML
    private TableColumn<SupervisorTableModel,String> columnLastName;

    @FXML
    private Button btnEdit;
    private boolean edit;

    private ObservableList<SupervisorTableModel> supervisorList;
    private SupervisorTableModel selectedSupervisor;

    private Afat selectedAfat;
    @FXML
    private void initialize(){
//        choiseChoseLevel.setItems(FXCollections.observableArrayList("Bachelor", "Master", "Doctorature"));

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
        });
        this.disableForms();
        this.studentId.setDisable(true);
        this.supervisorId.setDisable(true);
        this.applicationId.setDisable(true);


        this.txtSearch.setText(SESSION.getAdmin_supervisor_lastSearch());
        this.supervisorList = SupervisorService.searchMbikqyresi(this.txtSearch.getText());
        this.setColumns();
        this.edit = true;

    }

    @FXML
    private void handleEdit(ActionEvent ae){
        if(edit){
            this.enableForms();
            this.btnEdit.setText("Save");
            this.edit = false;
        }else{

            if (SupervisorRepository.editMbikqyresi(
                    new SupervisorEditDto(
                            Integer.parseInt(this.supervisorId.getText()),
                            this.studentId.getText(),
                            this.applicationId.getText(),
                            (this.piket.getText())
                    )
            )){
                PopUp.tick(150);
            } else {
                System.out.println("Edit not successful");
            }

            this.supervisorList = SupervisorService.searchMbikqyresi(SESSION.getAdmin_supervisor_lastSearch());
            this.setColumns();
            this.disableForms();
            this.btnEdit.setText("Edit");
            this.edit = true;
        }
    }
    @FXML
    private void handleSearch(ActionEvent ae){
        this.supervisorList = SupervisorService.searchMbikqyresi(this.txtSearch.getText().trim());
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
    private void handleDelete(ActionEvent ae){
        if(SupervisorRepository.deleteSupervisor(Integer.parseInt(this.supervisorId.getText()))){
            PopUp.tick(150);
            this.supervisorList = SupervisorService.searchMbikqyresi(SESSION.getAdmin_supervisor_lastSearch());
            this.setColumns();
    }else{
        System.out.println("Nuk u fshi");
    }
    }


    private void setColumns(){
        this.columnId.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,Integer>("mbikqyresiId"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("firstName"));
        this.columnFirstName.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("lastName"));
        this.columnLastName.setCellValueFactory(new PropertyValueFactory<SupervisorTableModel,String>("email"));
        this.tableSupervisor.setItems(this.supervisorList); }

    private void setTextFields(){
        this.supervisorId.setText(Integer.toString(this.selectedSupervisor.getMbikqyresiId()));
        this.studentId.setText(this.selectedSupervisor.getEmail());
        this.applicationId.setText(this.selectedSupervisor.getFirstName());
        this.piket.setText(this.selectedSupervisor.getLastName());
        }

    private void enableForms(){
        this.piket.setEditable(true);
    }
    private void disableForms() {
        this.piket.setEditable(false);
//        this.choiseChoseLevel.setDisable(true);
//        this.dateOpenDate.setDisable(true);
//        this.dateClosedDate.setDisable(true);
    }


}
