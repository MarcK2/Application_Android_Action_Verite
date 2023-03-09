package fr.utt.if26.projet_kadangha.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Joueur;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;

public class JeuChoixQView {
    View localView;
    RecyclerView recyclerView;
    JoueurRecyclerPresenter presenterJ;
    Context c;
    MainActivity ma;

    public JeuChoixQView(Context context , ViewGroup container){
        localView= LayoutInflater.from(context).inflate(R.layout.view_jeu_choix,container,false);
        c=context;
        ma=(MainActivity) c;
    }

    public void display(){
        TextView tv_j_actuel= (TextView) localView.findViewById(R.id.tv_joueur_actu) ;
        Button bt_action=(Button) localView.findViewById(R.id.bt_action);
        Button bt_verite=(Button) localView.findViewById(R.id.bt_verite);

        presenterJ= new JoueurRecyclerPresenter(localView.getContext());
        Joueur j= presenterJ.getRandomJ();

        tv_j_actuel.setText("C'est au tour du Joueur : "+j.getSurnom());

        bt_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.jeuActivite(1,j.getSurnom());
            }
        });

        bt_verite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.jeuActivite(2,j.getSurnom());
            }
        });

    }


    public View getRootView(){
        return localView;
    }
}
