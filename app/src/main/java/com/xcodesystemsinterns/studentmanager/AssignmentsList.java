package com.xcodesystemsinterns.studentmanager;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AssignmentsList extends AppCompatActivity {
    DataBaseHelper dt;
    ListView list;
    final Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_assignments_list);
        list=(ListView) findViewById(R.id.AssignmentsList);
        dt=new DataBaseHelper(this);
        /* Test Data

        dt.addAssignment("Assignment 1","5/8/2018","hoiiii",123);
        dt.addAssignment("Assignment 1","5/8/2018","hoiiii",123);
        dt.addAssignment("Assignment 1","5/8/2018","hoiiii",123);
         */
        Button checkOffButton=(Button) findViewById(R.id.CheckOffButton);
        checkOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOffAssignment newFragment = CheckOffAssignment.newInstance(dt.getAllAssignments(),c);
                newFragment.show(getSupportFragmentManager(),null);
            }
        });
        /*
        Test Data
        ArrayList<Assignment> arr=new ArrayList<>();
        arr.add(new Assignment(1,"Assignment 1","Maths","31/8/2018"));
        arr.add(new Assignment(1,"Assignment 2","English","18/8/2018"));
        arr.add(new Assignment(1,"Assignment 3","Sciences","1/9/2018"));
        arr.add(new Assignment(1,"Assignment 4","History","3/9/2018"));
        arr.add(new Assignment(1,"Assignment 4","History","3/9/2018"));
        arr.add(new Assignment(1,"Assignment 4","History","3/9/2018"));
        arr.add(new Assignment(1,"Assignment 4","History","3/9/2018"));
         */

        final MyAdapter adapter = new MyAdapter(this,dt.getAllAssignments());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedAssignmentID = adapter.getSelectedAssignmentID(position);
                Intent intent = new Intent(AssignmentsList.this,AssignmentActivity.class);
                intent.putExtra("ID",selectedAssignmentID);
                startActivity(intent);
            }
        });
    }
}
