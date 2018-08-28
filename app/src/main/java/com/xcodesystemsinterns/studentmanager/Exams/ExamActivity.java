package com.xcodesystemsinterns.studentmanager.Exams;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.Adapters.ExpandAdapter;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;

public class ExamActivity extends AppCompatActivity {

    DataBaseHelper dt;
    ExpandableListView expandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        dt=new DataBaseHelper(this);
        expandableList=(ExpandableListView) findViewById(R.id.lv_expandable_students_exam);
        Bundle b=getIntent().getExtras();
        int selectedExamID =b.getInt("ID");
        Cursor cursor =dt.getExamInfo(selectedExamID);
        cursor.moveToFirst();
        String name=cursor.getString(0);
        TextView title=(TextView) findViewById(R.id.tv_exam_title);
        title.setText(name);

        String DueDate=cursor.getString(1);
        TextView dueDate=(TextView) findViewById(R.id.tv_exam_duedate);
        dueDate.setText(DueDate);

        expandableList.setAdapter(new ExpandAdapter(this,dt.getDoneStudentsByExam(selectedExamID),dt.getUndoneStudentsByExam(selectedExamID),"exam"));

    }
}
