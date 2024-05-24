package service;

import model.Afat;
import repository.AfatRepository;
import repository.StudentRepository;

public class AfatService {


    public static Afat getAfatiRegjistrimitForStudent(int userId){
        return AfatRepository.getAfatiRegjistrimitForStudent(userId);
    }
}
