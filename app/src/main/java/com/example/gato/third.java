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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        cuadro = findViewById(R.id.cuadro);

        s = getIntent().getStringExtra(second.winner);

        if (s != null) {
            cuadro.setText("RESULTADO: " + s);
        }


    }

    public void againplay(View view) {
        Intent intent = new Intent(this, second.class);
        intent.putExtra(third.s, s );
        startActivity(intent);
    }
}
