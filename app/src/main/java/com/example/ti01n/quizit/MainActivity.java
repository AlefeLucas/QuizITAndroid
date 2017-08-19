package com.example.ti01n.quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    Button buttonSql;
    Button buttonWeb;
    Button buttonHard;
    Button buttonSo;
    Button buttonJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonWeb = (Button) findViewById(R.id.buttonWeb);
        buttonSql = (Button) findViewById(R.id.buttonSql);
        buttonJava = (Button) findViewById(R.id.buttonJava);
        buttonHard = (Button) findViewById(R.id.buttonHard);
        buttonSo = (Button) findViewById(R.id.buttonSo);

        CategoryListener listener = new CategoryListener();

        buttonWeb.setOnClickListener(listener);
        buttonSql.setOnClickListener(listener);
        buttonJava.setOnClickListener(listener);
        buttonHard.setOnClickListener(listener);
        buttonSo.setOnClickListener(listener);


    }

    public MainActivity getThis() {
        return this;
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private class CategoryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            int id = v.getId();
            ArrayList<Question> questions = null;

            String tema = null;
            if (id == R.id.buttonWeb) {
                questions = QuestionsData.getWeb();
                tema = "Web";
            } else if (id == R.id.buttonJava) {
                tema = "Java";
                questions = QuestionsData.getJava();
            } else if (id == R.id.buttonSql) {
                tema = "SQL";
                questions = QuestionsData.getSql();
            } else if (id == R.id.buttonHard) {
                tema = "Hardware";
                questions = QuestionsData.getHardware();
            } else if (id == R.id.buttonSo) {
                tema = "Sistemas Operacionais";
                questions = QuestionsData.getSo();
            } else {
                System.out.println("UE");
            }

            Intent quiz = new Intent(getThis(), QuestionActivity.class);

            QuestionWrapper questionWrapper = new QuestionWrapper(questions);

            quiz.putExtra("QUESTIONS", questionWrapper);
            quiz.putExtra("TEMA", tema);

            startActivity(quiz);
        }
    }
}
