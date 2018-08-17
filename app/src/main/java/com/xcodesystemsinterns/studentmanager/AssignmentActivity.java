package com.xcodesystemsinterns.studentmanager;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    DataBaseHelper dt;
    ExpandableListView expandableList;
    ArrayList<StudentDescription> doneStudents;
    ArrayList<StudentDescription> undoneStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        dt=new DataBaseHelper(this);
        expandableList=(ExpandableListView) findViewById(R.id.list);
        Bundle b=getIntent().getExtras();
        int selectedAssignmentID =b.getInt("ID");
        doneStudents=new ArrayList<>();
        undoneStudents=new ArrayList<>();
        Cursor cursor =dt.getAssignmentInfo(selectedAssignmentID);

        cursor.moveToFirst();
        String name=cursor.getString(0);
        TextView title=(TextView) findViewById(R.id.title);
        title.setText(name);

        String DueDate=cursor.getString(1);
        TextView dueDate=(TextView) findViewById(R.id.dueDate);
        dueDate.setText(DueDate);

        String Description=cursor.getString(2);
        TextView description=(TextView) findViewById(R.id.description);
        description.setText(Description);

       cursor=dt.getDoneAssignmentList(selectedAssignmentID);
        try {
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++)
            {
                String studentName=cursor.getString(0);
                int id=cursor.getInt(1);
                doneStudents.add(new StudentDescription(studentName,id));
                cursor.moveToNext();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        cursor=dt.getUndoneAssignmentList(selectedAssignmentID);
        try {
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++)
            {
                String studentName=cursor.getString(0);
                int id=cursor.getInt(1);
                undoneStudents.add(new StudentDescription(studentName,id));
                cursor.moveToNext();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }



/*
        ArrayList<StudentDescription> arr=new ArrayList<>();
        arr.add(new StudentDescription("mohamed",456,4));
        arr.add(new StudentDescription("Ahmed",789,5));
        arr.add(new StudentDescription("Khaled",123,6));
        */
        expandableList.setAdapter(new ExpandAdapter(this,doneStudents,undoneStudents));

    }
}
