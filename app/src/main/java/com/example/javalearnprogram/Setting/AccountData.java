package com.example.javalearnprogram.Setting;

public class AccountData {
    private static String PASSWORD = "";
    private static String LOGIN = "";

    public static boolean LoginAccount(String log, String pas) {
        return log.equals(LOGIN) && pas.equals(PASSWORD) ? true : false;
    }
}
