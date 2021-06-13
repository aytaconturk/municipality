package com.example.userregistration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class AddProblemActivity extends AppCompatActivity {

    private EditText etCategory, etTitle, etTown, etStreet, etAddress, etDistrict, etDescription;
    private Button btnSend;
    private ImageView imgPreview;
    private ProgressBar uploadProgress;
    private Uri imgUrl;

    private Date currentTime;

    private static final int CHOOSE_IMAGE = 1;

    private FirebaseDatabase database;
    private DatabaseReference problemRef;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    private FirebaseAuth mAuth;
    private static final String PROBLEMS = "problems";
    private Model model;

    private String[] categories= {"highway","parks","sewage","garbage","waterline","public transportation","street animals"};
    private String categoryName = "";

    private Spinner categorySpinner;
    private ArrayAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);

//        etCategory = findViewById(R.id.et_category);
        etTitle = findViewById(R.id.et_problem_title);
        etTown = findViewById(R.id.et_Town);
        etStreet = findViewById(R.id.et_street);
        etAddress = findViewById(R.id.et_address);
        etDistrict = findViewById(R.id.et_district);
        etDescription = findViewById(R.id.et_description);
        btnSend = findViewById(R.id.btn_send);
        imgPreview = findViewById(R.id.imageView);
        uploadProgress = findViewById(R.id.progressBar);

        categorySpinner = findViewById(R.id.category_select);

        categoryAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, categories);
        categoryAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        categorySpinner.setAdapter(categoryAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String category = categories[position];

                switch (category){
                    case "highway":
                        categoryName = "highway";
                        break;

                    case "parks":
                        categoryName = "parks";
                        break;

                    case "sewage":
                        categoryName = "sewage";
                        break;

                    case "waterline":
                        categoryName = "waterline";
                        break;

                    case "garbage":
                        categoryName = "garbage";
                        break;

                    case "public transportation":
                        categoryName = "public transportation";
                        break;

                    case "street animals":
                        categoryName = "street animals";
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        database = FirebaseDatabase.getInstance();
        problemRef = database.getReference(PROBLEMS);
        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        imgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChoose();
            }
        });



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String category = etCategory.getText().toString();
                String title = etTitle.getText().toString();
                String town = etTown.getText().toString();
                String district = etDistrict.getText().toString();
                String street = etStreet.getText().toString();
                String address = etAddress.getText().toString();
                String description = etDescription.getText().toString();

                if (mUploadTask != null && mUploadTask.isInProgress())
                    Toast.makeText(AddProblemActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                else {
                    if (imgUrl!=null){

                        System.out.println("img url not empty");
                        System.out.println("imgURL: " + imgUrl);

                        StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUrl));

                        System.out.println("after storage upload");

                        mUploadTask = fileReference.putFile(imgUrl)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                uploadProgress.setProgress(0);
                                            }
                                        }, 500);
                                        Toast.makeText(AddProblemActivity.this, "Upload Successfully", Toast.LENGTH_SHORT).show();

                                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                        String time = df.format(Calendar.getInstance().getTime());

                                        String keyID = problemRef.push().getKey();

                                        model = new Model(keyID, categoryName, title, town, district, street, address, description, taskSnapshot.getUploadSessionUri().toString(), time);

                                        System.out.println(model.toString());

                                        problemRef.child(keyID).setValue(model);

                                        System.out.println("keyID: " + keyID);

                                        System.out.println("success with image");

                                        Intent intent = new Intent(AddProblemActivity.this, ListActivity.class);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AddProblemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                        double progress = (100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                                        uploadProgress.setProgress((int) progress);
                                    }
                                });
                    }
                    else {
                        Toast.makeText(AddProblemActivity.this, "No files selected", Toast.LENGTH_SHORT).show();

                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String time = df.format(Calendar.getInstance().getTime());

                        String keyID = problemRef.push().getKey();

                        model = new Model(keyID, categoryName, title, town, district, street, address, description, null, time);

                        FirebaseUser user = mAuth.getCurrentUser();

                        Toast.makeText(AddProblemActivity.this, "name: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        System.out.println("name: " + user.getEmail());
                        System.out.println(model.toString());


                        problemRef.child(keyID).setValue(model);

                        System.out.println("success without image");

                        Intent intent = new Intent(AddProblemActivity.this, ListActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
    }

    private void showFileChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode==RESULT_OK && data != null && data.getData()!=null){
            imgUrl = data.getData();

            Picasso.get().load(imgUrl).into(imgPreview);
        }
    }
}