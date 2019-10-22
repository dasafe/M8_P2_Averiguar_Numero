package com.example.averiguarnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Main2Activity extends AppCompatActivity {

    public static ArrayList<String> ranking = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        burbuja();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        ListView listView = (ListView) findViewById(R.id.lista);
        listView.setAdapter(itemsAdapter);
        // Capture the layout's TextView and set the string as its text
    }

    public void burbuja() {
        String archivo = "ranking.xml";
        File file = new File(Main2Activity.this.getFilesDir(), archivo);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(file);
            NodeList nList = doc.getElementsByTagName("jugador");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    ranking.add(eElement.getTextContent() + " - " + eElement.getAttribute("intentos"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i, j, p1, p2;
        String aux;
        for (i = 0; i < ranking.size() - 1; i++) {
            for (j = 0; j < ranking.size() - i - 1; j++) {
                p1 = Integer.parseInt(ranking.get(j).split(" - ")[1]);
                p2 = Integer.parseInt(ranking.get(j + 1).split(" - ")[1]);
                if (p2 < p1) {
                    aux = ranking.get(j + 1);
                    ranking.set(j + 1, ranking.get(j));
                    ranking.set(j, aux);
                }
            }
        }
    }
}
