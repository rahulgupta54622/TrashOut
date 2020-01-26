package com.gupta54622.rahul.trashout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ActivityTrashView extends AppCompatActivity {

    private RecyclerView trashRecyclerView;
    private ArrayList<Trash> trashArrayList;

    private DatabaseReference databaseReference;

    private CustomAdapterForTrashView customAdapterForTrashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_view);


        databaseReference = FirebaseDatabase.getInstance().getReference("TRASH");

        trashRecyclerView = findViewById(R.id.trashRecyclerView);

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        trashRecyclerView.setLayoutManager(linearLayoutManager);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                trashArrayList = new ArrayList<>();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Trash e = dataSnapshot1.getValue(Trash.class);
                    trashArrayList.add(e);
                }

                System.out.println("TRASHES " + String.valueOf(trashArrayList.get(0).getImageUrl()));

                customAdapterForTrashView = new CustomAdapterForTrashView(ActivityTrashView.this, trashArrayList);
                trashRecyclerView.setAdapter(customAdapterForTrashView); // set the Adapter to RecyclerView

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
