package com.example.javalearnprogram.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javalearnprogram.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editLogin, editFIO, editClass, editSchool;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        // Найти элементы интерфейса
        editLogin = findViewById(R.id.edit_login);
        editFIO = findViewById(R.id.edit_fio);
        editClass = findViewById(R.id.edit_class);
        editSchool = findViewById(R.id.edit_school);
        buttonSave = findViewById(R.id.button_save);

        // Загрузить логин из файла loginSave.xml
        try {
            File file = new File(getFilesDir(), "loginSave.xml");
            if (file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new FileInputStream(file));
                NodeList nodes = doc.getElementsByTagName("value");
                String login = nodes.item(0).getFirstChild().getNodeValue();
                editLogin.setText(login); // Установить логин в поле
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Toast.makeText(this, "Ошибка при загрузке логина!", Toast.LENGTH_SHORT).show();
        }

        // Обработчик нажатия кнопки Сохранить
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });
    }

    private void saveProfile() {
        String login = editLogin.getText().toString();
        String fio = editFIO.getText().toString();
        String schoolClass = editClass.getText().toString();
        String school = editSchool.getText().toString();

        if (!login.isEmpty() && !fio.isEmpty() && !schoolClass.isEmpty() && !school.isEmpty()) {
            try {
                // Создать новый документ
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();

                Element rootElement = doc.createElement("profile");
                doc.appendChild(rootElement);

                Element elementLogin = doc.createElement("login");
                elementLogin.appendChild(doc.createTextNode(login));
                rootElement.appendChild(elementLogin);

                Element elementFIO = doc.createElement("fio");
                elementFIO.appendChild(doc.createTextNode(fio));
                rootElement.appendChild(elementFIO);

                Element elementClass = doc.createElement("class");
                elementClass.appendChild(doc.createTextNode(schoolClass));
                rootElement.appendChild(elementClass);

                Element elementSchool = doc.createElement("school");
                elementSchool.appendChild(doc.createTextNode(school));
                rootElement.appendChild(elementSchool);

                // Сохранение документа в файл profilStudent.xml
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new FileOutputStream(new File(getFilesDir(), "profilStudent.xml")));

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(source, result);

                Toast.makeText(this, "Профиль успешно сохранён!", Toast.LENGTH_SHORT).show();
            } catch (ParserConfigurationException | TransformerException | IOException e) {
                Toast.makeText(this, "Ошибка при сохранении профиля!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
        }
    }
}