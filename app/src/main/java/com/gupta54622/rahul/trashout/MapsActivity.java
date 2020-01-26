package com.gupta54622.rahul.trashout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private Trash trash;


    private DatabaseReference databaseReference;
    private ArrayList<Trash> trashArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        databaseReference = FirebaseDatabase.getInstance().getReference("TRASH");
        trashArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    trash = dataSnapshot1.getValue(Trash.class);
                    trashArrayList.add(trash);
                }

                // mark the map with trash
                for(Trash trash: trashArrayList){

                    latitude = trash.getLatitude();
                    longitude = trash.getLongitude();
                    String description = trash.getUploadDescription();

                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(description)).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.paper_waste));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        // get the passed location
//        Bundle bundle = getIntent().getExtras();
//        latitude = bundle.getDouble("LONGITUDE");
//        longitude = bundle.getDouble("LATITUDE");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        LatLng nitcal = new LatLng(11.3214581,75.9321293);
        mMap.addMarker(new MarkerOptions().position(new LatLng(11.3214581,75.9321293)).title("NIT CALICUT")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nitcal));
        //Move the camera to the user's location and zoom in!
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(nitcal.latitude, nitcal.longitude), 15.0f));
    }
}
