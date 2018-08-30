package com.xcodesystemsinterns.studentmanager.Classes;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;

public class ClassDialogInterface implements DialogInterface.OnClickListener {
    private int resLayout;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private View view;
    private LayoutInflater layoutInflater;
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private int classID;
    private Spinner spinner;
    private ArrayList<Integer> studentsIDs;
    ClassDialogInterface(Context context,int classID){
        this.context = context;
        this.classID = classID;
        dataBaseHelper = new DataBaseHelper(context);
        alertDialogBuilder = new AlertDialog.Builder(context);
        layoutInflater = LayoutInflater.from(context);
    }

    public void buildDialog(int resLayout){
        //Assign this instance's layout to layout entered for configurability during runtime.
        this.resLayout = resLayout;
        //Inflate the layout into the View.
        view = layoutInflater.inflate(resLayout,null);
        //***Special Construct for spinner populating in student's dialog*****
        if(resLayout == R.layout.dialog_add_student_class){
            spinner = view.findViewById(R.id.sp_add_student_class);
            studentsIDs = addStudentsOnSpinner(spinner);
        }
        //Set-up the alert dialog configurations.
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Submit",this);
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

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(resLayout)
        {
            case R.layout.dialog_add_student_class :
                int position = spinner.getSelectedItemPosition();
                if(position != AdapterView.INVALID_POSITION)
                {
                    int studentID = studentsIDs.get(position);
                    dataBaseHelper.addStudentToClass(studentID,classID);
                }
                else showErrorDialog();
                break;

            case R.layout.dialog_add_assignment :
                String assignmentName = ((EditText) view.findViewById(R.id.et1_add_assignment_name)).getText().toString();
                String assignmentDescription = ((EditText) view.findViewById(R.id.et2_add_assignment_desc)).getText().toString();
                String assignmentDate = ((EditText) view.findViewById(R.id.et3_add_assignment_duedate)).getText().toString();
                if (assignmentDate.length() == 0 || assignmentDescription.length() == 0 || assignmentName.length() == 0)
                    showErrorDialog();
                else
                    dataBaseHelper.addAssignment(assignmentName, assignmentDate, assignmentDescription, classID);
                break;

            case R.layout.dialog_add_exam :
                String examName = ((EditText) view.findViewById(R.id.et1_add_exam_name)).getText().toString();
                String examDate = ((EditText) view.findViewById(R.id.et2_add_exam_date)).getText().toString();
                if (examName.length() == 0 || examDate.length() == 0)
                    showErrorDialog();
                else
                    dataBaseHelper.addExam(examName, examDate, classID);
                break;
        }
    }

    private ArrayList<Integer> addStudentsOnSpinner(Spinner spinner){
        ArrayList<String> studentsNames = new ArrayList<>();
        ArrayList<Integer> studentsIDs = new ArrayList<>();
        Cursor cursor = dataBaseHelper.getUnregisteredStudentsByClass(classID);
        Log.e("Cursor", "HEEY: " + String.valueOf(cursor.getCount()));



        while(cursor.moveToNext())
        {
            studentsIDs.add(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            studentsNames.add(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,studentsNames);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(arrayAdapter);
        return studentsIDs;
    }

    private void showErrorDialog(){
        android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(context);
        errorDialog.setTitle("Error");
        errorDialog.setMessage(R.string.error_incomplete_operation);
        errorDialog.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        android.app.AlertDialog errorAlert = errorDialog.create();
        errorAlert.show();

    }

}
