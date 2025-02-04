package com.example.javalearnprogram.Classes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.R;

public class TopicActivity extends AppCompatActivity {

    public static int NUMteor = -1;
    public ImageButton undo;
    public TextView title;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_menu);

        TextView lessonTopic = findViewById(R.id.LessonTopic);
        title = findViewById(R.id.title);

        undo = findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessonListActivity.isTheory = true;
                LessonListActivity.isTest = false;
                startActivity(new Intent(TopicActivity.this, LessonListActivity.class));
                finish();
            }
        });

        if (NUMteor == 0) { // Теория со степенью
            title.setText("О степенях");
            lessonTopic.setText(getString(R.string.topic_degree));
        } else if (NUMteor == 1) { // Теория с одночленом
            title.setText("О одночлене");
            lessonTopic.setText(getString(R.string.topic_monomial));
        } else { // Теория с многочленом
            title.setText("О многочлене");
            lessonTopic.setText(getString(R.string.topic_polynomial));
        }
    }
}