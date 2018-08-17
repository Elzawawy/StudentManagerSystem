package com.xcodesystemsinterns.studentmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;

public class CheckOffCustomDialog extends DialogFragment {
    private Spinner assignmentsSpinner, studentsSpinner;
    private Button submitButton,cancelButton;
    /*
=======
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CheckOffCustomDialog extends DialogFragment {
    private Spinner assignmentsSpinner, studentsSpinner;
    private Button submitButton,cancelButton;
    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
    } */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        android.support.v7.app.AlertDialog.Builder alertDialogBuilderUserInput = new android.support.v7.app.AlertDialog.Builder(getActivity());
        View v=inflater.inflate(R.layout.checkoff_dialog, container, false);
        LayoutInflater l=LayoutInflater.from(getActivity());
        alertDialogBuilderUserInput.setView(v);
        alertDialogBuilderUserInput.create();
        alertDialogBuilderUserInput.show();
        Log.i("Info","traceeeeee");
     //   Spinner spinner=v.findViewById(R.id.sp_assignments);
     //   Spinner spinner2=v.findViewById(R.id.sp_students);
      //  addStudentsOnSpinner(spinner,spinner2);
        return  v;
    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }



/*
>>>>>>> c1e147bf6b87d0ade7853565bb950742ef872baa
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
<<<<<<< HEAD
     */


    private void addAssignmentsOnSpinner(){
        /* List<String> list = new ArrayList<String>();
	list.add("list 1");
	list.add("list 2");
	list.add("list 3");
	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, list);
	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
<<<<<<< HEAD
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
