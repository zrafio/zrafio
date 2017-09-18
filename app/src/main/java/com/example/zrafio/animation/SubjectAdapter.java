package com.example.zrafio.animation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zrafio on 9/15/17.
 */

public class SubjectAdapter extends BaseAdapter {

    private ArrayList<SubjectBean> subjects;
    private Context mContext;
    private LayoutInflater mInflater;

    public SubjectAdapter(ArrayList<SubjectBean> subjects, Context mContext) {
        this.subjects = subjects;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int i) {
        return subjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.subject_view, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout      
        TextView tv_Nom = (TextView)layoutItem.findViewById(R.id.TV_Nom);
        TextView tv_Prenom = (TextView)layoutItem.findViewById(R.id.TV_Prenom);

        //(3) : Renseignement des valeurs       
        tv_Nom.setText(subjects.get(position).getLastName());
        tv_Prenom.setText(subjects.get(position).getFirstName());

        //(4) Changement de la couleur du fond de notre item
        if (subjects.get(position).getGender() == "Male") {
            layoutItem.setBackgroundColor(Color.BLUE);
        } else {
            layoutItem.setBackgroundColor(Color.MAGENTA);
        }

        //On retourne l'item créé.
        return layoutItem;
    }
}
