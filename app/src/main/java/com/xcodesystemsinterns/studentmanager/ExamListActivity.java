package com.xcodesystemsinterns.studentmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ExamListActivity extends AppCompatActivity {
    // Find ListView to populate
    ListView lvExams;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        lvExams = (ListView) findViewById(R.id.lv_exams);
        dataBaseHelper = new DataBaseHelper(this);
        //addDummyExams();
        Cursor examCursor = dataBaseHelper.getAllExams();
        // Setup cursor adapter using cursor from last step
        ExamCursorAdapter examCursorAdapter = new ExamCursorAdapter(this,examCursor);
        // Attach cursor adapter to the ListView
        lvExams.setAdapter(examCursorAdapter);

    }

    void addDummyExams(){
        int classID = dataBaseHelper.addClass("Class1","This is a test");
        dataBaseHelper.addExam("Exam1","20-2",classID);
    }
}
