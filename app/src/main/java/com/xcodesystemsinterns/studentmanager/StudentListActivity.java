package com.xcodesystemsinterns.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Omar on 8/4/2018.
 */

public class StudentListActivity extends AppCompatActivity {

    DataBaseHelper dbHelper;
    ArrayAdapter adabter ;
    ArrayList<DataModel> myList = new ArrayList<DataModel>();
    Cursor cursor;
    DataModel entry;
    ListView studentsListView;




    @Override
    public  void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);

        dbHelper = new DataBaseHelper(getApplicationContext());

        SQLiteDatabase db=openOrCreateDatabase("StudentManager.db",MODE_PRIVATE,null);

        dbHelper.onUpgrade(db,0,0);//For testing, Delete previous Database before testing if exist


        addData();
        getStudents(this);







//Dummy Data for testing







    }

    void getStudents(Context context){

        DataBaseHelper  dbHelper = new DataBaseHelper(context);
        cursor = dbHelper.getAllStudents();
        String name,email,id;
        while(!cursor.isLast()&& cursor.moveToNext())
        {
            int index;
            index = cursor.getColumnIndexOrThrow("Name");
            name =cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("Email");
            email = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("_id");
            id = cursor.getString(index);

            myList.add(new DataModel(name,email,id));
        }

        CustomAdapter adabter = new CustomAdapter(myList,context);

        studentsListView = (ListView)findViewById(R.id.listview);
        studentsListView.setAdapter(adabter);

        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                entry= (DataModel) parent.getAdapter().getItem(position);
                Intent intent = new Intent(StudentListActivity.this, MainActivity.class);
                intent.putExtra("id", entry.getVersion_number());
                startActivity(intent);
            }
        });

    }

    void addData()
    {



        dbHelper.addClass("English","NONONONO");
        dbHelper.addClass("Arabic","NONONONO");
        dbHelper.addClass("Math","NONONONO");
        dbHelper.addClass("Physics","NONONONO");
        dbHelper.addClass("Deutsch","NONONONO");

        dbHelper.addStudent("OMAR","Omar.Email@hotmail.com","01010101","sdf3df34");
        dbHelper.addStudent("OMAR2","Omar.Email@hotmail.com","01010101","sdf3df34");
        dbHelper.addStudent("3mr","Amr.ZZEW@HHHH.com","01010101","sdf3df34");
        dbHelper.addStudent("OMAR3","Omar.Email@hotmail.com","01010101","sdf3df34");
        dbHelper.addStudent("3mr2","Amr.ZZEW@HHHH.com","01010101","sdf3df34");
        dbHelper.addStudent("OMAR4","Omar.Email@hotmail.com","01010101","sdf3df34");
        dbHelper.addStudent("3mr3","Amr.ZZEW@HHHH.com","01010101","sdf3df34");
        dbHelper.addStudent("Omar Hussein Elshrief","Omar.Email@hotmail.com","01010101","sdf3df34");
        dbHelper.addStudent("Amr Ashraf ELZewa","Amr.ElZew@hotmail.com","01010101","sdf3df34");
        dbHelper.addStudent("Rami 3bdAllah Khfagi","Rami.Khfagi@HHHH.com","01010101","sdf3df34");



        dbHelper.addStudentToClass(1,1);
        dbHelper.addStudentToClass(1,2);

        dbHelper.addAssignment("Ass1","1/1/2001","Ass1",1);
        dbHelper.addAssignment("Ass2","1/1/2001","Ass2",1);
        dbHelper.addAssignment("Ass3","1/1/2001","Ass3",1);
        dbHelper.addAssignment("Ass1","1/1/2001","Ass1",2);
        dbHelper.addAssignment("Ass1","1/1/2001","Ass1",3);
        dbHelper.addAssignment("Ass1","1/1/2001","Ass2",3);

    }




}
