package com.example.javalearnprogram;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_menu);

        TextView lessonTopic = findViewById(R.id.LessonTopic);

        String topicString = getResources().getString(R.string.topic_1);

        lessonTopic.setText(topicString);
    }
}