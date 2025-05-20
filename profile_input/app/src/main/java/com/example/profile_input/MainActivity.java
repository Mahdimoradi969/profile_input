package com.example.profile_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    Button saveButton;
    TextView helloText;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editTextName);
        saveButton = findViewById(R.id.buttonSave);
        helloText = findViewById(R.id.textViewGreeting);

        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String savedName = prefs.getString("name", "");

        if (!savedName.equals("")) {
            helloText.setText("سلام " + savedName);
            nameInput.setText(savedName);
        }

        saveButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(MainActivity.this, "نام را وارد کنید", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", name);
                editor.apply();

                Toast.makeText(MainActivity.this, "اسم ذخیره شد", Toast.LENGTH_SHORT).show();
                helloText.setText("سلام " + name);
            }
        });
    }
}
