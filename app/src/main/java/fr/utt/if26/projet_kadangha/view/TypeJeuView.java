package fr.utt.if26.projet_kadangha.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Question;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;
import fr.utt.if26.projet_kadangha.presenter.QuestionRecyclerPresenter;

public class TypeJeuView {
    View localView;
    RecyclerView recyclerView;
    QuestionRecyclerPresenter presenterQ;
    Context c;
    MainActivity ma;

    public TypeJeuView(Context context , ViewGroup container){
        c=context;
        ma=(MainActivity)c;
        localView= LayoutInflater.from(context).inflate(R.layout.view_type_jeu,container,false);

    }

    public void display(){
        RadioButton rb_random=(RadioButton) localView.findViewById(R.id.rb_random);
        RadioButton rb_genre=(RadioButton) localView.findViewById(R.id.rb_genre);
        RadioButton rb_cat_age=(RadioButton) localView.findViewById(R.id.rb_cat_age);


        Spinner sp_genre=(Spinner) localView.findViewById(R.id.spinner_genre);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(c, R.array.genre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_genre.setAdapter(adapter);

        Spinner sp_age=(Spinner) localView.findViewById(R.id.spinner_age);
        adapter=ArrayAdapter.createFromResource(c, R.array.categorie_age, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_age.setAdapter(adapter);
 ;

        presenterQ= new QuestionRecyclerPresenter(localView.getContext());


        Button bt_valider=(Button)localView.findViewById(R.id.bt_type_valider);

        rb_random.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Snackbar snackbar = Snackbar.make(localView,"Vous aurez tous les types de questions",Snackbar.LENGTH_LONG);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {snackbar.dismiss();}
                    });
                    snackbar.show();
                }
            }
        });
        rb_genre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sp_genre.setVisibility(View.VISIBLE);
                } else {
                    sp_genre.setVisibility(View.INVISIBLE);
                }
            }
        });

        rb_cat_age.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sp_age.setVisibility(View.VISIBLE);
                } else {
                    sp_age.setVisibility(View.INVISIBLE);
                }
            }
        });

        bt_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rb_random.isChecked()){
                    ma.setParamQ(1);
                }else if(rb_genre.isChecked()){
                    String genre =sp_genre.getSelectedItem().toString();
                    ma.setParamQ(2);
                    ma.setParamQ2(genre);
                }else if(rb_cat_age.isChecked()){
                    String cat_age =sp_age.getSelectedItem().toString();
                    ma.setParamQ(3);
                    ma.setParamQ2(cat_age);
                }else{
                    ma.setParamQ(1);
                    Snackbar snackbar = Snackbar.make(localView,"Par défaut vous aurez le random",Snackbar.LENGTH_LONG);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {snackbar.dismiss();}
                    });
                    snackbar.show();
                }
                Snackbar snackbar = Snackbar.make(localView,"Votre choix a bien été pris en compte",Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {snackbar.dismiss();}
                });
                snackbar.show();
                ma.jeuChoixQ();
            }

        });
    }

    public View getRootView(){
        return localView;
    }

}
