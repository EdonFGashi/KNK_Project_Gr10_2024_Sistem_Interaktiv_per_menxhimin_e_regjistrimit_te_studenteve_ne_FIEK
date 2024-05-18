package service.Admin;


import model.KonkurimetDataFromDbDto;
import model.dto.Admin.*;
import repository.AdminRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KonkurimetService {
    public static RegistrationListsToController ktheKonkurimet(KonkurimetByAfatDto konkurimetData){

        String niveli = konkurimetData.getNiveli();
        String hera = konkurimetData.getHera();
        System.out.println("Niveli: "+niveli);
        System.out.println("Hera: "+hera);
        System.out.println("AfatId:" +konkurimetData.getAfatId());

        ArrayList<KonkurimetDataFromDbDto> allKonkurimetPerAfat = AdminRepository.getAllKonkurimetPerAfat(konkurimetData.getAfatId());

         for(KonkurimetDataFromDbDto k:allKonkurimetPerAfat){
             System.out.println(k.getEmri());
         }

        DepartmentAcceptanceDto acceptanceData = AdminRepository.getAcceptance(
                new GetAcceptanceDto(niveli,hera)
        );

        if (acceptanceData != null) {
            int numriPranimeveIks = acceptanceData.getIKSNormal();
            int numriPranimeveEar = acceptanceData.getEARNormal();
            int numriPranimeveEe = acceptanceData.getEENormal();
            int numriPranimeveTik = acceptanceData.getTIKNormal();

            RegistrationMenager normalRegistation = new RegistrationMenager(
                    numriPranimeveIks, numriPranimeveEar, numriPranimeveTik, numriPranimeveEe
            );

            int numriMinoritetIks = acceptanceData.getIKSMinoritet();
            int numriMinoritetEar = acceptanceData.getEARMinoritet();
            int numriMinoritetEe = acceptanceData.getEEMinoritet();
            int numriMinoritetTik = acceptanceData.getTIKMinoritet();

            RegistrationMenager minoritetRegistration = new RegistrationMenager(
                    numriMinoritetIks, numriMinoritetEar, numriMinoritetTik, numriMinoritetEe
            );


            for (KonkurimetDataFromDbDto student : allKonkurimetPerAfat) {
                if (!student.isMinoritet()) {
                    normalRegistation.addNewStudent(student);
                }else{
                    minoritetRegistration.addNewStudent(student);
                }
            }

            return new RegistrationListsToController(
                  normalRegistation.getIksList(),
                    normalRegistation.getEarList(),
                    normalRegistation.getEeList(),
                    normalRegistation.getTikList(),
                    normalRegistation.getPaPranuarList(),
                    minoritetRegistration.getIksList(),
                    minoritetRegistration.getEarList(),
                    minoritetRegistration.getEeList(),
                    minoritetRegistration.getTikList(),
                    minoritetRegistration.getPaPranuarList()
            );

        }
        return null;
    }

}



//Klase ndihmese Regjistrimeve

class RegistrationMenager {
    private HashMap<KonkurimetDataFromDbDto, Double> iksList;
    private int nrIks; //numri total qe pranohen ne iks


    private HashMap<KonkurimetDataFromDbDto, Double> earList;
    private int nrEar;

    private HashMap<KonkurimetDataFromDbDto, Double> tikList;
    private int nrTik;

    private HashMap<KonkurimetDataFromDbDto, Double> eeList;

    private int nrEe;

    private HashMap<KonkurimetDataFromDbDto, Double> paPranuarList;

    private int nrPapranuar;


    public RegistrationMenager(int nrIks, int nrEar, int nrTik, int nrEe) {
        this.nrIks = nrIks;
        this.nrEar = nrEar;
        this.nrTik = nrTik;
        this.nrEe = nrEe;
        this.nrPapranuar = 20000;
        this.iksList = new HashMap<>();
        this.earList = new HashMap<>();
        this.eeList = new HashMap<>();;
        this.tikList = new HashMap<>();
        this.paPranuarList = new HashMap<>();
    }

    public void addNewStudent(KonkurimetDataFromDbDto student) {
        this.setStudentOnPriority(student, 1);
    }

    private void setStudentOnPriority(KonkurimetDataFromDbDto student, int iteration) {

        if (iteration > 4) {
            this.paPranuarList.put(student, student.getTotal());
            return;
        }

        String priority;
        if (iteration == 1) {
            priority = student.getP1();
        } else if (iteration == 2) {
            priority = student.getP2();
        } else if (iteration == 3) {
            priority = student.getP3();
        } else if (iteration == 4) {
            priority = student.getP4();
        } else {
            priority = "PaPranuar";
        }

        HashMap<KonkurimetDataFromDbDto, Double> currentList;
        int currentNumriPranimeve;

        if (priority.equals("IKS")) {
            this.manipulateList(this.iksList, this.nrIks, student, iteration);
        } else if (priority.equals("EAR")) {
            this.manipulateList(this.earList, this.nrEar, student, iteration);
        } else if (priority.equals("TIK")) {
            this.manipulateList(this.tikList, this.nrTik, student, iteration);
        } else if (priority.equals("EE")) {
            this.manipulateList(this.eeList, this.nrEe, student, iteration);
        } else {
            this.manipulateList(this.paPranuarList, this.nrPapranuar, student, iteration);
        }

    }

    private void manipulateList(HashMap<KonkurimetDataFromDbDto, Double> currentList, int currentNr, KonkurimetDataFromDbDto student, int iteration) {
        currentList.put(student, student.getTotal());

        List<Map.Entry<KonkurimetDataFromDbDto, Double>> sortedList =
                currentList.entrySet().stream().sorted(Map.Entry.<KonkurimetDataFromDbDto, Double>comparingByValue().reversed())
                        .collect(Collectors.toList());

       // HashMap<KonkurimetDataFromDbDto, Double> temporalArray = new HashMap<>();

        //Jan sortu sipas pikeve!

        if (sortedList.size() > currentNr) {
            //Nese
            currentList.clear();
            for (int i = 0; i < sortedList.size() - 1; i++) {
                Map.Entry<KonkurimetDataFromDbDto, Double> entry = sortedList.get(i);
                currentList.put(entry.getKey(), entry.getValue());
            }

            KonkurimetDataFromDbDto lastStudent = sortedList.get(sortedList.size() - 1).getKey(); //studenti i fundit
            if(lastStudent.getUserId() == student.getUserId()) {
                this.setStudentOnPriority(lastStudent, iteration + 1);
            }else{
                this.setStudentOnPriority(lastStudent, 2);
            }
        } else {
            currentList.clear();
            for (Map.Entry<KonkurimetDataFromDbDto, Double> entry : sortedList) {
                currentList.put(entry.getKey(), entry.getValue());
            }

        }
    }

    public ArrayList<KonkurimetDataFromDbDto> getIksList() {
        return getSortedList(this.iksList);
    }

    public ArrayList<KonkurimetDataFromDbDto> getEarList() {
        return getSortedList(this.earList);
    }

    public ArrayList<KonkurimetDataFromDbDto> getEeList() {
        return getSortedList(this.eeList);
    }

    public ArrayList<KonkurimetDataFromDbDto> getTikList() {
        return getSortedList(this.tikList);
    }

    public ArrayList<KonkurimetDataFromDbDto> getPaPranuarList() {
        return getSortedList(this.paPranuarList);
    }

    // New method to sort list
    private ArrayList<KonkurimetDataFromDbDto> getSortedList(HashMap<KonkurimetDataFromDbDto, Double> list) {
        return list.entrySet().stream()
                .sorted(Map.Entry.<KonkurimetDataFromDbDto, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
