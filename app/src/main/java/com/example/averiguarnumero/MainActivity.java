package com.example.averiguarnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity {
    static int intento = 0;
    Random r = new Random();
    int nRandom = r.nextInt(100) + 1;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.editText);
        final Button button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                String dif;
                if (!editText.getText().toString().equals("")) {
                    int nIntroducido = Integer.parseInt(editText.getText().toString());
                    if (nIntroducido != nRandom) {
                        intento++;
                        if (nIntroducido > nRandom) {
                            dif = "menor";
                        } else {
                            dif = "mayor";
                        }
                        textView.setText(intento + " Intentos" + "\nPista: El numero oculto es " + dif);
                        editText.setText("");
                    } else if (nIntroducido == nRandom) {
                        intento++;
                        textView.setText("");
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
                                //Main2Activity.ranking.add(message);
                                generarXML(message);
                                intent.putExtra(EXTRA_MESSAGE, message);
                                startActivity(intent);
                                intento = 0;
                                nRandom = r.nextInt(100) + 1;
                            }
                        });
                        dialog.show();
                        editText.setText("");
                    }
                } else {
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

    public void generarXML(String message) {
        String archivo = "ranking.xml";
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            Element jugador = doc.createElement("jugador");
            Attr intentos = doc.createAttribute("intentos");
            doc.appendChild(jugador);
            jugador.setAttributeNode(intentos);

            jugador.appendChild(doc.createTextNode(message.split(" - ")[0]));
            intentos.setValue(message.split(" - ")[1]);

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            // Creamos el XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(MainActivity.this.getFilesDir(), archivo));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
