package com.xcodesystemsinterns.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class gridAdapter extends BaseAdapter {

    Context context;
    private final String [] namesPhotos;
    private final int [] photos;
    View view;
    LayoutInflater inflater;

    public gridAdapter(Context context, String[] namesPhotos, int[] photos) {
        this.context = context;
        this.namesPhotos = namesPhotos;
        this.photos = photos;
    }


    @Override
    public int getCount() {
        return namesPhotos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        if (view== null) {
            view = new View(context);
            view = inflater.inflate(R.layout.simple_item, null);

            ImageView img = (ImageView) view.findViewById(R.id.imageview);
            TextView txt = (TextView) view.findViewById(R.id.textview);
            img.setImageResource(photos[i]);
            txt.setText(namesPhotos[i]);
        }

        return view;
    }
}
