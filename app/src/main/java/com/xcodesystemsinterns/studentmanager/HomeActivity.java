package com.xcodesystemsinterns.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xcodesystemsinterns.studentmanager.Assignments.AssignmentsList;
import com.xcodesystemsinterns.studentmanager.Classes.classListActivity;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;

public class HomeActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataBaseHelper = new DataBaseHelper(this);
    }

    public void showStudentlist(View view) {


    }

    public void showExamList(View view) {

    }

    public void showAssignmentList(View view) {
        startActivity(new Intent(this, AssignmentsList.class));
    }

    public void showClassList(View view) {
        Intent i = new Intent(this,classListActivity.class);
        startActivity(i);
    }
}
