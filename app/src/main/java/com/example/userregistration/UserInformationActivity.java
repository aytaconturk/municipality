package com.example.userregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInformationActivity extends AppCompatActivity {

    private TextView tvName, tvSurname, tvTC, tvEmail, tvPhone;

    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private static final String USER = "user";
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        tvName = findViewById(R.id.tv_name);
        tvSurname = findViewById(R.id.tv_surname);
        tvTC = findViewById(R.id.tv_tc_number);
        tvEmail = findViewById(R.id.tv_email);
        tvPhone = findViewById(R.id.tv_phone);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USER);

        System.out.println("intent email : " + email);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){
                        tvName.setText(ds.child("name").getValue(String.class));
                        tvSurname.setText(ds.child("surname").getValue(String.class));
                        tvTC.setText(ds.child("tc").getValue(String.class));
                        tvEmail.setText(ds.child("email").getValue(String.class));
                        tvPhone.setText(ds.child("phone").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}