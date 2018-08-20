package com.xcodesystemsinterns.studentmanager.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;


public class ClassCursorAdapter extends CursorAdapter {
    private String viewType;
    DataBaseHelper data;

    public ClassCursorAdapter(Context context, Cursor c, String viewType) {
        super(context, c,0);
        /*this.view = view;*/
        data = new DataBaseHelper(context);
        this.viewType = viewType;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textView_1= view.findViewById(R.id.tv1_item_class);
        TextView textView_2= view.findViewById(R.id.tv2_item_class);
        String tv1_text="", tv2_text="";
        switch(viewType)
        {
            case "student" :
                Cursor intermediateCursor = data.getStudentInfo(cursor.getInt(cursor.getColumnIndex("_id")));
                intermediateCursor.moveToFirst();
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = intermediateCursor.getString(intermediateCursor.getColumnIndexOrThrow("Email"));
                break;

            case "class" :
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = cursor.getString(cursor.getColumnIndex("Description"));
                break;

            case "assignment" :
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = cursor.getString(cursor.getColumnIndex("DueDate"));
                break;

            case "exam" :
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = cursor.getString(cursor.getColumnIndex("Date"));
                break;
        }
        textView_1.setText(tv1_text);
        textView_2.setText(tv2_text);
    }
}
