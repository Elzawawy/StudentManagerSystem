package com.xcodesystemsinterns.studentmanager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends CursorAdapter {

    public MyAdapter(Context context, Cursor c)
    {
        super(context, c,0);
    }
    public int getSelectedAssignmentID(int position){
        getCursor().moveToPosition(position);
        return getCursor().getInt(0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
         // first layout:    return LayoutInflater.from(context).inflate(R.layout.activity_list, parent, false);
        return LayoutInflater.from(context).inflate(R.layout.item_assignment, parent, false);
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {

        int ID=cursor.getInt(0);
        String name = cursor.getString(1);
        String dueDate=cursor.getString(2);
        String className = cursor.getString(3);
        /* The first layout
        TextView AssignmentName = (TextView) v.findViewById(R.id.AssignmentName);
        TextView ClassName = (TextView) v.findViewById(R.id.ClassName);
        TextView DueDate = (TextView) v.findViewById(R.id.DueDate);
        */
        TextView AssignmentName = (TextView) v.findViewById(R.id.tv1_item_assignment);
        TextView ClassName = (TextView) v.findViewById(R.id.tv3_item_assignment);
        TextView DueDate = (TextView) v.findViewById(R.id.tv2_item_assignment);

        AssignmentName.setText(name);
        ClassName.setText(className);
        DueDate.setText(dueDate);

    }

}

