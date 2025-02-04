package com.example.javalearnprogram.Classes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javalearnprogram.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    TextView textViewQuestion;
    RadioButton radioButtonAnswer1, radioButtonAnswer2, radioButtonAnswer3, radioButtonAnswer4;
    List<Question> questions;
    List<Test> tests = new ArrayList<>();
    int currentQuestionIndex = 0;
    Button nextQuest;
    ImageButton home;
    byte countCorrect = 0;

    public static int NUMtest = -1; // 0 - Дроби, 1 - Степень, 2 - Двухчлен

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioButtonAnswer1 = findViewById(R.id.radioButtonAnswer1);
        radioButtonAnswer2 = findViewById(R.id.radioButtonAnswer2);
        radioButtonAnswer3 = findViewById(R.id.radioButtonAnswer3);
        radioButtonAnswer4 = findViewById(R.id.radioButtonAnswer4);
        nextQuest = findViewById(R.id.buttonNext);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessonListActivity.isTest = true;
                LessonListActivity.isTheory = false;
                startActivity(new Intent(TestActivity.this, LessonListActivity.class));
                finish();
            }
        });

        setQuestAndTest();
        setCurentQuest();

        nextQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonAnswer1.isChecked() && questions.get(currentQuestionIndex).getCorrect().equals(tests.get(currentQuestionIndex).getAnsver(0))) {
                    countCorrect++;
                    Toast.makeText(TestActivity.this, "Верно!", Toast.LENGTH_SHORT).show();
                }
                if (radioButtonAnswer2.isChecked() && questions.get(currentQuestionIndex).getCorrect().equals(tests.get(currentQuestionIndex).getAnsver(1))) {
                    countCorrect++;
                    Toast.makeText(TestActivity.this, "Верно!", Toast.LENGTH_SHORT).show();
                }
                if (radioButtonAnswer3.isChecked() && questions.get(currentQuestionIndex).getCorrect().equals(tests.get(currentQuestionIndex).getAnsver(2))) {
                    countCorrect++;
                    Toast.makeText(TestActivity.this, "Верно!", Toast.LENGTH_SHORT).show();
                }
                if (radioButtonAnswer4.isChecked() && questions.get(currentQuestionIndex).getCorrect().equals(tests.get(currentQuestionIndex).getAnsver(3))) {
                    countCorrect++;
                    Toast.makeText(TestActivity.this, "Верно!", Toast.LENGTH_SHORT).show();
                }
                currentQuestionIndex++;
                if (currentQuestionIndex < 10) {
                    setCurentQuest();
                    radioButtonAnswer1.setChecked(false);
                    radioButtonAnswer2.setChecked(false);
                    radioButtonAnswer3.setChecked(false);
                    radioButtonAnswer4.setChecked(false);
                } else {
                    ResultTestActivity.point = countCorrect;
                    startActivity(new Intent(TestActivity.this, ResultTestActivity.class));
                    finish();
                }
            }
        });
    }

    public void setCurentQuest() {
        textViewQuestion.setText(questions.get(currentQuestionIndex).text);
        radioButtonAnswer1.setText(tests.get(currentQuestionIndex).answers.get(0));
        radioButtonAnswer2.setText(tests.get(currentQuestionIndex).answers.get(1));
        radioButtonAnswer3.setText(tests.get(currentQuestionIndex).answers.get(2));
        radioButtonAnswer4.setText(tests.get(currentQuestionIndex).answers.get(3));
    }

    public void setQuestAndTest() {
        questions = new ArrayList<>();

        if (NUMtest == 0) { // Степень
            Question q1 = new Question("Вопрос 1: Какое значение имеет 2^3", "8");
            questions.add(q1);
            Question q2 = new Question("Вопрос 2: Как записывается 5 в степени 5, в виде произведения?", "5 * 5 * 5 * 5 * 5");
            questions.add(q2);
            Question q3 = new Question("Вопрос 3: Найдите 3^0", "1");
            questions.add(q3);
            Question q4 = new Question("Вопрос 4: Какое значение имеет выражение 10^-2?", "0.1");
            questions.add(q4);
            Question q5 = new Question("Вопрос 5: Какова сумма степеней 2^2 + 2^3?", "12");
            questions.add(q5);
            Question q6 = new Question("Вопрос 6: Какое правило применяется при умножении одинаковых оснований?", "Степени складываются");
            questions.add(q6);
            Question q7 = new Question("Вопрос 7: Какое значение имеет 4^3?", "64");
            questions.add(q7);
            Question q8 = new Question("Вопрос 8: Как выразить корень из 16 через степени?", "4 ^ 2");
            questions.add(q8);
            Question q9 = new Question("Вопрос 9: Найдите значение 5^2 - 5^3.", "-100");
            questions.add(q9);
            Question q10 = new Question("Вопрос 10: Если x = 2, то чему равно значение x^4?", "32");
            questions.add(q10);

            List<String> St1 = new ArrayList<>();
            St1.add("6");
            St1.add("8");
            St1.add("9");
            St1.add("4");
            Test t1 = new Test(St1);
            tests.add(t1);

            List<String> St2 = new ArrayList<>();
            St2.add("5 * 5 * 5 * 5 * 5");
            St2.add("5 * 5");
            St2.add("5 ^ 2 + 3 ^ 5");
            St2.add("5 ^ 3 + 2 ^ 5");
            Test t2 = new Test(St2);
            tests.add(t2);

            List<String> St3 = new ArrayList<>();
            St3.add("9");
            St3.add("0");
            St3.add("3");
            St3.add("1");
            Test t3 = new Test(St3);
            tests.add(t3);

            List<String> St4 = new ArrayList<>();
            St4.add("1");
            St4.add("0.01");
            St4.add("1000");
            St4.add("0.1");
            Test t4 = new Test(St4);
            tests.add(t4);

            List<String> St5 = new ArrayList<>();
            St5.add("9");
            St5.add("12");
            St5.add("6");
            St5.add("4");
            Test t5 = new Test(St5);
            tests.add(t5);

            List<String> St6 = new ArrayList<>();
            St6.add("Степени складываются");
            St6.add("Степени вычитаются");
            St6.add("Степени перемножаются");
            St6.add("Нету подобного правила");
            Test t6 = new Test(St6);
            tests.add(t6);

            List<String> St7 = new ArrayList<>();
            St7.add("64");
            St7.add("27");
            St7.add("12");
            St7.add("Нету верного ответа");
            Test t7 = new Test(St7);
            tests.add(t7);

            List<String> St8 = new ArrayList<>();
            St8.add("4 ^ 2");
            St8.add("8 ^ 2");
            St8.add("4 ^ 4");
            St8.add("16 ^ 0");
            Test t8 = new Test(St8);
            tests.add(t8);

            List<String> St9 = new ArrayList<>();
            St9.add("100");
            St9.add("-125");
            St9.add("-25");
            St9.add("-100");
            Test t9 = new Test(St9);
            tests.add(t9);

            List<String> St10 = new ArrayList<>();
            St10.add("16");
            St10.add("2");
            St10.add("8");
            St10.add("32");
            Test t10 = new Test(St10);
            tests.add(t10);


        } else if (NUMtest == 1) { // Одночлен

            Question q11 = new Question("Вопрос 1: Что такое одночлен?", "Произведение чисел и переменных, возведённых в натуральные степени");
            questions.add(q11);
            Question q12 = new Question("Вопрос 2: Какой из следующих примеров является одночленом?", "7y ^ 2");
            questions.add(q12);
            Question q13 = new Question("Вопрос 3: Какова степень одночлена 4x^3y^2?", "5");
            questions.add(q13);
            Question q14 = new Question("Вопрос 4: Какое из следующих выражений не является одночленом?", "3x + 2");
            questions.add(q14);
            Question q15 = new Question("Вопрос 5: Какой коэффициент у одночлена -5x^4?", "-5");
            questions.add(q15);
            Question q16 = new Question("Вопрос 6: Как перевести одночлен 3a^2b в стандартную форму?", "3a ^ 2b ^ 1");
            questions.add(q16);
            Question q17 = new Question("Вопрос 7: Какое значение имеет одночлен 2x, если x = 3?", "6");
            questions.add(q17);
            Question q18 = new Question("Вопрос 8: Какой из следующих одночленов имеет переменную в степени 1?", "5x");
            questions.add(q18);
            Question q19 = new Question("Вопрос 9: Как получится одночлен, если умножить 2x на 3x^2?", "6x^3");
            questions.add(q19);
            Question q20 = new Question("Вопрос 10: Какое из следующих свойств относится к одночленам?", "Одночлены можно складывать и вычитать, если у них одинаковые параметры");
            questions.add(q20);

            List<String> St1 = new ArrayList<>();
            St1.add("Сумма нескольких чисел");
            St1.add("Произведение чисел и переменных, возведённых в натуральные степени");
            St1.add("Геометрическая фигура");
            St1.add("Нету правильного ответа");
            Test t1 = new Test(St1);
            tests.add(t1);

            List<String> St2 = new ArrayList<>();
            St2.add("5x + 3");
            St2.add("7y ^ 2");
            St2.add("a - b");
            St2.add("Все ответы верны");
            Test t2 = new Test(St2);
            tests.add(t2);

            List<String> St3 = new ArrayList<>();
            St3.add("3");
            St3.add("5");
            St3.add("6");
            St3.add("Нету правильного ответа");
            Test t3 = new Test(St3);
            tests.add(t3);

            List<String> St4 = new ArrayList<>();
            St4.add("6ab");
            St4.add("-2x ^ 2y");
            St4.add("3x + 2");
            St4.add("Все ответы верны");
            Test t4 = new Test(St4);
            tests.add(t4);

            List<String> St5 = new ArrayList<>();
            St5.add("-5");
            St5.add("4");
            St5.add("1");
            St5.add("Нету верного ответа");
            Test t5 = new Test(St5);
            tests.add(t5);

            List<String> St6 = new ArrayList<>();
            St6.add("3ab ^ 2");
            St6.add("3a ^ 2b ^ 1");
            St6.add("3a ^ 2b ^ 0");
            St6.add("Нету верного ответа");
            Test t6 = new Test(St6);
            tests.add(t6);

            List<String> St7 = new ArrayList<>();
            St7.add("6");
            St7.add("5");
            St7.add("0");
            St7.add("Нету верного ответа");
            Test t7 = new Test(St7);
            tests.add(t7);

            List<String> St8 = new ArrayList<>();
            St8.add("9x ^ 2");
            St8.add("12xy ^ 3");
            St8.add("5x");
            St8.add("Нету верного ответа");
            Test t8 = new Test(St8);
            tests.add(t8);

            List<String> St9 = new ArrayList<>();
            St9.add("6x^3");
            St9.add("5x^2");
            St9.add("6x^4");
            St9.add("Нету верного ответа");
            Test t9 = new Test(St9);
            tests.add(t9);

            List<String> St10 = new ArrayList<>();
            St10.add("Одночлены всегда имеют разные степени");
            St10.add("Одночлены можно складывать и вычитать, если у них одинаковые параметры");
            St10.add("Одночлены не могут иметь отрицательные степени");
            St10.add("Нету верного ответа");
            Test t10 = new Test(St10);
            tests.add(t10);


        } else if (NUMtest == 2) { // Многочлен

            Question q11 = new Question("Вопрос 1: Что такое многочлен?", "Сумма нескольких членов, каждый из которых является произведением коэффициента и переменной, возведенной в натуральную степень");
            questions.add(q11);
            Question q12 = new Question("Вопрос 2: Какой из следующих примеров является многочленом?", "2a2y + (−6ya)");
            questions.add(q12);
            Question q13 = new Question("Вопрос 3: Как называется наибольшая степень переменной в многочлене?", "Степень");
            questions.add(q13);
            Question q14 = new Question("Вопрос 4: Каковы коэффициенты многочлена 3x^4 - 2x^3 + 5x - 7?", "3, -2, 5, -7");
            questions.add(q14);
            Question q15 = new Question("Вопрос 5: Как выглядит сумма многочленов 2x^2 + x + 3 и x^2 - 4?", "3x^2 + x + 7");
            questions.add(q15);
            Question q16 = new Question("Вопрос 6: Какова степень многочлена 5x^3y^2 - 2xy + 8?", "5");
            questions.add(q16);
            Question q17 = new Question("Вопрос 7: Какой из следующих многочленов является полиномом степени 2?", "4x^2 - 6x + 1");
            questions.add(q17);
            Question q18 = new Question("Вопрос 8: Как называется многочлен, состоящий из одного члена?", "Моном");
            questions.add(q18);
            Question q19 = new Question("Вопрос 9: Что произойдет при умножении многочлена x + 2 на многочлен x - 3?", "Получится многочлен степени 2");
            questions.add(q19);
            Question q20 = new Question("Вопрос 10:  Какой из следующих многочленов является убывающим?", "-2x^3 + 4x^2 - x + 5");
            questions.add(q20);

            List<String> St1 = new ArrayList<>();
            St1.add("Алгебраическое выражение, содержащее только степени неполного вида");
            St1.add("Сумма нескольких членов, каждый из которых является произведением коэффициента и переменной, возведенной в натуральную степень");
            St1.add("Число, выраженное в степени");
            St1.add("Уравнение с одной переменной");
            Test t1 = new Test(St1);
            tests.add(t1);

            List<String> St2 = new ArrayList<>();
            St2.add("15a");
            St2.add("a − 3");
            St2.add("2a2y + (−6ya)");
            St2.add("7xy ^ 4 + 2x + 3y");
            Test t2 = new Test(St2);
            tests.add(t2);

            List<String> St3 = new ArrayList<>();
            St3.add("Коэффициент");
            St3.add("Степень");
            St3.add("График");
            St3.add("Константа");
            Test t3 = new Test(St3);
            tests.add(t3);

            List<String> St4 = new ArrayList<>();
            St4.add("3, -2, 5, -7");
            St4.add("3, -2, 5, 1");
            St4.add("4, 5, 2, 1");
            St4.add("3, -1, 5, -4");
            Test t4 = new Test(St4);
            tests.add(t4);

            List<String> St5 = new ArrayList<>();
            St5.add("3x^2 + x - 1");
            St5.add("3x^2 + x + 7");
            St5.add("2x^2 + 4x - 1");
            St5.add("2x^2 + x - 1");
            Test t5 = new Test(St5);
            tests.add(t5);

            List<String> St6 = new ArrayList<>();
            St6.add("3");
            St6.add("5");
            St6.add("4");
            St6.add("2");
            Test t6 = new Test(St6);
            tests.add(t6);

            List<String> St7 = new ArrayList<>();
            St7.add("4x^2 - 6x + 1");
            St7.add("-3x^4 + 2x^3");
            St7.add("x - 5");
            St7.add("7");
            Test t7 = new Test(St7);
            tests.add(t7);

            List<String> St8 = new ArrayList<>();
            St8.add("Бином");
            St8.add("Трином");
            St8.add("Моном");
            St8.add("Полином");
            Test t8 = new Test(St8);
            tests.add(t8);

            List<String> St9 = new ArrayList<>();
            St9.add("Получится многочлен степени 2");
            St9.add("Получится многочлен степени 1");
            St9.add("Получится многочлен степени 3");
            St9.add("Получится константа");
            Test t9 = new Test(St9);
            tests.add(t9);

            List<String> St10 = new ArrayList<>();
            St10.add("-2x^3 + 4x^2 - x + 5");
            St10.add("3x^3 + 2x^2 + x + 1");
            St10.add("5 - x^2 + 4x");
            St10.add("6x + 3x^3 - 2");
            Test t10 = new Test(St10);
            tests.add(t10);

        }
    }

    private class Question {
        public String text;
        public String correct;

        public Question(String text, String correct) {
            this.text = text;
            this.correct = correct;
        }

        public String getCorrect() {
            return correct;
        }
    }

    private class Test {
        public Test(List<String> answers) {
            this.answers = answers;
        }

        public List<String> answers;

        public String getAnsver(int var) {
            return answers.get(var);
        }
    }
}