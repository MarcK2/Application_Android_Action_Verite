package fr.utt.if26.projet_kadangha.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.presenter.GeneralPresenter;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;

public class ParametreView {
    View localView;
    Context c;
    MainActivity ma;
    GeneralPresenter presenterG;


    public ParametreView(Context context , ViewGroup container){
        c=context;
        ma=(MainActivity)c;
        localView= LayoutInflater.from(context).inflate(R.layout.view_parametre,container,false);
        presenterG=new GeneralPresenter(c);
    }

    public void display(){
        Button bt_tuto=(Button) localView.findViewById(R.id.bt_tuto);
        Button bt_del=(Button) localView.findViewById(R.id.bt_del_all);

        FloatingActionButton bt_mail=(FloatingActionButton) localView.findViewById(R.id.bt_send_mail);
        ImageButton bt_fb=(ImageButton) localView.findViewById(R.id.ibt_fb);
        ImageButton bt_param=(ImageButton) localView.findViewById(R.id.ibt_tw);



        bt_tuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.tuto();
            }
        });

        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Confirmation");
                builder.setIcon(R.drawable.ic_baseline_warning_24);
                builder.setMessage("Êtes-vous sûr de vouloir tout supprimer ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenterG.delAll();
                                Snackbar snackbar = Snackbar.make(localView,"Vos données ajoutées ont bien été supprimées",Snackbar.LENGTH_LONG);
                                snackbar.setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {snackbar.dismiss();}
                                });
                                snackbar.show();
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        bt_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.sendEmail();
            }
        });

        bt_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.goFacebook();
            }
        });

        bt_param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.goTwitter();
            }
        });

    }
    public View getRootView(){
        return localView;
    }
}


