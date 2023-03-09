package fr.utt.if26.projet_kadangha.view.verite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.utt.if26.projet_kadangha.R;

public class ContentVeriteView {
    View localView;
    FragmentManager fm;

    public ContentVeriteView(Context context , ViewGroup container, FragmentManager fm){
        localView= LayoutInflater.from(context).inflate(R.layout.content_question,container,false);
        this.fm=fm;
    }

    public void display(){
        BottomNavigationView bottomNav= localView.findViewById(R.id.bottom_navigation_question);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        fm.beginTransaction().replace(R.id.fragment_container_question, new FragmentVeriteListView(localView.getContext(),null)).commit();
    }
    private  BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragmentSelected= null ;
                    switch(item.getItemId()){
                        case R.id.nav_question_list:
                            fragmentSelected=  new FragmentVeriteListView(localView.getContext(),null);
                            break;
                        case R.id.nav_question_add:
                            fragmentSelected= new FragmentVeriteAddView(localView.getContext(),null);
                            break;
                        case R.id.nav_question_del:
                            fragmentSelected= new FragmentVeriteDelView(localView.getContext(),null);
                            break;
                    }
                    fm.beginTransaction().replace(R.id.fragment_container_question,
                           fragmentSelected).commit();

                    return true;
                }
            };

    public View getRootView(){
        return localView;
    }
}
