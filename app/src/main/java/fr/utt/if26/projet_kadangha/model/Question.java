package fr.utt.if26.projet_kadangha.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int id ;
    private String type; // Action ou verite
    private String genre; // nourriture , famille boisson
    private String categorie_age; // tranche d age pour classer les questions
    private String contenu ;
    private int nb_point; // nombre de points que rapporte la question
    private String gage ; //  à réaliser en cas de non realisation de la question


    public Question(String type, String genre, String categorie_age,String contenu, int nb_point,String gage) {

        this.type = type;
        this.genre = genre;
        this.categorie_age = categorie_age;
        this.contenu=contenu;
        this.nb_point = nb_point;
        this.gage=gage;
    }

    public Question(){}

    public Question(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCategorie_age() {
        return categorie_age;
    }

    public void setCategorie_age(String categorie_age) {
        this.categorie_age = categorie_age;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getNb_point() {
        return nb_point;
    }

    public void setNb_point(int nb_point) {
        this.nb_point = nb_point;
    }

    public String getGage() {
        return gage;
    }

    public void setGage(String gage) {
        this.gage = gage;
    }

    public void affiche() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Question{" +
                "Action/Vérité='" + type + '\'' +
                ", Genre='" + genre + '\'' +
                ", Categorie age='" + categorie_age + '\'' +
                ", Contenu =" + contenu +
                ", Valeur en Point =" + nb_point +
                ", Gage à réaliser =" + gage +
                '}';
    }
}