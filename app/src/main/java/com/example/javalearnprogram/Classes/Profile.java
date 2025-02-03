package com.example.javalearnprogram.Classes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.R;
import com.example.javalearnprogram.Setting.EditProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView tvFullName, tvBirthDate, tvEmail;
    private Button btnEditProfile, btnLogout, TheoryB, TestB;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        TheoryB = findViewById(R.id.TheoryB);
        TestB = findViewById(R.id.testB);
        tvFullName = findViewById(R.id.tvFullName2);
        tvBirthDate = findViewById(R.id.tvBirthDate2);
        tvEmail = findViewById(R.id.tvEmail2);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);

        TheoryB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessonListActivity.isTest = false;
                LessonListActivity.isTheory = true;
                startActivity(new Intent(Profile.this, LessonListActivity.class));
                finish();
            }
        });
        TestB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessonListActivity.isTest = true;
                LessonListActivity.isTheory = false;
                startActivity(new Intent(Profile.this, LessonListActivity.class));
                finish();
            }
        });

        loadUserData();

        btnEditProfile.setOnClickListener(v -> {
            startActivity(new Intent(Profile.this, EditProfileActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(Profile.this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Profile.this, LoginActivity.class));
            finish();
        });
    }

    private void loadUserData() {
        String userId = mAuth.getCurrentUser().getUid();
        mDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    tvFullName.setText("ФИО: " + user.fullName);
                    tvBirthDate.setText("Дата рождения: " + user.birthDate);
                    tvEmail.setText("Почта: " + user.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Ошибка загрузки данных: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}