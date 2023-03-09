package fr.utt.if26.projet_kadangha.view.action;

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

import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Question;
import fr.utt.if26.projet_kadangha.presenter.QuestionRecyclerPresenter;

public class FragmentActionAddView extends Fragment {

    View localView;
    QuestionRecyclerPresenter presenter;
    Context c;


    public  FragmentActionAddView(Context context, ViewGroup container) {
        c=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        localView= inflater.inflate(R.layout.fragment_question_add,container,false);
        display();
        return localView;
    }

    public void display(){
        TextView tv_title= (TextView) localView.findViewById(R.id.tv_add_question) ;
        tv_title.setText("Ajouter des Actions ");

        Spinner sp_genre=(Spinner) localView.findViewById(R.id.sp_genre);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(c, R.array.genre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_genre.setAdapter(adapter);

        Spinner sp_age=(Spinner) localView.findViewById(R.id.sp_cat_age);
        adapter=ArrayAdapter.createFromResource(c, R.array.categorie_age, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_age.setAdapter(adapter);

        EditText et_contenu= (EditText) localView.findViewById(R.id.et_contenu) ;
        EditText et_nb_point= (EditText) localView.findViewById(R.id.et_nb_pt) ;
        EditText et_gage= (EditText) localView.findViewById(R.id.et_gage) ;

        presenter= new QuestionRecyclerPresenter(localView.getContext());


        Button bt_add_action=(Button)localView.findViewById(R.id.bt_add_qst);

        bt_add_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "Action";
                String genre =sp_genre.getSelectedItem().toString();
                String cat_age =sp_age.getSelectedItem().toString();
                String contenu= et_contenu.getText().toString();
                int nb_point=  Integer.valueOf((et_nb_point.getText().toString()));
                String gage= et_gage.getText().toString();

                Question action= new Question(type,genre,cat_age,contenu,nb_point,gage);

                presenter.addAction(action);
                Snackbar snackbar = Snackbar.make(localView,"Votre action a été ajoutée",Snackbar.LENGTH_LONG);
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
