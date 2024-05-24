package controller.Admin;

import app.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import model.Afat;
import repository.AfatRepository;
import service.Admin.AdminService;
import controller.SESSION;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RegistrationMenuShowAndEditController {
    @FXML
    private TextField txtSearch;
    @FXML
    private ImageView imgSearch;
    @FXML
    private TextField txtId;

    ObservableList<String> nivelet =
            FXCollections.observableArrayList("Bachelor","Master","Doctorature");

    @FXML
    private TextField txtYear;
    @FXML
    private ChoiceBox<String> choiseChoseLevel;
    @FXML
    private DatePicker dateOpenDate;
    @FXML
    private DatePicker dateClosedDate;
    @FXML
    private RadioButton radioFirst, radioSecond;
    @FXML
    private TableView<Afat> tableAfat;
    @FXML
    private TableColumn<Afat,Integer> columnId;
    @FXML
    private TableColumn<Afat,Integer> columnYear;
    @FXML
    private TableColumn<Afat,String> columnTime;
    @FXML
    private TableColumn<Afat,String> columnOpenDate;
    @FXML
    private TableColumn<Afat,String> columnCloseDate;
    @FXML
    private TableColumn<Afat,String> columnLevel;
    @FXML
    private Button btnEdit;
    private boolean edit;

    private ObservableList<Afat> afatList;

    private Afat selectedAfat;


    @FXML
    private void initialize(){
        choiseChoseLevel.setItems(FXCollections.observableArrayList("Bachelor", "Master", "Doctorature"));

        try {
            this.imgSearch.setImage(new Image(new FileInputStream("Images/search.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

        tableAfat.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedAfat = newSelection;
                this.setTextFields();
            }
        });
        this.disableForms();
        this.txtId.setDisable(true);
        this.afatList = AdminService.searchAfat(SESSION.getAdmin_registration_lastSearch());
        this.txtSearch.setText(SESSION.getAdmin_registration_lastSearch());
        setColumns();
        this.tableAfat.setItems(this.afatList);
        this.edit = true;

        this.txtSearch.setOnKeyPressed( e -> {
            if(e.getCode()== KeyCode.ENTER){
                this.search();
            }
        });

    }

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
    @FXML
    private void handleEdit(ActionEvent ae){
        if(edit){
            this.enableForms();
            this.btnEdit.setText("Save");
            this.edit = false;
        }else{
           // AfatRepository.editAfat(new Afat(txtId.getText(),))
            String hera;
            if (radioFirst.isSelected()) {
                hera = "1";
            } else {
                hera = "2";
            }

            LocalDate openDate = this.dateOpenDate.getValue();
            String formattedOpenDate = openDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate closeDate = this.dateClosedDate.getValue();
            String formattedClosedDate = closeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String choiceLevel = this.choiseChoseLevel.getValue();
            if(AdminService.editAfat(
                    new Afat(
                            Integer.parseInt(this.txtId.getText()),
                             Integer.parseInt(this.txtYear.getText()),hera,
                            formattedOpenDate,formattedClosedDate,choiceLevel
                    )
            )){
                PopUp.tick(150);
            }else{
                System.out.println("Edit not succesful");
            }
            this.afatList = AdminService.searchAfat(SESSION.getAdmin_registration_lastSearch());
            this.tableAfat.setItems(this.afatList);
            this.disableForms();
            this.btnEdit.setText("Edit");
            this.edit = true;
        }
    }
    @FXML
    private void handleSearch(ActionEvent ae){
        this.search();
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        this.search();
    }
    private void search(){
        this.afatList = AdminService.searchAfat(this.txtSearch.getText().trim());
        SESSION.setAdmin_registration_lastSearch(this.txtSearch.getText().trim());
        this.tableAfat.setItems(this.afatList);
    }

    @FXML
    private void handleDelete(ActionEvent ae){
       if(AfatRepository.deleteAfat(Integer.parseInt(this.txtId.getText()))){
           PopUp.tick(150);
           this.afatList = AdminService.searchAfat(SESSION.getAdmin_registration_lastSearch());
           this.tableAfat.setItems(this.afatList);
       }else{
           System.out.println("Nuk u fshi");
       }
    }


    private void setColumns(){
        this.columnId.setCellValueFactory(new PropertyValueFactory<Afat,Integer>("id"));
        this.columnTime.setCellValueFactory(new PropertyValueFactory<Afat,String>("hera"));
        this.columnYear.setCellValueFactory(new PropertyValueFactory<Afat,Integer>("year"));
        this.columnOpenDate.setCellValueFactory(new PropertyValueFactory<Afat,String>("dataHapjes"));
        this.columnCloseDate.setCellValueFactory(new PropertyValueFactory<Afat,String>("dataMbylljes"));
        this.columnLevel.setCellValueFactory(new PropertyValueFactory<Afat,String>("niveli"));
    }

    private void setTextFields(){
        this.txtId.setText(Integer.toString(selectedAfat.getId()));
        this.txtYear.setText(Integer.toString(selectedAfat.getYear()));
        if(this.selectedAfat.getHera().equals("1")){
            this.radioFirst.setSelected(true);
        }else{
            this.radioSecond.setSelected(true);
        }
        LocalDate dateHapjes = LocalDate.parse(this.selectedAfat.getDataHapjes(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate dateMbylljes = LocalDate.parse(this.selectedAfat.getDataMbylljes(), DateTimeFormatter.ISO_LOCAL_DATE);
        this.dateOpenDate.setValue(dateHapjes);
        this.dateClosedDate.setValue(dateMbylljes);
        this.choiseChoseLevel.setValue(this.selectedAfat.getNiveli());

    }

    private void enableForms(){
              this.txtYear.setEditable(true);
              this.choiseChoseLevel.setDisable(false);
              this.dateOpenDate.setDisable(false);
              this.dateClosedDate.setDisable(false);
    }
    private void disableForms() {
        this.txtYear.setEditable(false);
        this.choiseChoseLevel.setDisable(true);
        this.dateOpenDate.setDisable(true);
        this.dateClosedDate.setDisable(true);
    }


}
