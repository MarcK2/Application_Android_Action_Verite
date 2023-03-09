package fr.utt.if26.projet_kadangha.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.projet_kadangha.model.Question;
import fr.utt.if26.projet_kadangha.model.QuestionModel;

public class QuestionRecyclerPresenter {
    private Context context;
    private QuestionModel questionModel;

    public QuestionRecyclerPresenter(Context context){
        this.context=context;
        this.questionModel= new QuestionModel(context);
    }

    //Méthodes métiers


    public void getAllActions(RecyclerView recyclerView){
        ArrayList<Question> list= questionModel.getActions();
        QuestionRecyclerAdapter adapter = new QuestionRecyclerAdapter(context,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    public void getAllVerites(RecyclerView recyclerView){
        ArrayList<Question> list= questionModel.getVerites();
        QuestionRecyclerAdapter adapter = new QuestionRecyclerAdapter(context,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }


    public Question getQuestion(int action_verite,int typeJeu,String genre_age ){
        if(typeJeu==1){
            if(action_verite==1){
                return questionModel.getRandomAction();
            }
            else{
                return questionModel.getRandomVerite();
            }
        }
        else if(typeJeu==2){
            if(action_verite==1){
                return questionModel.getRandomActionByGenre(genre_age);
            }
            else{
                return questionModel.getRandomVeriteByGenre(genre_age);
            }
        }
        else{
            if(action_verite==1){
                return questionModel.getRandomActionByAge(genre_age);
            }
            else{
                return questionModel.getRandomVeriteByAge(genre_age);
            }
        }


    }

    public void addAction(Question a){
        questionModel.addQuestion(a);
    }
    public void addVerite(Question v){
        questionModel.addQuestion(v);
    }

    public void delAction(Question a){
        questionModel.delQuestion(a);
    }
    public void delVerite(Question v){
        questionModel.delQuestion(v);
    }
}
