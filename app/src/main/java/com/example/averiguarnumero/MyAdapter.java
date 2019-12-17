package com.example.averiguarnumero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Jugador> {

    ArrayList<Jugador> j;
    Context context;

    public MyAdapter(@NonNull Context context, ArrayList<Jugador> j) {
        super(context, R.layout.ranking_adapter);
        this.context = context;
        this.j = j;
    }

    public int getCount() {
        return this.j.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.ranking_adapter, parent, false);

        ImageView imagen = (ImageView) convertView.findViewById(R.id.imgJugador);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombreJuagdor);

        File imgFile = new File(j.get(position).getRuta());
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        imagen.setImageBitmap(myBitmap);

        nombre.setText(j.get(position).getNombre());

        return convertView;
    }
}
