package com.example.javalearnprogram.Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javalearnprogram.R;
import com.example.javalearnprogram.Setting.AccountData;

public class MainActivity extends AppCompatActivity {

    public Button BTN_regist;
    public EditText login, password;
    public TextView error_log;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        BTN_regist = findViewById(R.id.log);
        login = findViewById(R.id.Module_login);
        password = findViewById(R.id.Module_password);
        error_log = findViewById(R.id.Module_error);

        error_log.setAlpha(0f); // Делаем текст с ошибкой невидемым

        BTN_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AccountData.LoginAccount(String.valueOf(login.getText()), String.valueOf(password.getText()))) {
                    error_log.setAlpha(0f);
                    // Реализовать переход на основную страницу
                    // Временно для проверки работы
                    Toast.makeText(MainActivity.this, "ВЫ ВОШЛИ!", Toast.LENGTH_LONG).show();
                } else {
                    error_log.setAlpha(1f);
                }
            }
        });

    }
}