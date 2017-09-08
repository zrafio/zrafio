package com.example.zrafio.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {


    private Button createSub;
    private Button findSub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createSub = (Button) findViewById(R.id.create_subject);
        findSub = (Button) findViewById(R.id.find_subject);

        createSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSubject();
            }
        });

        findSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findSubject();
            }
        });
    }

    public void createSubject(){
        Intent create =new Intent(SettingsActivity.this,CreateSubjectActivity.class);
        startActivity(create);
    }

    public void findSubject(){
        Intent find =new Intent(SettingsActivity.this,FindSubjectActivity.class);
        startActivity(find);
    }

}
