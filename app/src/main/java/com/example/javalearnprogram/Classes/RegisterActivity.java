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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullName, etBirthDate, etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.fulName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString();
                String birthDate = etBirthDate.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (password.equals(confirmPassword)) {
                    saveUserData(fullName, birthDate, email, password);
                    Intent intent = new Intent(RegisterActivity.this, Profile.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserData(String fullName, String birthDate, String email, String password) {
        try {
            File file = new File(getFilesDir(), "accountData.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc;

            if (file.exists()) {
                doc = docBuilder.parse(file);
            } else {
                doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("users");
                doc.appendChild(rootElement);
            }

            Element userElement = doc.createElement("user");

            Element fullNameElement = doc.createElement("fullName");
            fullNameElement.appendChild(doc.createTextNode(fullName));
            userElement.appendChild(fullNameElement);

            Element birthDateElement = doc.createElement("birthDate");
            birthDateElement.appendChild(doc.createTextNode(birthDate));
            userElement.appendChild(birthDateElement);

            Element emailElement = doc.createElement("email");
            emailElement.appendChild(doc.createTextNode(email));
            userElement.appendChild(emailElement);

            Element passwordElement = doc.createElement("password");
            passwordElement.appendChild(doc.createTextNode(password));
            userElement.appendChild(passwordElement);

            doc.getDocumentElement().appendChild(userElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(file));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | IOException | TransformerException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }
}