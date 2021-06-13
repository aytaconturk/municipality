package com.example.userregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView tvRegister;
    private Button btnSingIn;

    private EditText etEmail, etPassword;
    private String txtEmail, txtPassword;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvRegister = findViewById(R.id.tv_register);
//        btnSingIn = findViewById(R.id.btn_signin);
//        etEmail = findViewById(R.id.et_email);
//        etPassword = findViewById(R.id.et_password);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        // giris yapan kullanici yoksa
//        if (mUser == null){
//            // giris yap aktivitesine gonder
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }
//        else {
//            //list activiti sayfasina gonder
//            Intent intent = new Intent(MainActivity.this, ListActivity.class);
////            String email = etEmail.getText().toString();
////            intent.putExtra("email", email);
//            startActivity(intent);
//        }

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);



    }

}