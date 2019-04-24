package com.example.application;

import android.content.Context;
import android.content.SharedPreferences;

public class CCustomSharedPreference
{
    private static SharedPreferences sharedPrefCustom;

    public CCustomSharedPreference(Context context) {
        sharedPrefCustom = context.getSharedPreferences("SHARED_PREF_CUSTOM", Context.MODE_PRIVATE);
    }

    //Save Access_token
    public static void setAccessToken(String access_token){
        sharedPrefCustom.edit().putString("ACCESS_TOKEN", access_token).apply();
    }

    //Get Access_token
    public static String getAccessToken(){
        return sharedPrefCustom.getString("ACCESS_TOKEN", "");
    }
}
