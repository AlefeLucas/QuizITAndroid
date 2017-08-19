package com.example.ti01n.quizit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {


    TextView title;
    TextView question;
    Button[] ops;
    Button next;

    Question current;
    ArrayList<Question> questions;
    String selectedOp;
    Random random;
    int acertos;

    int total;

    Drawable normal;
    Drawable selec;

    ProgressBar pb;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        StateSave state = new StateSave(current, questions, selectedOp, acertos, total);

        outState.putSerializable("STATE", state);


        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        normal = getResources().getDrawable(R.drawable.backresposta);
        selec = getResources().getDrawable(R.drawable.backresposta2);

        Intent intent0 = getIntent();

        random = new Random();

        this.title = (TextView) findViewById(R.id.textViewTitle);
        title.setText(intent0.getStringExtra("TEMA"));
        this.question = (TextView) findViewById(R.id.textViewQuestion);
        this.next = (Button) findViewById(R.id.buttonNext);
        this.ops = new Button[4];
        ops[0] = (Button) findViewById(R.id.buttonA);
        ops[1] = (Button) findViewById(R.id.buttonB);
        ops[2] = (Button) findViewById(R.id.buttonC);
        ops[3] = (Button) findViewById(R.id.buttonD);

        RespListener listener = new RespListener();

        NextListener nextListener = new NextListener();

        next.setOnClickListener(nextListener);

        for (int x = 0; x < ops.length; x++) {
            ops[x].setOnClickListener(listener);
            ops[x].setBackgroundDrawable(normal);
        }

        if (savedInstanceState != null) {
            System.out.println("IS NOT NULL");

            StateSave state = (StateSave) savedInstanceState.getSerializable("STATE");

            questions = state.getQuestions();



            current = state.getCurrent();
            selectedOp = state.getSelectedOp();
            acertos = state.getAcertos();
            total = state.getTotal();

            this.question.setText(current.getQuestion());

            String[] resps = current.getAllOps();

            this.ops[0].setText(resps[0]);
            this.ops[1].setText(resps[1]);
            this.ops[2].setText(resps[2]);
            this.ops[3].setText(resps[3]);


            for (int x = 0; x < ops.length; x++) {
                if (ops[x].getText().toString().equals(selectedOp)) {
                    ops[x].setBackgroundDrawable(selec);
                    next.setEnabled(true);
                    next.setVisibility(View.VISIBLE);
                    break;
                }
            }
        } else {
            System.out.println("IS NULL");

            Intent intent = getIntent();



            QuestionWrapper qw = (QuestionWrapper) intent.getSerializableExtra("QUESTIONS");
            questions = qw.getQuestions();


            current = questions.get(random.nextInt(questions.size()));

            this.question.setText(current.getQuestion());

            String[] resps = current.getAllOps();

            this.ops[0].setText(resps[0]);
            this.ops[1].setText(resps[1]);
            this.ops[2].setText(resps[2]);
            this.ops[3].setText(resps[3]);
        }


        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setMax(total + questions.size());
        pb.setProgress(total);


    }

    private class RespListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            for (int x = 0; x < ops.length; x++) {


                ops[x].setBackgroundDrawable(normal);
            }

            Button clicked = (Button) findViewById(v.getId());
            clicked.setBackgroundDrawable(selec);
            selectedOp = clicked.getText().toString();

            next.setEnabled(true);
            next.setVisibility(View.VISIBLE);
        }
    }


    public QuestionActivity getThis() {
        return this;
    }

    private class NextListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {




            if (selectedOp != null && current != null) {
                if (current.getOpCorreta().equals(selectedOp)) {
                    System.out.println("CORRETO");
                    acertos++;
                    total++;
                } else {
                    System.out.println("ERRADO");
                    total++;
                }
                pb.incrementProgressBy(1);

                for (int x = 0; x < ops.length; x++) {
                    ops[x].setBackgroundDrawable(normal);

                }


                questions.remove(current);



                selectedOp = null;
                current = null;
                next.setEnabled(false);
                next.setVisibility(View.INVISIBLE);

                if (!questions.isEmpty()) {

                    current = questions.get(random.nextInt(questions.size()));

                    question.setText(current.getQuestion());

                    String[] resps = current.getAllOps();

                    ops[0].setText(resps[0]);
                    ops[1].setText(resps[1]);
                    ops[2].setText(resps[2]);
                    ops[3].setText(resps[3]);
                } else {
                    Intent fim = new Intent(getThis(), ResultadoActivity.class);

                    fim.putExtra("ACERTOS", acertos);
                    fim.putExtra("TOTAL", total);

                    startActivity(fim);
                    finish();

                }
            } else if (current == null){
                System.out.println("CURRENT NULO");
            }

        }
    }
}
