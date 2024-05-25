package controller.Admin;

import app.PopUp;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.Afat;
import model.KonkurimetDataFromDbDto;
import model.UserStudent;
import model.dto.Admin.KonkurimetByAfatDto;
import model.dto.Admin.RegistrationListsToController;
import model.dto.RegisteredStudents.RegisteredStudents;
import repository.StudentRepository;
import service.Admin.AdminService;
import service.Admin.KonkurimetService;
import service.AfatService;
import service.Student.StudentService;

import java.util.ArrayList;

public class StudentMenuKonkurimetController {

    @FXML
    private Button btnApprove;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Integer> columnAcceptanceTest;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, String> columnFirstName;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, String> columnLastName;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Double> columnMature;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Integer> columnMaturePoints;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Integer> columnNr;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Integer> columnS1;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Integer> columnS2;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Integer> columnS3;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Integer> columnStudentId;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Double> columnSucces;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Double> columnTest;

    @FXML
    private TableColumn<KonkurimetDataFromDbDto, Double> columnTotal;

    @FXML
    private ComboBox<Afat> comboAfatId;



    @FXML
    private TableView<KonkurimetDataFromDbDto> tableStudents;

    @FXML
    private RadioButton radioNormal;
    @FXML
    private RadioButton radioMinority;
    @FXML
    private Button btnSwitchTables;
    @FXML
    private ComboBox<String>comboDepartment;

    private Afat selectedAfat;
    private String selectedDepartment;
   private RegistrationListsToController allArrayLists;

   private AllRegistrationObservableLists lists;



    @FXML
    private void initialize(){
     this.columnStudentId.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Integer>("userId"));
     this.columnFirstName.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,String>("emri"));
     this.columnLastName.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,String>("mbiemri"));
     this.columnS1.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Integer>("suksesiKl10"));
     this.columnS2.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Integer>("suksesiKl11"));
     this.columnS3.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Integer>("suksesiKl12"));
     this.columnSucces.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Double>("totalSucces"));
     this.columnMaturePoints.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Integer>("piketMatures"));
     this.columnMature.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Double>("totalPiketMatures"));
     this.columnAcceptanceTest.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Integer>("piketPranues"));
     this.columnTest.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Double>("totalPranues"));
     this.columnTotal.setCellValueFactory(new PropertyValueFactory<KonkurimetDataFromDbDto,Double>("total"));

        this.columnNr.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(tableStudents.getItems().indexOf(param.getValue()) + 1));

   this.btnSwitchTables.setDisable(true);

   this.comboAfatId.setItems(
           AdminService.searchAfat("")
   );


   //E qet veq Id:
        comboAfatId.setCellFactory(param -> new ListCell<Afat>() {
            @Override
            protected void updateItem(Afat item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(item.getId()));
                }
            }
        });
        comboAfatId.setConverter(new StringConverter<Afat>() {
            @Override
            public String toString(Afat afat) {
                if (afat == null) {
                    return null;
                } else {
                    return String.valueOf(afat.getId());
                }
            }

            @Override
            public Afat fromString(String string) {
                return null;
            }
        });

    this.comboDepartment.setItems(FXCollections.observableArrayList("IKS","EAR","EE","TIK","Un Accepted"));

    }

    @FXML
    private void handleLoadAfat(ActionEvent ae){
        this.selectedAfat = this.comboAfatId.getValue();

        this.allArrayLists = KonkurimetService.ktheKonkurimet(new KonkurimetByAfatDto(
                selectedAfat.getId(), selectedAfat.getNiveli(), selectedAfat.getHera()
        ));

        if(allArrayLists != null) {
            this.lists = new AllRegistrationObservableLists(allArrayLists);
        }
        this.btnSwitchTables.setDisable(false);
    }

    @FXML
    private void handleAprove(ActionEvent event) {

        String vitiAfatit = Integer.toString(selectedAfat.getYear()).substring(2);
        String nrFakultetit = "07";

        String numriDepartamentit = "";
        this.selectedDepartment = this.comboDepartment.getValue();
        if(selectedDepartment.equals("IKS")){
            numriDepartamentit = "56";
        } else if(selectedDepartment.equals("EE")){
            numriDepartamentit = "18";
        } else if(selectedDepartment.equals("TIK")){
            numriDepartamentit = "58";
        } else if(selectedDepartment.equals("EAR")){
            numriDepartamentit = "57";
        }
        numriDepartamentit += "1";
        System.out.println("Viti afatit: " + vitiAfatit);
        System.out.println("Numri fakultetit: " + nrFakultetit);
        System.out.println("Numri departamentit: " + selectedDepartment + numriDepartamentit);


        ArrayList<KonkurimetDataFromDbDto> konkurimetList = allArrayLists.getIksNormal();
        if(comboDepartment.getValue().equals("IKS") && radioNormal.isSelected()){
            konkurimetList = allArrayLists.getIksNormal();
        } else if(comboDepartment.getValue().equals("IKS") && radioMinority.isSelected()){
            konkurimetList = allArrayLists.getIksMinoritet();
        } else if(comboDepartment.getValue().equals("EE") && radioNormal.isSelected()){
            konkurimetList = allArrayLists.getEeNormal();
        } else if(comboDepartment.getValue().equals("EE") && radioMinority.isSelected()){
            konkurimetList = allArrayLists.getEeMinoritet();
        } else if(comboDepartment.getValue().equals("TIK") && radioNormal.isSelected()){
            konkurimetList = allArrayLists.getTikNormal();
        } else if(comboDepartment.getValue().equals("TIK") && radioMinority.isSelected()){
            konkurimetList = allArrayLists.getTikMinoritet();
        } if(comboDepartment.getValue().equals("EAR") && radioNormal.isSelected()){
            konkurimetList = allArrayLists.getEarNormal();
        } else if(comboDepartment.getValue().equals("EAR") && radioMinority.isSelected()){
            konkurimetList = allArrayLists.getEarMinoritet();
        }

        int idCounter = 0;
        for(KonkurimetDataFromDbDto studenti : konkurimetList){
            String id = KonkurimetService.gjeneroId(vitiAfatit, nrFakultetit, numriDepartamentit, idCounter);
            System.out.println("Id-ja: " + id);
            idCounter++;

            String email = KonkurimetService.gjeneroEmail(studenti.getEmri(), studenti.getMbiemri());
            RegisteredStudents student = new RegisteredStudents(studenti.getUserId(), email, id, selectedDepartment, studenti.getNiveli());

            boolean uRegjistrua = StudentService.registerStundent(student);
            //System.out.println(uRegjistrua);
        }
    }


    @FXML
    private void handleSwitchTables(ActionEvent ae){
         String department = this.comboDepartment.getValue();

             if(department.equals("IKS")){
                 if(this.radioNormal.isSelected()){
                     this.tableStudents.setItems(lists.getIksNormal());
                 }else{
                     this.tableStudents.setItems(lists.getIksMinoritet());
                 }
             }else if(department.equals("EAR")){
                 if(this.radioNormal.isSelected()){
                     this.tableStudents.setItems(lists.getEarNormal());
                 }else{
                     this.tableStudents.setItems(lists.getEarMinoritet());
                 }
             }else if(department.equals("TIK")){
                 if(this.radioNormal.isSelected()){
                     this.tableStudents.setItems(lists.getTikNormal());
                 }else{
                     this.tableStudents.setItems(lists.getTikMinoritet());
                 }
             }else if(department.equals("EE")){
                 if(this.radioNormal.isSelected()){
                     this.tableStudents.setItems(lists.getEeNormal());
                 }else{
                     this.tableStudents.setItems(lists.getEeMinoritet());
                 }
             }else if(department.equals("Un Accepted")){
                 if(this.radioNormal.isSelected()){
                     this.tableStudents.setItems(lists.getPaPranuarNormal());
                 }else{
                     this.tableStudents.setItems(lists.getPaPranuarMinoritet());
                 }
             }else{
                 PopUp.loading("Departamenti i pa Zgjedhur",false,"");
             }
        PopUp.tick(200);
    }
}



class AllRegistrationObservableLists {

    private ObservableList<KonkurimetDataFromDbDto> iksNormal;
    private ObservableList<KonkurimetDataFromDbDto> earNormal;
    private ObservableList<KonkurimetDataFromDbDto> eeNormal;
    private ObservableList<KonkurimetDataFromDbDto> tikNormal;
    private ObservableList<KonkurimetDataFromDbDto> paPranuarNormal;
    private ObservableList<KonkurimetDataFromDbDto> iksMinoritet;
    private ObservableList<KonkurimetDataFromDbDto> earMinoritet;
    private ObservableList<KonkurimetDataFromDbDto> eeMinoritet;
    private ObservableList<KonkurimetDataFromDbDto> tikMinoritet;
    private ObservableList<KonkurimetDataFromDbDto> paPranuarMinoritet;

    public AllRegistrationObservableLists(RegistrationListsToController data) {

        this.iksNormal = FXCollections.observableArrayList(data.getIksNormal());
        this.earNormal = FXCollections.observableArrayList(data.getEarNormal());
        this.eeNormal = FXCollections.observableArrayList(data.getEeNormal());
        this.tikNormal = FXCollections.observableArrayList(data.getTikNormal());
        this.paPranuarNormal = FXCollections.observableArrayList(data.getPaPranuarNormal());
        this.iksMinoritet = FXCollections.observableArrayList(data.getIksMinoritet());
        this.earMinoritet = FXCollections.observableArrayList(data.getEarMinoritet());
        this.eeMinoritet = FXCollections.observableArrayList(data.getEeMinoritet());
        this.tikMinoritet = FXCollections.observableArrayList(data.getTikMinoritet());
        this.paPranuarMinoritet = FXCollections.observableArrayList(data.getPaPranuarMinoritet());
    }
    public ObservableList<KonkurimetDataFromDbDto> getIksNormal() {
        return iksNormal;
    }

    public ObservableList<KonkurimetDataFromDbDto> getEarNormal() {
        return earNormal;
    }

    public ObservableList<KonkurimetDataFromDbDto> getEeNormal() {
        return eeNormal;
    }

    public ObservableList<KonkurimetDataFromDbDto> getTikNormal() {
        return tikNormal;
    }

    public ObservableList<KonkurimetDataFromDbDto> getPaPranuarNormal() {
        return paPranuarNormal;
    }

    public ObservableList<KonkurimetDataFromDbDto> getIksMinoritet() {
        return iksMinoritet;
    }

    public ObservableList<KonkurimetDataFromDbDto> getEarMinoritet() {
        return earMinoritet;
    }

    public ObservableList<KonkurimetDataFromDbDto> getEeMinoritet() {
        return eeMinoritet;
    }

    public ObservableList<KonkurimetDataFromDbDto> getTikMinoritet() {
        return tikMinoritet;
    }

    public ObservableList<KonkurimetDataFromDbDto> getPaPranuarMinoritet() {
        return paPranuarMinoritet;
    }
}
