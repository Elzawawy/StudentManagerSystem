package com.xcodesystemsinterns.studentmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

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

    public static CheckOffAssignment newInstance(List<Assignment> assignmentsList,Context context) {
        CheckOffAssignment fragment = new CheckOffAssignment();
        for(Assignment a:assignmentsList){
            assignmentsNamesList.add(a.getName());
            assignmentsIDList.add(a.getID());
        }
        dt=new DataBaseHelper(context);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       final View v=getActivity().getLayoutInflater().inflate(R.layout.fragment_check_off_assignment, null, false);
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
        Log.i("Info",assignmentID+"");
        Cursor cursor=dt.getUndoneAssignmentList(assignmentID);
        try {
            cursor.moveToFirst();
            studentsNamesList.clear();
            studentsIDList.clear();
            for(int i=0;i<cursor.getCount();i++)
            {
                studentsNamesList.add(cursor.getString(0));
                studentsIDList.add(cursor.getInt(1));
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