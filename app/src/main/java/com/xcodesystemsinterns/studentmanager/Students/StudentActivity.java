package com.xcodesystemsinterns.studentmanager.Students;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xcodesystemsinterns.studentmanager.Adapters.CustomAdapter;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.DataModel;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentActivity extends AppCompatActivity {


    private ImageButton imageButton;
    private Button button;
    private DataBaseHelper dbHelper;
    private int index;
    private int studentID;
    private Cursor cursor;
    private ArrayList<Integer> classesID;
    private View dialog_classes_view, dialog_about_view, dialog_removeClasses_view, drop_down_view, dialog_addNoteVeiw;
    private AlertDialog dialog_classes;
    private AlertDialog dialog_about;
    private AlertDialog dialog_removeClasses;
    private Dialog dialog_dropDownMenu;
    private AlertDialog dialog_addNote;
    private String firstName, Email, studentAddress, phoneNumber, id;
    Spinner spinner_Classes;
    ArrayList<String> classes_names;
    ArrayList classes_id;
    ArrayList studentClasses;
    private final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);
        final HashMap<Integer, String> Classes = new HashMap<>();
        classes_names = new ArrayList<String>();
        classes_id = new ArrayList<Integer>();
        //Receiving Student ID from the Prev. Activity.
        Bundle b = getIntent().getExtras();
        if (b != null)
            studentID = b.getInt("id");
        //Instance of the DBHelper
        dbHelper = new DataBaseHelper(getApplicationContext());

        //For Holding Id's of the Classes to get The assignments of each class.

        final Context c = this;
        getStudentInfo();
        getStudentClasses();
        getStudentDoneAssignments();
        getStudentUnDoneAssignments();
        getStudentExams();
        getStudentNote();


        drop_down_view = LayoutInflater.from(c).inflate(R.layout.drop_down_menu, null);
        dialog_about_view = LayoutInflater.from(c).inflate(R.layout.dialoug_about, null);
        dialog_classes_view = LayoutInflater.from(c).inflate(R.layout.dialog_classes, null);
        dialog_removeClasses_view = LayoutInflater.from(c).inflate(R.layout.dialog_remove_class, null);
        dialog_addNoteVeiw = LayoutInflater.from(c).inflate(R.layout.dialog_add_note, null);


        spinner_Classes = dialog_classes_view.findViewById(R.id.spinner);


        //Add Class Dialog
        dialog_classes =  new AlertDialog.Builder(this)
                .setTitle("Add Class")
                .setView(dialog_classes_view)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // ok button
                        int id = (int) classes_id.get(classes_names.indexOf((String) spinner_Classes.getSelectedItem()));

                        if (classesID.contains(id)) {
                            Toast.makeText(getApplicationContext(), "Student Already in this Class", Toast.LENGTH_LONG).show();
                        } else {
                            if (dbHelper.addStudentToClass(studentID, id)) {
                                Toast.makeText(getApplicationContext(), "Student Added to the Class", Toast.LENGTH_LONG).show();
                                getStudentClasses();
                                getStudentDoneAssignments();
                                getStudentUnDoneAssignments();
                                dialog_classes.cancel();

                            } else
                                Toast.makeText(getApplicationContext(), "Adding Faild: No such Class", Toast.LENGTH_LONG).show();

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog_classes.cancel();
                    }
                }).create();



        dialog_classes.setCanceledOnTouchOutside(false);

        final Spinner spinnerRemoveClass = dialog_removeClasses_view.findViewById(R.id.spinner2);
        dialog_removeClasses = new  AlertDialog.Builder(this)
                .setTitle("Bereken")
                .setView(dialog_removeClasses_view)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        int id = classesID.get(studentClasses.indexOf(spinnerRemoveClass.getSelectedItem()));
                        if (classesID.contains(id)) {

                            Toast.makeText(getApplicationContext(), "Removed from Arraylist", Toast.LENGTH_LONG).show();

                            if (dbHelper.removeStudentFromClass(studentID, id)) {
                                Toast.makeText(getApplicationContext(), "Student Removed from Class", Toast.LENGTH_LONG).show();
                                getStudentClasses();

                                getStudentDoneAssignments();
                                getStudentUnDoneAssignments();
                                dialog_removeClasses.cancel();
                            } else
                                Toast.makeText(getApplicationContext(), "Faild Removing Student", Toast.LENGTH_LONG).show();


                        } else {
                            Toast.makeText(getApplicationContext(), "Student is not Registered in this Class", Toast.LENGTH_LONG).show();

                        }


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog_removeClasses.cancel();
                    }
                }).create();




        dialog_removeClasses.setContentView(dialog_removeClasses_view);
        dialog_removeClasses.setCanceledOnTouchOutside(false);


        final EditText t1 = dialog_about_view.findViewById(R.id.nametext);
        final EditText t2 = dialog_about_view.findViewById(R.id.emailtext);
        final EditText t3 = dialog_about_view.findViewById(R.id.phonetext);
        final EditText t4 = dialog_about_view.findViewById(R.id.addresstext);



        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {


                firstName = t1.getText().toString();
            }
        });


        t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {


                Email = t2.getText().toString();
            }
        });


        t3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {


                phoneNumber = t3.getText().toString();
            }
        });


        t4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {


                studentAddress = t4.getText().toString();
            }
        });


        //Dialog Edit Basic Information
        dialog_about = new AlertDialog.Builder(this)
                .setTitle("Edit Basic info")
                .setView(dialog_about_view)
                .setPositiveButton("Submit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Submit button



                        dbHelper.editStudent(studentID, firstName, Email, phoneNumber, studentAddress);
                        getStudentInfo();
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");


                        dialog_about.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel button
                        dialog_about.cancel();
                    }
                }).create();


        dialog_about.setCanceledOnTouchOutside(false);

        dialog_dropDownMenu = new Dialog(this);
        dialog_dropDownMenu.setContentView(drop_down_view);



        //Add Note Dialog
        dialog_addNote = new AlertDialog.Builder(this)
                .setTitle("Add Note")
                .setView(dialog_addNoteVeiw)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    final EditText editText = dialog_addNoteVeiw.findViewById(R.id.et_add_note);


                    public void onClick(DialogInterface dialog, int which) {

                        dbHelper.addStudentNote(studentID,editText.getText().

                                toString());

                        getStudentNote();
                        editText.setText("");
                        dialog_addNote.cancel();

                    }




                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel button
                    }
                }).create();



        dialog_addNote.setCanceledOnTouchOutside(false);



        ///For the Add Class Dialog Buttons and Listeners




//For the Add Class Dialog Cancel Button


        //Remove Class Dialog





        //For the Drop-Down edit menu
        ArrayList<String> items = new ArrayList<String>();
        items.add("Add CLass");
        items.add("Remove Class");
        items.add("Edit Basic Info");

        ArrayAdapter dropdownMenuAdabter = new ArrayAdapter(this, R.layout.activity_listview, R.id.text1, items.toArray());


        ListView listview = drop_down_view.findViewById(R.id.listview);
        listview.setAdapter(dropdownMenuAdabter);
        setListViewHeightBasedOnChildren(listview);
        Window window = dialog_dropDownMenu.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setGravity(Gravity.TOP | Gravity.RIGHT);
        window.setAttributes(wlp);
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_dropDownMenu.show();

            }
        });


        //For the dialog_About




        //DropDown list onClick Listener
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0: //Add Class Choice
                    {
                        classes_names = new ArrayList<>();
                        classes_id = new ArrayList();
                        cursor = dbHelper.getUnregisteredClassesByStudent(studentID);
                        while (cursor.moveToNext()) {
                            classes_names.add(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
                            classes_id.add(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                        }


                        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, classes_names.toArray());
                        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                        spinner_Classes.setAdapter(spinnerAdapter);
                        dialog_classes.show();
                        break;


                    } //End of Case 0

                    case 1: {
                        ArrayAdapter spinnerRemoveClassAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, studentClasses.toArray());
                        spinnerRemoveClassAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                        spinnerRemoveClass.setAdapter(spinnerRemoveClassAdapter);

                        Toast.makeText(getApplicationContext(), (String) "Class Removed", Toast.LENGTH_LONG).show();
                        dialog_removeClasses.show();
                        break;


                    }
                    case 2: {
                        dialog_about.show();
                        break;


                    }


                }
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dbHelper.removeStudent(studentID);
                dialog.dismiss();
                StudentListActivity.getInstance().finish();
                startActivity(new Intent(context, StudentListActivity.class));
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });


        Button addNoteButton = findViewById(R.id.button_addNote);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_addNote.show();
            }
        });


        Button button_removeStudent = findViewById(R.id.button_RemoveStudent);

        button_removeStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alert = builder.create();
                alert.show();


            }
        });



    }

    private void getStudentInfo() {
        cursor = dbHelper.getStudentInfo(studentID);

        if (cursor.getCount() != 0)
            while (!cursor.isLast() && cursor.moveToNext()) {
                int index;

                index = cursor.getColumnIndexOrThrow("Name");
                firstName = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("Email");
                Email = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("_id");
                id = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("PhoneNumber");
                phoneNumber = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("Address");
                studentAddress = cursor.getString(index);

                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setText(firstName);

                TextView tv2 = (TextView) findViewById(R.id.textView6);
                tv2.setText(Email);


                TextView tv4 = (TextView) findViewById(R.id.textView7);
                tv4.setText(phoneNumber);
                TextView tv5 = (TextView) findViewById(R.id.textView5);
                tv5.setText(studentAddress);

            }


    }


    private void getStudentClasses() {
        cursor = dbHelper.getClassesByStudent(studentID);
        studentClasses = new ArrayList<String>();
        classesID = new ArrayList<>();
        if (cursor.getCount() != 0)

            while (!cursor.isLast() && cursor.moveToNext()) {

                index = cursor.getColumnIndexOrThrow("Name");
                studentClasses.add(cursor.getString(index));
                index = cursor.getColumnIndexOrThrow("_id");
                classesID.add(cursor.getInt(index));

            }

        ArrayAdapter arrayAdabter2 = new ArrayAdapter(this, R.layout.activity_listview, studentClasses.toArray());
        ListView listView2 = (ListView) findViewById(R.id.listview2);
        listView2.setAdapter(arrayAdabter2);
        setListViewHeightBasedOnChildren(listView2);


    }

    private void getStudentDoneAssignments() {
        CustomAdapter adabter_studentAss;
        ArrayList<DataModel> model_studentAss = new ArrayList();
        int index;
        String id, name, dueDate, rate;

        cursor = dbHelper.getAssignmentsByStudent(studentID);
        while (cursor.moveToNext()) { //StudnetRate
            index = cursor.getColumnIndexOrThrow("_id");
            id = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow("Name");
            name = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow("StudnetRate");
            rate = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow("DueDate");
            dueDate = "Due Date: " + cursor.getString(index);
            model_studentAss.add(new DataModel(name, dueDate, "Rate: " + rate));

        }

        adabter_studentAss = new CustomAdapter(model_studentAss, getApplicationContext());
        ListView lv_studentAssignment = findViewById(R.id.listview3);
        lv_studentAssignment.setAdapter(adabter_studentAss);
        setListViewHeightBasedOnChildren(lv_studentAssignment);


    }

    private void getStudentUnDoneAssignments() {
        CustomAdapter adabter_studentAss;
        ArrayList<DataModel> model_studentAss = new ArrayList();
        int index;
        String id, name, dueDate, rate;
        cursor = dbHelper.getUndoneAssignmentsByStudent(studentID);
        while (cursor.moveToNext()) { //StudnetRate
            index = cursor.getColumnIndexOrThrow("Name");
            name = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow("DueDate");
            dueDate = "Due Date: " + cursor.getString(index);
            model_studentAss.add(new DataModel(name, dueDate, ""));

        }


        adabter_studentAss =new  CustomAdapter(model_studentAss, getApplicationContext());
        ListView lv_studentAssignment = findViewById(R.id.listview33);
        lv_studentAssignment.setAdapter(adabter_studentAss);

        setListViewHeightBasedOnChildren(lv_studentAssignment);


    }




    private void getStudentExams() {
        //It return a Cursor Object of 3 columns ---> First Column is ID and Second Column is Name and the third column is the student grade.
        cursor = dbHelper.getExamsByStudent(studentID);
        ArrayList<DataModel> model_exams = new ArrayList();
        int index;
        String name, grade, id;


        while (cursor.moveToNext()) {


            index = cursor.getColumnIndexOrThrow("Name");
            name = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("StudentGrade");
            grade = cursor.getString(index);

            model_exams.add(new DataModel(name, "", "Grade: " + grade));

        }

        CustomAdapter adabter_studentExams =new  CustomAdapter(model_exams, getApplicationContext());
        ListView lv_studentAssignment = findViewById(R.id.listview4);
        lv_studentAssignment.setAdapter(adabter_studentExams);
        setListViewHeightBasedOnChildren(lv_studentAssignment);


    }


    void getStudentNote() {
        TextView tv_student_note = findViewById(R.id.tv_student_profile_note);
        Cursor cursor = dbHelper.getStudentNote(studentID);
        ArrayList notes = new ArrayList();
        String note = "";
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            note =(cursor.getString(cursor.getColumnIndexOrThrow("Note")));

        }

        tv_student_note.setText(note);
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}