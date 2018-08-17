package com.xcodesystemsinterns.studentmanager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ExamCursorAdapter extends CursorAdapter {

    public ExamCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_exam, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tv_Name = view.findViewById(R.id.tv1_item_exam);
        TextView tv_Date = view.findViewById(R.id.tv2_item_exam);
        TextView tv_Class = view.findViewById(R.id.tv3_item_exam);
        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("Date"));
        String className = cursor.getString(cursor.getColumnIndexOrThrow("className"));
        // Populate fields with extracted properties
        tv_Name.setText(name);
        tv_Date.setText(date);
        tv_Class.setText(className);
    }
}
