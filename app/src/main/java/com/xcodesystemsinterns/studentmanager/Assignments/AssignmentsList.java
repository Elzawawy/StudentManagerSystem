package com.xcodesystemsinterns.studentmanager.Assignments;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.Adapters.MyAdapter;
import com.xcodesystemsinterns.studentmanager.R;

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
        Button checkOffButton=(Button) findViewById(R.id.CheckOffButton);
        checkOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOffAssignment newFragment = CheckOffAssignment.newInstance(dt.getAllAssignments(),c);
                newFragment.show(getSupportFragmentManager(),null);
            }
        });
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
