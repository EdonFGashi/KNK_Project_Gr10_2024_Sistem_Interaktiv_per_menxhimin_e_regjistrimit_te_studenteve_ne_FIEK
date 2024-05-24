package service.Supervisor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SemsStafModel;
import repository.Supervisor.SemsStafRepository;

public class SemsStafService {
    public static ObservableList<SemsStafModel> searchSemsStaf(String search) {
        try {
            if (search.isEmpty()) {
                return FXCollections.observableArrayList(SemsStafRepository.getAllSemsStaf());

            } else {
                return FXCollections.observableArrayList(SemsStafRepository.getSemsStafSearch(search));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }


}
