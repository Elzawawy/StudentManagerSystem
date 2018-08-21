package com.xcodesystemsinterns.studentmanager.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;
import com.xcodesystemsinterns.studentmanager.Students.StudentListActivity;

import java.util.ArrayList;

public class StudentCursorAdapter extends CursorAdapter implements View.OnClickListener {
    Context context;
    Integer studentID;
    ListView listView;
    ArrayList<Integer> studentIDs;
    public StudentCursorAdapter(Context context, Cursor c,ListView listView)
    {
        super(context, c,0);
        this.context = context;
        this.listView = listView;
        studentIDs = new ArrayList<>();

    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tv_Name = view.findViewById(R.id.tv_item_student_name);
        TextView tv_Email = view.findViewById(R.id.tv_item_student_email);
        ImageView removeImageView = view.findViewById(R.id.iv_item_student);
        // Extract properties from cursor
        studentIDs.add(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
        // Populate fields with extracted properties
        tv_Name.setText(name);
        tv_Email.setText(email);
        removeImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            final int position = listView.getPositionForView(v);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Confirm");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                    dataBaseHelper.DropStudentFromSystem(studentIDs.get(position));
                    Cursor cursor = dataBaseHelper.getAllStudents();
                    listView.setAdapter(new StudentCursorAdapter(context,cursor,listView));
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

    }

    public void refresh(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        Cursor cursor = dataBaseHelper.getAllStudents();
        listView.setAdapter(new StudentCursorAdapter(context,cursor,listView));
        notifyDataSetChanged();
    }
}
