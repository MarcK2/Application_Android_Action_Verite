package fr.utt.if26.projet_kadangha.view.joueur;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Joueur;
import fr.utt.if26.projet_kadangha.model.Question;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;
import fr.utt.if26.projet_kadangha.presenter.QuestionRecyclerPresenter;

public class FragmentJoueurAddView extends Fragment {

    View localView;
    JoueurRecyclerPresenter presenter;
    Context c;

    public  FragmentJoueurAddView(Context context, ViewGroup container) {
        c=context;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        localView= inflater.inflate(R.layout.fragment_joueur_add,container,false);
        display();
        return localView;
    }

    public void display(){
        EditText et_surnom= (EditText) localView.findViewById(R.id.et_surnom) ;
        EditText et_age= (EditText) localView.findViewById(R.id.et_age) ;

        presenter= new JoueurRecyclerPresenter(localView.getContext());

        Button bt_add_joueur=(Button)localView.findViewById(R.id.bt_add_joueur);
        bt_add_joueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String surnom= et_surnom.getText().toString();
                int age=  Integer.valueOf((et_age.getText().toString()));

                Joueur j= new Joueur(surnom,age);
                presenter.addJoueur(j);
                Snackbar snackbar = Snackbar.make(localView,"Votre joueur a bien été ajouté",Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {snackbar.dismiss();}
                });
                snackbar.show();
            }
        });
    }

    public View getRootView() { return localView; }

}
