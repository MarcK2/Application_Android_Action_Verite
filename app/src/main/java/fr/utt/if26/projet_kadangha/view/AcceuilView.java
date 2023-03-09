package fr.utt.if26.projet_kadangha.view;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.presenter.GeneralPresenter;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;
import fr.utt.if26.projet_kadangha.view.action.ContentActionView;

public class AcceuilView {
    View localView;
    Context c;
    MainActivity ma;
    JoueurRecyclerPresenter presenterJ;
    GeneralPresenter presenterG;


    public AcceuilView(Context context , ViewGroup container){
        c=context;
        ma=(MainActivity)c;
        localView= LayoutInflater.from(context).inflate(R.layout.activity_main,container,false);

    }

    public void display(){
        TextView tv_title= (TextView) localView.findViewById(R.id.main_title) ;
        Button bt_jouer=(Button) localView.findViewById(R.id.main_jouer);
        Button bt_continuer=(Button) localView.findViewById(R.id.main_continuer);

        FloatingActionButton bt_mute=(FloatingActionButton) localView.findViewById(R.id.main_bt_mute);
        FloatingActionButton bt_param=(FloatingActionButton) localView.findViewById(R.id.main_bt_param);

        presenterJ= new JoueurRecyclerPresenter(localView.getContext());
        presenterG= new GeneralPresenter(localView.getContext());

        bt_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterJ.addPartie();
                ma.choixTypeJeu();
            }
        });

        bt_continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.jeuChoixQ();
            }
        });

        bt_param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.ouvrirParam();
            }
        });
        bt_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ma.isMusicPlaying()==false){
                    ma.setMp(MediaPlayer.create(c, R.raw.jojo));
                    presenterG.playMusic();
                    ma.setMusicPlaying(true);
                    bt_mute.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_volume_up));
                }else{

                    presenterG.stopMusic();
                    ma.setMusicPlaying(false);
                    bt_mute.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_volume_off));
                }

            }
        });

    }
    public View getRootView(){
        return localView;
    }
}
