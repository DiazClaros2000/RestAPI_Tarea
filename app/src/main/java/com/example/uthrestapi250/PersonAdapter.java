package com.example.uthrestapi250;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uthrestapi250.Config.Personas;
import com.example.uthrestapi250.R;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {

    Context context;
    ArrayList<Personas> list;

    public PersonAdapter(Context context, ArrayList<Personas> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(list.get(i).getId());
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false);

        TextView name = view.findViewById(R.id.txtName);
        TextView phone = view.findViewById(R.id.txtPhone);
        ImageView photo = view.findViewById(R.id.imgPerson);

        name.setText(list.get(i).getNombres() + " " + list.get(i).getApellidos());
        phone.setText(list.get(i).getTelefono());

        // Convertir imagen Base64 a Bitmap
        byte[] imageBytes = Base64.decode(list.get(i).getFoto(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        photo.setImageBitmap(bitmap);

        return view;


    }

}
