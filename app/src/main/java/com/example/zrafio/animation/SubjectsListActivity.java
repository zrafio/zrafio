package com.example.zrafio.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class SubjectsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects_list_view);
        SubjectAdapter adapter = new SubjectAdapter(FindSubjectActivity.subjectsList.subjects, this);
        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.ListView01);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);
    }
}