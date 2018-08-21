package com.xcodesystemsinterns.studentmanager.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.R;

import java.util.List;

public class AssignmentCursorAdapter extends CursorAdapter {

    public AssignmentCursorAdapter(Context context, Cursor c)
    {
        super(context, c,0);
    }
    public int getSelectedAssignmentID(int position){
        getCursor().moveToPosition(position);
        return getCursor().getInt(0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_assignment, parent, false);
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {

        int ID=cursor.getInt(0);
        String name = cursor.getString(1);
        String dueDate=cursor.getString(2);
        String className = cursor.getString(3);
        TextView AssignmentName = (TextView) v.findViewById(R.id.tv1_item_assignment);
        TextView ClassName = (TextView) v.findViewById(R.id.tv3_item_assignment);
        TextView DueDate = (TextView) v.findViewById(R.id.tv2_item_assignment);

        AssignmentName.setText(name);
        ClassName.setText(className);
        DueDate.setText(dueDate);

    }

}

