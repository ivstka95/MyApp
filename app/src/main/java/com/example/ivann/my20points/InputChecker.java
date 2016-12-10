package com.example.ivann.my20points;

import android.content.Context;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * Created by Ivann on 08.12.2016.
 */
public class InputChecker {
    static public boolean checkLogin(String s) {
        if (s.length() == 0) return false;
        else return true;
    }

    static public boolean checkEmail(String s) {
        return true;
    }

    static public boolean checkNumber(String s) {
        if (s.charAt(0) == '+') {

            if (s.length() != 13) {
                //Toast.makeText(getContext(), "enter 12 numbers", Toast.LENGTH_LONG).show();
                return false;
            }
            if (s.length() == 13)
                return true;

        }

        if (s.charAt(0) == '0') {

            if (s.length() != 10) {

               // Toast.makeText(getContext(), "enter 10 numbers", Toast.LENGTH_LONG).show();
                return false;
            }
            if (s.length() == 10)
                return true;

        }
        return false;
    }


}
