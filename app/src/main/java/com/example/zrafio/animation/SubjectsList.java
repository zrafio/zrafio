package com.example.zrafio.animation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by zrafio on 9/14/17.
 */

public class SubjectsList implements ValueEventListener {
    public ArrayList<SubjectBean> subjects = new ArrayList<>();

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        subjects.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
            subjects.add(snapshot.getValue(SubjectBean.class));
            System.err.println(snapshot.getValue(SubjectBean.class).getId());
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
