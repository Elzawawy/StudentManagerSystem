package com.xcodesystemsinterns.studentmanager.Exams;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;
import java.util.List;


public class CheckOffExam extends DialogFragment {

    private static DataBaseHelper dt;
    private Spinner studentsSpinner;
    private Spinner examsSpinner;
    private Spinner classesSpinner;
    private Spinner gradesSpinner;
    private static List<String> classesNamesList;
    private static List<Integer> classesIDList;
    private static List<String> examsNamesList;
    private static List<Integer> examsIDList;
    private static List<String> studentsNamesList;
    private static List<Integer> studentsIDList;
    public CheckOffExam() {
        classesNamesList=new ArrayList<>();
        classesIDList=new ArrayList<>();
        examsNamesList=new ArrayList<>();
        examsIDList=new ArrayList<>();
        studentsNamesList=new ArrayList<>();
        studentsIDList=new ArrayList<>();
    }

    public static CheckOffExam newInstance(Cursor c, Context context) {
        CheckOffExam fragment = new CheckOffExam();
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++)
            {
                classesIDList.add(c.getInt(0));
                classesNamesList.add(c.getString(1));
                c.moveToNext();
            }

        dt=new DataBaseHelper(context);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       final View v=getActivity().getLayoutInflater().inflate(R.layout.check_off_exam_dialog, null, false);
       classesSpinner=v.findViewById(R.id.sp_classes);
       studentsSpinner=v.findViewById(R.id.sp_students_exam);
       examsSpinner=v.findViewById(R.id.sp_exams);
       gradesSpinner=v.findViewById(R.id.sp_grades);
       setCancelable(false);
       //final EditText et_studentGrade = v.findViewById(R.id.et_grade_check_off_exam);

       addClassesOnSpinner();
       addGradesOnSpinner();
       classesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(!classesIDList.isEmpty())
                    addExamsOnSpinner(classesIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        examsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(!examsIDList.isEmpty())
                    addStudentsOnSpinner(examsIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.checkoff_dialog_bt_1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            dt.putExamGrade(
                                    studentsIDList.get(studentsSpinner.getSelectedItemPosition()),
                                    examsIDList.get(examsSpinner.getSelectedItemPosition()),
                                    gradesSpinner.getSelectedItem().toString()
                            );

                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            Toast.makeText(getActivity(),"Submit Unsuccessful",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.checkoff_dialog_bt_2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                        // User cancelled the dialog
                    }
                });
        builder.setView(v);
        return builder.create();
    }

    private void addClassesOnSpinner(){

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, classesNamesList);
        classesSpinner.setAdapter(dataAdapter);
    }
    private void addExamsOnSpinner(int classID){
        Cursor cursor=dt.getExamsByClass(classID);
        try {
            cursor.moveToFirst();
            examsNamesList.clear();
            examsIDList.clear();
            studentsNamesList.clear();
            studentsIDList.clear();
            studentsSpinner.setAdapter(null);
            for(int i=0;i<cursor.getCount();i++)
            {
                examsNamesList.add(cursor.getString(1));
                examsIDList.add(cursor.getInt(0));
                cursor.moveToNext();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, examsNamesList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        examsSpinner.setAdapter(dataAdapter);
    }
    private void addStudentsOnSpinner(int examID){
        Cursor cursor=dt.getStudentListByExam(examID);
        try {
            cursor.moveToFirst();
            examsNamesList.clear();
            examsIDList.clear();
            studentsNamesList.clear();
            studentsIDList.clear();
            for(int i=0;i<cursor.getCount();i++)
            {
                studentsNamesList.add(cursor.getString(1));
                studentsIDList.add(cursor.getInt(0));
                cursor.moveToNext();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, studentsNamesList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentsSpinner.setAdapter(dataAdapter);
    }
    private void addGradesOnSpinner(){
        ArrayList<String> gradesList = new ArrayList<>();
        gradesList.add("A+");
        gradesList.add("A");
        gradesList.add("A-");
        gradesList.add("B+");
        gradesList.add("B");
        gradesList.add("B-");
        gradesList.add("C+");
        gradesList.add("C");
        gradesList.add("C-");
        gradesList.add("D+");
        gradesList.add("D");
        gradesList.add("F");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gradesList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradesSpinner.setAdapter(dataAdapter);
    }



}
