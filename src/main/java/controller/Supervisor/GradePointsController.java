package controller.Supervisor;

import app.PopUp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPiketValue;
import service.CustomExceptions.InvalidSearch;
import service.Supervisor.SupervisorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Objects;

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
    private Label errorMessageLabel;

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

    private Locale currentLocale = new Locale("en");
    @FXML
    private void handleChangeLanguage(ActionEvent ae){
        if (currentLocale.getLanguage().equals("en")) {
            currentLocale = new Locale("sq");
        } else {
            currentLocale = new Locale("en");
        }
        //loadLanguage(currentLocale.getLanguage());
    }

    private Afat selectedAfat;

    private int MAX_POINTS = 20;

    @FXML
    private void initialize(){
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

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

        if (!SESSION.getSupervisor_lastSearch().equals("")){
            this.selectedKonkurim = SupervisorService.searchKonkurimi(SESSION.getSupervisor_lastSearch());
            this.setTextFields();
            System.out.println("There is a search history!");
        }else {
            System.out.println("There is NO search history!");
        }


        this.disableForms();
        this.studentId.setDisable(true);
        this.supervisorId.setDisable(true);
        this.applicationId.setDisable(true);

        this.txtSearch.setText(SESSION.getSupervisor_lastSearch());
        this.konkurimetList = SupervisorService.searchKonkurimet("");
        this.setColumns();
        this.edit = true;

        txtSearch.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    handleSearch(new ActionEvent());
                } catch (InvalidSearch e) {
                    throw new RuntimeException(e);
                }
            }
        });

        piket.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    handleEdit(new ActionEvent());
                } catch (InvalidPiketValue e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    @FXML
    private void handleEdit(ActionEvent ae) throws InvalidPiketValue {
        if(edit){
            this.enableForms();
            this.btnEdit.setText("Save");
            this.edit = false;
        }else{
            try {if (Integer.parseInt(this.piket.getText()) > MAX_POINTS || Integer.parseInt(this.piket.getText()) < 0) {
                    throw new InvalidPiketValue("Piket duhet te jene mes 0-20");
                }
            } catch (InvalidPiketValue e){
                errorMessageLabel.setText(e.getMessage());
                errorMessageLabel.setVisible(true);
                return;
            }

            errorMessageLabel.setText("");
            errorMessageLabel.setVisible(false);

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

            this.konkurimetList = SupervisorService.searchKonkurimet("");
            this.setColumns();
            this.disableForms();
            this.btnEdit.setText("Edit");
            this.edit = true;
        }
    }

    @FXML
    private void handleSearch(ActionEvent ae) throws InvalidSearch {
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        try {
            this.selectedKonkurim = SupervisorService.searchKonkurimi(this.txtSearch.getText().trim());
            if (this.selectedKonkurim.getAplikimiId() == 0) {
                throw new InvalidSearch("Studenti nuk u gjend!");
            }
            setTextFields();
            this.konkurimetList = SupervisorService.searchKonkurimet(this.txtSearch.getText().trim());
        }catch (InvalidSearch e) {
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);
        }


        SESSION.setSupervisor_lastSearch(this.txtSearch.getText().trim());
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        try {
            this.selectedKonkurim = SupervisorService.searchKonkurimi(this.txtSearch.getText().trim());
            if (this.selectedKonkurim.getAplikimiId() == 0) {
                throw new InvalidSearch("Studenti nuk u gjend!");
            }
            setTextFields();
            this.konkurimetList = SupervisorService.searchKonkurimet(this.txtSearch.getText().trim());
        }catch (InvalidSearch e) {
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);
        }


        SESSION.setSupervisor_lastSearch(this.txtSearch.getText().trim());
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
