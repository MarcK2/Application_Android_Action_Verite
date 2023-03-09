package fr.utt.if26.projet_kadangha.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Joueur;
import fr.utt.if26.projet_kadangha.model.Question;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;
import fr.utt.if26.projet_kadangha.presenter.QuestionRecyclerPresenter;

public class JeuActiviteView {
    View localView;
    Context c;
    MainActivity ma;
    JoueurRecyclerPresenter presenterJ;
    QuestionRecyclerPresenter presenterQ;
    int type;
    String surnom;

    public JeuActiviteView(Context context , ViewGroup container,int t,String jActuel){
        type=t;
        surnom=jActuel;
        c=context;
        ma=(MainActivity)c;
        localView= LayoutInflater.from(context).inflate(R.layout.view_jeu_activite,container,false);

    }

    public void display(){
        TextView tv_j_actuel= (TextView) localView.findViewById(R.id.tv_joueur_tour) ;
        TextView tv_question= (TextView) localView.findViewById(R.id.tv_jeu_contenu_q) ;
        TextView tv_gage= (TextView) localView.findViewById(R.id.tv_jeu_gage_q) ;
        TextView tv_type= (TextView) localView.findViewById(R.id.tv_jeu_action_verite) ;
        TextView tv_point= (TextView) localView.findViewById(R.id.tv_jeu_nb_points) ;

        Button bt_reussite=(Button) localView.findViewById(R.id.bt_reussite);
        Button bt_gage=(Button) localView.findViewById(R.id.bt_gage);
        Button bt_echec=(Button) localView.findViewById(R.id.bt_echec);

        presenterJ= new JoueurRecyclerPresenter(localView.getContext());
        presenterQ= new QuestionRecyclerPresenter(localView.getContext());

        Question q=presenterQ.getQuestion(type,ma.getParamQ(),ma.getParamQ2());

        tv_j_actuel.setText(surnom);
        tv_type.setText(q.getType());
        tv_question.setText(q.getContenu());
        tv_gage.setText(q.getGage());
        tv_point.setText(String.valueOf(q.getNb_point()));

        bt_reussite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(localView,"Il a réussi",Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {snackbar.dismiss();}
                });
                snackbar.show();
                presenterJ.updateJ(surnom,q.getNb_point());
                ma.jeuChoixQ();
            }
        });

        bt_gage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(localView,"Il a fait son gage pas de penalités",Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {snackbar.dismiss();}
                });
                snackbar.show();
                ma.jeuChoixQ();
            }
        });

        bt_echec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(localView,"Il a perdu des points",Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {snackbar.dismiss();}
                });
                snackbar.show();presenterJ.updateJ(surnom,-q.getNb_point());
                ma.jeuChoixQ();
            }
        });




    }

    public View getRootView(){
        return localView;
    }


}
