package com.example.application;

import android.annotation.SuppressLint;
import android.app.Application;


@SuppressLint("Registered")
public class CCustomApplication extends Application
{

    private CCustomSharedPreference sharedCustom;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedCustom                                                                = new CCustomSharedPreference(getApplicationContext());
    }

    public CCustomSharedPreference getSharedCustom(){

        return sharedCustom;
    }

}


