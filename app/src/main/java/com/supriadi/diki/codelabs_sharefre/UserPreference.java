package com.supriadi.diki.codelabs_sharefre;

import android.content.Context;
import android.content.SharedPreferences;

import com.supriadi.diki.codelabs_sharefre.model.UserModel;

class UserPreference {
    private static final String PREFS_NAME = "MahasiswaPref";
    private static final String APP_FIRST_RUN = "app_first_run";
    private SharedPreferences prefs;

    public UserPreference(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(APP_FIRST_RUN, input);
        editor.apply();
    }
    public Boolean getFirstRun() {
        return prefs.getBoolean(APP_FIRST_RUN, true);
    }

}
