package com.xcodesystemsinterns.studentmanager.Classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.Adapters.ClassCursorAdapter;
import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.R;

import java.util.ArrayList;

public class ClassListActivity extends AppCompatActivity {
    ListView listView;
    DataBaseHelper data;
    ArrayList<Integer> classIDs;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new DataBaseHelper(this);
        setTitle("Classes");
        setContentView(R.layout.list_class);
        FloatingActionButton fab = findViewById(R.id.fab_class_list);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=ClassListActivity.this;
                final Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.add_class_menu);

                final Button restart=dialog.findViewById(R.id.addClassButton);
                dialog.setTitle("Add Class");
                dialog.show();
                restart.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = ((TextView)dialog.findViewById(R.id.name)).getText().toString();
                        String description = ((TextView)dialog.findViewById(R.id.description)).getText().toString();
                        if(name.length() != 0 &&description.length() != 0){
                            int id = data.addClass(name,description);
                            Cursor cursor = data.getAllClasses();
                            ClassCursorAdapter classCursorAdapter = new ClassCursorAdapter(context,cursor,listView,"class");
                            listView.setAdapter(classCursorAdapter);

                            classCursorAdapter.notifyDataSetChanged();
                            classIDs = classCursorAdapter.getClassIDs();
                            dialog.dismiss();
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                        }
                        else {

                            showDialog(1);
                        }
                    }
                });
            }
        });
        listView = findViewById(R.id.listView);
        //createClassObject(data.getAllClasses());
        Cursor cursor = data.getAllClasses();
        ClassCursorAdapter classCursorAdapter = new ClassCursorAdapter(this, cursor ,listView,"class");
        listView.setAdapter(classCursorAdapter);
        classIDs = classCursorAdapter.getClassIDs();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // put you activity's name instead of NewActivity
                Intent intent = new Intent(getBaseContext(),ClassActivity.class);
                intent.putExtra("ClassID", classIDs.get(i));




                startActivity(intent);
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case 1:
                AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
                errorDialog.setTitle("Error");
                errorDialog.setMessage("Name and Description can't be empty!");
                errorDialog.setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog errorAlert = errorDialog.create();
                return errorAlert;

            default:
                break;
        }
        return dialog;
    }

}