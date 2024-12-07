package com.example.javalearnprogram.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javalearnprogram.R;

public class Profile extends AppCompatActivity {
    public Button theoryButton;
    public Button editingProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        theoryButton = findViewById(R.id.theoryButton);

        theoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, LessonListActivity.class); // Предполагаем, что ваше другое Activity называется LessonListActivity
            startActivity(intent);
        });

        editingProfileButton = findViewById(R.id.EditingProfileButton);
        editingProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}