package com.example.averiguarnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int intento = 0;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

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
                String dif;
                if (editText.getText()!=null){
                int nIntroducido = Integer.parseInt(editText.getText().toString());
                if (nIntroducido!=nRandom) {
                    intento++;
                    if (nIntroducido>nRandom){
                        dif = "mayor";
                    }else{
                        dif = "menor";
                    }
                    textView.setText(intento + " Intentos" +"\nPista: Tu numero es "+dif);
                    editText.setText("");
                } else if (nIntroducido==nRandom){
                    intento++;
                    textView.setText("VICTORIA");
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.dialog_signin);
                    Button button = (Button) dialog.findViewById(R.id.button3);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            dialog.dismiss();
                            EditText user = dialog.findViewById(R.id.username);
                            String name = user.getText().toString();
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            String message = name + " - " + Integer.toString(intento);
                            Main2Activity.ranking.add(message);
                            intent.putExtra(EXTRA_MESSAGE, message);
                            startActivity(intent);
                            intento = 0;
                        }
                    });
                    dialog.show();
                }
            }else{
                    textView.setText("Introduce un numero");
                }

            }
        });
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
}
