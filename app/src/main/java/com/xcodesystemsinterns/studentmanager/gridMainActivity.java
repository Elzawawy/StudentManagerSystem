package com.xcodesystemsinterns.studentmanager.InsideClass;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.xcodesystemsinterns.studentmanager.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;

public class gridMainActivity extends AppCompatActivity {

    GridView griView;
    final Context c = this;
    DataBaseHelper data;
   // ArrayList<students> studentsList;
    String studentName,studentEmail, assignmentName, examName,assignmentDate,assignmentDescription;

    String[] iconName = {
            "Add Student",
            "Student list",
            "Add Assignment",
            "Assignments",
            "Add Exam",
            "Display Exams"
    };

    int[] photos = {
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

    /*class students {
        String firstName;
        String lastName;
        String email;
        long id;

        students(long id, String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.id = id;
        }
    }*/

     long classID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Class Managemenet");
        setContentView(R.layout.activity_main_grid);
        displayLists(data.getAllStudents(),data.getAssignmentsByClass((int)classID),data.getExamsByClass((int)classID));
        data = new DataBaseHelper(this);

        //Added
        classID=getIntent().getLongExtra("ClassID", Long.valueOf(0));

        //Set the display as gridView
        griView = (GridView) findViewById(R.id.grid);
        gridAdapter gri = new gridAdapter(this, iconName, photos);
        griView.setAdapter(gri);


        griView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                AlertDialog alertDialogAndroid;
                View mView;
                EditText userInputDialogEditText;

                switch (position) {

                    //to add student
                    case 0:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_student, null);
                        alertDialogBuilderUserInput.setView(mView);

                        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                        studentName=userInputDialogEditText.getText().toString();
                        studentEmail=((EditText) mView.findViewById(R.id.userEmailDialog)).getText().toString();

                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        data.addStudent(studentName,studentEmail);
                                        studentNames.add(studentName);
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
                        ShowAlertDialogWithListview("Students", studentNames);
                        break;

                    //to add assignment
                    case 2:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_assignment, null);
                        alertDialogBuilderUserInput.setView(mView);

                        userInputDialogEditText = (EditText) mView.findViewById(R.id.assignNameDialog);
                        assignmentName=userInputDialogEditText.getText().toString();
                        assignmentDate=((EditText) mView.findViewById(R.id.dueDateDialog)).getText().toString();
                        assignmentDescription=((EditText) mView.findViewById(R.id.descriptionDialog)).getText().toString();
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        data.addAssignment(assignmentName,assignmentDate,assignmentDescription,(int)classID);
                                        assignmentNames.add(assignmentName);
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
                        ShowAlertDialogWithListview("Assignments", assignmentNames);
                        break;

                    //To add Exam
                    case 4:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_exam, null);
                        alertDialogBuilderUserInput.setView(mView);

                        userInputDialogEditText = (EditText) mView.findViewById(R.id.examNameDialog);
                        examName=userInputDialogEditText.getText().toString();
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        data.addExam(examName,(int)classID);
                                        examsNames.add(examName);
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
                        ShowAlertDialogWithListview("Exams", examsNames);
                        break;
                }
            }
        });

    }


    //This method help showing the listView in a dialog
    public void ShowAlertDialogWithListview(final String title, ArrayList<String> theList) {
        //Create sequence of items
        final CharSequence[] displayList = theList.toArray(new String[theList.size()]);

        //Create dialog and set title and list of items
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("" + title);
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

    public void displayLists(Cursor cursorS, Cursor cursorA,Cursor cursorE)
    {
        try {
            cursorS.moveToFirst();
            for (int i = 0; i < cursorS.getCount(); i++) {
                //int idIndex = cursorS.getColumnIndexOrThrow("StudentID");
                int nameIndex = cursorS.getColumnIndexOrThrow("Name");
                //int emailIndex = cursorS.getColumnIndexOrThrow("Email");
                // long id = cursorS.getLong(idIndex);
                String name = cursorS.getString(nameIndex);
                //name.split("");
                // String email = cursorS.getString(emailIndex);
                //studentsList.add(new students(id, name,email));
                studentNames.add(name);
                cursorS.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cursorA.moveToFirst();
            for (int j = 0; j < cursorA.getCount(); j++) {
                int nameIndex = cursorA.getColumnIndexOrThrow("Name");
                String name = cursorA.getString(nameIndex);
                assignmentNames.add(name);
                cursorA.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cursorE.moveToFirst();
            for (int i = 0; i < cursorE.getCount(); i++) {
                int nameIndex = cursorE.getColumnIndexOrThrow("Name");
                String name = cursorE.getString(nameIndex);
                examsNames.add(name);
                cursorE.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
