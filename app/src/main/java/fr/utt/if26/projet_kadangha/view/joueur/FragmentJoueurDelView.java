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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Joueur;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;

public class FragmentJoueurDelView extends Fragment {
    View localView;
    JoueurRecyclerPresenter presenter;
    Context c;

    public  FragmentJoueurDelView(Context context, ViewGroup container) {
        c=context;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        localView= inflater.inflate(R.layout.fragment_joueur_del,container,false);
        display();
        return localView;
    }

    public void display(){
        presenter= new JoueurRecyclerPresenter(localView.getContext());

        Spinner sp_del_j=(Spinner) localView.findViewById(R.id.sp_del_joueur);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_spinner_item,presenter.getSurnom());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_del_j.setAdapter(adapter);

        Button bt_del_joueur=(Button)localView.findViewById(R.id.bt_del_joueur);
        bt_del_joueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String surnom =sp_del_j.getSelectedItem().toString();

                Joueur j= new Joueur(surnom,0);
                presenter.delJoueur(j);
                Snackbar snackbar = Snackbar.make(localView,"Le joueur a bien été supprimé",Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {snackbar.dismiss();}
                });
                snackbar.show();
            }
        });
    }


}
