package com.example.gato;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class second extends AppCompatActivity{

    private GridLayout gridLayout;
    private Button[] buttons = new Button[9];
    private boolean jugadorX = true;
    private boolean perder = false;
    private TextView estadoTextView;
    private Button finalizar;
    public static String s;
    public static String marcadorO;
    public static String marcadorx;

    public static char winner;

    private static Button marc1;
    private static Button marc2;

    public static int marcadorPlayerO = 0;
    public static int marcadorPlayerX = 0;
    public static String winner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        gridLayout = findViewById(R.id.gridLayout);
        estadoTextView = findViewById(R.id.estadoTextView);
        finalizar = findViewById(R.id.finalizar);
        marc1 = findViewById(R.id.marcador1);
        marc2 = findViewById(R.id.marcador2);




        marcadorO = getIntent().getStringExtra(third.marcadorO);
        marcadorx = getIntent().getStringExtra(third.marcadorX);


        s = getIntent().getStringExtra(third.s);
        if (s != null) {
            if (s.equals("jugador O")) {
                marcadorPlayerO++;
            } else if (s.equals("jugador X")) {
                marcadorPlayerX++;
            }
        }

        marc1.setText("PlayerO:  " + marcadorPlayerO);
        marc2.setText("PlayerX:  " + marcadorPlayerX);


        for (int i = 0; i < 9; i++) {
            String buttonID = "button" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
        }
    }



    public void clickencelda(View view) {
        Button button = (Button) view;
        if (!perder && button.getText().toString().equals("")) {
            if (jugadorX) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            button.setEnabled(false);
            jugadorX = !jugadorX;

            if (ganar()) {
                winner = jugadorX ? 'O' : 'X';
                if(winner == 'O'){
                    winner2 = "jugador O";
                }
                else {
                    winner2 = "jugador X";
                }
                estadoTextView.setText("¡" + winner + " gana!");
                estadoTextView.setVisibility(View.VISIBLE);
                finalizar.setVisibility(View.VISIBLE);
                perder = true;
            } else if (tablallena()) {
                winner = 'E';
                estadoTextView.setText("Empate");
                estadoTextView.setVisibility(View.VISIBLE);
                finalizar.setVisibility(View.VISIBLE);
                perder = true;
            }
        }
    }

    public void finalizar(View view) {
        Intent intent = new Intent(this, third.class);
        intent.putExtra(second.winner2, winner2);
        intent.putExtra(String.valueOf(second.marcadorPlayerO), marcadorPlayerO);
        intent.putExtra(String.valueOf(second.marcadorPlayerX), marcadorPlayerX);
        startActivity(intent);
    }


    private boolean ganar() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText().toString();
        }

        for (int[] winningCombo : combinaciones_ganadoras) {
            if (board[winningCombo[0]].equals(board[winningCombo[1]]) &&
                    board[winningCombo[1]].equals(board[winningCombo[2]]) &&
                    !board[winningCombo[0]].equals("")) {
                return true;
            }
        }
        return false;
    }

    private boolean tablallena() {
        for (Button button : buttons) {
            if (button.getText().toString().equals("")) {
                return false;
            }
        }
        return true;
    }

    private static final int[][] combinaciones_ganadoras = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };
}
