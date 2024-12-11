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
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    public Button theoryButton;
    public Button testButton;
    public Button practiceButton;
    public Button editingProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(Profile.this, LoginActivity.class));
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        theoryButton = findViewById(R.id.theoryButton);
        testButton = findViewById(R.id.TestsProfile);
        practiceButton = findViewById(R.id.PracticeProfile);

        theoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, LessonListActivity.class); // Предполагаем, что ваше другое Activity называется LessonListActivity
            startActivity(intent);
            LessonListActivity.TypeList(1);
        });
        testButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, LessonListActivity.class); // Предполагаем, что ваше другое Activity называется LessonListActivity
            startActivity(intent);
            LessonListActivity.TypeList(3);
        });
        practiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, LessonListActivity.class); // Предполагаем, что ваше другое Activity называется LessonListActivity
            startActivity(intent);
            LessonListActivity.TypeList(2);
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
