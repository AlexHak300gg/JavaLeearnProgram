package com.example.javalearnprogram.Classes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.R;

public class TopicActivity extends AppCompatActivity {

    public static int NUMteor = -1;
    public Button undo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_menu);

        TextView lessonTopic = findViewById(R.id.LessonTopic);
        undo = findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (NUMteor == 0) { // Теория со степенью
            lessonTopic.setText("ВСТАВЬ");
        } else if (NUMteor == 1) { // Теория с одночленом
            lessonTopic.setText("ВСТАВЬ");
        } else { // Теория с многочленом
            lessonTopic.setText("ВСТАВЬ");
        }
    }
}