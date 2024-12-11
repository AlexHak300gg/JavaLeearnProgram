package com.example.javalearnprogram.Classes;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioButton radioButtonAnswer1, radioButtonAnswer2, radioButtonAnswer3, radioButtonAnswer4;
    private List<Question> questions = new ArrayList<>();
    private List<Test> tests = new ArrayList<>();
    private int currentQuestionIndex = 0;

    private static int NUMtest = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioButtonAnswer1 = findViewById(R.id.radioButtonAnswer1);
        radioButtonAnswer2 = findViewById(R.id.radioButtonAnswer2);
        radioButtonAnswer3 = findViewById(R.id.radioButtonAnswer3);
        radioButtonAnswer4 = findViewById(R.id.radioButtonAnswer4);
        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        parseQuestionsXml();
        parseTestsXml();
        showCurrentQuestion();
    }

    private void parseQuestionsXml() {
        XmlPullParserFactory factory = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            InputStream is = getAssets().open("questions.xml");
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            Question question = null;
            while (eventType!= XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("question")) {
                        question = new Question();
                        questions.add(question);
                    } else if (parser.getName().equals("text")) {
                        if (question!= null) {
                            question.setText(parser.nextText());
                        }
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    private void parseTestsXml() {
        XmlPullParserFactory factory = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            InputStream is = getAssets().open("tests.xml");
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            Test test = null;
            while (eventType!= XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("test")) {
                        test = new Test();
                        tests.add(test);
                    } else if (parser.getName().equals("answer")) {
                        if (test!= null) {
                            test.getAnswers().add(parser.nextText());
                        }
                    } else if (parser.getName().equals("correct")) {
                        if (test!= null) {
                            test.setCorrect(parser.nextText());
                        }
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            Test test = tests.get(currentQuestionIndex);

            textViewQuestion.setText(question.getText());
            radioButtonAnswer1.setText(test.getAnswers().get(0));
            radioButtonAnswer2.setText(test.getAnswers().get(1));
            if (test.getAnswers().size() > 2) {
                radioButtonAnswer3.setText(test.getAnswers().get(2));
                radioButtonAnswer3.setVisibility(View.VISIBLE);
            } else {
                radioButtonAnswer3.setVisibility(View.GONE);
            }
            if (test.getAnswers().size() > 3) {
                radioButtonAnswer4.setText(test.getAnswers().get(3));
                radioButtonAnswer4.setVisibility(View.VISIBLE);
            } else {
                radioButtonAnswer4.setVisibility(View.GONE);
            }
        }
    }

    private void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            showCurrentQuestion();
            clearRadioButtons();
        } else {
            Toast.makeText(this, "Вопросы закончились", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearRadioButtons() {
        radioButtonAnswer1.setChecked(false);
        radioButtonAnswer2.setChecked(false);
        radioButtonAnswer3.setChecked(false);
        radioButtonAnswer4.setChecked(false);
    }

    private static class Question {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    private static class Test {
        private List<String> answers = new ArrayList<>();
        private String correct;

        public List<String> getAnswers() {
            return answers;
        }

        public String getCorrect() {
            return correct;
        }

        public void setCorrect(String correct) {
            this.correct = correct;
        }
    }

    public static void SetTest(int num) {
        NUMtest = num;
    }
}