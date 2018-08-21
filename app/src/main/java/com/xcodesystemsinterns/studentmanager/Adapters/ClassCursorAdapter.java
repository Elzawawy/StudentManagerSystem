package com.xcodesystemsinterns.studentmanager.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;


public class ClassCursorAdapter extends CursorAdapter implements View.OnClickListener,DialogInterface.OnClickListener{
    private String viewType;
    private DataBaseHelper dataBaseHelper;
    private ListView listView;
    private Context context;
    private View view;
    private int classID;
    private ArrayList<Integer> assignmentIDs;
    private ArrayList<Integer> examIDs;
    private ArrayList<Integer> studentIDs;
    private ArrayList<Integer> classIDs;

    //Context for controlling main layout in hand.
    //Cursor for data source.
    //classID for usage in removal of objects.
    //ListView to get position of object to be removed.
    //viewType for usage as a flag to know type of list in hand.
    public ClassCursorAdapter(Context context, Cursor c,ListView listView, String viewType) {
        super(context, c,0);
        dataBaseHelper = new DataBaseHelper(context);
        this.listView = listView;
        this.viewType = viewType;
        this.context = context;
        assignmentIDs = new ArrayList<>();
        examIDs = new ArrayList<>();
        studentIDs = new ArrayList<>();
        classIDs = new ArrayList<>();
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public ArrayList<Integer> getClassIDs(){
        return this.classIDs;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textView_1= view.findViewById(R.id.tv1_item_class);
        TextView textView_2= view.findViewById(R.id.tv2_item_class);
        ImageView imageView = view.findViewById(R.id.iv_item_class);
        String tv1_text="", tv2_text="";
        switch(viewType)
        {
            case "student" :
                studentIDs.add(cursor.getInt(cursor.getColumnIndex("_id")));
                Cursor intermediateCursor = dataBaseHelper.getStudentInfo(cursor.getInt(cursor.getColumnIndex("_id")));
                intermediateCursor.moveToFirst();
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = intermediateCursor.getString(intermediateCursor.getColumnIndexOrThrow("Email"));
                break;

            case "class" :
                classIDs.add(cursor.getInt(cursor.getColumnIndex("_id")));
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = cursor.getString(cursor.getColumnIndex("Description"));
                break;

            case "assignment" :
                assignmentIDs.add(cursor.getInt(cursor.getColumnIndex("_id")));
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = cursor.getString(cursor.getColumnIndex("DueDate"));
                break;

            case "exam" :
                examIDs.add(cursor.getInt(cursor.getColumnIndex("_id")));
                tv1_text = cursor.getString(cursor.getColumnIndex("Name"));
                tv2_text = cursor.getString(cursor.getColumnIndex("Date"));
                break;
        }
        textView_1.setText(tv1_text);
        textView_2.setText(tv2_text);
        imageView.setOnClickListener(this);
    }

    //implemented for View.OnClickListener when the image remove view is clicked on.
    @Override
    public void onClick(View view) {
        this.view = view;
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();
        switch(viewType)
        {
            case "student" :
                alertDialog.setTitle("Delete Student");
                alertDialog.setMessage("You are about to remove a student from class. Are you sure ?");
                alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", this);
                alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case "class" :
                alertDialog.setTitle("Delete Class");
                alertDialog.setMessage("You are about to delete this class. Are you sure ?");
                alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", this);
                alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case "assignment" :
                alertDialog.setTitle("Delete Assignment");
                alertDialog.setMessage("You are about to remove an assignment from this class. Are you sure ?");
                alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", this);
                alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case "exam" :
                alertDialog = new android.app.AlertDialog.Builder(context).create();
                alertDialog.setTitle("Delete Exam");
                alertDialog.setMessage("You are about to remove an Exam from this class. Are you sure ?");
                alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "YES", this);
                alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
        }
    }

    //implemented for DialogInterface.OnClickListener when the YES button in the dialog is clicked on.
    @Override
    public void onClick(DialogInterface dialog, int which) {
        int listViewPosition = listView.getPositionForView(view);
        ClassCursorAdapter newAdapter;
        switch(viewType)
        {
            case "student" :
                dataBaseHelper.removeStudentFromClass(studentIDs.get(listViewPosition),classID);
                Cursor newStudents = dataBaseHelper.getStudentsByClass(classID);
                newAdapter = new ClassCursorAdapter(context,newStudents,listView,"student");
                newAdapter.setClassID(classID);
                listView.setAdapter(newAdapter);
                notifyDataSetChanged();
                break;

            case "class" :
                dataBaseHelper.removeClass(classIDs.get(listViewPosition));
                Cursor newClasses = dataBaseHelper.getClassList();
                newAdapter = new ClassCursorAdapter(context,newClasses,listView,"class");
                listView.setAdapter(newAdapter);
                notifyDataSetChanged();
                break;

            case "assignment" :
                dataBaseHelper.removeAssignment(assignmentIDs.get(listViewPosition));
                Cursor newAssignments = dataBaseHelper.getAssignmentsByClass(classID);
                newAdapter = new ClassCursorAdapter(context,newAssignments,listView,"assignment");
                listView.setAdapter(newAdapter);
                notifyDataSetChanged();
                break;

            case "exam" :
                dataBaseHelper.removeExam(examIDs.get(listViewPosition));
                Cursor newExams = dataBaseHelper.getExamsByClass(classID);
                newAdapter = new ClassCursorAdapter(context,newExams,listView,"exam");
                listView.setAdapter(newAdapter);
                notifyDataSetChanged();
                break;
        }
    }


}
