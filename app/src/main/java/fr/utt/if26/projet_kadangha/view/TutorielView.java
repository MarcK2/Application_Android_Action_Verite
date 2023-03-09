package fr.utt.if26.projet_kadangha.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.utt.if26.projet_kadangha.MainActivity;
import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;

public class TutorielView {
    View localView;
    Context c;
    MainActivity ma;
    JoueurRecyclerPresenter presenterJ;


    public TutorielView(Context context , ViewGroup container){
        c=context;
        ma=(MainActivity)c;
        localView= LayoutInflater.from(context).inflate(R.layout.view_tutoriel,container,false);

    }

    public void display(){

    }
    public View getRootView(){
        return localView;
    }
}
