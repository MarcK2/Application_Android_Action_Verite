package fr.utt.if26.projet_kadangha.presenter;

import android.content.Context;
import android.widget.CheckBox;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Joueur;
import fr.utt.if26.projet_kadangha.model.JoueurModel;
import fr.utt.if26.projet_kadangha.model.Question;
import fr.utt.if26.projet_kadangha.model.QuestionModel;

public class JoueurRecyclerPresenter {
    private Context context;
    private JoueurModel joueurModel;

    public JoueurRecyclerPresenter(Context context){
        this.context=context;
        this.joueurModel= new JoueurModel(context);
    }

    //Méthodes métiers


    public void getAllJoueur(RecyclerView recyclerView){
        ArrayList<Joueur> list= joueurModel.getBDJoueur();
        JoueurRecyclerAdapter adapter = new JoueurRecyclerAdapter(context,list,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }



    public void addJoueur(Joueur j){
        joueurModel.addJoueur(j);
    }

    public void delJoueur(Joueur j){
        joueurModel.delJoueur(j);
    }
    public ArrayList<String> getSurnom(){
        return joueurModel.getSurnom();
    }


    public void addPartie(){
        joueurModel.addPartiePlayed();
        joueurModel.initScore();
    }

    public void updateJ(String surnom,int valeur_q){
        Joueur j =joueurModel.findJ(surnom);
        j.setScore(j.getScore()+valeur_q);
        joueurModel.updateJoueur(j);
    }


    public Joueur getRandomJ(){
        return joueurModel.randomJ();
    }
    public Joueur getActuelJ(){
        return joueurModel.getjActuel();
    }

}
