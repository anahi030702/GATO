package com.example.gato;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        gridLayout = findViewById(R.id.gridLayout);
        estadoTextView = findViewById(R.id.estadoTextView);

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
                String winner = jugadorX ? "O" : "X";
                estadoTextView.setText("¡" + winner + " gana!");
                estadoTextView.setVisibility(View.VISIBLE);
                perder = true;
            } else if (tablallena()) {
                estadoTextView.setText("Empate");
                estadoTextView.setVisibility(View.VISIBLE);
                perder = true;
            }
        }
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
