package com.xcodesystemsinterns.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xcodesystemsinterns.studentmanager.Assignments.AssignmentsList;
import com.xcodesystemsinterns.studentmanager.Classes.ClassListActivity;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.Exams.ExamListActivity;
import com.xcodesystemsinterns.studentmanager.Students.StudentListActivity;

public class HomeActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataBaseHelper = new DataBaseHelper(this);
    }

    public void showStudentlist(View view) {
        startActivity(new Intent(this, StudentListActivity.class));

    }

    public void showExamList(View view) {
        startActivity(new Intent(this, ExamListActivity.class));
    }

    public void showAssignmentList(View view) {
        startActivity(new Intent(this, AssignmentsList.class));
    }

    public void showClassList(View view) {
        startActivity(new Intent(this,ClassListActivity.class));
    }
}
