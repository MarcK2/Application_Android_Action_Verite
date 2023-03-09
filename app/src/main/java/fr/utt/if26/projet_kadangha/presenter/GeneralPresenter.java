package fr.utt.if26.projet_kadangha.presenter;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.App;
import fr.utt.if26.projet_kadangha.model.JoueurModel;

public class GeneralPresenter {
    // Il gère la gestion de la musique du choix rgpd
    private Context context;
    private App app;
    private MainActivity ma;
    private static final String PREF_FORM_SUBMITTED = "form_submitted";
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private boolean mFormSubmitted=false;

    public boolean ismFormSubmitted() {
        mFormSubmitted = loadFormSubmitted();
        return mFormSubmitted;
    }

    public void setmFormSubmitted(boolean mFormSubmitted) {
        this.mFormSubmitted = mFormSubmitted;
    }

    public GeneralPresenter(Context context){
        this.context=context;
        this.app= new App(context);
        ma=(MainActivity)context;
    }

    public void playMusic() {
        // Créez un nouvel objet MediaPlayer en utilisant le fichier audio téléchargé
        ma.getMp().setLooping(true);
        ma.getMp().start();
    }

    // Dans la méthode de votre Presenter qui gère l'arrêt de la musique
    public void stopMusic() {
        if (ma.getMp() != null) {
            ma.getMp().stop();
        }
    }

    public void saveFormSubmitted(boolean formSubmitted) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_FORM_SUBMITTED, formSubmitted);
        editor.apply();
    }

    public boolean loadFormSubmitted() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(PREF_FORM_SUBMITTED, false);
    }

    public void permissionLocalisation(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ma,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    public void rmPermission(){
                ActivityCompat.requestPermissions(ma,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_LOCATION_PERMISSION);
    }




    public void delAll(){
        app.delAll();
        saveFormSubmitted(false);
        rmPermission();
    }
}
