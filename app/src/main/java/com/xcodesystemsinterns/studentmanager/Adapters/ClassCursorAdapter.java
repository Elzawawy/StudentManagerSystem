package com.xcodesystemsinterns.studentmanager.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;


public class ClassCursorAdapter extends CursorAdapter {

    //View variable can be "R.layout.item_class" or "R.layout.item_class_element"
   // private int view;
    //String variable can be "Assignment", "Student" , "Exam"
    private String elementType;
    DataBaseHelper data;

    public ClassCursorAdapter(Context context, Cursor c, /*int view,*/ String elementType) {
        super(context, c,0);
        /*this.view = view;*/
        data = new DataBaseHelper(context);
        this.elementType = elementType;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_class_element, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tv_1= view.findViewById(R.id.tv_item_1);
        TextView tv_2= view.findViewById(R.id.tv_item_2);
        /*switch (this.view)
        {
            case R.layout.item_class :
                TextView tv_Name = view.findViewById(R.id.tv_item_class_name);
                TextView tv_description = view.findViewById(R.id.tv_item_class_description);

                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String description = cursor.getString(cursor.getColumnIndex("Description"));

                tv_Name.setText(name);
                tv_description.setText(description);

                break;

            case  R.layout.item_class_element :*/
                if(elementType.equalsIgnoreCase("assignment"))
                {
                    String nameA = cursor.getString(cursor.getColumnIndex("Name"));
                    String dueA = cursor.getString(cursor.getColumnIndex("DueDate"));

                    tv_1.setText(nameA);
                    tv_2.setText(dueA);
                }
                else if (elementType.equalsIgnoreCase("exam"))
                {
                    String nameE = cursor.getString(cursor.getColumnIndex("Name"));

                    String dateE = cursor.getString(cursor.getColumnIndex("Date"));
                    tv_1.setText(nameE);
                    tv_2.setText(dateE);
                }
                else if (elementType.equalsIgnoreCase("student"))
                {
                    Cursor cs = data.getStudentInfo(cursor.getInt(cursor.getColumnIndex("_id")));

                    String nameS = cursor.getString(cursor.getColumnIndex("Name"));
                    //msh aryha
                    //String emailS = cs.getString(cs.getColumnIndex("E"));

                    tv_1.setText(nameS);
                    //tv_2.setText(emailS);
                }
                //break;
        //}
    }
}
