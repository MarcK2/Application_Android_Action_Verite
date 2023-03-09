package fr.utt.if26.projet_kadangha.view;

import android.Manifest;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.presenter.GeneralPresenter;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;

public class FormulaireRGPDView {
    View localView;
    Context c;
    MainActivity ma;
    GeneralPresenter presenterG;


    public FormulaireRGPDView(Context context , ViewGroup container){
        c=context;
        ma=(MainActivity)c;
        localView= LayoutInflater.from(context).inflate(R.layout.view_formulaire_rgpd,container,false);

    }

    public void display(){
        Button bt_continuer=(Button) localView.findViewById(R.id.button_submit);
        CheckBox ch_acc=(CheckBox) localView.findViewById(R.id.cb_accept);
        presenterG= new GeneralPresenter(localView.getContext());

        bt_continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterG.saveFormSubmitted(true);
                if(ch_acc.isChecked()){
                    presenterG.permissionLocalisation();
                }
                ma.authentification();
            }
        });


    }
    public View getRootView(){
        return localView;
    }
}
