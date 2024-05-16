package controller;

import model.Admin;

public class SESSION {
    private static Admin loggedAdmin;

    private static String adminMenu = "";
    private static String admin_registration_lastSearch = "";
    private static String admin_supervisor_lastSearch = "";
    private static int admin_reset_PasswordId = 0;
    private static String admin_reset_type = "";
    private static String admin_student_lastSearch = "";
    private SESSION() {}

    public static String getAdmin_registration_lastSearch() {
        return admin_registration_lastSearch;
    }

    public static void setAdmin_registration_lastSearch(String admin_registration_lastSearch) {
        SESSION.admin_registration_lastSearch = admin_registration_lastSearch;
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

    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static void setLoggedAdmin(Admin loggedAdmin) {
        SESSION.loggedAdmin = loggedAdmin;
    }
}
