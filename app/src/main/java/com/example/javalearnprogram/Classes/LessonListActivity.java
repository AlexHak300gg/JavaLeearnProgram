package com.example.javalearnprogram.Classes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javalearnprogram.Adapters.ExesizeAdapter;
import com.example.javalearnprogram.R;

import java.util.ArrayList;

public class LessonListActivity extends AppCompatActivity {

        ArrayList<String> NameExesize = new ArrayList<>();
        TextView title;
        public RecyclerView list;
        ExesizeAdapter EX_Adapter;
        public static boolean isTheory = false, isTest = false, isPractic = false;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.lesson_list);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            // Вывод названия на гл. текст
            title = findViewById(R.id.name);
            title.setText(isTheory ? "Список теории" : "Список практики");

            NameExesize.add("Степень"); // 0
            NameExesize.add("Одночлен"); // 1
            NameExesize.add("Многочлен"); // 2
            EX_Adapter = new ExesizeAdapter(this, NameExesize);

            list = findViewById(R.id.list);
            list.setLayoutManager(new LinearLayoutManager(this));
            list.setAdapter(EX_Adapter);
        }

        public void onClickExesize(View v) { // Срабатывает при нажатии на выбранную задачу (Список задась будет как список ссылок)
            int id = v.getId();
            if (isTheory) {
                Intent intent = new Intent(this, TopicActivity.class); // Предполагаем, что ваше другое Activity называется LessonListActivity
                TopicActivity.NUMteor = id;
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, TestActivity.class); // Предполагаем, что ваше другое Activity называется LessonListActivity
                TestActivity.NUMtest = id;
                startActivity(intent);
                finish();
            }
        }

        // Вызывать когда создаётся Intent сюда! (Вызывать перед Intent)
        public static void TypeList(int code) {
            switch (code) {
                case 1: isTheory = true; break;
                case 2: isPractic = true; break;
                case 3: isTest = true; break;
            }
        }
}