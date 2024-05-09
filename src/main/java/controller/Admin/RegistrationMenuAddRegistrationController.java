package controller.Admin;

import app.Navigatior;
import app.PopUp;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.dto.Admin.AddNewAfatDto;
import repository.AfatRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrationMenuAddRegistrationController {
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
    @FXML RadioButton radioFirst, radioSecond;


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
          String formattedClosedDate = openDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          String choiceLevel = this.choiseChoseLevel.getValue();

        AddNewAfatDto addNewAfatDto = new AddNewAfatDto(
            year,hera,formattedOpenDate,formattedClosedDate,choiceLevel
        );
       if(AfatRepository.addNewAfat(addNewAfatDto)){
           PopUp.tick(250);
       }else{
           System.out.println("Afat did not create");
       };

    }

}
