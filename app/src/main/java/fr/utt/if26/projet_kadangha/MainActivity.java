package fr.utt.if26.projet_kadangha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import fr.utt.if26.projet_kadangha.presenter.GeneralPresenter;
import fr.utt.if26.projet_kadangha.view.AcceuilView;
import fr.utt.if26.projet_kadangha.view.AuthentificationView;
import fr.utt.if26.projet_kadangha.view.FormulaireRGPDView;
import fr.utt.if26.projet_kadangha.view.JeuActiviteView;
import fr.utt.if26.projet_kadangha.view.JeuChoixQView;
import fr.utt.if26.projet_kadangha.view.ParametreView;
import fr.utt.if26.projet_kadangha.view.TutorielView;
import fr.utt.if26.projet_kadangha.view.TypeJeuView;
import fr.utt.if26.projet_kadangha.view.action.ContentActionView;
import fr.utt.if26.projet_kadangha.view.joueur.ContentJoueurView;
import fr.utt.if26.projet_kadangha.view.verite.ContentVeriteView;

public class MainActivity extends AppCompatActivity {
     public FragmentManager fm = getSupportFragmentManager();
     private int paramQ; //3 valeurs possibles => 1 :question random ; 2 :Question par genre ; 3 : question par categorie d'age
    private String paramQ2;// => la valeur du genre ou de la catgorie d age
    private boolean isMusicPlaying = false; // => savoir si la musique est joué
    private MediaPlayer mp ; // le lecteur de music de toute l'application


    public MediaPlayer getMp() {
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }

    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }

    public void setMusicPlaying(boolean musicPlaying) {
        isMusicPlaying = musicPlaying;
    }

    public String getParamQ2() {
        return paramQ2;
    }
    public void setParamQ2(String paramQ2) {
        this.paramQ2 = paramQ2;
    }
    public int getParamQ() {
        return paramQ;
    }
    public void setParamQ(int paramQ) {
        this.paramQ = paramQ;
    }

    public  FragmentManager getFm() {
        return fm;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AcceuilView acceuilView =new AcceuilView(MainActivity.this , null);

        GeneralPresenter presenter= new GeneralPresenter(this);
        if(presenter.ismFormSubmitted()){
            AuthentificationView view = new AuthentificationView(MainActivity.this,null);
            setContentView(view.getRootView());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                view.display();
            }
        }else{
            FormulaireRGPDView view=new FormulaireRGPDView(MainActivity.this,null);
            setContentView(view.getRootView());
            view.display();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acceuil:
                acceuil();
                return true;
            case R.id.main_menu_gerer_action:
                gererActions();
                return true;
            case R.id.main_menu_gerer_verite:
                gererVerite();
                return true;
            case R.id.main_menu_gerer_joueur:
               gererJoueur();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void acceuil(){
        AcceuilView acceuilView= new AcceuilView(this, null);
        setContentView(acceuilView.getRootView());
        acceuilView.display();
    }


    public void choixTypeJeu(){
        TypeJeuView typeJeuView= new TypeJeuView(this, null);
        setContentView(typeJeuView.getRootView());
        typeJeuView.display();
    }

    public void jeuChoixQ(){
        JeuChoixQView jeuView= new JeuChoixQView(this, null);
        setContentView(jeuView.getRootView());
        jeuView.display();
    }

    public void jeuActivite(int type,String jActuel){
        JeuActiviteView jeuView= new JeuActiviteView(this, null,type,jActuel);
        setContentView(jeuView.getRootView());
        jeuView.display();
    }

    private void gererActions(){
        ContentActionView actionView = new ContentActionView(this, null,fm);
        setContentView(actionView.getRootView());
        actionView.display();
    }

    private void gererVerite(){
                ContentVeriteView veriteView = new ContentVeriteView(this, null,fm);
        setContentView(veriteView.getRootView());
        veriteView.display();
    }

    private void gererJoueur(){
        ContentJoueurView jView = new ContentJoueurView(this, null,fm);
        setContentView(jView.getRootView());
        jView.display();
    }

    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"kadangha@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sujet du mail");
        intent.putExtra(Intent.EXTRA_TEXT, "Corps du mail");
        startActivity(Intent.createChooser(intent, "Envoyer un e-mail"));
    }

    public void ouvrirParam(){
        ParametreView pView = new ParametreView(this, null);
        setContentView(pView.getRootView());
        pView.display();
    }

    public void tuto(){
        TutorielView tView = new TutorielView(this, null);
        setContentView(tView.getRootView());
        tView.display();
    }

    public void goFacebook() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/arthur.kadangha/"));
        startActivity(intent);
    }
    public void goTwitter() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://twitter.com/marckadangha"));
        startActivity(intent);
    }
    public void authentification(){
        AuthentificationView view = new AuthentificationView(this, null);
        setContentView(view.getRootView());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            view.display();
        }

    }

    public  Context getC(){
        return this;
    }

}