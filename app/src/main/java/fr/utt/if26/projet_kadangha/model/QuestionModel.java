package fr.utt.if26.projet_kadangha.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class QuestionModel {
    private ArrayList<Question> data;
    private Question qActuel;
    private Context context;

    public QuestionModel() {
        data = new ArrayList<Question>();
    }

    public QuestionModel(Context context) {
        data = new ArrayList<Question>();
        this.context=context;
    }

    public void ajoute(Question q) {
        data.add(q);
    }

    public void initLocal() {
        data.add(new Question("Action","Sport","Tout age","Faire 10 Pompes",1,"Boire un verre d'eau"));
        data.add(new Question("Verite","Sport","Tout age","Quel sport te procure le plus de sentations fortes ?",1,"Sors prendre l'air pendant 2 min"));
        data.add(new Question("Action","Amour","+15 ans","Faire un calin pendant 2 min à la persone à ta gauche",2,"La personne à ta gauche te donne un gage"));
        data.add(new Question("Verite","Amour","+20 ans","Combien de partenaires as tu eu dans la vie ?",3,"Faire un cul sec avec un verre de Vodka"));
        data.add(new Question("Action","Vie privée","+10 ans","Montre nous le mouvement de danse que tu maitrises le mieux",1,"Fais 5 pompes"));
        data.add(new Question("Verite","Vie privée","+10 ans","Parmi les personnes présentes , qui est celle qui te plait le plus ?",2,"Manger lentement un glacon"));
        data.add(new Question("Action","Science","+20 ans","Melange un verre de martini , de vodka , de rhum ; bois le et donne nous ton ressenti",3,"La personne à ta droite te mets une petite claque"));
        data.add(new Question("Verite","Science","+15 ans","Quel effet aura l'abondance des ions H3O+ pour une solution aqueuse",1,"Boire un verre de Fanta"));
        data.add(new Question("Action","Libre","Tout age","Fais nous 20 secondes de gainage abdominal",2,"Fais un bie au mec le plus proche de toi dans la salle"));
        data.add(new Question("Verite","Libre","+ 15 ans","Que ressens tu pour la personne assise en face de toi ",3,"Lui donner 2€"));

    }

    public ArrayList<String> getId() {
        ArrayList res = new ArrayList();
        Iterator iterateur = data.iterator();
        while (iterateur.hasNext()) {
            Question q = (Question) (iterateur.next());
            res.add(q.getId());
        }
        return res;
    }


    public ArrayList<Question> getLocalQuestion() {
        this.initLocal();
        return data;
    }

    public ArrayList<Question> getBDQuestion() {
        this.initBD();
        return data;
    }
    // Aller chercher les actions/verites ; eventuellement par genre ou categorie d'âge
    public ArrayList<Question> getActions() {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        data=bdOH.getAllActions();
        if (data.size()==0){
            bdOH.initQuestions();
            data=bdOH.getAllActions();
        }
        bdOH.close();
        return data ;
    }

    public ArrayList<Question> getActionsByGenre(String genre) {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        data=bdOH.getActionsByGenre(genre);
        if (data.size()==0){
            bdOH.initQuestions();
            data=bdOH.getActionsByGenre(genre);
        }
        bdOH.close();
        return data ;
    }

    public ArrayList<Question> getActionsByAge(String catAge){
        BDOpenHelper bdOH = new BDOpenHelper(context);
        data=bdOH.getActionsByAge(catAge);
        if (data.size()==0){
            bdOH.initQuestions();
            data=bdOH.getActionsByAge(catAge);
        }
        bdOH.close();
        return data ;
    }


    public ArrayList<Question> getVerites() {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        data=bdOH.getAllVerite();
        if (data.size()==0){
            bdOH.initQuestions();
            data=bdOH.getAllVerite();
        }
        bdOH.close();
        return data ;
    }
    public ArrayList<Question> getVeritesByGenre(String genre) {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        data=bdOH.getVeritesByGenre(genre);
        if (data.size()==0){
            bdOH.initQuestions();
            data=bdOH.getAllVerite();
        }
        bdOH.close();
        return data ;
    }

    public ArrayList<Question> getVeritesByAge(String catAge){
        BDOpenHelper bdOH = new BDOpenHelper(context);
        data=bdOH.getVeritesByAge(catAge);
        if (data.size()==0){
            bdOH.initQuestions();
            data=bdOH.getVeritesByAge(catAge);
        }
        bdOH.close();
        return data ;
    }

    public Question getRandomAction(){
        data=getActions();
        qActuel=data.get(new Random().nextInt(data.size()));
        return qActuel;
    }

    public Question getRandomVerite(){
        data=getVerites();
        qActuel=data.get(new Random().nextInt(data.size()));
        return qActuel;
    }

    public Question getRandomActionByGenre(String genre){
        data=getActionsByGenre(genre);
        qActuel=data.get(new Random().nextInt(data.size()));
        return qActuel;
    }
    public Question getRandomVeriteByGenre(String genre){
        data=getVeritesByGenre(genre);
        qActuel=data.get(new Random().nextInt(data.size()));
        return qActuel;
    }

    public Question getRandomActionByAge(String age){
        data=getActionsByAge(age);
        qActuel=data.get(new Random().nextInt(data.size()));
        return qActuel;
    }
    public Question getRandomVeriteByAge(String age){
        data=getVeritesByAge(age);
        qActuel=data.get(new Random().nextInt(data.size()));
        return qActuel;
    }

    public void initBD(){
        BDOpenHelper bdOH = new BDOpenHelper(context);
        if (bdOH.countQuestion()==0){
            bdOH.initQuestions();
        }
        data=bdOH.getAllQuestions();
        bdOH.close();
    }

    public void addQuestion(Question q) {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        bdOH.createQuestion(q);
        bdOH.close();
    }

    public void delQuestion(Question q) {
        BDOpenHelper bdOH = new BDOpenHelper(context);
        bdOH.deleteQuestion(q);
        bdOH.close();
    }
}
