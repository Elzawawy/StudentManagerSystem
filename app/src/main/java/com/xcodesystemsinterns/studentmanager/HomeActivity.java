package com.xcodesystemsinterns.studentmanager;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataBaseHelper = new DataBaseHelper(this);
        //boolean result = dataBaseHelper.removeClass(3);
        //Cursor cursor =dataBaseHelper.getClassList();
        //Log.d("myTAG",String.valueOf(cursor.getCount()));
        //Log.d("myTAG",String.valueOf(result));
    }
}
