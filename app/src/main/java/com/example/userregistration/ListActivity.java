package com.example.userregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.userregistration.adapter.ProblemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayList<Model> models;
    private ProblemAdapter problemAdapter;

    private FirebaseDatabase database;
    private DatabaseReference problemRef;
    private FirebaseAuth mAuth;
    private static final String PROBLEMS = "problems";
    private Model model;
    private ArrayList<Model> problemList;
    private  String data = "";

    private Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list_view);

        problemList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        problemRef = database.getReference(PROBLEMS);

        currentTime = Calendar.getInstance().getTime();

        String time = currentTime.toString();

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());


        System.out.println("time: " + date);

        problemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                problemList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
//                    if(ds.child("problemSolved").getValue().equals(false)){
                        String id = ds.getKey();
                        String category = ds.child("category").getValue(String.class);
                        String title = ds.child("title").getValue(String.class);
                        String town = ds.child("town").getValue(String.class);
                        String street = ds.child("street").getValue(String.class);
                        String district = ds.child("district").getValue(String.class);
                        String address = ds.child("address").getValue(String.class);
                        String time = ds.child("time").getValue(String.class);
                        String vote = ds.child("voteNumber").getValue(String.class);

                        String description = ds.child("description").getValue(String.class);

                        System.out.println("problemList category: " + category);

//                        int voteInt = Integer.parseInt(vote);

//                        model = new Model(category, title, city, district, description);
                        model = new Model(id, category, title, town, district, street, address, description, null, time);

                        model.setVoteNumber(vote);

                        problemList.add(model);
//                    }
                }


                for (int i=0; i<problemList.size(); i++){
                    data = data + "title: " + problemList.get(i).getTitle() + "\n";
                }

                System.out.println("data: " + data);


                problemAdapter = new ProblemAdapter(ListActivity.this, problemList);
                listView.setAdapter(problemAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Model model = problemList.get(position);

                        System.out.println("Clicked item: " + model.getTitle());

                        Toast.makeText(ListActivity.this, "Clicked item: " + model.getTitle(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ListActivity.this, ListDetailActivity.class);
                        String title = model.getTitle();
                        String category = model.getCategory();
                        String town = model.getTown();
                        String district = model.getDistrict();
                        String street = model.getStreet();
                        String address = model.getAddress();
                        String description = model.getDescription();
                        String imgURL = model.getImageURL();
                        String time = model.getTime();
                        String voteNumber = ""+ model.getVoteNumber();
                        String keyID = model.getId();

                        intent.putExtra("title", title);
                        intent.putExtra("category", category);
                        intent.putExtra("town", town);
                        intent.putExtra("district", district);
                        intent.putExtra("street", street);
                        intent.putExtra("address", address);
                        intent.putExtra("description", description);
                        intent.putExtra("imgURL", imgURL);
                        intent.putExtra("time", time);
                        intent.putExtra("voteNumber", voteNumber);
                        intent.putExtra("id", keyID);


                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






//        models = ListDetails.getList();
//        listView.setFooterDividersEnabled(false);



//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(ListActivity.this, AddProblemActivity.class);
               startActivity(intent);
            }
        });
    }

    public void SignOut(View view) {
        mAuth.signOut();

        Intent intent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(intent);
    }
}