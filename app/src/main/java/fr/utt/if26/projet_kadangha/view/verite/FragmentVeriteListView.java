package fr.utt.if26.projet_kadangha.view.verite;

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
import fr.utt.if26.projet_kadangha.presenter.QuestionRecyclerPresenter;

public class FragmentVeriteListView extends Fragment {
    View localView;
    RecyclerView recyclerView;
    QuestionRecyclerPresenter presenter;
    Context c;

    public  FragmentVeriteListView(Context context, ViewGroup container) {
        c=context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //localView= LayoutInflater.from(context).inflate(R.layout.activity_main,container,false);
        localView= inflater.inflate(R.layout.fragment_question_list,container,false);
        display();
        return localView;
    }

    public void display(){
        TextView tv_title= (TextView) localView.findViewById(R.id.fql_tv) ;
        tv_title.setText("Liste des Vértiés ");
        recyclerView=localView.findViewById(R.id.recycler_view);
        //setContentView(view.getRootView());
        presenter= new QuestionRecyclerPresenter(localView.getContext());
        presenter.getAllVerites(this.getRecyclerView());
    }

    public View getRootView() { return localView; }
    public RecyclerView getRecyclerView() { return recyclerView; }



}
