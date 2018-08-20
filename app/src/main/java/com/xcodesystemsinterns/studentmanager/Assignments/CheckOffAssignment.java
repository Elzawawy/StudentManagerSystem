package com.xcodesystemsinterns.studentmanager.Assignments;

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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;
import java.util.List;


public class CheckOffAssignment extends DialogFragment {

    private static DataBaseHelper dt;
    private Spinner studentsSpinner;
    private Spinner assignmentsSpinner;
    private static List<String> assignmentsNamesList;
    private static List<Integer> assignmentsIDList;
    private static List<String> studentsNamesList;
    private static List<Integer> studentsIDList;
    public CheckOffAssignment() {
        assignmentsNamesList=new ArrayList<>();
        assignmentsIDList=new ArrayList<>();
        studentsNamesList=new ArrayList<>();
        studentsIDList=new ArrayList<>();
    }

    public static CheckOffAssignment newInstance(Cursor c,Context context) {
        CheckOffAssignment fragment = new CheckOffAssignment();
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++)
            {
                assignmentsIDList.add(c.getInt(0));
                assignmentsNamesList.add(c.getString(1));
                c.moveToNext();
            }

        dt=new DataBaseHelper(context);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       final View v=getActivity().getLayoutInflater().inflate(R.layout.check_off_assignment_dialog, null, false);
        studentsSpinner=v.findViewById(R.id.sp_students);
        assignmentsSpinner=v.findViewById(R.id.sp_assignments);
        setCancelable(false);
       final RatingBar ratingBar=v.findViewById(R.id.rating);
        ratingBar.setIsIndicator(false);
        addAssignmentsOnSpinner();
        assignmentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(!assignmentsNamesList.isEmpty())
                    addStudentsOnSpinner(assignmentsIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.checkoff_dialog_bt_1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            dt.checkAssignmentOff(
                                    studentsIDList.get(studentsSpinner.getSelectedItemPosition()),
                                    assignmentsIDList.get(assignmentsSpinner.getSelectedItemPosition()),
                                    ratingBar.getNumStars()
                            );

                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            Toast.makeText(getActivity(),"Submit Unsuccessful",Toast.LENGTH_LONG);
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

    private void addAssignmentsOnSpinner(){

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, assignmentsNamesList);
        assignmentsSpinner.setAdapter(dataAdapter);
    }
    private void addStudentsOnSpinner(int assignmentID){
        Cursor cursor=dt.getUndoneAssignmentList(assignmentID);
        try {
            cursor.moveToFirst();
            studentsNamesList.clear();
            studentsIDList.clear();
            for(int i=0;i<cursor.getCount();i++)
            {
                studentsIDList.add(cursor.getInt(0));
                studentsNamesList.add(cursor.getString(1));
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



}
