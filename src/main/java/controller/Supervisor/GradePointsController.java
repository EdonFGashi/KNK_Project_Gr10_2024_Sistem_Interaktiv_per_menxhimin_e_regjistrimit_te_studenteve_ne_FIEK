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
//        choiseChoseLevel.setItems(FXCollections.observableArrayList("Bachelor", "Master", "Doctorature"));

        try {
            this.imgSearch.setImage(new Image(new FileInputStream("Images/search.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }
//        tableKonkurimet.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                this.selectedKonkurim = newSelection;
//                this.setTextFields();
//            }
//        });

        tableKonkurimet.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println("SelectedItemProperty: " + tableKonkurimet.getSelectionModel().selectedItemProperty().get().getPiket());
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



                System.out.println();
                System.out.println("The new selection: ");
                System.out.println("MbikqyresiId: " + mbikqyresiId);
                System.out.println("StudentiId: " + studentiId);
                System.out.println("AplikimiId: " + aplikimiId);
                System.out.println("Piket: " + piket);
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
        System.out.println("--------------------------");
        System.out.println("konkurimetList: " + this.selectedKonkurim);
        System.out.println("The new selection: ");
        System.out.println("MbikqyresiId: " + this.selectedKonkurim.getMbikqyresiId());
        System.out.println("StudentiId: " + this.selectedKonkurim.getStudentiId());
        System.out.println("AplikimiId: " + this.selectedKonkurim.getAplikimiId());
        System.out.println("Piket: " + this.selectedKonkurim.getPiket());
        System.out.println("--------------------------");
        setTextFields();
        this.konkurimetList = SupervisorService.searchKonkurimet(this.txtSearch.getText().trim());
//        SESSION.setAdmin_supervisor_lastSearch(this.txtSearch.getText().trim());
//        this.setColumns();
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        this.selectedKonkurim = SupervisorService.searchKonkurimi(this.txtSearch.getText().trim());
        setTextFields();
        this.konkurimetList = SupervisorService.searchKonkurimet(this.txtSearch.getText());
//        SESSION.setAdmin_supervisor_lastSearch(this.txtSearch.getText().trim());
//        this.setColumns();
    }

    @FXML
    private void handleDelete(ActionEvent ae){
//        if(SupervisorRepository.deleteSupervisor(Integer.parseInt(this.supervisorId.getText()))){
//            PopUp.tick(150);
////            this.konkurimetList = SupervisorService.searchMbikqyresi(SESSION.getAdmin_supervisor_lastSearch());
//            this.setColumns();
//    }else{
//        System.out.println("Nuk u fshi");
//    }
    }


    private void setColumns(){
        this.columnMbikqyresi.setCellValueFactory(new PropertyValueFactory<>("piket"));
        this.columnStudenti.setCellValueFactory(new PropertyValueFactory<>("studentiId"));
        this.columnAplikimi.setCellValueFactory(new PropertyValueFactory<>("mbikqyresiId"));
        this.columnPiket.setCellValueFactory(new PropertyValueFactory<>("aplikimiId"));
        this.tableKonkurimet.setItems(this.konkurimetList); }

//    private void setTextFields(){
//        this.supervisorId.setText(Integer.toString(this.selectedSupervisor.getMbikqyresiId()));
//        this.studentId.setText(this.selectedSupervisor.getEmail());
//        this.applicationId.setText(this.selectedSupervisor.getFirstName());
//        this.piket.setText(this.selectedSupervisor.getLastName());
//        }

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
//        this.choiseChoseLevel.setDisable(true);
//        this.dateOpenDate.setDisable(true);
//        this.dateClosedDate.setDisable(true);
    }


}
