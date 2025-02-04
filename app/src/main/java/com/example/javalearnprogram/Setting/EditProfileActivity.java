package com.example.javalearnprogram.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.Classes.Profile;
import com.example.javalearnprogram.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etFullName, etBirthDate, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        etFullName = findViewById(R.id.etFullName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etPassword = findViewById(R.id.etPassword);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.back);

        loadUserData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString();
                String birthDate = etBirthDate.getText().toString();
                String password = etPassword.getText().toString();

                updateUserData(fullName, birthDate, password);
                Toast.makeText(EditProfileActivity.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditProfileActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, Profile.class);
                startActivity(intent);
            }
        });
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
                String password = userElement.getElementsByTagName("password").item(0).getTextContent();

                etFullName.setText(fullName);
                etBirthDate.setText(birthDate);
                etPassword.setText(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserData(String fullName, String birthDate, String password) {
        try {
            File file = new File(getFilesDir(), "accountData.xml");
            if (!file.exists()) return;

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList userList = doc.getElementsByTagName("user");

            if (userList.getLength() > 0) {
                Element userElement = (Element) userList.item(0);

                userElement.getElementsByTagName("fullName").item(0).setTextContent(fullName);
                userElement.getElementsByTagName("birthDate").item(0).setTextContent(birthDate);
                userElement.getElementsByTagName("password").item(0).setTextContent(password);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new FileOutputStream(file));
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}