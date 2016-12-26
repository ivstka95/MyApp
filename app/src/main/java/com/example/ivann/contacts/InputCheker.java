package com.example.ivann.contacts;

/**
 * Created by Ivann on 21.12.2016.
 */

public class InputCheker {
    static public boolean checkLogin(String s) {
        if (s.length() == 0) return false;
        else return true;
    }

    static public boolean checkEmail(String s) {
        if (s.contains("@") && s.contains("."))
            if (s.indexOf('@') < s.indexOf('.'))
                return true;
        return false;
    }

    static public boolean checkNumber(String s) {
        if (s.charAt(0) == '+') {
            if (s.length() != 13)
                return false;
            if (s.length() == 13)
                return true;
        }

        if (s.charAt(0) == '0') {
            if (s.length() != 10)
                return false;
            if (s.length() == 10)
                return true;
        }
        return false;
    }
}


