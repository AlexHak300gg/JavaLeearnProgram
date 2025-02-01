package com.example.javalearnprogram.Setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javalearnprogram.Classes.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.javalearnprogram.R;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editLogin, editFIO, editUseFIO, editClass, editSchool, editEmail;
    private Button buttonSave, close;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        editLogin = findViewById(R.id.editPassword); // Я поставил логин как пароль!
        editFIO = findViewById(R.id.editName);
        editUseFIO = findViewById(R.id.editUsername); // На эту кнопку кода нет
        editEmail = findViewById(R.id.editEmail); // На эту кнопку кода нет

        //TODO Зачем нам класс и школа? Её нет в изменении профиля, если это надо - добавь доп поля и раскоментируй это:
//        editClass = findViewById(R.id.edit_class); // На эту кнопку кода где то еще лежит
//        editSchool = findViewById(R.id.edit_school); // На эту кнопку кода где то еще лежит

        buttonSave = findViewById(R.id.saveButton);
        close = findViewById(R.id.back); // закрыающая страницу кнопка

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, Profile.class));
                finish();
            }
        });

        databaseReference.child(getCurrentUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user!= null) {
                        editLogin.setText(user.getLogin());
                        editFIO.setText(user.getFio());
                        editClass.setText(user.getSchoolClass());
                        editSchool.setText(user.getSchool());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfileActivity.this, "Ошибка при загрузке профиля!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

    }

    private void saveProfile() {
        String login = editLogin.getText().toString();
        String fio = editFIO.getText().toString();
        String schoolClass = editClass.getText().toString();
        String school = editSchool.getText().toString();

        if (!login.isEmpty() &&!fio.isEmpty() &&!schoolClass.isEmpty() &&!school.isEmpty()) {
            User user = new User(login, fio, schoolClass, school);
            databaseReference.child(getCurrentUserId()).setValue(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditProfileActivity.this, "Профиль успешно сохранён!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditProfileActivity.this, "Ошибка при сохранении профиля!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentUserId() {
        return FirebaseAuth.getInstance().getCurrentUser()!= null
                ? FirebaseAuth.getInstance().getCurrentUser().getUid()
                : UUID.randomUUID().toString();
    }

    public static class User {
        private String login;
        private String fio;
        private String schoolClass;
        private String school;

        public User() {}

        public User(String login, String fio, String schoolClass, String school) {
            this.login = login;
            this.fio = fio;
            this.schoolClass = schoolClass;
            this.school = school;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
        public String getFio() {
            return fio;
        }
        public void setFio(String fio) {
            this.fio = fio;
        }
        public String getSchoolClass() {
            return schoolClass;
        }
        public void setSchoolClass(String schoolClass) {
            this.schoolClass = schoolClass;
        }
        public String getSchool() {
            return school;
        }
        public void setSchool(String school) {
            this.school = school;
        }
    }
}