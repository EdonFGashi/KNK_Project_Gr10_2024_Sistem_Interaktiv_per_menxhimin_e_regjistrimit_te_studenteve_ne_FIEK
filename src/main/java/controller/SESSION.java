package controller;

public class SESSION {
    private static String loggedUserEmail = "edon@admin.uni-pr.edu";
    private static String adminMenu = "";
    private static String admin_registration_lastSearch = "";
    private static String admin_supervisor_lastSearch = "";
    private static int admin_reset_PasswordId = 0;
    private static String admin_reset_type = "";
    private static String admin_student_lastSearch = "";
//    Temporary
    private static int user;
    private SESSION() {}

    public static String getAdmin_registration_lastSearch() {
        return admin_registration_lastSearch;
    }

    public static void setAdmin_registration_lastSearch(String admin_registration_lastSearch) {
        SESSION.admin_registration_lastSearch = admin_registration_lastSearch;
    }

    public static String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public static void setLoggedUserEmail(String loggedUserEmail) {
        SESSION.loggedUserEmail = loggedUserEmail;
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

    public static int getUser(){
        return user;
    }
    public static void setUser(int user){SESSION.user = user;}
}
