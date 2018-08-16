package com.xcodesystemsinterns.studentmanager;

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

import java.util.ArrayList;

public class gridMainActivity extends AppCompatActivity {

    GridView griView;
    final Context c = this;
    DataBaseHelper data ;

    String studentName, studentEmail, studentPhone, studentAddress,
            assignmentName, assignmentDate, assignmentDescription,
             examName,examDate;

    View mView;
    int classID,idS,idE,idA;
    LayoutInflater layoutInflaterAndroid;

    //To create Objects
    ArrayList<students> Students;
    ArrayList<assignments> Assignments;
    ArrayList<exams> Exams;

    //To display
    ArrayList<String> student_array_list;
    ArrayList<String> assignment_array_list;
    ArrayList<String> exams_array_list;


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


    //In order to save and upload from database
    class students {
        String firstName;
        String email;
        students(String firstName, String email) {
            this.firstName = firstName;
            this.email = email;
        }
    }

    class assignments {
        String assignName;
        assignments(String assignName) {
            this.assignName = assignName;
        }
    }

    class exams {
        String examName;
        exams(String examName) {
            this.examName = examName;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Class Managemenet");
        setContentView(R.layout.activity_main_grid);
        data = new DataBaseHelper(c);
        layoutInflaterAndroid = LayoutInflater.from(c);

        //To know which class
        classID= getIntent().getIntExtra("ClassID",0);

        //ArrayList of objects
        Students = new ArrayList<>();
        Assignments = new ArrayList<>();
        Exams = new ArrayList<>();

        createObjects(data.getStudentsByClass(classID), data.getAssignmentsByClass(classID), data.getExamsByClass(classID));

        //Set the display as gridView
        griView = findViewById(R.id.grid);
        gridAdapter gri = new gridAdapter(this, iconName, photos);
        griView.setAdapter(gri);
        griView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                AlertDialog alertDialogAndroid;
                switch (position) {
                    //to add student
                    case 0:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_student, null);
                        alertDialogBuilderUserInput.setView(mView);
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        studentEmail = ((EditText) mView.findViewById(R.id.userEmailDialog)).getText().toString();
                                        studentName = ((EditText) mView.findViewById(R.id.userInputDialog)).getText().toString();
                                        studentName = studentName +" "+((EditText) mView.findViewById(R.id.userInputLastDialog)).getText().toString();
                                        studentPhone = ((EditText) mView.findViewById(R.id.userPhoneDialog)).getText().toString();
                                        studentAddress = ((EditText) mView.findViewById(R.id.userAddressDialog)).getText().toString();
                                        if (studentName.length() == 0 || studentEmail.length() == 0) {
                                            android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(c);
                                            errorDialog.setTitle("Error");
                                            errorDialog.setMessage("All field are required");
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
                                        else
                                            {
                                                idS = data.addStudent(studentName, studentEmail, studentPhone ,studentAddress);
                                                Boolean a = data.addStudentToClass(idS, classID);
                                                if(!a)
                                                {
                                                    android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(c);
                                                    errorDialog.setTitle("Error");
                                                    errorDialog.setMessage("Couldn't add student");
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
                                                Students.add(new students(studentName, studentEmail));
                                            }
                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialogBox.cancel();
                                            }
                                        });
                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.show();
                        break;

                    //students list
                    case 1:
                        transferStudents(Students);
                        break;

                    //to add assignment
                    case 2:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_assignment, null);
                        alertDialogBuilderUserInput.setView(mView);
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        assignmentName = ((EditText) mView.findViewById(R.id.assignNameDialog)).getText().toString();
                                        assignmentDescription = ((EditText) mView.findViewById(R.id.descriptionDialog)).getText().toString();
                                        assignmentDate = ((EditText) mView.findViewById(R.id.assignDueDateDialog)).getText().toString();
                                        if (assignmentDate.length() == 0 || assignmentDescription.length() == 0 || assignmentName.length() == 0) {
                                            android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(c);
                                            errorDialog.setTitle("Error");
                                            errorDialog.setMessage("All field are required");
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
                                        else
                                        {
                                        idA = data.addAssignment(assignmentName, assignmentDate, assignmentDescription, classID);
                                        Assignments.add(new assignments(assignmentName /*, assignmentDescription, assignmentDate*/));
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialogBox.cancel();
                                            }
                                        });
                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.show();
                        break;

                    //for assign list
                    case 3:
                        transferAssignment(Assignments);
                        break;

                    //To add Exam
                    case 4:
                        mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_exam, null);
                        alertDialogBuilderUserInput.setView(mView);
                        alertDialogBuilderUserInput
                                .setCancelable(false)
                                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        examName = ((EditText) mView.findViewById(R.id.examNameDialog)).getText().toString();
                                        examDate = ((EditText) mView.findViewById(R.id.examDateDialog)).getText().toString();
                                        if (examName.length() == 0 ||examDate.length()==0) {
                                            android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(c);
                                            errorDialog.setTitle("Error");
                                            errorDialog.setMessage("All field are required");
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
                                        else
                                            {
                                                idE = data.addExam(examName,examDate,classID);
                                                Exams.add(new exams(examName));
                                            }
                                    }
                                })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialogBox.cancel();
                                            }
                                        });
                        alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.show();
                        break;

                    case 5:
                        transferExams(Exams);
                        break;
                }
            }
        });

    }

    public void transferStudents (ArrayList<students> std)
    {
        int i;
        student_array_list= new ArrayList<String>();
        for(i=0; i<std.size(); i++)
        {
            student_array_list.add(std.get(i).firstName);
        }
        ShowAlertDialogWithListview("Students", student_array_list);
    }

    public void transferAssignment (ArrayList<assignments> asg)
    {
        int i;
        assignment_array_list= new ArrayList<String>();
        for(i=0; i<asg.size(); i++)
        {
            assignment_array_list.add(asg.get(i).assignName);
        }
        ShowAlertDialogWithListview("Assignment", assignment_array_list);
    }

    public void transferExams (ArrayList<exams> exm)
    {
        int i;
        exams_array_list= new ArrayList<String>();
        for(i=0; i<exm.size(); i++)
        {
            exams_array_list.add(exm.get(i).examName);
        }
        ShowAlertDialogWithListview("Exams", exams_array_list);
    }

    //This method help showing the listView in a dialog
    public void ShowAlertDialogWithListview(final String title, ArrayList<String> theList) {

        final CharSequence[] displayList = theList.toArray(new String[theList.size()]);
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
                }
                */

            }
        });

        //The action that happen when the user click on cancel
        dialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        AlertDialog alertDialogObject = dialogBuilder.create();
        alertDialogObject.show();
    }


    //First we need to create class objects:
    public void createObjects(Cursor cursorS, Cursor cursorA, Cursor cursorE) {
        int idIndex, nameIndex, emailIndex;
        String name, email, assignName;

        //get Student Info
        try {
             cursorS.moveToFirst();
            for (int i = 0; i < cursorS.getCount(); i++) {
                idIndex = cursorS.getColumnIndex("_id");
                nameIndex = cursorS.getColumnIndex("Name");
                name = cursorS.getString(nameIndex);
                 Cursor cs = data.getStudentInfo(cursorS.getInt(idIndex));
                 try{
                     cs.moveToFirst();
                     for(int j=0;j< cs.getCount();j++)
                     {
                         emailIndex= cs.getColumnIndex("Email");
                         email=cs.getString(emailIndex);
                         Students.add(new students(name,email));
                         cs.moveToNext();
                     }
                 } catch (Exception e)
                 { e.printStackTrace(); }
                cursorS.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get Exam Info
        try {
            cursorE.moveToFirst();
            for (int j = 0; j < cursorE.getCount(); j++) {
                nameIndex = cursorE.getColumnIndex("Name");
                examName = cursorE.getString(nameIndex);
                Exams.add(new exams(examName));
                cursorE.moveToNext();
            }
        } catch (Exception e) {e.printStackTrace(); }


        //Assign info
        try {
            cursorA.moveToFirst();
            for (int k = 0; k < cursorA.getCount(); k++) {
                nameIndex = cursorA.getColumnIndex("Name");
                assignName= cursorA.getString(nameIndex);
                Assignments.add(new assignments(assignName));
                cursorA.moveToNext();
            }
        } catch (Exception e) {e.printStackTrace(); }

    }}