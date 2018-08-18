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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        dt=new DataBaseHelper(this);
        expandableList=(ExpandableListView) findViewById(R.id.lv_expandable_students);
        Bundle b=getIntent().getExtras();
        int selectedAssignmentID =b.getInt("ID");
        Cursor cursor =dt.getAssignmentInfo(selectedAssignmentID);
        cursor.moveToFirst();
        String name=cursor.getString(0);
        TextView title=(TextView) findViewById(R.id.tv_assignment_title);
        title.setText(name);

        String DueDate=cursor.getString(1);
        TextView dueDate=(TextView) findViewById(R.id.tv_assignment_duedate);
        dueDate.setText(DueDate);

        String Description=cursor.getString(2);
        TextView description=(TextView) findViewById(R.id.tv_assignment_description);
        description.setText(Description);
        expandableList.setAdapter(new ExpandAdapter(this,dt.getDoneAssignmentList(selectedAssignmentID),dt.getUndoneAssignmentList(selectedAssignmentID)));

    }
}
