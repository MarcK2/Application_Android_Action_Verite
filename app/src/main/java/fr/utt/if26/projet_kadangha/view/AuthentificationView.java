package fr.utt.if26.projet_kadangha.view;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.App;
import fr.utt.if26.projet_kadangha.presenter.GeneralPresenter;

public class AuthentificationView {
    View localView;
    Context c;
    MainActivity ma;
    GeneralPresenter presenterG;

    private CancellationSignal cancellationSignal = null;

    // create an authenticationCallback
    private BiometricPrompt.AuthenticationCallback authenticationCallback;



    public AuthentificationView(Context context , ViewGroup container){
        c=context;
        ma=(MainActivity)c;
        localView= LayoutInflater.from(context).inflate(R.layout.view_authentification,container,false);

    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void display(){
        Button bt_aut=(Button) localView.findViewById(R.id.start_authentication);
        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(
                    int errorCode, CharSequence errString)
            {
                super.onAuthenticationError(errorCode, errString);
                notifyUser("Erreur d'authentification : " + errString);

            }
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result)
            {
                super.onAuthenticationSucceeded(result);
                notifyUser("Authentication réussie");
                ma.acceuil();
            }
        };

        checkBiometricSupport();
        // create a biometric dialog on Click of button
       bt_aut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view)
            {

                BiometricPrompt biometricPrompt = new BiometricPrompt
                        .Builder(c)
                        .setTitle("Ouvrir l'application")
                        .setSubtitle("Action-Vérité")
                        .setDescription("Utiliser votre empreinte")
                        .setNegativeButton("Annuler", ma.getMainExecutor(), new DialogInterface.OnClickListener() {
                            @Override
                            public void
                            onClick(DialogInterface dialogInterface, int i)
                            {
                                notifyUser("Authentication Annulée");
                            }
                        }).build();

                // start the authenticationCallback in
                // mainExecutor
                biometricPrompt.authenticate(
                        getCancellationSignal(),
                        ma.getMainExecutor(),
                        authenticationCallback);
            }
        });
    }

    // it will be called when
    // authentication is cancelled by
    // the user
    private CancellationSignal getCancellationSignal()
    {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(
                new CancellationSignal.OnCancelListener() {
                    @Override public void onCancel()
                    {
                        notifyUser("Vous avez annulé l'authentification");
                    }
                });
        return cancellationSignal;
    }

    // it checks whether the
    // app the app has fingerprint
    // permission
    @RequiresApi(Build.VERSION_CODES.M)
    private Boolean checkBiometricSupport()
    {
        KeyguardManager keyguardManager = (KeyguardManager)ma.getSystemService(Context.KEYGUARD_SERVICE);
        if (!keyguardManager.isDeviceSecure()) {
            notifyUser("L'authentification avec empreinte na pas été activé dans les réglages");
            return false;
        }
        if (ActivityCompat.checkSelfPermission(c, android.Manifest.permission.USE_BIOMETRIC)!= PackageManager.PERMISSION_GRANTED) {
            notifyUser("Nous n'avons pas la permission d'empreinte digitale");
            return false;
        }
        if (ma.getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            return true;
        }
        else
            return true;
    }


    private void notifyUser(String message)
    {
        Snackbar snackbar = Snackbar.make(localView,message,Snackbar.LENGTH_LONG);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {snackbar.dismiss();}
        });
        snackbar.show();
    }

    public View getRootView(){
        return localView;
    }
}
