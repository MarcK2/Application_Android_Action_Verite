package fr.utt.if26.projet_kadangha.view.action;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Question;
import fr.utt.if26.projet_kadangha.presenter.QuestionRecyclerPresenter;

public class FragmentActionDelView extends Fragment {
    View localView;
    QuestionRecyclerPresenter presenter;
    Context c;

    public  FragmentActionDelView(Context context, ViewGroup container) {
        c=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        localView =inflater.inflate(R.layout.fragment_question_del,container,false);
        Display();
        return localView;
    }

    public void Display(){
        TextView tv_title= (TextView) localView.findViewById(R.id.tv_del_qst) ;
        tv_title.setText("Supprimer des Actions ");

        EditText et_id= (EditText) localView.findViewById(R.id.et_id_del_qst) ;
        Button bt_del_action=(Button)localView.findViewById(R.id.bt_del_qst);
        presenter= new QuestionRecyclerPresenter(localView.getContext());

        bt_del_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.valueOf((et_id.getText().toString()));
                Question action= new Question(id);

                presenter.delAction(action);
                Snackbar snackbar = Snackbar.make(localView,"L'action a bien été supprimé",Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {snackbar.dismiss();}
                });
                snackbar.show();
            }
        });
    }


}
