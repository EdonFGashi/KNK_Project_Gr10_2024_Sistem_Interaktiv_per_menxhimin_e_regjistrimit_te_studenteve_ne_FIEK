package service;

import model.Arkiva;
import repository.AdminRepository;
import repository.ArkivaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArkivaDokumenteveService {
    public static int getLastDocumentId(){
        return AdminRepository.getLastDocumentIdFromDb();
    }
    public static boolean arkivoDokumentin(Arkiva arkivimi){
        boolean isInserted = ArkivaRepository.arkivoDokumentin(arkivimi);
        System.out.println("Ü insertua");
        return isInserted;
    }

    public static boolean verifiko(Arkiva arkiva){
//        boolean verifikuar = ArkivaRepository.verifiko(arkiva);
        Arkiva arkiva1 = ArkivaRepository.verifiko(arkiva);
        if(arkiva1 == null){
            return false;
        } else{
            return true;
        }
    }
}
