package com.xcodesystemsinterns.studentmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;

public class CheckOffCustomDialog extends DialogFragment {
    private Spinner assignmentsSpinner, studentsSpinner;
    private Button submitButton,cancelButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return super.onCreateDialog(savedInstanceState);

    }


    /*
     super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.checkoff_dialog);
        assignmentsSpinner = findViewById(R.id.sp_assignments);
        studentsSpinner = findViewById(R.id.sp_students);
        submitButton = findViewById(R.id.bt1_checkoff_dialog);
        cancelButton = findViewById(R.id.bt2_checkoff_dialog);
        addAssignmentsOnSpinner();
        addStudentsOnSpinner();
        submitButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
     */


    private void addAssignmentsOnSpinner(){
        /* List<String> list = new ArrayList<String>();
	list.add("list 1");
	list.add("list 2");
	list.add("list 3");
	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, list);
	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinner2.setAdapter(dataAdapter); */
    }

    private void addStudentsOnSpinner(){
        /* List<String> list = new ArrayList<String>();
	list.add("list 1");
	list.add("list 2");
	list.add("list 3");
	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, list);
	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinner2.setAdapter(dataAdapter); */
    }

}
