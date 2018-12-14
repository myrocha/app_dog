package br.com.appdog.model.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import br.com.appdog.R;


public class SharedPreference {

    private SharedPreferences sharedPref;


    @Inject
    public SharedPreference(final SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    public boolean isSaveTokenUser(final Context context, final String token) {
        final SharedPreferences.Editor editor = sharedPref.edit().putString(context.getString(R.string.token),
                token);
        return  editor.commit();


    }

    public String getIsToken(final Context context) {
        return sharedPref.getString(context
                .getString(R.string.token), "");
        //return token;

    }
}
