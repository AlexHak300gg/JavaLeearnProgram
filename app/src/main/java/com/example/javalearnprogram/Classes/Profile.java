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
import com.google.firebase.auth.FirebaseUser;
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
    private ValueEventListener userListener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        checkUserAuthorization();
        setupButtons();
    }

    private void initializeViews() {
        TheoryB = findViewById(R.id.TheoryB);
        TestB = findViewById(R.id.testB);
        tvFullName = findViewById(R.id.tvFullName2);
        tvBirthDate = findViewById(R.id.tvBirthDate2);
        tvEmail = findViewById(R.id.tvEmail2);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
    }

    private void checkUserAuthorization() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            redirectToLogin();
        } else {
            loadUserData(currentUser.getUid());
        }
    }

    private void setupButtons() {
        TheoryB.setOnClickListener(v -> openLessonList(false));
        TestB.setOnClickListener(v -> openLessonList(true));

        btnEditProfile.setOnClickListener(v ->
                startActivity(new Intent(Profile.this, EditProfileActivity.class)));

        btnLogout.setOnClickListener(v -> performLogout());
    }

    private void openLessonList(boolean isTest) {
        Intent intent = new Intent(Profile.this, LessonListActivity.class);
        intent.putExtra("isTest", isTest);
        intent.putExtra("isTheory", !isTest);
        startActivity(intent);
        finish();
    }

    private void loadUserData(String userId) {
        userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    updateUI(user);
                } else {
                    showDataError();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showDatabaseError(error);
            }
        };
        mDatabase.child(userId).addValueEventListener(userListener);
    }

    private void updateUI(User user) {
        tvFullName.setText(getString(R.string.full_name_format, user.fullName));
        tvBirthDate.setText(getString(R.string.birth_date_format, user.birthDate));
        tvEmail.setText(getString(R.string.email_format, user.email));
    }

    private void showDataError() {
        Toast.makeText(this, "Данные пользователя не найдены", Toast.LENGTH_SHORT).show();
    }

    private void showDatabaseError(DatabaseError error) {
        Toast.makeText(this, "Ошибка базы данных: " + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void performLogout() {
        mAuth.signOut();
        Toast.makeText(this, "Вы успешно вышли", Toast.LENGTH_SHORT).show();
        redirectToLogin();
    }

    private void redirectToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (userListener != null) {
            mDatabase.child(mAuth.getUid()).removeEventListener(userListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Обновляем данные при возвращении на экран
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            loadUserData(user.getUid());
        }
    }
}