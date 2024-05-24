package controller.Admin;

import app.PopUp;
import controller.SESSION;
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
import model.SemsStafModel;
import model.SupervisorTableModel;
import service.Supervisor.SemsStafService;
import service.Supervisor.SupervisorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SupervisorMenuApproveController {
    @FXML
    private Button btnApproveSupervisor;

    @FXML
    private TableColumn<SemsStafModel, String> columnEmail;

    @FXML
    private TableColumn<SemsStafModel, Integer> columnFacultyID;

    @FXML
    private TableColumn<SemsStafModel, String> columnFirstName;

    @FXML
    private TableColumn<SemsStafModel, Integer> columnId;

    @FXML
    private TableColumn<SemsStafModel, String> columnLastName;

    @FXML
    private ImageView imgSearch;

    @FXML
    private TableView<SemsStafModel> tableSemsStaf;

    @FXML
    private TextField txtSearch;

    private ObservableList<SemsStafModel> semsStafList;
    private SemsStafModel seletedStaf;

    @FXML
    private void initialize(){
        try {
            this.imgSearch.setImage(new Image(new FileInputStream("Images/search.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

        tableSemsStaf.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.seletedStaf = newSelection;
//                this.setTextFields();
            }
        });

        this.txtSearch.setText(SESSION.getAdmin_semsStaf_lastSearch());
        this.semsStafList = SemsStafService.searchSemsStaf(this.txtSearch.getText());
//        this.columnId.setCellValueFactory(new PropertyValueFactory<SemsStafModel,Integer>("stafId"));
//        this.columnEmail.setCellValueFactory(new PropertyValueFactory<SemsStafModel,String>("email"));
//        this.columnFirstName.setCellValueFactory(new PropertyValueFactory<SemsStafModel,String>("emri"));
//        this.columnLastName.setCellValueFactory(new PropertyValueFactory<SemsStafModel,String>("mbiemri"));
//        this.columnFacultyID.setCellValueFactory(new PropertyValueFactory<SemsStafModel,Integer>("facultyId"));
        this.setColumns();
    }
    @FXML
    private void handleSearch(ActionEvent ae) {
        this.semsStafList = SemsStafService.searchSemsStaf(this.txtSearch.getText());
        SESSION.setAdmin_semsStaf_lastSearch(this.txtSearch.getText().trim());
        this.setColumns();
    }

    @FXML
    private void handleSearchClick(MouseEvent me){
        this.semsStafList = SemsStafService.searchSemsStaf(this.txtSearch.getText());
        SESSION.setAdmin_semsStaf_lastSearch(this.txtSearch.getText().trim());
        this.setColumns();
    }

    private void setColumns(){
        this.columnId.setCellValueFactory(new PropertyValueFactory<SemsStafModel,Integer>("stafId"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<SemsStafModel,String>("email"));
        this.columnFirstName.setCellValueFactory(new PropertyValueFactory<SemsStafModel,String>("emri"));
        this.columnLastName.setCellValueFactory(new PropertyValueFactory<SemsStafModel,String>("mbiemri"));
        this.columnFacultyID.setCellValueFactory(new PropertyValueFactory<SemsStafModel,Integer>("facultyId"));
        this.tableSemsStaf.setItems(this.semsStafList);
    }

    @FXML
    void handleApproveFromSems(ActionEvent event) {

        if( SupervisorService.addSupervisorFromSems(
                new SupervisorTableModel(
                        this.seletedStaf.getEmri(),
                        this.seletedStaf.getMbiemri(),
                        this.seletedStaf.getEmail(),
                        this.seletedStaf.getSalt(),
                        this.seletedStaf.getPasswordHash()
                )
        )
        ){
            PopUp.tick(150);
        }else{
            PopUp.loading("Mbikqyrësi ekziston !",false, "");
        }
        this.semsStafList = SemsStafService.searchSemsStaf(SESSION.getAdmin_semsStaf_lastSearch());
        this.setColumns();
    }


    }
