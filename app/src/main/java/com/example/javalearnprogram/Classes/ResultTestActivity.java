package com.example.javalearnprogram.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javalearnprogram.R;

public class ResultTestActivity extends AppCompatActivity {

    public Button toMenu;
    public TextView title;
    public static int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toMenu = findViewById(R.id.go);
        title = findViewById(R.id.title);

        title.setText("Баллы: " + point + "/10");
        toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessonListActivity.isTest = true;
                LessonListActivity.isTheory = false;
                startActivity(new Intent(ResultTestActivity.this, LessonListActivity.class));
                finish();
            }
        });
    }
}