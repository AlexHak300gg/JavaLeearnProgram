package com.example.javalearnprogram.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.R;
import com.example.javalearnprogram.Setting.EditProfileActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Profile extends AppCompatActivity {

    private TextView tvFullName2, tvBirthDate2, tvEmail2;
    private Button btnEditProfile, btnLogout, TheoryB, TestB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvFullName2 = findViewById(R.id.tvFullName2);
        tvBirthDate2 = findViewById(R.id.tvBirthDate2);
        tvEmail2 = findViewById(R.id.tvEmail2);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);
        TheoryB = findViewById(R.id.TheoryB);
        TestB = findViewById(R.id.testB);

        loadUserData();
        setupButtons();
    }

    private void setupButtons() {
        TheoryB.setOnClickListener(v -> openLessonList(false));
        TestB.setOnClickListener(v -> openLessonList(true));

        btnEditProfile.setOnClickListener(v -> startActivity(new Intent(this, EditProfileActivity.class)));

        btnLogout.setOnClickListener(v -> performLogout());
    }

    private void openLessonList(boolean isTest) {
        Intent intent = new Intent(this, LessonListActivity.class);
        if (isTest) {
            LessonListActivity.isTheory = false;
            LessonListActivity.isTest = true;
        } else {
            LessonListActivity.isTheory = true;
            LessonListActivity.isTest = false;
        }
        startActivity(intent);
        finish();
    }

    private void performLogout() {
        // Здесь можно добавить код для выхода из аккаунта, например, очистку данных сессии
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadUserData() {
        try {
            File file = new File(getFilesDir(), "accountData.xml");
            if (!file.exists()) return;

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList userList = doc.getElementsByTagName("user");

            if (userList.getLength() > 0) {
                Element userElement = (Element) userList.item(0);

                String fullName = userElement.getElementsByTagName("fullName").item(0).getTextContent();
                String birthDate = userElement.getElementsByTagName("birthDate").item(0).getTextContent();
                String email = userElement.getElementsByTagName("email").item(0).getTextContent();

                tvFullName2.setText(fullName);
                tvBirthDate2.setText(birthDate);
                tvEmail2.setText(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}