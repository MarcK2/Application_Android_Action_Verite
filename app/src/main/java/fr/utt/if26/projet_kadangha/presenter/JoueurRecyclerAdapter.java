package fr.utt.if26.projet_kadangha.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_kadangha.R;
import fr.utt.if26.projet_kadangha.model.Joueur;
import fr.utt.if26.projet_kadangha.model.Question;

public class JoueurRecyclerAdapter extends RecyclerView.Adapter<JoueurRecyclerAdapter.JoueurHolder>{

    List<Joueur> joueurs ;
    private Context context;
    private int type;

    JoueurRecyclerAdapter(Context context,List<Joueur> joueurs,int type) {
        this.joueurs = joueurs;
        this.context=context;
        this.type=type;
    }



    @NonNull
    @Override
    public JoueurRecyclerAdapter.JoueurHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= null;
        if(type==1){
             view = layoutInflater.inflate(R.layout.joueur_item,parent,false);

        }
        else if(type==2){
             view = layoutInflater.inflate(R.layout.surnom_checkbox_item,parent,false);
        }
        return new JoueurRecyclerAdapter.JoueurHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JoueurRecyclerAdapter.JoueurHolder holder, int position) {
        holder.display(joueurs.get(position));
    }

    @Override
    public int getItemCount() {
        return joueurs.size();
    }


    class JoueurHolder extends RecyclerView.ViewHolder{
        private TextView surnom ;
        private TextView score;
        private TextView nb_partie ;
        private CheckBox sur;

       JoueurHolder(@NonNull View itemView) {
            super(itemView);
            if(type==1){
                this.surnom = (TextView) itemView.findViewById(R.id.surnom);
                this.score =(TextView) itemView.findViewById(R.id.score);
                this.nb_partie = (TextView) itemView.findViewById(R.id.nb_partie);
            }
            else if(type==2){
                this.sur = (CheckBox) itemView.findViewById(R.id.cb_surnom);
            }

        }

        void display(Joueur joueur){
           if(type==1){
               surnom.setText(joueur.getSurnom());
               score.setText(String.valueOf(joueur.getScore()));
               nb_partie.setText( String.valueOf(joueur.getNb_partie_joue()));
           }
           else if(type==2){
               sur.setText(joueur.getSurnom());
           }

        }
    }

}
