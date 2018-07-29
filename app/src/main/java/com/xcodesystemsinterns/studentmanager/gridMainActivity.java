package com.xcodesystemsinterns.studentmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class gridMainActivity extends AppCompatActivity {

    GridView griView;
    final Context c = this;

    String [] iconName = {
            "Add Student",
            "Student list",
            "Add Assignment",
            "Assignments",
            "Add Exam",
            "Display Exams"
    };

    int [] photos = {
            R.drawable.adduser,
            R.drawable.studentlist,
            R.drawable.addassignment,
            R.drawable.assignment,
            R.drawable.exam,
            R.drawable.test

    };

    //Here this arrayList should be filled from the database
    ArrayList<String> studentNames = new ArrayList<String>();
    ArrayList<String> assignmentNames = new ArrayList<String>();
    ArrayList<String> examsNames = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        //Set the display as gridView
        griView = (GridView) findViewById(R.id.grid);
        gridAdapter gri = new gridAdapter(this, iconName, photos);
        griView.setAdapter(gri);

        /*For testing
        studentNames.add("Mohamed");
        studentNames.add("Ashraf");
        studentNames.add("Nour");
        assignmentNames.add("Assign 1");
        assignmentNames.add("Assign 2");
        assignmentNames.add("Assign 3");
        examsNames.add("Exam 'A'");
        examsNames.add("Exam 'B'");
        examsNames.add("Exam 'C'");*/


        griView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater layoutInflaterAndroid= LayoutInflater.from(c);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                AlertDialog alertDialogAndroid;
                View mView;
                final EditText userInputDialogEditText;

                switch (position) {

                    //to add student
                    case 0:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_student, null);
                        alertDialogBuilderUserInput.setView(mView);

                        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        // Here we should open database and add info the user just entered.
                                        //aka add to database function
                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialogBox.cancel();
                                            }
                                        });

                        //Create the dialog
                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.show();
                        break;

                        //Case 1: for students list display
                        case 1:
                            ShowAlertDialogWithListview("Students",studentNames);
                            break;

                     //to add assignment
                    case 2:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_assignment, null);
                        alertDialogBuilderUserInput.setView(mView);

                        userInputDialogEditText = (EditText) mView.findViewById(R.id.assignNameDialog);
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        // Add info to database function
                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialogBox.cancel();
                                            }
                                        });

                        //Create the dialog
                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.show();
                        break;

                        //for assign. list
                    case 3:
                        ShowAlertDialogWithListview("Assignments",assignmentNames);
                        break;

                        //To add Exam
                    case 4:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_exam, null);
                        alertDialogBuilderUserInput.setView(mView);

                        userInputDialogEditText = (EditText) mView.findViewById(R.id.examNameDialog);
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        // add info to database function
                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialogBox.cancel();
                                            }
                                        });

                        //Show the dialog
                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.show();
                        break;

                    case 5:
                        ShowAlertDialogWithListview("Exams",examsNames);
                        break;
                }
            }
        });

    }



    //This method help showing the listView in a dialog
    public void ShowAlertDialogWithListview(final String title, ArrayList<String> theList)
    {
        //Create sequence of items
        final CharSequence[] displayList = theList.toArray(new String[theList.size()]);

        //Create dialog and set title and list of items
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(""+title);
        dialogBuilder.setItems(displayList, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                //To shift to another activity
                Intent i;

                /*if(title.equalsIgnoreCase("Students"))
                {
                    i = new Intent(this,activity_name_ofStudent);
                    startActivity(i);
                }

                else if (title.equalsIgnoreCase("Assignments"))
                {
                    i= new Intent(this, activity_name_ofAssignment);
                    startActivity(i);
                }

                else
                {
                    i= new Intent(this, activity_name_ofExams);
                    startActivity(i);
                }*/

            }
        });

        //The action that happen when the user click on cancel
       dialogBuilder.setNegativeButton("Cancel",
               new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialogBox, int id) {
                       dialogBox.cancel();
                   }
               });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }

}
