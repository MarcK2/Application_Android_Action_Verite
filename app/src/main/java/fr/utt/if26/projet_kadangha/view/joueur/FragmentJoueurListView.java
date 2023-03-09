package fr.utt.if26.projet_kadangha.view.joueur;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.presenter.JoueurRecyclerPresenter;
import fr.utt.if26.projet_kadangha.presenter.QuestionRecyclerPresenter;

public class FragmentJoueurListView extends Fragment {
    View localView;
    RecyclerView recyclerView;
    JoueurRecyclerPresenter presenter;
    Context c;

    public  FragmentJoueurListView(Context context, ViewGroup container) {
        c=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        localView= inflater.inflate(R.layout.fragment_joueur_list,container,false);
        display();
        return localView;
    }

    public void display(){
        recyclerView=localView.findViewById(R.id.recycler_view_j);
        presenter= new JoueurRecyclerPresenter(localView.getContext());
        presenter.getAllJoueur(this.getRecyclerView());
    }

    public View getRootView() { return localView; }
    public RecyclerView getRecyclerView() { return recyclerView; }



}
