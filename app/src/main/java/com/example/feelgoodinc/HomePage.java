package com.example.feelgoodinc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent myIntent = new Intent(HomePage.this, TutorialActivity.class);
//            myIntent.putExtra("key", value); //Optional parameters
            HomePage.this.startActivity(myIntent);
        });
    }
}