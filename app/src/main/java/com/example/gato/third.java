package com.example.gato;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class third extends AppCompatActivity{

    private TextView cuadro;
    public static String s;
    public static String marcadorO;
    public static String marcadorX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        cuadro = findViewById(R.id.cuadro);

        s = getIntent().getStringExtra(second.winner2);
        marcadorO = getIntent().getStringExtra(String.valueOf(second.marcadorPlayerO));
        marcadorX = getIntent().getStringExtra(String.valueOf(second.marcadorPlayerX));


        if (s != null) {
            cuadro.setText("RESULTADO: " + s);
        }


    }

    public void againplay(View view) {
        Intent intent = new Intent(this, second.class);
        intent.putExtra(third.s, s );
        intent.putExtra(third.marcadorO, marcadorO);
        intent.putExtra(third.marcadorX, marcadorX);
        startActivity(intent);
    }
}
