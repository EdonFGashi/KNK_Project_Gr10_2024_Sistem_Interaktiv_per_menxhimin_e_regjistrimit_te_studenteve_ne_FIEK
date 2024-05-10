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
import javafx.scene.input.MouseEvent;
import model.Afat;
import repository.AfatRepository;
import service.Admin.AdminService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        this.afatList =  AdminService.searchAfat("");
        setColumns();
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
        this.edit = true;

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
            if(AfatRepository.editAfat(
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
            this.afatList = AdminService.searchAfat("");
            this.setColumns();
            this.disableForms();
            this.btnEdit.setText("Edit");
            this.edit = true;
        }
    }
    @FXML
    private void handleSearch(ActionEvent ae){
        this.afatList = AdminService.searchAfat(this.txtSearch.getText().trim());
        this.setColumns();
    }
    @FXML
    private void handleSearchClick(MouseEvent me){
        this.afatList = AdminService.searchAfat(this.txtSearch.getText().trim());
        this.setColumns();
    }

    @FXML
    private void handleDelete(ActionEvent ae){
       if(AfatRepository.deleteAfat(Integer.parseInt(this.txtId.getText()))){
           PopUp.tick(150);
           this.afatList = AdminService.searchAfat("");
           this.setColumns();
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
        this.tableAfat.setItems(this.afatList);
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
              this.dateOpenDate.setEditable(false);
              this.dateClosedDate.setEditable(false);
    }
    private void disableForms() {
        this.txtYear.setEditable(false);
        this.choiseChoseLevel.setDisable(true);
        this.dateOpenDate.setDisable(true);
        this.dateClosedDate.setDisable(true);
    }


}
