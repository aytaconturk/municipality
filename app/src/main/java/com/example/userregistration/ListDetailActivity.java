package com.example.userregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

public class ListDetailActivity extends AppCompatActivity {

    private TextView titleTV, voteTV, locationTV, categoryTV, timeTV, descriptionTV, increaseVoteTV;

    private Button markAsSolved;
    private ImageView imgPreview;
    private int vote;


    private FirebaseDatabase database;
    private DatabaseReference problemRef;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    private FirebaseAuth mAuth;

    private static final String PROBLEMS = "problems";
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        titleTV = findViewById(R.id.list_item_tv_title);
        voteTV = findViewById(R.id.tv_vote_number);
        locationTV = findViewById(R.id.list_item_location);
        categoryTV = findViewById(R.id.list_item_category);
        timeTV = findViewById(R.id.list_item_time);
        descriptionTV = findViewById(R.id.tv_description);
        increaseVoteTV = findViewById(R.id.increasing_vote);
        markAsSolved = findViewById(R.id.btn_mark_as_solved);

        imgPreview = findViewById(R.id.imgPreview);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String category = intent.getStringExtra("category");
        String town = intent.getStringExtra("town");
        String district = intent.getStringExtra("district");
        String street = intent.getStringExtra("street");
        String address = intent.getStringExtra("address");
        String description = intent.getStringExtra("description");
        String imgURL = intent.getStringExtra("imgURL");
        String time = intent.getStringExtra("time");
        String voteNumber = intent.getStringExtra("voteNumber");
        String id = intent.getStringExtra("id");



        titleTV.setText(title);
        voteTV.setText(voteNumber);
        locationTV.setText(town + " - " + district);
        categoryTV.setText(category);
        timeTV.setText(time);
        descriptionTV.setText(description);



        database = FirebaseDatabase.getInstance();
        problemRef = database.getReference(PROBLEMS);
        mAuth = FirebaseAuth.getInstance();

        Picasso.get().load(imgURL)
                .placeholder(R.drawable.selectimage)
                .fit()
                .centerCrop()
                .into(imgPreview);
//        titleTV.setText(title);

        vote = Integer.parseInt(voteNumber);
        System.out.println("vote number " + vote);

        increaseVoteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote = vote + 1;
                voteTV.setText("" + vote);
                problemRef.child(id).child("voteNumber").setValue("" + vote);
            }
        });

        if(!mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
            markAsSolved.setVisibility(View.GONE);
        }
        else {
            markAsSolved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    problemRef.child(id).child("problemSolved").setValue(true);
                }
            });
        }
    }
}