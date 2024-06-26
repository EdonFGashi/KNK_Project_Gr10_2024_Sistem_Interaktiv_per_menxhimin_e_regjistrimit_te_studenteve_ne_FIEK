package controller.Admin;

import app.Navigatior;
import app.PopUp;
import controller.SESSION;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.dto.Admin.AddNewAfatDto;
import repository.AfatRepository;
import service.Admin.AdminService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RegistrationMenuAddRegistrationController {
   ObservableList<String> nivelet =
           FXCollections.observableArrayList("BSC","MSC","PHD");



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
    private void initialize(){
          this.choiseChoseLevel.setItems(nivelet);

    }
    @FXML
    private void handleSave(ActionEvent ae){
         String hera;
          if (radioFirst.isSelected()) {
              hera = "1";
          } else {
              hera = "2";
          }
          int year;
          if(this.txtYear.getText() != null) {
              year = Integer.parseInt(this.txtYear.getText());
          }else{
               year = 2024;
          }
          LocalDate openDate = this.dateOpenDate.getValue();
          String formattedOpenDate = openDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          LocalDate closeDate = this.dateClosedDate.getValue();
          String formattedClosedDate = closeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          String choiceLevel = this.choiseChoseLevel.getValue();

        AddNewAfatDto addNewAfatDto = new AddNewAfatDto(
            year,hera,formattedOpenDate,formattedClosedDate,choiceLevel
        );
       if(AdminService.addNewAfat(addNewAfatDto)){
           PopUp.tick(250);
       }else{
           if(SESSION.isToggleShqip()) PopUp.loading("Error: Afati nuk u krijua",false,"");
           else PopUp.loading("Error: Registration did not Create",false,"");
       };
    }

}
