package com.xcodesystemsinterns.studentmanager.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.Adapters.gridAdapter;
import com.xcodesystemsinterns.studentmanager.Assignments.AssignmentActivity;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.Exams.ExamListActivity;
import com.xcodesystemsinterns.studentmanager.HomeActivity;
import com.xcodesystemsinterns.studentmanager.R;
import com.xcodesystemsinterns.studentmanager.Students.StudentActivity;

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
    ListView lv;

    //To create Objects
    ArrayList<students> Students;
    ArrayList<assignments> Assignments;
    ArrayList<exams> Exams;


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
        int ID;
        students(String firstName, String email,int ID) {
            this.firstName = firstName;
            this.email = email;
            this.ID=ID;
        }
    }

    class assignments {
        String assignName;
        int IdA;
        assignments(String assignName, int IdA) {
            this.assignName = assignName;
            this.IdA= IdA;
        }
    }

    class exams {
        String examName;
        int IdE;
        exams(String examName, int IdE) {
            this.examName = examName;
            this.IdE = IdE;
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
        gridAdapter gri = new gridAdapter(gridMainActivity.this, iconName, photos);
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
                                                Students.add(new students(studentName, studentEmail,idS));
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
                        ShowStudents("Students");
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
                                        Assignments.add(new assignments(assignmentName,idA));
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
                        ShowAssign("Assignments");
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
                                                Exams.add(new exams(examName,idE));
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
                        ShowExams("Exams");
                        break;
                }
            }
        });

    }

    //Student Management
    class gridStudentAdapter extends ArrayAdapter<students>
    {
        public gridStudentAdapter(Activity context, ArrayList<students> Students)
        { super(context,0, Students); }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            //students currentStudent = getItem(position);
            View listItemView= convertView;
            if(listItemView == null)
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_student,parent,false);
            TextView name= (TextView) listItemView.findViewById(R.id.tv_item_student_name);
            name.setText(Students.get(position).firstName);
            return listItemView;
        }
    }
    public void ShowStudents(final String title) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gridMainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.student_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(title);
        lv = convertView.findViewById(R.id.listview_student);
        gridStudentAdapter adapter = new gridStudentAdapter(this,Students);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(),StudentActivity.class);
                intent.putExtra("id",Students.get(i).ID);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        alertDialog.show();

    }

    public void studentRemove(final View view)
    {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(gridMainActivity.this).create();
        alertDialog.setTitle("Delete Student");
        alertDialog.setMessage("You are about to remove a student. Are you sure ?");
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int i = lv.getPositionForView(view);
                int classId = Students.get(i).ID;
                Students.remove(i);
                data.removeStudentFromClass(classId,classID);
                lv.setAdapter(new gridStudentAdapter(gridMainActivity.this,Students));
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


    //To manage exam, adapter, showing and removing
    class gridExamAdapter extends ArrayAdapter<exams>
    {
        public gridExamAdapter(Activity context, ArrayList<exams> Exams)
        { super(context,0, Exams); }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            //exams currentExam = getItem(position);
            View listItemView= convertView;
            if(listItemView == null)
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.exam_item,parent,false);
            TextView name= (TextView) listItemView.findViewById(R.id.tv_item_exam_name);
            name.setText(Exams.get(position).examName);
            return listItemView;

        }
    }
    public void ShowExams(final String title) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gridMainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.exam_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(title);
        lv = convertView.findViewById(R.id.listview_exam);
        gridExamAdapter adapter = new gridExamAdapter(this,Exams);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(),ExamListActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        alertDialog.show();
    }
    public void examRemove(final View view)
    {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(gridMainActivity.this).create();
        alertDialog.setTitle("Delete Exam");
        alertDialog.setMessage("You are about to remove an Exam. Are you sure ?");
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int i = lv.getPositionForView(view);
                int examId = Exams.get(i).IdE;
                Exams.remove(i);
                data.removeExam(examId);
                lv.setAdapter(new gridExamAdapter(gridMainActivity.this,Exams));
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


    //Assign Management
    class gridAssignAdapter extends ArrayAdapter<assignments>
    {
        public gridAssignAdapter(Activity context, ArrayList<assignments> Assignments)
        { super(context,0, Assignments); }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            //assignments currentAssign = getItem(position);
            View listItemView= convertView;
            if(listItemView == null)
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.assign_item,parent,false);
            TextView name= listItemView.findViewById(R.id.tv_item_assign_name);
            name.setText(Assignments.get(position).assignName);
            return listItemView;
        }
    }
    public void ShowAssign(final String title) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(gridMainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.assign_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(title);
        lv = convertView.findViewById(R.id.listview_assign);
        gridAssignAdapter adapter = new gridAssignAdapter(this,Assignments);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(),AssignmentActivity.class);
                intent.putExtra("ID",Assignments.get(i).IdA);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
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
                data.removeAssignment(assigId);
                lv.setAdapter(new gridAssignAdapter(gridMainActivity.this,Assignments));
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
                         Students.add(new students(name,email,cursorS.getInt(idIndex)));
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
                idIndex = cursorE.getColumnIndex("_id");
                nameIndex = cursorE.getColumnIndex("Name");
                examName = cursorE.getString(nameIndex);
                Exams.add(new exams(examName,cursorE.getInt(idIndex)));
                cursorE.moveToNext();
            }
        } catch (Exception e) {e.printStackTrace(); }


        //Assign info
        try {
            cursorA.moveToFirst();
            for (int k = 0; k < cursorA.getCount(); k++) {
                idIndex= cursorA.getColumnIndex("_id");
                nameIndex = cursorA.getColumnIndex("Name");
                assignName= cursorA.getString(nameIndex);
                Assignments.add(new assignments(assignName,cursorA.getInt(idIndex)));
                cursorA.moveToNext();
            }
        } catch (Exception e) {e.printStackTrace(); }

    }

}