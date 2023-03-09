package fr.utt.if26.projet_kadangha.view.joueur;

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

public class ContentJoueurView {
    View localView;
    FragmentManager fm;

    public ContentJoueurView(Context context , ViewGroup container, FragmentManager fm){
        localView= LayoutInflater.from(context).inflate(R.layout.content_joueur,container,false);
        this.fm=fm;
    }

    public void display(){
        BottomNavigationView bottomNav= localView.findViewById(R.id.bottom_navigation_joueur);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        fm.beginTransaction().replace(R.id.fragment_container_joueur, new FragmentJoueurListView(localView.getContext(),null)).commit();
    }
    private  BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragmentSelected= null ;
                    switch(item.getItemId()){
                        case R.id.nav_joueur_list:
                            fragmentSelected=  new FragmentJoueurListView(localView.getContext(),null);
                            break;
                        case R.id.nav_joueur_add:
                            fragmentSelected= new FragmentJoueurAddView(localView.getContext(),null);
                            break;
                        case R.id.nav_joueur_del:
                            fragmentSelected= new FragmentJoueurDelView(localView.getContext(),null);
                            break;
                    }
                    fm.beginTransaction().replace(R.id.fragment_container_joueur,
                            fragmentSelected).commit();
                    return true;
                }
            };

    public View getRootView(){
        return localView;
    }
}

