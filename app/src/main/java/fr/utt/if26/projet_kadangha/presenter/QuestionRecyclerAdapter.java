package fr.utt.if26.projet_kadangha.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Question;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionHolder>{

    List<Question> questions ;
    private Context context;

    QuestionRecyclerAdapter(Context context,List<Question> questions) {
        this.questions = questions;
        this.context=context;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.question_item,parent,false);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        holder.display(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    class QuestionHolder extends RecyclerView.ViewHolder{
        private TextView id ;
        private TextView contenu;
        private TextView gage ;
        private TextView nb_point ;

        QuestionHolder(@NonNull View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id.id);
            this.contenu =(TextView) itemView.findViewById(R.id.q_contenu);
            this.gage = (TextView) itemView.findViewById(R.id.gage);
            this.nb_point = (TextView) itemView.findViewById(R.id.nb_point);
        }

        void display(Question question){
            id.setText(String.valueOf(question.getId()));
            contenu.setText(question.getContenu());
            gage.setText(question.getGage());
            nb_point.setText( String.valueOf(question.getNb_point()));
        }
    }

}

