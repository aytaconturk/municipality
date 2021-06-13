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

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegister;
    private Button btnSingIn;

    private EditText etEmail, etPassword;
    private String txtEmail, txtPassword;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.tv_register);
        btnSingIn = findViewById(R.id.btn_signin);
        etEmail = findViewById(R.id.et_e_mail_signin);
        etPassword = findViewById(R.id.et_password);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();



        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtEmail = etEmail.getText().toString();
                txtPassword = etPassword.getText().toString();

                if (!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtPassword)){

                    mAuth.signInWithEmailAndPassword(txtEmail, txtPassword)
                            .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    mUser = mAuth.getCurrentUser();

                                    System.out.println("success");

                                    System.out.println("Kullanici Adi: " + mUser.getDisplayName());
                                    System.out.println("Kullanici email: " + mUser.getEmail());
                                    System.out.println("Kullanici uid: " + mUser.getUid());


                                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                                    String email = etEmail.getText().toString();
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                }
                            });

                }else {
                    Toast.makeText(LoginActivity.this, "Email and password can not be empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void Register(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}