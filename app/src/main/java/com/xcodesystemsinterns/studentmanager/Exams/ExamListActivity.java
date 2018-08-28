package com.xcodesystemsinterns.studentmanager.Exams;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.Adapters.ExamCursorAdapter;
import com.xcodesystemsinterns.studentmanager.R;

public class ExamListActivity extends AppCompatActivity {
    DataBaseHelper dt;
    ListView list;
    final Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_exam_list);
        list = findViewById(R.id.lv_exams);
        dt=new DataBaseHelper(this);
        FloatingActionButton checkOffButton= findViewById(R.id.fab_exam_list);
        checkOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOffExam newFragment = CheckOffExam.newInstance(dt.getAllClasses(),c);
                newFragment.show(getSupportFragmentManager(),null);
            }
        });
        final ExamCursorAdapter adapter = new ExamCursorAdapter(this,dt.getAllExams());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedExamID = adapter.getSelectedExamID(position);
                Intent intent = new Intent(ExamListActivity.this,ExamActivity.class);
                intent.putExtra("ID",selectedExamID);
                startActivity(intent);
            }
        });
    }
}
