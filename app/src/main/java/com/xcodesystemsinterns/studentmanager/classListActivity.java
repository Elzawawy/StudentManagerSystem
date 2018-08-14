package com.xcodesystemsinterns.studentmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


class myAdapter extends BaseAdapter{
    Context context;
    ArrayList<classes> Classes;

    public myAdapter(Context context, ArrayList<com.xcodesystemsinterns.studentmanager.classes> classes) {
        this.context = context;
        this.Classes = classes;
    }


    @Override
    public int getCount() {
        return Classes.size();
    }

    @Override
    public Object getItem(int i) {
        return Classes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = (View) inflater.inflate(R.layout.class_list_view,null);
        }
        TextView name = view.findViewById(R.id.name);
        TextView description = view.findViewById(R.id.description);
        name.setText(Classes.get(i).name);
        description.setText(Classes.get(i).description);
        return view;
    }
}


class classes {
    String name;
    String description;
    int id;
    classes(int id, String name, String description){
        this.name = name;
        this.description = description;
        this.id = id;
    }
}


public class classListActivity extends AppCompatActivity {

    ArrayList<classes> Classes;
    ListView listView;
    DataBaseHelper data;

    public void createClassObject(Cursor cursor){
        Classes = new ArrayList<>();
        try {
            cursor.moveToFirst();

            for(int i=0;i<cursor.getCount();i++)
            {
                int idIndex = cursor.getColumnIndexOrThrow("ClassID");
                int nameIndex = cursor.getColumnIndexOrThrow("Name");
                int descriptionIndex = cursor.getColumnIndexOrThrow("Description");
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String description = cursor.getString(descriptionIndex);
                Classes.add(new classes(id,name,description));
                cursor.moveToNext();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new DataBaseHelper(this);
        setTitle("Classes");
        setContentView(R.layout.list_class);
        listView = findViewById(R.id.listView);
        createClassObject(data.getClassList());
        listView.setAdapter(new myAdapter(this,Classes));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // put you activity's name instead of NewActivity
                Intent intent = new Intent(getBaseContext(),gridMainActivity.class);
                intent.putExtra("ClassID",Classes.get(i).id);
                startActivity(intent);
                // to get the id in your activity use:
                // getIntent().getLongExtra("ClassID",Long.valueOf(0))
                // it will return long
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_class:
                Log.i("class","Done");
                Context context=classListActivity.this;
                final Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.add_class_menu);

                Button restart=dialog.findViewById(R.id.addClassButton);
                dialog.setTitle("Add Class");
                dialog.show();
                restart.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = ((TextView)dialog.findViewById(R.id.name)).getText().toString();
                        String description = ((TextView)dialog.findViewById(R.id.description)).getText().toString();
                        if(name.length() != 0 &&description.length() != 0){

                            int id = data.addClass(name,description);
                            Classes.add(new classes(id,name,description));
                            listView.setAdapter(new myAdapter(getBaseContext(),Classes));
                            dialog.dismiss();
                        }
                        else {

                            showDialog(1);
                        }
                    }
                });
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
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