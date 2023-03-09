package fr.utt.if26.projet_kadangha.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class BDOpenHelper extends SQLiteOpenHelper {
    // Definitions pour la base
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ActionVerite_2022.db";

    // Définition d'un tag pour les Log
    private String TAG = "BDHelper";

    // ===========================================================================
    // Définitions pour la table des joueurs

    private static final String JOUEUR_TABLE_NAME = "Joueurs";
    private static final String JOUEUR_ATTRIBUT_SURNOM = "surnom";
    private static final String JOUEUR_ATTRIBUT_AGE= "age";
    private static final String JOUEUR_ATTRIBUT_SCORE = "score";
    private static final String JOUEUR_ATTRIBUT_NB_PARTIE = "nb_partie";

    private final String JOUEUR_TABLE_CREATE =  "create table " + JOUEUR_TABLE_NAME + "(" +
            JOUEUR_ATTRIBUT_SURNOM + " TEXT primary key," +
            JOUEUR_ATTRIBUT_AGE + " INTEGER, " +
            JOUEUR_ATTRIBUT_SCORE + " INTEGER, " +
            JOUEUR_ATTRIBUT_NB_PARTIE + " INTEGER)";

    private final String JOUEUR_TABLE_DROP = "drop table if exists " + JOUEUR_TABLE_NAME;

    // ===========================================================================
    // Définitions pour la table des questions
    private static final String QUESTION_TABLE_NAME = "Questions";
    private static final String QUESTION_ATTRIBUT_ID = "id";
    private static final String QUESTION_ATTRIBUT_TYPE = "type";
    private static final String QUESTION_ATTRIBUT_GENRE = "genre";
    private static final String QUESTION_ATTRIBUT_CATEGORIE_AGE = "categorie_age";
    private static final String QUESTION_ATTRIBUT_CONTENU = "contenu";
    private static final String QUESTION_ATTRIBUT_NB_POINT = "nb_point";
    private static final String QUESTION_ATTRIBUT_GAGE = "gage";


    // TODO !
    private final String QUESTION_TABLE_CREATE =  "create table " + QUESTION_TABLE_NAME + "(" +
            QUESTION_ATTRIBUT_ID + " INTEGER primary key  AUTOINCREMENT NOT NULL," +
            QUESTION_ATTRIBUT_TYPE + " TEXT ," +
            QUESTION_ATTRIBUT_GENRE + " TEXT ," +
            QUESTION_ATTRIBUT_CATEGORIE_AGE + " TEXT , " +
            QUESTION_ATTRIBUT_CONTENU + " TEXT ," +
            QUESTION_ATTRIBUT_NB_POINT + " INTEGER , " +
            QUESTION_ATTRIBUT_GAGE + " TEXT , " +
            "UNIQUE ("+QUESTION_ATTRIBUT_CONTENU+", "+QUESTION_ATTRIBUT_GAGE+", "+QUESTION_ATTRIBUT_NB_POINT+"))";

    private final String QUESTION_TABLE_DROP = "drop table if exists " + QUESTION_TABLE_NAME;


    // ===========================================================================


    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // Constructeur
    public BDOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // 3eme paramètre est une instance de SQLiteDatabase.CursorFactory (ici = NULL)
    }

    // Destruction de la base de données
    public void destruct(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(JOUEUR_TABLE_DROP);
        db.execSQL(QUESTION_TABLE_DROP);
        onCreate(db);
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Liste des créations de tables
        db.execSQL(JOUEUR_TABLE_CREATE);
        db.execSQL(QUESTION_TABLE_CREATE);

        // Remarque importante :
        // Ne pas faire d'insertion dans le onCreate !
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supression des tables si elles existent
        db.execSQL(JOUEUR_TABLE_DROP);
        db.execSQL(QUESTION_TABLE_DROP);

        // Création des tables
        onCreate(db);
    }

    // ===========================================================================
    // JOUEUR CRUD (create, read, update, delete)
    //  create --> createJoueur
    //  read --> getJoueur et getAllJoueurs
    // ===========================================================================

    public void createJoueur(Joueur joueur) {

        // preparation du ContentValue avec les data d'un tuple
        ContentValues tuple = new ContentValues();
        tuple.put(JOUEUR_ATTRIBUT_SURNOM, joueur.getSurnom());
        tuple.put(JOUEUR_ATTRIBUT_AGE,joueur.getAge());
        tuple.put(JOUEUR_ATTRIBUT_SCORE, joueur.getScore());
        tuple.put(JOUEUR_ATTRIBUT_NB_PARTIE, joueur.getNb_partie_joue());

        Log.i(TAG + ":createJoueur:j = ", tuple.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insertWithOnConflict(JOUEUR_TABLE_NAME,null,tuple,SQLiteDatabase.CONFLICT_IGNORE);
        //db.insert(JOUEUR_TABLE_NAME, null, tuple);
        db.close();
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // recherche d'un tuple dans la base via la clé primaire

    @SuppressLint("Range")
    public Joueur getJoueur(String primarykey) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from " + JOUEUR_TABLE_NAME + " where " + JOUEUR_ATTRIBUT_SURNOM + " = " + primarykey;
        Log.i(TAG + ":getJoueur:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur != null) curseur.moveToFirst();

        Joueur tuple = new Joueur();
        tuple.setSurnom(curseur.getString(curseur.getColumnIndex(JOUEUR_ATTRIBUT_SURNOM)));
        tuple.setAge(curseur.getInt(curseur.getColumnIndex(JOUEUR_ATTRIBUT_AGE)));
        tuple.setScore(curseur.getInt(curseur.getColumnIndex(JOUEUR_ATTRIBUT_SCORE)));
        tuple.setNb_partie_joue(curseur.getInt(curseur.getColumnIndex(JOUEUR_ATTRIBUT_NB_PARTIE)));
        db.close();
        return tuple;
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // retourne l'ensemble des joueurs

    @SuppressLint("LongLogTag")
    public ArrayList<Joueur> getAllJoueurs() {
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + JOUEUR_TABLE_NAME;
        Log.i(TAG + ":getAllJoueurs:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Joueur tuple = new Joueur();
                tuple.setSurnom(curseur.getString(0));
                tuple.setAge(curseur.getInt(1));
                tuple.setScore(curseur.getInt(2));
                tuple.setNb_partie_joue(curseur.getInt(3));


                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getAllJoueurs:t = ", tuple.toString());
                listeJoueurs.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeJoueurs;
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // Update

    @SuppressLint("LongLogTag")
    public int updateJoueur(Joueur joueur) {
        // preparation du ContentValue avec les data
        ContentValues tuple = new ContentValues();
        tuple.put(JOUEUR_ATTRIBUT_SURNOM, joueur.getSurnom());
        tuple.put(JOUEUR_ATTRIBUT_AGE, joueur.getAge());
        tuple.put(JOUEUR_ATTRIBUT_SCORE, joueur.getScore());
        tuple.put(JOUEUR_ATTRIBUT_NB_PARTIE, joueur.getNb_partie_joue());

        Log.i(TAG + ":updateJoueur:t = ", tuple.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.update(JOUEUR_TABLE_NAME, tuple,
                JOUEUR_ATTRIBUT_SURNOM + " = ?",
                new String[] { joueur.getSurnom() });
        db.close();
        return result;
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // supprime un joueur dans la base

    public void deleteJoueur(Joueur joueur) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(JOUEUR_TABLE_NAME, JOUEUR_ATTRIBUT_SURNOM + " = ?",
                new String[] {joueur.getSurnom() });
        db.close();
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // ajoute 3 Joueurs

    public void initJoueurs() {
        JoueurModel lJoueur = new JoueurModel();
        ArrayList<Joueur> data = lJoueur.getLocalJoueur();

        // insertion des tuples dans la table
        for (Joueur joueur : data) {
            createJoueur(joueur);
        }
    }

    public void initJoueursScore(){

        String sql = "UPDATE "+JOUEUR_TABLE_NAME+" SET "+JOUEUR_ATTRIBUT_SCORE+" =0";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);

    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // retourne le nombre de joueur

    public int countJoueur() {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + JOUEUR_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        result = cursor.getCount();
        cursor.close();
        return result;
    }

    // ===========================================================================
    // QUESTION CRUD (create, read, update, delete)
    // ===========================================================================
    public void createQuestion(Question question) {
        // preparation du ContentValue avec les data d'un tuple
        ContentValues tuple = new ContentValues();
        //tuple.put(QUESTION_ATTRIBUT_ID, question.getId());
        tuple.put(QUESTION_ATTRIBUT_TYPE, question.getType());
        tuple.put(QUESTION_ATTRIBUT_GENRE, question.getGenre());
        tuple.put(QUESTION_ATTRIBUT_CATEGORIE_AGE, question.getCategorie_age());
        tuple.put(QUESTION_ATTRIBUT_CONTENU, question.getContenu());
        tuple.put(QUESTION_ATTRIBUT_NB_POINT, question.getNb_point());
        tuple.put(QUESTION_ATTRIBUT_GAGE, question.getGage());

        Log.i(TAG + ":createQuestion:t = ", tuple.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(QUESTION_TABLE_NAME, null, tuple);
        db.close();
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // recherche d'un tuple dans la base via la clé primaire

    @SuppressLint("Range")
    public Question getQuestion(String primarykey) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from " + QUESTION_TABLE_NAME + " where " + QUESTION_ATTRIBUT_ID + " = " + primarykey;
        Log.i(TAG + ":getQuestion:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur != null) curseur.moveToFirst();

        Question tuple = new Question();
        tuple.setId(curseur.getInt(curseur.getColumnIndex(QUESTION_ATTRIBUT_ID)));
        tuple.setType(curseur.getString(curseur.getColumnIndex(QUESTION_ATTRIBUT_TYPE)));
        tuple.setGenre(curseur.getString(curseur.getColumnIndex(QUESTION_ATTRIBUT_GENRE)));
        tuple.setCategorie_age(curseur.getString(curseur.getColumnIndex(QUESTION_ATTRIBUT_CATEGORIE_AGE)));
        tuple.setContenu(curseur.getString(curseur.getColumnIndex(QUESTION_ATTRIBUT_CONTENU)));
        tuple.setNb_point(curseur.getInt(curseur.getColumnIndex(QUESTION_ATTRIBUT_NB_POINT)));
        tuple.setGage(curseur.getString(curseur.getColumnIndex(QUESTION_ATTRIBUT_GAGE)));

        db.close();
        return tuple;
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // retourne l'ensemble des tuples

    @SuppressLint("LongLogTag")
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> listeQuestions = new ArrayList<Question>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME +" order by "+QUESTION_ATTRIBUT_ID;
        Log.i(TAG + ":getAllQuestions:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Question tuple = new Question();
                tuple.setId(curseur.getInt(0));
                tuple.setType(curseur.getString(1));
                tuple.setGenre(curseur.getString(2));
                tuple.setCategorie_age(curseur.getString(3));
                tuple.setContenu(curseur.getString(4));
                tuple.setNb_point(curseur.getInt(5));
                tuple.setGage(curseur.getString(6));


                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getAllestions:t = ", tuple.toString());
                listeQuestions.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeQuestions;
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // Update

    @SuppressLint("LongLogTag")
    public int updateQuestion(Question question) {
        // preparation du ContentValue avec les data
        ContentValues tuple = new ContentValues();
        tuple.put(QUESTION_ATTRIBUT_ID, question.getId());
        tuple.put(QUESTION_ATTRIBUT_TYPE,question.getType());
        tuple.put(QUESTION_ATTRIBUT_TYPE, question.getType());
        tuple.put(QUESTION_ATTRIBUT_CATEGORIE_AGE, question.getCategorie_age());
        tuple.put(QUESTION_ATTRIBUT_CONTENU, question.getContenu());
        tuple.put(QUESTION_ATTRIBUT_NB_POINT,question.getNb_point());
        tuple.put(QUESTION_ATTRIBUT_GAGE, question.getGage());
        Log.i(TAG + ":updateQuestio,:t = ", tuple.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.update(QUESTION_TABLE_NAME, tuple,
                QUESTION_ATTRIBUT_ID + " = ?",
                new String[] { String.valueOf(question.getId()) });
        db.close();
        return result;
    }


    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // supprime une question dans la base

    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(QUESTION_TABLE_NAME, QUESTION_ATTRIBUT_ID + " = ?",
                new String[] { String.valueOf(question.getId()) });
        db.close();
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // ajoute plusieurs Questions

    public void initQuestions() {

        QuestionModel lQuestion = new QuestionModel();
        ArrayList<Question> data = lQuestion.getLocalQuestion();

        // insertion des tuples dans la table
        for (Question question : data) {
            createQuestion(question);
        }
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // retourne le nombre de questions

    public int countQuestion() {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        result = cursor.getCount();
        cursor.close();
        return result;
    }

    // -----o-----o-----o-----o-----o-----o-----o-----o-----o
    // retourne la liste des actions
    public ArrayList<Question> getAllActions() {
        ArrayList<Question> listeQuestions = new ArrayList<Question>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME +" where "+ QUESTION_ATTRIBUT_TYPE +" = 'Action' order by "+QUESTION_ATTRIBUT_ID;
        Log.i(TAG + ":getAllAction:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Question tuple = new Question();
                tuple.setId(curseur.getInt(0));
                tuple.setType(curseur.getString(1));
                tuple.setGenre(curseur.getString(2));
                tuple.setCategorie_age(curseur.getString(3));
                tuple.setContenu(curseur.getString(4));
                tuple.setNb_point(curseur.getInt(5));
                tuple.setGage(curseur.getString(6));
                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getAllActions:t = ", tuple.toString());
                listeQuestions.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeQuestions;
    }

    // Retourne la liste des vérités
    public ArrayList<Question> getAllVerite() {
        ArrayList<Question> listeQuestions = new ArrayList<Question>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME +" where "+QUESTION_ATTRIBUT_TYPE+" ='Verite' order by "+QUESTION_ATTRIBUT_ID;
        Log.i(TAG + ":getAllAction:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Question tuple = new Question();
                tuple.setId(curseur.getInt(0));
                tuple.setType(curseur.getString(1));
                tuple.setGenre(curseur.getString(2));
                tuple.setCategorie_age(curseur.getString(3));
                tuple.setContenu(curseur.getString(4));
                tuple.setNb_point(curseur.getInt(5));
                tuple.setGage(curseur.getString(6));
                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getAllVerites:t = ", tuple.toString());
                listeQuestions.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeQuestions;
    }

    public ArrayList<Question> getActionsByGenre(String genre) {
        ArrayList<Question> listeQuestions = new ArrayList<Question>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME +" where "+ QUESTION_ATTRIBUT_TYPE +" = 'Action' AND "+QUESTION_ATTRIBUT_GENRE+"= '"+genre+"' order by "+QUESTION_ATTRIBUT_ID;
        Log.i(TAG + ":getActionsByGenre:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Question tuple = new Question();
                tuple.setId(curseur.getInt(0));
                tuple.setType(curseur.getString(1));
                tuple.setGenre(curseur.getString(2));
                tuple.setCategorie_age(curseur.getString(3));
                tuple.setContenu(curseur.getString(4));
                tuple.setNb_point(curseur.getInt(5));
                tuple.setGage(curseur.getString(6));
                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getActiosbyGenre:t = ", tuple.toString());
                listeQuestions.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeQuestions;
    }

    public ArrayList<Question> getVeritesByGenre(String genre) {
        ArrayList<Question> listeQuestions = new ArrayList<Question>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME +" where "+ QUESTION_ATTRIBUT_TYPE +" = 'Verite' AND "+QUESTION_ATTRIBUT_GENRE+"= '"+genre+"' order by "+QUESTION_ATTRIBUT_ID;
        Log.i(TAG + ":getVeritesByGenre:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Question tuple = new Question();
                tuple.setId(curseur.getInt(0));
                tuple.setType(curseur.getString(1));
                tuple.setGenre(curseur.getString(2));
                tuple.setCategorie_age(curseur.getString(3));
                tuple.setContenu(curseur.getString(4));
                tuple.setNb_point(curseur.getInt(5));
                tuple.setGage(curseur.getString(6));
                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getVeriteByGenre:t = ", tuple.toString());
                listeQuestions.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeQuestions;
    }

    public ArrayList<Question> getActionsByAge(String cat_age) {
        ArrayList<Question> listeQuestions = new ArrayList<Question>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME +" where "+ QUESTION_ATTRIBUT_TYPE +" = 'Action'AND "+QUESTION_ATTRIBUT_CATEGORIE_AGE+"= '"+cat_age+"' order by "+QUESTION_ATTRIBUT_ID;
        Log.i(TAG + ":getActionsByAge:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Question tuple = new Question();
                tuple.setId(curseur.getInt(0));
                tuple.setType(curseur.getString(1));
                tuple.setGenre(curseur.getString(2));
                tuple.setCategorie_age(curseur.getString(3));
                tuple.setContenu(curseur.getString(4));
                tuple.setNb_point(curseur.getInt(5));
                tuple.setGage(curseur.getString(6));
                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getActionsByAge:t = ", tuple.toString());
                listeQuestions.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeQuestions;
    }

    public ArrayList<Question> getVeritesByAge(String cat_age) {
        ArrayList<Question> listeQuestions = new ArrayList<Question>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + QUESTION_TABLE_NAME +" where "+ QUESTION_ATTRIBUT_TYPE +" = 'Verite' AND "+QUESTION_ATTRIBUT_CATEGORIE_AGE+"= '"+cat_age+"' order by "+QUESTION_ATTRIBUT_ID;
        Log.i(TAG + ":getVeritesByAge:q = ", query);

        Cursor curseur = db.rawQuery(query, null);
        if (curseur.moveToFirst()) {
            do {
                Question tuple = new Question();
                tuple.setId(curseur.getInt(0));
                tuple.setType(curseur.getString(1));
                tuple.setGenre(curseur.getString(2));
                tuple.setCategorie_age(curseur.getString(3));
                tuple.setContenu(curseur.getString(4));
                tuple.setNb_point(curseur.getInt(5));
                tuple.setGage(curseur.getString(6));
                // Ajout du tuple de la base dans la liste résultat
                Log.i(TAG + ":getVeritesByAge:t = ", tuple.toString());
                listeQuestions.add(tuple);
            } while (curseur.moveToNext());
        }
        db.close();
        return listeQuestions;
    }


    // TODO !

}
