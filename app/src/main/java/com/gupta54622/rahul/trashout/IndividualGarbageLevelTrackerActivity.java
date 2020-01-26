package com.gupta54622.rahul.trashout;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class IndividualGarbageLevelTrackerActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    private double MAX_HEIGHT = 28; // in cm
    private double currentHeight = 0;
    private double percentage = 0;

    private DatabaseReference databaseReference;
    private TextView textViewPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_garbage_level_tracker);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        mProgressBar = findViewById(R.id.progressBarGarbage);
        textViewPercentage = findViewById(R.id.tv_percentage);


        databaseReference = FirebaseDatabase.getInstance().getReference("GarbageCans/can1/distance");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                currentHeight = dataSnapshot.getValue(Double.class);
                System.out.println("CURR_HEIGHT: " + currentHeight);

                percentage = ((MAX_HEIGHT - currentHeight)/MAX_HEIGHT) * 100;
                mProgressBar.setProgress((int) percentage);
                textViewPercentage.setText((int)percentage + " %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}


