package fr.utt.if26.projet_kadangha.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class JoueurModel {
    private ArrayList<Joueur> data;
    private ArrayList<Joueur> dataTemp= new ArrayList<>();
    private Joueur jActuel;
    private Context context;

    public JoueurModel() {
        data = new ArrayList<Joueur>();
    }

    public JoueurModel(Context context) {
        data = new ArrayList<Joueur>();
        this.context=context;
    }

    public void ajoute(Joueur j) {
        data.add(j);
    }

    public void initLocal() {
        ajoute(new Joueur("Marc",21));
        ajoute(new Joueur("Kenny",7));
        ajoute(new Joueur("Gloria",12));
        ajoute(new Joueur("Jovana",15));

    }

    public ArrayList<String> getSurnom() {
        ArrayList<String> res = new ArrayList<>();
        Iterator iterateur = getBDJoueur().iterator();
        while (iterateur.hasNext()) {
            Joueur j = (Joueur) (iterateur.next());
            res.add(j.getSurnom());
        }
        return res;
    }


    public ArrayList<Joueur> getTempJoueur() {
        return dataTemp;
    }

    public ArrayList<Joueur> getBDJoueur() {
        this.initBD();
        return data;
    }

    public void initBD(){
        BDOpenHelper bdOH = new BDOpenHelper(context);
        if (bdOH.countJoueur()==0){
            bdOH.initJoueurs();
        }
        data=bdOH.getAllJoueurs();
        bdOH.close();
    }

    public void updateJoueur(Joueur j) {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        bdOH.updateJoueur(j);
        bdOH.close();
    }

    public void addJoueur(Joueur j) {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        bdOH.createJoueur(j);
        bdOH.close();
    }

    public void delJoueur(Joueur j) {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        bdOH.deleteJoueur(j);
        bdOH.close();
    }

    public void addJoueurTemp(Joueur j) {
        dataTemp.add(j);
    }

    public void initScore(){
        BDOpenHelper bdOH = new BDOpenHelper(context);
        bdOH.initJoueursScore();
        bdOH.close();
    }

    //Prendre le joueur dont le surnom est selectionner
    public void addSelected(String nom){
        for (Joueur j : data) {
            if (j.getSurnom().equals(nom)) {
                addJoueurTemp(j);
                System.out.println(j);
                System.out.println(dataTemp.size());
            }
        }
    }

    public Joueur findJ(String nom){
        initBD();
        for (Joueur j : data) {
            if (j.getSurnom().equals(nom)) {
                return j;
            }
        }
        return null;
    }

    public ArrayList<Joueur> getLocalJoueur() {
        initLocal();
        return data;
    }

    public void addPartiePlayed(){
        initBD();
        for(Joueur j : data){
            j.setNb_partie_joue(j.getNb_partie_joue()+1);
            updateJoueur(j);
        }
    }

    public void updateAll(){
        for(Joueur j : data){
            updateJoueur(j);
        }
    }
    public Joueur randomJ(){
        initBD();
        System.out.println(data.size());
        jActuel=data.get(new Random().nextInt(data.size()));
        return jActuel;
    }

    public Joueur getjActuel() {
        return jActuel;
    }
}
