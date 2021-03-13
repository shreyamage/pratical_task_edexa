package com.practical.myapplication.common;


import android.app.Service;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Commn {
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
