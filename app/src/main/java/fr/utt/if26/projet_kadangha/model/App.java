package fr.utt.if26.projet_kadangha.model;

import android.content.Context;

import java.util.ArrayList;

public class App {
    private Context context;

    public App(Context context) {
                this.context=context;
    }
    public void delAll(){
        BDOpenHelper bdOH = new BDOpenHelper(context);
        bdOH.destruct();
        bdOH.close();
    }
}
