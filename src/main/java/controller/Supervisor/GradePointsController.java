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
import model.dto.Supervisor.KonkurimetSaveDto;
import model.dto.Supervisor.KonkurimetShowDto;
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

    // TEMP
    @FXML
    private TableView<KonkurimetShowDto> tableKonkurimet;



    @FXML
    private TableColumn<KonkurimetShowDto,Integer> columnMbikqyresi;
    @FXML
    private TableColumn<KonkurimetShowDto,Integer> columnStudenti;
    @FXML
    private TableColumn<KonkurimetShowDto,Integer> columnAplikimi;
    @FXML
    private TableColumn<KonkurimetShowDto,Integer> columnPiket;

    @FXML
    private Button btnEdit;
    private boolean edit;

    private ObservableList<KonkurimetShowDto> konkurimetList;
    private KonkurimetShowDto selectedKonkurim;

    private Afat selectedAfat;
    @FXML
    private void initialize(){

        try {
            this.imgSearch.setImage(new Image(new FileInputStream("Images/search.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

        tableKonkurimet.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.selectedKonkurim = newSelection;
                int mbikqyresiId = newSelection.getMbikqyresiId();
                int studentiId = newSelection.getStudentiId();
                int aplikimiId = newSelection.getAplikimiId();
                int piket = newSelection.getPiket();

                newSelection.setMbikqyresiId(piket);
                newSelection.setStudentiId(studentiId);
                newSelection.setAplikimiId(mbikqyresiId);
                newSelection.setPiket(aplikimiId);

                this.setTextFields();
            }
        });

        this.disableForms();
        this.studentId.setDisable(true);
        this.supervisorId.setDisable(true);
        this.applicationId.setDisable(true);


        this.txtSearch.setText(SESSION.getAdmin_supervisor_lastSearch());
        this.konkurimetList = SupervisorService.searchKonkurimet(this.txtSearch.getText());
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

            if (SupervisorRepository.editKonkurimi(
                    new KonkurimetSaveDto(
                            Integer.parseInt(this.applicationId.getText()),
                            Integer.parseInt(this.piket.getText()),
                            SESSION.getLoggedSupervisor().getMbikqyresiId()
                    )
            )){
                PopUp.tick(150);
            } else {
                System.out.println("Edit not successful");
            }

            this.konkurimetList = SupervisorService.searchKonkurimet(SESSION.getAdmin_supervisor_lastSearch());
            this.setColumns();
            this.disableForms();
            this.btnEdit.setText("Edit");
            this.edit = true;
        }
    }
    @FXML
    private void handleSearch(ActionEvent ae){
//        Ktu ruhen konkurimet e kerkuara
//        this.konkurimetList = SupervisorService.searchKonkurimi(this.txtSearch.getText().trim());
        this.selectedKonkurim = SupervisorService.searchKonkurimi(this.txtSearch.getText().trim());

        setTextFields();
        this.konkurimetList = SupervisorService.searchKonkurimet(this.txtSearch.getText().trim());
//        SESSION.setAdmin_supervisor_lastSearch(this.txtSearch.getText().trim());
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        this.selectedKonkurim = SupervisorService.searchKonkurimi(this.txtSearch.getText().trim());
        setTextFields();
        this.konkurimetList = SupervisorService.searchKonkurimet(this.txtSearch.getText());
//        SESSION.setAdmin_supervisor_lastSearch(this.txtSearch.getText().trim());
    }

    private void setColumns(){
        this.columnMbikqyresi.setCellValueFactory(new PropertyValueFactory<>("piket"));
        this.columnStudenti.setCellValueFactory(new PropertyValueFactory<>("studentiId"));
        this.columnAplikimi.setCellValueFactory(new PropertyValueFactory<>("mbikqyresiId"));
        this.columnPiket.setCellValueFactory(new PropertyValueFactory<>("aplikimiId"));
        this.tableKonkurimet.setItems(this.konkurimetList);
    }


    private void setTextFields(){
        this.supervisorId.setText(Integer.toString(this.selectedKonkurim.getMbikqyresiId()));
        this.studentId.setText(Integer.toString( this.selectedKonkurim.getStudentiId()));
        this.applicationId.setText(Integer.toString(this.selectedKonkurim.getAplikimiId()));
        this.piket.setText(Integer.toString(this.selectedKonkurim.getPiket()));
    }

    private void enableForms(){
        this.piket.setEditable(true);
    }
    private void disableForms() {
        this.piket.setEditable(false);
    }


}
