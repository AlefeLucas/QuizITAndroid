package com.example.ti01n.quizit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    TextView acertosTV;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


            acertosTV = (TextView) findViewById(R.id.textViewAcertos);
            imagem = (ImageView) findViewById(R.id.imageButtonResult);

            Intent intent = getIntent();

            double acertos = intent.getIntExtra("ACERTOS", -1);
            double total = intent.getIntExtra("TOTAL", -1);

            double porcentagem = (acertos / total) * 100;

            if(imagem != null){
                BitmapDrawable bd = ((BitmapDrawable) imagem.getDrawable());

                if(bd != null){
                    Bitmap bm = bd.getBitmap();

                    if(bm != null){
                        bm.recycle();
                    }
                }
            }

            if (porcentagem < 60) {
                imagem.setImageResource(R.drawable.bad);
            } else if (porcentagem <= 100) {
                imagem.setImageResource(R.drawable.good);
            }

            acertosTV.setText(String.format("%.1f ", porcentagem) + "%");


    }


}
