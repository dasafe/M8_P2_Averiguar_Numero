package com.example.averiguarnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int intento = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random r = new Random();
        final int nRandom = r.nextInt(100) + 1;
        final EditText editText = findViewById(R.id.editText);
        final Button button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int nIntroducido = Integer.parseInt(editText.getText().toString());
                if (nIntroducido!=nRandom) {
                    intento++;
                    textView.setText(intento + " Intentos" +"\n(Solucion: "+nRandom+")");
                    editText.setText("");
                } else{
                  textView.setText("VICTORIA");
                }

            }
        });
    }
}
