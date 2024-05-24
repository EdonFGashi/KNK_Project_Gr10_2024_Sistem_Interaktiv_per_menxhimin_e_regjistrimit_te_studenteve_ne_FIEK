package controller;

import model.Admin;
import model.SupervisorTableModel;
import model.User;

import java.util.Locale;

public class SESSION {
    private static Admin loggedAdmin;

    private static SupervisorTableModel loggedSupervisor;

    private static String loggedUserEmail;
    private static User loggedUser;

    private static String adminMenu = "";

    private static String supervisor_lastSearch = "";

    private static String admin_registration_lastSearch = "";
    private static String admin_supervisor_lastSearch = "";
    private static String admin_semsStaf_lastSearch = "";
    private static int admin_reset_PasswordId = 0;
    private static String admin_reset_type = "";
    private static String admin_student_lastSearch = "";
//    Temporary
    private static int user;
    private static String UserLevel = "";
    private static int AplicantAfatID;
    private static boolean loginPenalty = false;
    private static int loginPenaltyRemainingTime;
    private static int loginAttemptCount;
    private static int loginPenaltyTime;



    private static boolean toggleShqip = true;
    private SESSION() {}

    public static String getSupervisor_lastSearch() {return supervisor_lastSearch;}
    public static void setSupervisor_lastSearch (String supervisor_lastSearch){
        SESSION.supervisor_lastSearch = supervisor_lastSearch;
    }

    public static String getAdmin_registration_lastSearch() {
        return admin_registration_lastSearch;
    }

    public static void setAdmin_registration_lastSearch(String admin_registration_lastSearch) {
        SESSION.admin_registration_lastSearch = admin_registration_lastSearch;
    }

    public static int getUser() {
        return user;
    }

    public static String getAdminMenu() {
        return adminMenu;
    }

    public static void setAdminMenu(String adminMenu) {
        SESSION.adminMenu = adminMenu;
    }

    public static String getAdmin_supervisor_lastSearch() {
        return admin_supervisor_lastSearch;
    }

    public static int getAdmin_reset_PasswordId() {
        return admin_reset_PasswordId;
    }

    public static void setAdmin_reset_PasswordId(int admin_reset_PasswordId) {
        SESSION.admin_reset_PasswordId = admin_reset_PasswordId;
    }

    public static void setAdmin_supervisor_lastSearch(String admin_supervisor_lastSearch) {
        SESSION.admin_supervisor_lastSearch = admin_supervisor_lastSearch;
    }

    public static String getAdmin_semsStaf_lastSearch() {
        return admin_semsStaf_lastSearch;
    }

    public static void setAdmin_semsStaf_lastSearch(String admin_semsStaf_lastSearch) {
        SESSION.admin_semsStaf_lastSearch = admin_semsStaf_lastSearch;
    }

    public static void setAdmin_reset_type(String admin_reset_type) {
        SESSION.admin_reset_type = admin_reset_type;
    }

    public static String getAdmin_reset_type() {
        return admin_reset_type;
    }

    public static String getAdmin_student_lastSearch() {
        return admin_student_lastSearch;
    }

    public static void setAdmin_student_lastSearch(String admin_student_lastSearch) {
        SESSION.admin_student_lastSearch = admin_student_lastSearch;
    }

    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static void setLoggedAdmin(Admin loggedAdmin) {
        SESSION.loggedAdmin = loggedAdmin;
    }

    public static SupervisorTableModel getLoggedSupervisor() {return loggedSupervisor;}
    public static void setLoggedSupervisor(SupervisorTableModel loggedSupervisor) {
        SESSION.loggedSupervisor = loggedSupervisor;
    }

    public static String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public static void setLoggedUserEmail(String loggedUserEmail) {
        SESSION.loggedUserEmail = loggedUserEmail;
    }

    public static void setUser(int user) {
        SESSION.user = user;
    }
     public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        SESSION.loggedUser = loggedUser;
    }

    public static void setDeptLevel(String Level) {
        SESSION.UserLevel = Level;
    }
    public static String getDeptLevel() {
        return UserLevel;
    }

    public static void setAplicantAfatID(int Id) {
        SESSION.AplicantAfatID = Id;
    }
    public static int getAplicantAfatId() {
        return  AplicantAfatID;
    }

    public static String getUserLevel() {
        return UserLevel;
    }

    public static void setUserLevel(String userLevel) {
        UserLevel = userLevel;
    }

    public static int getAplicantAfatID() {
        return AplicantAfatID;
    }

    public static boolean isToggleShqip() {
        return toggleShqip;
    }

    public static void setToggleShqip(boolean toggleShqip) {
        SESSION.toggleShqip = toggleShqip;
        if(toggleShqip) {
            Locale.setDefault(new Locale("sq"));
        }else{
            Locale.setDefault(new Locale("en"));
        }
    }
    public static boolean getLoginPenalty(){return loginPenalty;}
    public static void setLoginPenalty(boolean loginPenalty) {SESSION.loginPenalty = loginPenalty;}

    public static int getLoginRemainingPenaltyTime(){return loginPenaltyRemainingTime;}
    public static void setLoginRemainingPenaltyTime(int loginPenaltyTime) {SESSION.loginPenaltyRemainingTime = loginPenaltyTime;}

    public static int getLoginAttemptCount(){return loginAttemptCount;}
    public static void setLoginAttemptCount(int loginAttemptCount) {SESSION.loginAttemptCount = loginAttemptCount;}

    public static int getLoginPenaltyTime(){return loginPenaltyTime;}
    public static void setLoginPenaltyTime(int loginPenaltyTime) {SESSION.loginPenaltyTime = loginPenaltyTime;}


}
