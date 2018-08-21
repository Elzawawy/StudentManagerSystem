package com.xcodesystemsinterns.studentmanager.Students;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.xcodesystemsinterns.studentmanager.Adapters.CustomAdapter;
import com.xcodesystemsinterns.studentmanager.Adapters.StudentCursorAdapter;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.DataModel;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;

/**
 * Created by Omar on 8/4/2018.
 */

public class StudentListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {

    private DataBaseHelper dbHelper;
    private ListView studentsListView;
    private View addStudentView;
    private ArrayList<Integer> studentIDs;
    private StudentCursorAdapter studentCursorAdapter;
    private Context context = this;
    private EditText studentName;
    private EditText studentLastName;
    private EditText studentPhone;
    private EditText studentEmail;
    private EditText studentAddress;
    LayoutInflater layoutInflater;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);
        layoutInflater = LayoutInflater.from(this);
        alertDialogBuilder = new AlertDialog.Builder(context);
        dbHelper = new DataBaseHelper(this);
        studentIDs = new ArrayList<>();
        studentsListView = findViewById(R.id.listview_student);
        FloatingActionButton fab_addStudent = findViewById(R.id.fab_student_list);
        studentCursorAdapter = new StudentCursorAdapter(this, dbHelper.getAllStudents(), studentsListView);
        studentsListView.setAdapter(studentCursorAdapter);
        studentsListView.setOnItemClickListener(this);
        fab_addStudent.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(StudentListActivity.this, StudentActivity.class);
        Cursor cursor = dbHelper.getAllStudents();
        while (cursor.moveToNext())
            studentIDs.add(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        intent.putExtra("id", studentIDs.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        addStudentView = layoutInflater.inflate(R.layout.dialog_add_student, null, false);
        studentName = addStudentView.findViewById(R.id.userInputDialog);
        studentLastName = addStudentView.findViewById(R.id.userInputLastDialog);
        studentPhone = addStudentView.findViewById(R.id.userPhoneDialog);
        studentEmail = addStudentView.findViewById(R.id.userEmailDialog);
        studentAddress = addStudentView.findViewById(R.id.userAddressDialog);
        alertDialogBuilder.setView(addStudentView);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int i = dbHelper.addStudent(studentName.getText().toString() + " " + studentLastName.getText().toString(), studentEmail.getText().toString(), studentPhone.getText().toString(), studentAddress.getText().toString());
                studentCursorAdapter.refresh();
                Toast.makeText(getApplicationContext(), "Student Added", Toast.LENGTH_LONG).show();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        alertDialog = alertDialogBuilder.create();
        //Show the configured dialog.
        alertDialog.show();

    }

}