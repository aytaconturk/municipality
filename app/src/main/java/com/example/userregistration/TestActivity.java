package com.example.userregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class TestActivity extends AppCompatActivity {

    private TextInputEditText editText;
    private Button button;

    private String[] categories= {"highway","parks","sewage","garbage","waterline","public transportation","street animals"};
    private String categoryName = "";

    private Spinner categorySpinner;
    private ArrayAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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

//        editText = findViewById(R.id.et_test);
//        button = findViewById(R.id.buttom);
//
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String test = editText.getText().toString();
//
//                Toast.makeText(TestActivity.this, test, Toast.LENGTH_SHORT).show();
//
//                System.out.println("text: " + test);
//            }
//        });
    }
}