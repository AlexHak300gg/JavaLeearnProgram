package com.example.javalearnprogram.Classes;

import android.annotation.SuppressLint;
import android.os.Bundle;

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
    public RecyclerView list;
    ExesizeAdapter EX_Adapter;

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

        NameExesize.add("Дроби");
        NameExesize.add("Степень");
        NameExesize.add("Двухчлен");
        EX_Adapter = new ExesizeAdapter(this, NameExesize);

        list = findViewById(R.id.Rlist);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(EX_Adapter);

    }

    public void onClickExesize() { // Срабатывает при нажатии на выбранную задачу (Список задась будет как список ссылок)
        // Переход на Activity с теорией/заданиями/тестом
    }
}