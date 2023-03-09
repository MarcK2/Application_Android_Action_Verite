package fr.utt.if26.projet_kadangha.model;

import java.io.Serializable;

public class Joueur implements Serializable {
    // toute personne qui participera au jeu
    private String surnom; // clé surnom=>username
    private int age , score, nb_partie_joue;

    public Joueur(String surnom, int age) {
        this.surnom = surnom;
        this.age = age;
        this.score = 0;
        this.nb_partie_joue = 0;
    }

    public Joueur() {
    }



    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNb_partie_joue() {
        return nb_partie_joue;
    }

    public void setNb_partie_joue(int nb_partie_joue) {
        this.nb_partie_joue = nb_partie_joue;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "surnom=" + surnom +
                ", age='" + age + '\'' +
                ", score='" + score + '\'' +
                ", Nombre de parties jouées='" + nb_partie_joue + '\'' +
                '}';
    }

    public void affiche() {
        System.out.println(toString());
    }
}