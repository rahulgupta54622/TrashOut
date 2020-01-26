package com.gupta54622.rahul.trashout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickViewKachra(View view) {

        moveToActivity(MapsActivity.class);
    }

    public void onClickUplaodKachra(View view) {

        moveToActivity(ActivityUploadKachraAndLocation.class);
    }

    public void onClickViewKachraImages(View view) {

        moveToActivity(ActivityTrashView.class);

    }

    private void moveToActivity(Class activity) {

        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }


    public void trackDustbin(View view) {

      moveToActivity(IndividualGarbageLevelTrackerActivity.class);
    }
}
