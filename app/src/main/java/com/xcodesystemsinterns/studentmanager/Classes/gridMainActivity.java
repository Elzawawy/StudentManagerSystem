package com.xcodesystemsinterns.studentmanager.Classes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.xcodesystemsinterns.studentmanager.Adapters.ClassCursorAdapter;
import com.xcodesystemsinterns.studentmanager.Adapters.gridAdapter;
import com.xcodesystemsinterns.studentmanager.Assignments.AssignmentActivity;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.Exams.ExamListActivity;
import com.xcodesystemsinterns.studentmanager.R;
import com.xcodesystemsinterns.studentmanager.Students.StudentActivity;

public class gridMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    GridView gridView;
    final Context context = this;
    DataBaseHelper dataBaseHelper;
    ClassDialogInterface classDialogInterface;
    String studentName, studentEmail, studentPhone, studentAddress,
            assignmentName, assignmentDate, assignmentDescription,
            examName, examDate;

    View mView;
    int classID, idS, idE, idA;
    LayoutInflater layoutInflaterAndroid;
    ListView lv;


    //To get the display we want
    String[] iconName = {
            "Add Student",
            "Students list",
            "Add Assignment",
            "Assignments",
            "Add Exam",
            "Exams list"
    };

    int[] photos = {
            R.drawable.adduser,
            R.drawable.studentlist,
            R.drawable.addassignment,
            R.drawable.assignment_inside,
            R.drawable.exam_inside,
            R.drawable.test

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);
        dataBaseHelper = new DataBaseHelper(context);
        layoutInflaterAndroid = LayoutInflater.from(context);
        //To know which class.
        classID = getIntent().getIntExtra("ClassID", 0);
        //Set the display as gridView.
        gridView = findViewById(R.id.grid);
        //Define a new GridView Adapter.
        gridAdapter gridAdapter = new gridAdapter(gridMainActivity.this, iconName, photos);
        //Set the grid view to the created Adapter instance.
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(this);
        classDialogInterface = new ClassDialogInterface(this,classID);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        AlertDialog alertDialogAndroid;
        switch (position) {
            //to add student
            case 0:
                classDialogInterface.buildDialog(R.layout.dialog_add_student_class);
                break;
            //students list
            case 1:
                showStudents();
                break;
            //to add assignment
            case 2:
                classDialogInterface.buildDialog(R.layout.dialog_add_assignment);
                break;
            //for assign list
            case 3:
                showAssignments();
                break;
            //To add Exam
            case 4:
                classDialogInterface.buildDialog(R.layout.dialog_add_exam);
                break;
            case 5:
                ShowExams();
                break;
        }

    }


    public void showStudents() {
        //Set-up Dialog with custom layout.
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gridMainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.inside_class_list_view_elements, null);
        alertDialog.setView(convertView);
        //Set-up Dialog's title.
        alertDialog.setTitle(R.string.student_list_title);
        alertDialog.setCancelable(false);
        //Set-up List View to show elements of class.
        lv = convertView.findViewById(R.id.lv_class_elements);
        //Define data to populate in List View
        final Cursor cursor = dataBaseHelper.getStudentsByClass(classID);
        //Define an Adapter to control the data population flow.
        ClassCursorAdapter adapter = new ClassCursorAdapter(this, cursor, "student");
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                Intent intent = new Intent(getBaseContext(), StudentActivity.class);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });

        //Show the configured alert dialog.
        alertDialog.show();

    }

    public void ShowExams() {
        //Set-up Dialog with custom layout.
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gridMainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.inside_class_list_view_elements, null);
        alertDialog.setView(convertView);
        //Set-up Dialog's title.
        alertDialog.setTitle(R.string.exam_list_title);
        alertDialog.setCancelable(false);
        //Set-up List View to show elements of class.
        lv = convertView.findViewById(R.id.lv_class_elements);
        //Define data to populate in List View.
        final Cursor cursor = dataBaseHelper.getExamsByClass(classID);
        //Define an Adapter to control the data population flow.
        ClassCursorAdapter adapter = new ClassCursorAdapter(this, cursor, "exam");
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), ExamListActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        //Show the configured Dialog.
        alertDialog.show();
    }

    public void showAssignments() {
        //Set-up Dialog with custom layout.
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gridMainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.inside_class_list_view_elements, null);
        alertDialog.setView(convertView);
        //Set-up Dialog's title.
        alertDialog.setTitle(R.string.assignment_list_title);
        alertDialog.setCancelable(false);
        //Set-up List View to show elements of class.
        lv = convertView.findViewById(R.id.lv_class_elements);
        //Define data to populate in List View.
        final Cursor cursor = dataBaseHelper.getAssignmentsByClass(classID);
        //Define an Adapter to control the data population flow.
        ClassCursorAdapter adapter = new ClassCursorAdapter(this, cursor, "assignment");
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                Intent intent = new Intent(getBaseContext(), AssignmentActivity.class);
                intent.putExtra("ID", cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        //Show the configured Dialog.
        alertDialog.show();
    }

    /*public void examRemove(final View view)
    {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(gridMainActivity.this).create();
        alertDialog.setTitle("Delete Exam");
        alertDialog.setMessage("You are about to remove an Exam. Are you sure ?");
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int i = lv.getPositionForView(view);
                int examId = Exams.get(i).IdE;
                Exams.remove(i);
                dataBaseHelper.removeExam(examId);
                lv.setAdapter(new ClassCursorAdapter(gridMainActivity.this, dataBaseHelper.getExamsByClass(classID),"exam"));
                dialog.dismiss();
            }
        });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void assignRemove(final View view)
    {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(gridMainActivity.this).create();
        alertDialog.setTitle("Delete Assignment");
        alertDialog.setMessage("You are about to remove an assignment. Are you sure ?");
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int i = lv.getPositionForView(view);
                int assigId = Assignments.get(i).IdA;
                Assignments.remove(i);
                dataBaseHelper.removeAssignment(assigId);
                lv.setAdapter(new ClassCursorAdapter(getBaseContext(), dataBaseHelper.getAssignmentsByClass(classID),"assignment"));
                dialog.dismiss();
            }
        });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


    public void studentRemove(final View view)
    {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(gridMainActivity.this).create();
        alertDialog.setTitle("Remove Student");
        alertDialog.setMessage("You are about to remove a student from this class. Are you sure ?");
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int i = lv.getPositionForView(view);
                int classId = Students.get(i).ID;
                Students.remove(i);
                dataBaseHelper.removeStudentFromClass(classId,classID);
                lv.setAdapter(new ClassCursorAdapter(gridMainActivity.this, dataBaseHelper.getStudentsByClass(classID),"student"));
                dialog.dismiss();
            }
        });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }*/

}