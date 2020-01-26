package com.gupta54622.rahul.trashout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterForTrashView extends RecyclerView.Adapter<CustomAdapterForTrashView.MyViewHolder> {

    private Context context;
    private ArrayList<Trash> trashArrayList;
    private StorageReference storageReference;
    ArrayList<Uri> uris;

    public CustomAdapterForTrashView(Context context, ArrayList<Trash> trashes) {

        this.context = context;
        this.trashArrayList = trashes;
        storageReference = FirebaseStorage.getInstance().getReference();
        uris = new ArrayList<>();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate the item Layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trash_view_element, viewGroup, false);

        // set the view's size, margins, padding and layout parameters
        MyViewHolder vh = new MyViewHolder(v);

        return vh;

    }


    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int i) {

        if(trashArrayList.get(i).getUploadDescription().equals(""))
            viewHolder.textViewDescription.setText("No any description");

        else  viewHolder.textViewDescription.setText(trashArrayList.get(i).getUploadDescription());


        storageReference.child(trashArrayList.get(i).getImageUrl()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                uris.add(uri);
                Picasso.get().load(uri).placeholder(R.drawable.progress_animation)
                        .fit().centerCrop().into(viewHolder.imageViewEvent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //System.out.println("TRASH LOAD ERROR: " + e.getMessage());
                Toast.makeText(context, "Trash: " + i + " failed to load", Toast.LENGTH_SHORT).show();
            }
        });


        // get longitude and latitude
        final double longitude = trashArrayList.get(i).getLongitude();
        final double latitude = trashArrayList.get(i).getLatitude();


        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Do you want to visit trash?");
                // Add the buttons
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // open map
                        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + ","+ longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        context.startActivity(mapIntent);

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });


                builder.create();
                builder.show();
            }
        });


//        // implement setOnClickListener event on item view.
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // Toast.makeText(context, eventsMetaDataArrayList.get(i).getDescription(), Toast.LENGTH_SHORT).show();
//
//                androidx.appcompat.app.AlertDialog.Builder dialogBilder = new AlertDialog.Builder(context);
//
//                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//
//                final View dialogView = layoutInflater.inflate(R.layout.event_in_photo_view, null);
//
//                dialogBilder.setView(dialogView);
//
//                photoView = dialogView.findViewById(R.id.photoView);
//
//
//                storageReference.child(eventsMetaDataArrayList.get(i).getImageUrl()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        Picasso.get().load(uri).placeholder(R.drawable.progress_animation)
//                                .fit().into(photoView);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(context, "Event: " + i + " failed to load", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//                AlertDialog alertDialog = dialogBilder.create();
//
//                alertDialog.show();
//            }
//        });
//
//        if (mAuth.getCurrentUser() != null) {
//            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//                    new AlertDialog.Builder(context)
//                            .setMessage("Are you sure to delete?")
//                            .setCancelable(false)
//                            .setTitle("Alert")
//                            .setIcon(R.drawable.ic_warning_black_24dp)
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int j) {
//                                    firebaseDatabase.getReference("events").child(sport).child(eventsMetaDataArrayList.get(i).getReference()).removeValue();
//                                    storageReference.child(eventsMetaDataArrayList.get(i).getImageUrl()).delete();
//                                    Toast.makeText(context, "Deleted successfully!!", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .setNegativeButton("No", null)
//                            .create()
//                            .show();
//
//
//                    return false;
//                }
//            });
//        }
    }


    @Override
    public int getItemCount() {
        return trashArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDescription;
        ImageView imageViewEvent;


        public MyViewHolder(View itemView) {
            super(itemView);

            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewEvent = itemView.findViewById(R.id.imageViewTrash);

        }
    }
}
