package com.example.javalearnprogram.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.password);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button singUp = findViewById(R.id.btRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (validateUser(email, password)) {
                    Intent intent = new Intent(LoginActivity.this, Profile.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Неверный email или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private boolean validateUser(String email, String password) {
        try {
            File file = new File(getFilesDir(), "accountData.xml");
            if (!file.exists()) return false;

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList userList = doc.getElementsByTagName("user");

            for (int i = 0; i < userList.getLength(); i++) {
                Element userElement = (Element) userList.item(i);
                String storedEmail = userElement.getElementsByTagName("email").item(0).getTextContent();
                String storedPassword = userElement.getElementsByTagName("password").item(0).getTextContent();

                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}